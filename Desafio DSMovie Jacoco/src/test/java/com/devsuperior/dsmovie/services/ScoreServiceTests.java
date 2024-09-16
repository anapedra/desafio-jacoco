package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.ScoreDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.entities.ScoreEntity;
import com.devsuperior.dsmovie.entities.UserEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.repositories.ScoreRepository;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import com.devsuperior.dsmovie.tests.ScoreFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;



import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ScoreServiceTests {

    @InjectMocks
    private ScoreService service;

    @Mock
    private UserService userService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ScoreRepository scoreRepository;

    @InjectMocks
    private MovieService serviceMovie;

    @Mock
    private MovieRepository repository;


    public ScoreServiceTests() {
        MockitoAnnotations.openMocks(this);
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void saveScoreShouldReturnMovieDTO() {

        UserEntity user = new UserEntity();
        MovieEntity movie = MovieFactory.createMovieEntity();
        ScoreDTO scoreDTO = ScoreFactory.createScoreDTO();
        ScoreEntity scoreEntity = ScoreFactory.createScoreEntity();
        double expectedScore = scoreEntity.getValue();
        when(userService.authenticated()).thenReturn(user);
        when(movieRepository.findById(scoreDTO.getMovieId())).thenReturn(Optional.of(movie));
        when(scoreRepository.saveAndFlush(any(ScoreEntity.class))).thenReturn(scoreEntity);
        when(movieRepository.save(any(MovieEntity.class))).thenReturn(movie);
        MovieDTO result = service.saveScore(scoreDTO);
        assertNotNull(result);
        assertEquals(movie.getId(), result.getId());

    }


    @Test
    public void saveScoreShouldThrowResourceNotFoundExceptionWhenNonExistingMovieId() {

        ScoreDTO scoreDTO = ScoreFactory.createScoreDTO();

        when(userService.authenticated()).thenReturn(new UserEntity());
        when(movieRepository.findById(scoreDTO.getMovieId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.saveScore(scoreDTO));
    }
}
