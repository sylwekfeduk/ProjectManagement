package pl.fis.lbd.day2.ProjectManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.fis.lbd.day2.ProjectManagement.exception.UserStoryNotSavedException;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.model.UserStoryStatus;
import pl.fis.lbd.day2.ProjectManagement.repository.UserStoryRepository;

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
}
