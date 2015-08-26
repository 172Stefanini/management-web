package com.stefanini.bob.management.web;

import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtils {

	public String getLoggedUserName(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
	
	public List<String> getGrants(){
		List<String> grants = new LinkedList<String>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for(Object ga : auth.getAuthorities().toArray()){
			GrantedAuthority gas = (GrantedAuthority) ga;
			grants.add(gas.getAuthority());
		}
		return grants;
	}
	
	public boolean hasRole(String role){
		return this.getGrants().contains(role);
	}
}
