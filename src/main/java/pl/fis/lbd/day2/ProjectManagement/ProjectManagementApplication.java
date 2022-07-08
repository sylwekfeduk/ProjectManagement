package pl.fis.lbd.day2.ProjectManagement;

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

@SpringBootApplication
public class ProjectManagementApplication {

	@Autowired
	private SprintService sprintService;

	@Autowired
	private UserStoryService userStoryService;

	@PostConstruct
	public void createSprintAndSave () {
		Sprint sprint = new Sprint(LocalDate.of( 2022,06,15), LocalDate.of(2022,06,30), SprintStatus.CANCELED);
		sprintService.saveSprint(sprint);
		UserStory userStory = new UserStory("Adding basket feature", "Create adding new basket feature", UserStoryStatus.DONE);
		userStoryService.saveUserStory(userStory);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

}
