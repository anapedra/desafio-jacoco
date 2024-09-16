package com.devsuperior.dsmovie.tests;

import java.util.ArrayList;
import java.util.List;

import com.devsuperior.dsmovie.projections.UserDetailsProjection;

public class UserDetailsFactory {
	
	public static List<UserDetailsProjection> createCustomClientUser(String username) {
		
		List<UserDetailsProjection> list = new ArrayList<>();
		list.add(new UserDetailsImpl(username, "123", 1L, "ROLE_CLIENT"));
		return list;
	}
	
	public static List<UserDetailsProjection> createCustomAdminUser(String username) {
		
		List<UserDetailsProjection> list = new ArrayList<>();
		list.add(new UserDetailsImpl(username, "123", 2L, "ROLE_ADMIN"));
		return list;
	}
	
	public static List<UserDetailsProjection> createCustomAdminClientUser(String username) {
		
		List<UserDetailsProjection> list = new ArrayList<>();
		list.add(new UserDetailsImpl(username, "123", 1L, "ROLE_CLIENT"));
		list.add(new UserDetailsImpl(username, "123", 2L, "ROLE_ADMIN"));
		return list;
	}

}

