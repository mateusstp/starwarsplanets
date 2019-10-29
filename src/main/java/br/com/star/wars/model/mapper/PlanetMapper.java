package br.com.star.wars.model.mapper;

import br.com.star.wars.model.dto.PlanetDto;
import br.com.star.wars.model.entity.Planet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {FilmMapper.class})
public interface PlanetMapper{

	Planet planetDtoToPlanet(PlanetDto dto);

	PlanetDto planetToPlanetDto(Planet entity);

	List<Planet> planetDtosToPlanets(List<PlanetDto> dtoList);

	List<PlanetDto> planetsToPlanetDtos(List<Planet> entityList);

}
