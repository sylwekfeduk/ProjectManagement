package pl.fis.lbd.day2.ProjectManagement.dto;

import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;

import java.util.Set;

public class UserStoryDto {

    private Long id;

    private String name;

    private String description;

    private byte[] attachments;

    private Integer numberOfStoryPoints;

    private UserStoryStatus userStoryStatus;

    private Set<Sprint> sprints;

    public UserStoryDto() {
    }

    public UserStoryDto(Long id, String name, Integer numberOfStoryPoints, UserStoryStatus userStoryStatus, Set<Sprint> sprints) {
        this.id = id;
        this.name = name;
        this.numberOfStoryPoints = numberOfStoryPoints;
        this.userStoryStatus = userStoryStatus;
        this.sprints = sprints;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAttachments() {
        return attachments;
    }

    public void setAttachments(byte[] attachments) {
        this.attachments = attachments;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
