package pl.fis.lbd.day2.ProjectManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;

import java.util.Set;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {

    Sprint findSprintById(Long id);
}
