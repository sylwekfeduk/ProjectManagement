package pl.fis.lbd.day2.ProjectManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;
import pl.fis.lbd.day2.ProjectManagement.service.SprintService;
import pl.fis.lbd.day2.ProjectManagement.service.UserStoryService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ProjectManagementApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectManagementApplication.class);

	@Autowired
	private SprintService sprintService;

	@Autowired
	private UserStoryService userStoryService;

	@PostConstruct
	public void createSprintAndSave () {
		Sprint sprint = new Sprint(LocalDate.of( 2022,06,15), LocalDate.of(2022,06,30), SprintStatus.CANCELED);
		UserStory userStory = new UserStory("Adding basket feature", "Create adding new basket feature", UserStoryStatus.DONE);
		UserStory userStory1 = new UserStory("Adding basket feature", "Create adding new basket feature", UserStoryStatus.REVIEW);
		Set<UserStory> userStories = new HashSet<>();
		userStories.add(userStory1);
		userStories.add(userStory);
		try {
			LOG.info("Create sprint with user stories");
			sprintService.createSprintWithUserStories(sprint, userStories);
		} catch (Exception e) {
			LOG.info("Error occurred in creating sprint with user stories", e);
		}

		LOG.info("Fetching all sprints");
		sprintService.findPagesWithPageNumberAndSizeOfPageAndSortBy(0, 10, "startDate")
				.forEach(s -> LOG.info(s.toString()));

		LOG.info("Fetching all user stories");
		userStoryService.findPagesWithPageNumberAndSizeOfPage(0, 10)
				.forEach(u ->LOG.info(u.toString()));
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

}
