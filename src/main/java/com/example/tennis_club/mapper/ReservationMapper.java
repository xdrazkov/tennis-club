package com.example.tennis_club.mapper;

import com.example.tennis_club.service.ReservationService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ReservationService.class})
public class ReservationMapper {
}
