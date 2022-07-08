package pl.fis.lbd.day2.ProjectManagement.exception;

public class UserStoryNotSavedException extends RuntimeException{

    public UserStoryNotSavedException(String message) {
        super(message);
    }
}
