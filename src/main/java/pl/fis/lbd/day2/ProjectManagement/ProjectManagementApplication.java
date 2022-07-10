package pl.fis.lbd.day2.ProjectManagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;
import pl.fis.lbd.day2.ProjectManagement.service.NBPApiService;
import pl.fis.lbd.day2.ProjectManagement.service.SprintService;
import pl.fis.lbd.day2.ProjectManagement.service.UserStoryService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ProjectManagementApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ProjectManagementApplication.class);

	@Autowired
	private SprintService sprintService;

	@Autowired
	private UserStoryService userStoryService;

	@Autowired
	private NBPApiService nbpApi;

	@PostConstruct
	public void createSprintAndSave () {
		/*Sprint sprint = new Sprint(LocalDate.of( 2022,06,15), LocalDate.of(2022,06,30), SprintStatus.CANCELED);
		UserStory userStory = new UserStory("Adding basket feature", "Create adding new basket feature", UserStoryStatus.DONE);
		UserStory userStory1 = new UserStory("Adding basket feature", "Create adding new basket feature", UserStoryStatus.REVIEW);
		sprint.addUserStories(userStory);
		sprint.addUserStories(userStory1);
		sprintService.saveSprint(sprint);

		LOG.info("Fetching all sprints");
		sprintService.findPagesWithPageNumberAndSizeOfPageAndSortBy(0, 10, "startDate")
				.forEach(s -> LOG.info(s.toString()));

		LOG.info("Fetching all user stories");
		userStoryService.findPagesWithPageNumberAndSizeOfPage(0, 10)
				.forEach(u ->LOG.info(u.toString()));*/
		//nbpApi.getTableWithExchangeRatesFromYesterday();
		//nbpApi.getUsdExchangeRatesForLast10Days();
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}

}
