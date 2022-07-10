package pl.fis.lbd.day2.ProjectManagement.dto;

import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;

import java.time.LocalDate;
import java.util.Set;

public class SprintWithListOfUserStoriesWithNameAndStoryPointsDto {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private SprintStatus sprintStatus;

    private Set<UserStoryWithNameAndStoryPointsDto> userStories;

    public SprintWithListOfUserStoriesWithNameAndStoryPointsDto() {

    }

    public SprintWithListOfUserStoriesWithNameAndStoryPointsDto(Long id, LocalDate startDate, LocalDate endDate, String description, SprintStatus sprintStatus, Set<UserStoryWithNameAndStoryPointsDto> userStories) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.sprintStatus = sprintStatus;
        this.userStories = userStories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(SprintStatus sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

    public Set<UserStoryWithNameAndStoryPointsDto> getUserStories() {
        return userStories;
    }

    public void setUserStories(Set<UserStoryWithNameAndStoryPointsDto> userStories) {
        this.userStories = userStories;
    }
}
