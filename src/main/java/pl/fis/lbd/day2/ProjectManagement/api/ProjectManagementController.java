package pl.fis.lbd.day2.ProjectManagement.api;

import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.fis.lbd.day2.ProjectManagement.dto.*;
import pl.fis.lbd.day2.ProjectManagement.mapper.SprintMapper;
import pl.fis.lbd.day2.ProjectManagement.mapper.UserStoryMapper;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;
import pl.fis.lbd.day2.ProjectManagement.service.SprintService;
import pl.fis.lbd.day2.ProjectManagement.service.UserStoryService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1.0")
public class ProjectManagementController {

    private final SprintService sprintService;
    private final UserStoryService userStoryService;
    private final UserStoryMapper userStoryMapper;
    private static final Logger LOG = LoggerFactory.getLogger(ProjectManagementController.class);

    public ProjectManagementController(SprintService sprintService, UserStoryService userStoryService, UserStoryMapper userStoryMapper) {
        this.sprintService = sprintService;
        this.userStoryService = userStoryService;
        this.userStoryMapper = userStoryMapper;
    }

    @PostMapping(value = "/sprints/{sprintId}")
    public void addUserStoryToSprint(@PathVariable Long sprintId, UserStoryDto userStoryDto) {
        UserStory userStory = userStoryMapper.mapDtoToEntity(userStoryDto);
        Sprint sprint = sprintService.getSprintById(sprintId);
        sprint.addUserStories(userStory);
        sprintService.saveSprint(sprint);
    }

    @GetMapping(value = "/sprints/{sprintId}")
    public Integer getNumberOfStoryPointsInGivenSprint(@PathVariable Long sprintId) {
        return userStoryService.getNumberOfStoryPointsInGivenSprint(sprintId);
    }

    @GetMapping(value = "/listOfStoryPoints/{id}")
    public List<UserStoryResponseDto> getAllUserStoriesFromGivenSprint(@PathVariable Long sprintId) {
        Set<UserStoryDto> userStoriesDto = (Set<UserStoryDto>) sprintService.getAllUserStoriesFromSprintById(sprintId).stream().filter(u -> u.getUserStoryStatus() == UserStoryStatus.DONE).map(userStoryMapper::mapEntityToDto);
        List<UserStoryResponseDto> responseDtos = new ArrayList<>();
        for(UserStoryDto userStoryDto : userStoriesDto) {
            responseDtos.add(new UserStoryResponseDto(userStoryDto.getName(), userStoryDto.getNumberOfStoryPoints(), userStoryDto.getUserStoryStatus(), userStoryDto.getSprints()));
        }
        return responseDtos;
    }

    @GetMapping(value = "/descriptionOfUserStory/{id}")
    public String getDescriptionOfGivenUserStory(@PathVariable Long id) {
        return userStoryService.getUserStoryById(id).getDescription();
    }

    @PostMapping(value = "/attachmentsOfUserStory/{id}")
    public void addAttachmentToGivenUserStory(@PathVariable Long id, byte[] attachment) {
        UserStory userStory = userStoryService.getUserStoryById(id);
        userStory.setAttachments(attachment);
    }

    @GetMapping(value = "/attachmentsOfUserStory/{id}")
    public byte[] getAttachmentOfUserStory(@PathVariable Long id) {
        return userStoryService.getAttachmentFromUserStory(id);
    }

    @PutMapping(value = "/sprintStatus/{id}")
    public void updateStatusOfGivenSprint(@PathVariable Long id, SprintStatus status) {
        Sprint sprint = sprintService.getSprintById(id);
        sprint.setSprintStatus(status);
        sprintService.saveSprint(sprint);
    }

    @GetMapping(value = "/sprintInfo/{startDate}/{endDate}")
    public List<SprintResponseDto> getInfoAboutSprintsInGivenDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        List<Sprint> sprints = sprintService.getAllSprintsBetweenDates(startDate, endDate);
        List<SprintResponseDto> responseDtos = new ArrayList<>();
        for(Sprint sprint : sprints) {
            responseDtos.add(new SprintResponseDto(sprint.getDescription(), sprint.getStartDate(), sprint.getEndDate(), sprint.getSprintStatus()));
        }
        return responseDtos;
    }

    @GetMapping(value = "/listOfUserStories/{pageSize}/{pageNumber}")
    public Page<UserStoryDto> getAllUserStoriesSortedByGivenColumn(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return userStoryService.findPagesWithPageNumberAndSizeOfPageAndSortBy(pageNumber, pageSize, "name").map(userStoryMapper::mapEntityToDto);
    }

    @DeleteMapping(value = "/userStories/{id}")
    public void deleteUserStoryByGivenId(@PathVariable Long id) {
        userStoryService.deleteUserStoryById(id);
    }

    @GetMapping(value = "/loggedInUser")
    public void showLoggedInUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        LOG.info("Logged in user " + authentication.getName() + " " + authentication.getAuthorities());
    }
}
