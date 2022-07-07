package pl.fis.lbd.day2.ProjectManagement.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class UserStory {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private byte[] attachments;

    private Integer numberOfStoryPoints;

    @Enumerated(EnumType.STRING)
    private UserStoryStatus userStoryStatus;

    @ManyToMany(mappedBy = "userStories")
    private Set<Sprint> sprints;
}
