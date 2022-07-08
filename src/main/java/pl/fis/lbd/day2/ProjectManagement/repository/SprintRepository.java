package pl.fis.lbd.day2.ProjectManagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {

    Sprint findSprintById(Long id);

    List<Sprint> findSprintsByStartDateAfterAndEndDateBefore(LocalDate startDate, LocalDate endDate);
}
