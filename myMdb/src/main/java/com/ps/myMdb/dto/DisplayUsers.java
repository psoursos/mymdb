package com.ps.myMdb.dto;
import com.ps.myMdb.entities.Role;
import com.ps.myMdb.entities.User;

public interface DisplayUsers {
	
	public Long getId();
	public String getName();
	public String getSurname();
	public String getUsername();
	public String getEmail();
	public Role getRole();
	public Boolean getConfirmed();

}
