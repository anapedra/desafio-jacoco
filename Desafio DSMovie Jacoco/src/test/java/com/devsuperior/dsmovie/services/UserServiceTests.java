package com.devsuperior.dsmovie.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.devsuperior.dsmovie.entities.UserEntity;


import com.devsuperior.dsmovie.projections.UserDetailsProjection;
import com.devsuperior.dsmovie.tests.UserDetailsImpl;
import com.devsuperior.dsmovie.utils.CustomUserUtil;
import com.devsuperior.dsmovie.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public class UserServiceTests {

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository repository;

	@Mock
	private CustomUserUtil userUtil;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void authenticatedShouldReturnUserEntityWhenUserExists() {

		String username = "testuser";
		UserEntity mockUser = new UserEntity();
		mockUser.setUsername(username);

		when(userUtil.getLoggedUsername()).thenReturn(username);
		when(repository.findByUsername(username)).thenReturn(Optional.of(mockUser));


		UserEntity result = service.authenticated();

		assertNotNull(result);
		assertEquals(username, result.getUsername());
	}

	@Test
	public void authenticatedShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {

		String username = "testuser";
		when(userUtil.getLoggedUsername()).thenReturn(username);
		when(repository.findByUsername(username)).thenReturn(Optional.empty());


		assertThrows(UsernameNotFoundException.class, () -> {
			service.authenticated();
		});
	}

	@Test
	public void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {

		String username = "testuser";
		UserDetailsProjection mockUserDetails = new UserDetailsImpl(username, "password", 1L, "ROLE_USER");
		when(repository.searchUserAndRolesByUsername(username)).thenReturn(List.of(mockUserDetails));


		UserEntity result = (UserEntity) service.loadUserByUsername(username);


		assertNotNull(result);
		assertEquals(username, result.getUsername());
		assertEquals("password", result.getPassword());
		assertEquals(1, result.getRoles().size());
	}

	@Test
	public void loadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenUserDoesNotExists() {

		String username = "testuser";
		when(repository.searchUserAndRolesByUsername(username)).thenReturn(List.of());


		assertThrows(UsernameNotFoundException.class, () -> {
			service.loadUserByUsername(username);
		});
	}
}
