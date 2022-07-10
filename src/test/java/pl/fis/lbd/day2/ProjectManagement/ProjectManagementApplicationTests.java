package pl.fis.lbd.day2.ProjectManagement;

import org.apache.commons.lang3.reflect.TypeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.fis.lbd.day2.ProjectManagement.dto.SprintResponseDto;
import pl.fis.lbd.day2.ProjectManagement.dto.UserStoryDto;
import pl.fis.lbd.day2.ProjectManagement.exception.SprintNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.exception.UserStoryNotExistException;
import pl.fis.lbd.day2.ProjectManagement.exception.UserStoryNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;
import pl.fis.lbd.day2.ProjectManagement.service.SprintService;
import pl.fis.lbd.day2.ProjectManagement.service.UserStoryService;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ProjectManagementApplicationTests {

	@Autowired
	SprintService sprintService;

	@Autowired
	UserStoryService userStoryService;

	private RestTemplate restTemplate = new RestTemplate();
	private static final String baseUrl = "http://localhost:8080/api/v1.0";

	@Test
	void contextLoads() {
	}

	@Test
	void whenSavingSprint_thenOk() {
		Sprint sprintSaved = sprintService.saveSprint(new Sprint(LocalDate.of( 2022,06,15), LocalDate.of(2022,06,30), SprintStatus.CANCELED));
		Assertions.assertNotNull(sprintSaved);
	}

	@Test
	void whenSavingSprint_thenExceptionSprintNotSaved() {
		assertThatExceptionOfType(SprintNotSavedException.class)
				.isThrownBy(() -> sprintService.saveSprint(new Sprint(LocalDate.of(2022,06,15), LocalDate.of(2022,06,30))))
				.withMessage("Unable to save sprint");
	}

	@Test
	void whenGettingUserStoriesFromSprintById_thenOk() {
		Set<UserStory> userStories = new HashSet<>(2);
		Sprint savedSprint = sprintService.saveSprint(new Sprint(LocalDate.of( 2022,06,15), LocalDate.of(2022,06,30), SprintStatus.CANCELED, userStories));
		userStories.add(userStoryService.saveUserStory(new UserStory("Adding basket feature", "Create adding new basket feature", UserStoryStatus.DONE)));
		userStories.add(userStoryService.saveUserStory(new UserStory("New tab", "Create new tab in web application", UserStoryStatus.REVIEW)));
		Set<UserStory> userStoriesReceived = sprintService.getAllUserStoriesFromSprintById(savedSprint.getId());
		assertThat(userStoriesReceived.contains(userStories));
	}

	@Test
	void whenGettingSprintsBetweenDates_thenOk() {
		List<Sprint> temp = new ArrayList<>(3);
		temp.add(sprintService.saveSprint(new Sprint(LocalDate.of(2022,6,15), LocalDate.of(2022,6,30), SprintStatus.CANCELED)));
		temp.add(sprintService.saveSprint(new Sprint(LocalDate.of(2022,6,15), LocalDate.of(2022,7,2), SprintStatus.INPROGRESS)));
		temp.add(sprintService.saveSprint(new Sprint(LocalDate.of(2022,7,30), LocalDate.of(2022,8,1), SprintStatus.FINISHED)));
		assertThat(sprintService.getAllSprintsBetweenDates(LocalDate.of(2022,6,14), LocalDate.of(2022,7,3)).containsAll(temp.subList(0,1)));
	}

	@Test
	void whenSavingUserStory_thenOk() {
		UserStory userStorySaved = userStoryService.saveUserStory(new UserStory("Adding basket feature", "Create adding new basket feature", UserStoryStatus.DONE));
		Assertions.assertNotNull(userStorySaved);
	}

	@Test
	void whenSavingUserStory_thenExceptionUserStoryNotSaved() {
		assertThatExceptionOfType(UserStoryNotSavedException.class)
				.isThrownBy(() -> userStoryService.saveUserStory(new UserStory("Test")))
				.withMessage("Unable to save user story");
	}

	@Test
	void whenCountingStoryPoints_thenOk() {
		Set<UserStory> userStories = new HashSet<>(2);
		userStories.add(userStoryService.saveUserStory(new UserStory("Adding basket feature", "Create adding new basket feature", 10, UserStoryStatus.DONE)));
		userStories.add(userStoryService.saveUserStory(new UserStory("New tab", "Create new tab in web application", 7, UserStoryStatus.DONE)));
		Sprint savedSprint = sprintService.saveSprint(new Sprint( LocalDate.of(2022,6,15), LocalDate.of(2022,6,30), SprintStatus.CANCELED, userStories));
		assertThat(userStoryService.getNumberOfStoryPointsInGivenSprint(savedSprint.getId())).isEqualTo(17);
	}

	@Test
	void givenUserStoriesDataCreated_whenFindAllPaginated_thenSuccess() {
		Page<UserStory> retrievedUserStories = userStoryService.findPagesWithPageNumberAndSizeOfPage(0, 10);
		assertThat(retrievedUserStories.getContent().size()).isEqualTo(10);
	}

	@Test
	void givenUserStoriesDataCreated_whenFindAllPaginatedAndSort_thenSuccess() {
		Sprint sprint = sprintService.saveSprint(new Sprint(LocalDate.of(2022,6,15), LocalDate.of(2022,6,30), SprintStatus.CANCELED));
		Sprint sprint1 = sprintService.saveSprint(new Sprint(LocalDate.of( 2022,1,15), LocalDate.of(2022,7,5), SprintStatus.INPROGRESS));
		Page<Sprint> retrievedSprints = sprintService.findPagesWithPageNumberAndSizeOfPageAndSortBy(0, 10, "startDate");
		assertThat(retrievedSprints.getContent().containsAll(List.of(sprint, sprint1)));
	}

	@Test
	public void whenGettingDescriptionOfGivenUserStory_thenOk() {
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/descriptionOfUserStory/1", String.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void whenUpdatingStatusOfSprint_thenOk() {
		Sprint sprint = sprintService.saveSprint(new Sprint(LocalDate.of(2022,6,15), LocalDate.of(2022,6,30), SprintStatus.CANCELED));
		SprintStatus statusUpdated = SprintStatus.INPROGRESS;
		restTemplate.put(baseUrl + "/sprintStatus/" + sprint.getId() + "?status=" + statusUpdated, SprintStatus.class);
		assertThat(sprintService.getSprintById(sprint.getId()).getSprintStatus()).isEqualTo(statusUpdated);
	}

	@Test
	public void whenDeletingUserStoryById_thenOk() {
		Long idOfDeletingUserStory = 1L;
		restTemplate.delete(baseUrl + "/userStories/" + idOfDeletingUserStory);
		assertThatExceptionOfType(UserStoryNotExistException.class)
				.isThrownBy(() -> userStoryService.getUserStoryById(idOfDeletingUserStory))
				.withMessage("User story with given id doesn't exist");
	}

}
