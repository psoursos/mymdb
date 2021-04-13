package com.ps.myMdb.entities;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import com.ps.myMdb.api.validators.UniqueValue;




@Entity
@Table(name="USERS")
public class User {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional = false)
    @Column(name = "id")
	private long userId;
	@Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 45)
    @Column(name = "first_name")
	private String firstName;
	@Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 45)
    @Column(name = "last_name")
	private String lastName;
	@Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 45)
    @Column(name = "username",unique=true)
//	@UniqueValue
	private String username;
	@Basic(optional = false)
    @NotNull
    //@Size(min = 8, max = 65)
    @Column(name = "password")
	private String password;
	@Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 45)
    @Column(name = "email")
	private String email;
	@Basic(optional = false)
    @NotNull
    @Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	@Basic(optional = true)
    @Column(name = "confirmed")
	private boolean confirmed;
	
	
//	@ManyToMany
//	@JoinTable(
//			name = "FAVORITES",
//			joinColumns = @JoinColumn(name="user_id"),
//			inverseJoinColumns = @JoinColumn(name="movie_id")
//	)
	
	//@JsonBackReference(value="fav")
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Favorite> favMovies;
	

	
	
	//@JsonBackReference(value="own")
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Owns> ownCinemas;
	
	
	public User() {
		super();
	}
	
	public User(long userId, @NotNull @Size(min = 4, max = 45) String firstName,
			@NotNull @Size(min = 4, max = 45) String lastName, @NotNull @Size(min = 4, max = 45) String username,
			@NotNull String password, @NotNull String email, @NotNull Role role, boolean confirmed) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.confirmed = confirmed;

	}


	public User(long userId, @NotNull @Size(min = 4, max = 45) String firstName,
			@NotNull @Size(min = 4, max = 45) String lastName, @NotNull @Size(min = 4, max = 45) String username,
			@NotNull String password, @NotNull String email, @NotNull Role role, boolean confirmed,
			List<Favorite> favMovies, List<Owns> ownCinemas) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.confirmed = confirmed;
		this.favMovies = favMovies;
		this.ownCinemas = ownCinemas;
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public boolean isConfirmed() {
		return confirmed;
	}


	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}


	public List<Favorite> getFavMovies() {
		return favMovies;
	}


	public void setFavMovies(List<Favorite> favMovies) {
		this.favMovies = favMovies;
	}


	public List<Owns> getOwnCinemas() {
		return ownCinemas;
	}


	public void setOwnCinemas(List<Owns> ownCinemas) {
		this.ownCinemas = ownCinemas;
	}
	

	
}

