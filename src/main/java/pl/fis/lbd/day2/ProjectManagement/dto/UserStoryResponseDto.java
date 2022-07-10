package pl.fis.lbd.day2.ProjectManagement.dto;

import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;

import java.util.Set;

public class UserStoryResponseDto {

    private String name;

    private Integer numberOfStoryPoints;

    private UserStoryStatus userStoryStatus;

    private Set<Sprint> sprints;

    public UserStoryResponseDto() {
    }

    public UserStoryResponseDto(String name, Integer numberOfStoryPoints, UserStoryStatus userStoryStatus, Set<Sprint> sprints) {
        this.name = name;
        this.numberOfStoryPoints = numberOfStoryPoints;
        this.userStoryStatus = userStoryStatus;
        this.sprints = sprints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberOfStoryPoints() {
        return numberOfStoryPoints;
    }

    public void setNumberOfStoryPoints(Integer numberOfStoryPoints) {
        this.numberOfStoryPoints = numberOfStoryPoints;
    }

    public UserStoryStatus getUserStoryStatus() {
        return userStoryStatus;
    }

    public void setUserStoryStatus(UserStoryStatus userStoryStatus) {
        this.userStoryStatus = userStoryStatus;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }
}
