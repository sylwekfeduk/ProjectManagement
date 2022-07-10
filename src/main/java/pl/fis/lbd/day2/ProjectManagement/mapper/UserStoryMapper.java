package pl.fis.lbd.day2.ProjectManagement.mapper;

import org.mapstruct.Mapper;
import pl.fis.lbd.day2.ProjectManagement.dto.UserStoryDto;
import pl.fis.lbd.day2.ProjectManagement.model.UserStory;

@Mapper(componentModel = "spring")
public interface UserStoryMapper {

    UserStoryDto mapEntityToDto(UserStory userStory);

    UserStory mapDtoToEntity(UserStoryDto userStoryDto);
}
