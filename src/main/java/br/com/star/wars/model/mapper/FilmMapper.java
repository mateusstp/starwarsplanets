package br.com.star.wars.model.mapper;

import java.util.List;

import br.com.star.wars.model.dto.FilmDto;
import br.com.star.wars.model.entity.Film;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmMapper {

	Film filmDtoToFilm(FilmDto dto);

	FilmDto filmToFilmDto(Film entity);

	List<Film> filmDtosToFilms(List<FilmDto> dtoList);

	List<FilmDto> filmsToFilmDtos(List<Film> entityList);

}
