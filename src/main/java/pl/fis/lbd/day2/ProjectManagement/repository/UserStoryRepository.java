package pl.fis.lbd.day2.ProjectManagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;

@Repository
public interface UserStoryRepository extends PagingAndSortingRepository<UserStory, Long> {

    @Query(value = "SELECT sum(u.number_of_story_points) FROM User_story u JOIN Sprint_user_story s " +
            "ON s.user_story_id=u.id " +
           "WHERE u.user_story_status='DONE' and s.sprint_id = ?1",
            nativeQuery=true)
    int countStoryPointsOfSprintById(Long id);
}
