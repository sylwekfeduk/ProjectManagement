package pl.fis.lbd.day2.ProjectManagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fis.lbd.day2.ProjectManagement.exception.SprintNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.exception.UserStoryNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.repository.SprintRepository;
import pl.fis.lbd.day2.ProjectManagement.repository.UserStoryRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Service
public class SprintService {

    private final SprintRepository sprintRepository;
    private final UserStoryRepository userStoryRepository;

    public SprintService(SprintRepository sprintRepository, UserStoryRepository userStoryRepository) {
        this.sprintRepository = sprintRepository;
        this.userStoryRepository = userStoryRepository;
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
        return sprintRepository.findSprintsByStartDateAfterAndEndDateBefore(startDate, endDate);
    }

    public Page<Sprint> findPagesWithPageNumberAndSizeOfPageAndSortBy(int pageNumber, int pageSize, String sortColumn) {
        return sprintRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc(sortColumn))));
    }

    @Transactional(rollbackFor = UserStoryNotSavedException.class)
    public void createSprintWithUserStories(Sprint sprint, Set<UserStory> userStories) throws UserStoryNotSavedException {
        Sprint savedSprint = sprintRepository.save(sprint);
        for(UserStory userStory : userStories) {
            userStoryRepository.save(userStory);
        }
        savedSprint.setUserStories(userStories);
        sprintRepository.save(savedSprint);
    }
}
