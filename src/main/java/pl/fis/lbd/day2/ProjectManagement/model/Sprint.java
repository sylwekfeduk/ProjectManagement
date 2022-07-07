package pl.fis.lbd.day2.ProjectManagement.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Sprint {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private SprintStatus sprintStatus;

    @ManyToMany
    @JoinTable(
            name = "sprint_userStory",
            joinColumns = @JoinColumn(name = "sprint_id"),
            inverseJoinColumns = @JoinColumn(name = "userStory_id")
    )
    private Set<UserStory> userStories;

}
