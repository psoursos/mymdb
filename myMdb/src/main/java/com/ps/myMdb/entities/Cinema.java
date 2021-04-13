package com.ps.myMdb.entities;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
@Table(name="CINEMAS")
public class Cinema {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional = false)
    @Column(name = "id")
	private long cinemaId;
	private String owner; 
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "theCinema") ///(fetch=FetchType.LAZY, cascade=CascadeType.ALL )
	//@JoinColumn(name = "cinema_id")
	private List<Movie> movies;
	
	
//	@ManyToMany(mappedBy = "ownCinemas")
//	private List<User> users;
//	@JoinTable(
//			name = "OWNS",
//			joinColumns = @JoinColumn(name="cinemaId"),
//			inverseJoinColumns = @JoinColumn(name="userId")
//			)
	@JsonIgnore
//@JsonManagedReference(value="own")	
@OneToMany(mappedBy = "cinema")
private List<Owns> ownCinemas;


public Cinema() {
	super();
}


public Cinema(long cinemaId, String owner, String name, List<Movie> movies, List<Owns> ownCinemas) {
	super();
	this.cinemaId = cinemaId;
	this.owner = owner;
	this.name = name;
	this.movies = movies;
	this.ownCinemas = ownCinemas;
}


public long getCinemaId() {
	return cinemaId;
}


public void setCinemaId(long cinemaId) {
	this.cinemaId = cinemaId;
}


public String getOwner() {
	return owner;
}


public void setOwner(String owner) {
	this.owner = owner;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public List<Movie> getMovies() {
	return movies;
}


public void setMovies(List<Movie> movies) {
	this.movies = movies;
}


public List<Owns> getOwnCinemas() {
	return ownCinemas;
}


public void setOwnCinemas(List<Owns> ownCinemas) {
	this.ownCinemas = ownCinemas;
}
	
	
	
	
}
