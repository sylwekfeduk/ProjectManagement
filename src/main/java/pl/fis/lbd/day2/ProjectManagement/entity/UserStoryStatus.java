package pl.fis.lbd.day2.ProjectManagement.entity;

public enum UserStoryStatus {

    TODO("To do"),
    INPROGRESS("In progress"),
    REVIEW("Review"),
    DONE("Done");

    private final String name;

    UserStoryStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
