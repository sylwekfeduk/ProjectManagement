package pl.fis.lbd.day2.ProjectManagement.dto;

public class UserStoryWithNameAndStoryPointsDto {

    private String name;

    private int storyPoints;

    public UserStoryWithNameAndStoryPointsDto() {
    }

    public UserStoryWithNameAndStoryPointsDto(String name, int storyPoints) {
        this.name = name;
        this.storyPoints = storyPoints;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }
}
