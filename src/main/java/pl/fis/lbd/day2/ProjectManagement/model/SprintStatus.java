package pl.fis.lbd.day2.ProjectManagement.model;

public enum SprintStatus {

    PENDING("Pending"),
    INPROGRESS("In progress"),
    FINISHED("Finished"),
    CANCELED("Canceled");

    private final String name;

    SprintStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
