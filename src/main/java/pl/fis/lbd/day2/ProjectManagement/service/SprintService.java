package pl.fis.lbd.day2.ProjectManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fis.lbd.day2.ProjectManagement.exception.SprintNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.repository.SprintRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


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

    public Set<UserStory> getAllUserStoriesFromSprintById(Long id) {
        Sprint sprint = sprintRepository.findSprintById(id);
        return sprint.getUserStories();
    }

    public List<Sprint> getAllSprintsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return sprintRepository.findSprintsBetweenDatesByStartDateAndEndDate(startDate, endDate);
    }
}
