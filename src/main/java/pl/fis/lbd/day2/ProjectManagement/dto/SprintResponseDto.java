package pl.fis.lbd.day2.ProjectManagement.dto;

import pl.fis.lbd.day2.ProjectManagement.model.SprintStatus;

import java.time.LocalDate;

public class SprintResponseDto {

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private SprintStatus sprintStatus;

    public SprintResponseDto() {

    }

    public SprintResponseDto(String name, LocalDate startDate, LocalDate endDate, SprintStatus sprintStatus) {
        this.description = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sprintStatus = sprintStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public SprintStatus getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(SprintStatus sprintStatus) {
        this.sprintStatus = sprintStatus;
    }
}
