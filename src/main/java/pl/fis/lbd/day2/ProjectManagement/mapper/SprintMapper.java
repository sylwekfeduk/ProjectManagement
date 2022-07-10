package pl.fis.lbd.day2.ProjectManagement.mapper;

import org.mapstruct.Mapper;
import pl.fis.lbd.day2.ProjectManagement.dto.SprintDto;
import pl.fis.lbd.day2.ProjectManagement.model.Sprint;

@Mapper(componentModel = "spring")
public interface SprintMapper {

    SprintDto mapEntityToDto(Sprint sprint);

    Sprint mapDtoToEntity(SprintDto sprintDto);
}
