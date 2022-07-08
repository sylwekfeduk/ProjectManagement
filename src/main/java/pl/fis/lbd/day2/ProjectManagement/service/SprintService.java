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
    public void saveSprint(Sprint sprint) {
        if(sprint.getStartDate() != null && sprint.getEndDate() != null && sprint.getStartDate().isBefore(sprint.getEndDate())
                && sprint.getSprintStatus().getName().equals(SprintStatus.CANCELED.getName())
                || sprint.getSprintStatus().getName().equals(SprintStatus.PENDING.getName())
                || sprint.getSprintStatus().getName().equals(SprintStatus.FINISHED.getName())
                || sprint.getSprintStatus().getName().equals(SprintStatus.INPROGRESS.getName()))
                    sprintRepository.save(sprint);
        else
            throw new SprintNotSavedException("Unable to save sprint");
    }
}
