package pl.fis.lbd.day2.ProjectManagement.exception;

public class UserStoryNotExistException extends RuntimeException{

    public UserStoryNotExistException(String message) {
        super(message);
    }
}
