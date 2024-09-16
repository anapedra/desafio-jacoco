package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.services.exceptions.DatabaseException;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MovieServiceTests {

	@InjectMocks
	private MovieService service;

	@Mock
	private MovieRepository repository;

	public MovieServiceTests() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void findAllShouldReturnPagedMovieDTO() {
		Pageable pageable = Pageable.unpaged();
		MovieEntity movie = new MovieEntity(1L, "Title", 8.0, 100, "image.jpg");
		Page<MovieEntity> page = new PageImpl<>(List.of(movie));
		when(repository.searchByTitle("Title", pageable)).thenReturn(page);

		Page<MovieDTO> result = service.findAll("Title", pageable);
		assertEquals(1, result.getTotalElements());
		assertEquals("Title", result.getContent().get(0).getTitle());
	}

	@Test
	public void findByIdShouldReturnMovieDTOWhenIdExists() {
		MovieEntity movie = new MovieEntity(1L, "Title", 8.0, 100, "image.jpg");
		when(repository.findById(1L)).thenReturn(Optional.of(movie));

		MovieDTO result = service.findById(1L);
		assertEquals("Title", result.getTitle());
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		when(repository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> service.findById(1L));
	}

	@Test
	public void insertShouldReturnMovieDTO() {
		MovieDTO dto = new MovieDTO(1L,"Title", 8.0, 100, "image.jpg");
		MovieEntity entity = new MovieEntity(null, "Title", 8.0, 100, "image.jpg");
		when(repository.save(any(MovieEntity.class))).thenReturn(entity);

		MovieDTO result = service.insert(dto);
		assertEquals("Title", result.getTitle());
	}

	@Test
	public void updateShouldReturnMovieDTOWhenIdExists() {
		MovieDTO dto = new MovieDTO(1L,"Title", 8.0, 100, "image.jpg");
		MovieEntity existingEntity = new MovieEntity(1L, "Old Title", 7.0, 50, "oldimage.jpg");
		MovieEntity updatedEntity = new MovieEntity(1L, "Title", 8.0, 100, "image.jpg");

		when(repository.getReferenceById(1L)).thenReturn(existingEntity);
		when(repository.save(existingEntity)).thenReturn(updatedEntity);

		MovieDTO result = service.update(1L, dto);
		assertEquals("Title", result.getTitle());
	}

	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		MovieDTO dto = new MovieDTO(1L,"Title", 8.0, 100, "image.jpg");
		when(repository.getReferenceById(1L)).thenThrow(EntityNotFoundException.class);

		assertThrows(ResourceNotFoundException.class, () -> service.update(1L, dto));
	}

	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		when(repository.existsById(1L)).thenReturn(true);

		assertDoesNotThrow(() -> service.delete(1L));
	}

	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		when(repository.existsById(1L)).thenReturn(false);

		assertThrows(ResourceNotFoundException.class, () -> service.delete(1L));
	}

	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
		when(repository.existsById(1L)).thenReturn(true);
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(1L);

		assertThrows(DatabaseException.class, () -> service.delete(1L));
	}
}
