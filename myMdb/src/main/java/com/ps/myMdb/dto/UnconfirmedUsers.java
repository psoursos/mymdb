package com.ps.myMdb.dto;

import com.ps.myMdb.entities.Role;

public interface UnconfirmedUsers {
	
	public String getName();
	public String getSurname();
	public String getUsername();
	public String getEmail();
	public Role getRole();
	public Boolean getConfirmed();
	
	

}
