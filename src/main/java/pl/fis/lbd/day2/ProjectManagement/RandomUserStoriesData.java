package pl.fis.lbd.day2.ProjectManagement;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;
import pl.fis.lbd.day2.ProjectManagement.repository.UserStoryRepository;

import java.util.Random;

@Component
public class RandomUserStoriesData implements ApplicationContextAware {

    @Autowired
    private UserStoryRepository userStoryRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        for(int i = 0; i < 100; i++) {
            userStoryRepository.save(new UserStory("Test" + i, "Testing data " + i, (int) new Random().nextInt()*2));
        }
    }
}
