package pl.fis.lbd.day2.ProjectManagement.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fis.lbd.day2.ProjectManagement.exception.UserStoryNotExistException;
import pl.fis.lbd.day2.ProjectManagement.exception.UserStoryNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;
import pl.fis.lbd.day2.ProjectManagement.repository.UserStoryRepository;

import java.util.Optional;

@Service
public class UserStoryService {

    private final UserStoryRepository userStoryRepository;

    public UserStoryService(UserStoryRepository userStoryRepository) {
        this.userStoryRepository = userStoryRepository;
    }

    @Transactional(rollbackFor = UserStoryNotSavedException.class)
    public UserStory saveUserStory(UserStory userStory) {
        if(userStory.getName() != null && userStory.getDescription() != null) {
            if(userStory.getUserStoryStatus().getName() == null) {
                userStory.setUserStoryStatus(UserStoryStatus.TODO);
            }
            userStoryRepository.save(userStory);
        }
        else
            throw new UserStoryNotSavedException("Unable to save user story");
        return userStory;
    }

    public int getNumberOfStoryPointsInGivenSprint(Long id) {
        return userStoryRepository.countStoryPointsOfSprintById(id);
    }

    public Page<UserStory> findPagesWithPageNumberAndSizeOfPage(int pageNumber, int pageSize) {
        return userStoryRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public UserStory getUserStoryById(Long id) {
        return userStoryRepository.findById(id).orElseThrow(() -> new UserStoryNotExistException("User story with given id doesn't exist"));
    }

    public Page<UserStory> findPagesWithPageNumberAndSizeOfPageAndSortBy(int pageNumber, int pageSize, String sortColumn) {
        return userStoryRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Order.desc(sortColumn))));
    }

    public void deleteUserStoryById(Long id) {
        userStoryRepository.deleteById(id);
    }

    public byte[] getAttachmentFromUserStory(Long id) {
        return userStoryRepository.findById(id).orElseThrow(() -> new UserStoryNotExistException("User story with given id doesn't exist")).getAttachments();
    }

}
