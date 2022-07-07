package pl.fis.lbd.day2.ProjectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
}
