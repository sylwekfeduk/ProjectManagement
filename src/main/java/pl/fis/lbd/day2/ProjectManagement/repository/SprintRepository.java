package pl.fis.lbd.day2.ProjectManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
