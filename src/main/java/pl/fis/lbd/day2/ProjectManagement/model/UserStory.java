package pl.fis.lbd.day2.ProjectManagement.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

@Entity
@Table(name = "user_story")
public class UserStory {

    @Id
    @SequenceGenerator(
            name = "user_story_id_seq",
            sequenceName = "user_story_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_story_id_seq"
    )
    private Long id;

    private String name;

    private String description;

    private byte[] attachments;

    private Integer numberOfStoryPoints;

    @Enumerated(EnumType.STRING)
    private UserStoryStatus userStoryStatus;

    @ManyToMany(mappedBy = "userStories")
    private Set<Sprint> sprints;

    public UserStory() {
    }

    public UserStory(String name) {
        this.name = name;
    }

    public UserStory(Long id, String name, String description, UserStoryStatus userStoryStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userStoryStatus = userStoryStatus;
    }

    public UserStory(String name, String description, UserStoryStatus userStoryStatus) {
        this.name = name;
        this.description = description;
        this.userStoryStatus = userStoryStatus;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getAttachments() {
        return attachments;
    }

    public Integer getNumberOfStoryPoints() {
        return numberOfStoryPoints;
    }

    public UserStoryStatus getUserStoryStatus() {
        return userStoryStatus;
    }

    public Set<Sprint> getSprints() {
        return sprints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAttachments(byte[] attachments) {
        this.attachments = attachments;
    }

    public void setNumberOfStoryPoints(Integer numberOfStoryPoints) {
        this.numberOfStoryPoints = numberOfStoryPoints;
    }

    public void setUserStoryStatus(UserStoryStatus userStoryStatus) {
        this.userStoryStatus = userStoryStatus;
    }

    public void setSprints(Set<Sprint> sprints) {
        this.sprints = sprints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserStory userStory = (UserStory) o;

        return new EqualsBuilder().append(id, userStory.id).append(name, userStory.name).append(description, userStory.description).append(attachments, userStory.attachments).append(numberOfStoryPoints, userStory.numberOfStoryPoints).append(userStoryStatus, userStory.userStoryStatus).append(sprints, userStory.sprints).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(name).append(description).append(attachments).append(numberOfStoryPoints).append(userStoryStatus).append(sprints).toHashCode();
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", attachments=" + Arrays.toString(attachments) +
                ", numberOfStoryPoints=" + numberOfStoryPoints +
                ", userStoryStatus=" + userStoryStatus +
                ", sprints=" + sprints +
                '}';
    }
}
