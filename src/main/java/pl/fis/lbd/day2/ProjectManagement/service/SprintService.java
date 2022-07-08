package pl.fis.lbd.day2.ProjectManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fis.lbd.day2.ProjectManagement.exception.SprintNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;
import pl.fis.lbd.day2.ProjectManagement.repository.SprintRepository;


@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public SprintService(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Transactional(rollbackFor = SprintNotSavedException.class)
    public Sprint saveSprint(Sprint sprint) {
        if(sprint.getStartDate() != null && sprint.getEndDate() != null && sprint.getStartDate().isBefore(sprint.getEndDate())
                && sprint.getSprintStatus() != null)
                    sprintRepository.save(sprint);
        else
            throw new SprintNotSavedException("Unable to save sprint");
        return sprint;
    }
}
