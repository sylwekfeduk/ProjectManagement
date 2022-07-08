package pl.fis.lbd.day2.ProjectManagement;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import pl.fis.lbd.day2.ProjectManagement.exception.SprintNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.exception.UserStoryNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;
import pl.fis.lbd.day2.ProjectManagement.service.SprintService;
import pl.fis.lbd.day2.ProjectManagement.service.UserStoryService;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class ProjectManagementApplicationTests {

	@Autowired
	SprintService sprintService;

	@Autowired
	UserStoryService userStoryService;

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
		sprintService.saveSprint(new Sprint(LocalDate.of( 2022,06,15), LocalDate.of(2022,06,30), SprintStatus.CANCELED, userStories));
		userStories.add(userStoryService.saveUserStory(new UserStory("Adding basket feature", "Create adding new basket feature", UserStoryStatus.DONE)));
		userStories.add(userStoryService.saveUserStory(new UserStory("New tab", "Create new tab in web application", UserStoryStatus.REVIEW)));
		Set<UserStory> userStoriesReceived = sprintService.getAllUserStoriesFromSprintById(1L);
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
		sprintService.saveSprint(new Sprint( LocalDate.of(2022,6,15), LocalDate.of(2022,6,30), SprintStatus.CANCELED, userStories));
		assertThat(userStoryService.getNumberOfStoryPointsInGivenSprint(1L)).isEqualTo(17);
	}

	@Test
	void givenUserStoriesDataCreated_whenFindAllPaginated_thenSuccess() {
		Page<UserStory> retrievedUserStories = userStoryService.findPagesWithPageNumberAndSizeOfPage(0, 10);
		assertThat(retrievedUserStories.getTotalPages()).isEqualTo(10);
		assertThat(retrievedUserStories.getContent().size()).isEqualTo(10);
	}

}
