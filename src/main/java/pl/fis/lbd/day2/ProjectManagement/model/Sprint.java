package pl.fis.lbd.day2.ProjectManagement.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "sprint")
public class Sprint {

    @Id
    @SequenceGenerator(
            name = "sprint_id_seq",
            sequenceName = "sprint_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sprint_id_seq")
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private SprintStatus sprintStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sprint_user_story",
            joinColumns = @JoinColumn(name = "sprint_id"),
            inverseJoinColumns = @JoinColumn(name = "user_story_id")
    )
    private Set<UserStory> userStories;

    public Sprint() {
    }

    public Sprint(Long id, LocalDate startDate, LocalDate endDate, SprintStatus sprintStatus) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintStatus = sprintStatus;
    }

    public Sprint(LocalDate startDate, LocalDate endDate, SprintStatus sprintStatus, Set<UserStory> userStories) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintStatus = sprintStatus;
        this.userStories = userStories;
    }

    public Sprint(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Sprint(LocalDate startDate, LocalDate endDate, SprintStatus sprintStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintStatus = sprintStatus;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }

    public Set<UserStory> getUserStories() {
        return userStories;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSprintStatus(SprintStatus sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

    public void setUserStories(Set<UserStory> userStories) {
        this.userStories = userStories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Sprint sprint = (Sprint) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder().append(id, sprint.id).append(startDate, sprint.startDate).append(endDate, sprint.endDate).append(description, sprint.description).append(sprintStatus, sprint.sprintStatus).append(userStories, sprint.userStories).isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37).append(id).append(startDate).append(endDate).append(description).append(sprintStatus).append(userStories).toHashCode();
    }

    @Override
    public String toString() {
        return "Sprint{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", sprintStatus=" + sprintStatus +
                ", userStories=" + userStories +
                '}';
    }
}
