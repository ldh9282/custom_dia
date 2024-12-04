package com.custom.dia.cmmn.security.model;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.custom.dia.cmmn.model.CustomMap;

public class CustomUser extends User {

	private static final long serialVersionUID = 1L;

	public CustomUser(CustomMap memberMap) {
		
		super(memberMap.getString("memId"), 
				memberMap.getString("memPw"), 
				memberMap.getList("memAuthList").stream()
								            .map(SimpleGrantedAuthority::new)
								            .collect(Collectors.toList()));
	}
	
	
	
	
}
