package com.example.tennis_club.mapper;

import com.example.tennis_club.api.CourtCreateDto;
import com.example.tennis_club.api.CourtViewDto;
import com.example.tennis_club.data.dao.SurfaceTypeDao;
import com.example.tennis_club.data.model.Court;
import com.example.tennis_club.service.CourtService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CourtService.class, SurfaceTypeDao.class})
public interface CourtMapper {

    @Mapping(source="surfaceTypeId", target="surfaceType", defaultExpression = "java(null)")
    Court mapFromCreate(CourtCreateDto court);

    @Mapping(source="surfaceType", target="surfaceType")
    CourtViewDto mapToDto(Court court);

    List<CourtViewDto> mapToList(List<Court> courts);
}
