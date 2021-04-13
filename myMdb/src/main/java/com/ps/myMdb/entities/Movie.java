package com.ps.myMdb.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="MOVIES")
public class Movie {

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional = false)
    @Column(name = "movie_id")
	private long movieId;
	private String title;
//	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	private LocalDate startDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
//	@Temporal(TemporalType.DATE)
	private LocalDate endDate;
	private String cinemaName;
	private String category;
	//private long cinema_id;
	@JsonIgnore
	//@JsonBackReference(value="cin")
    @ManyToOne
    @JoinColumn(name="cinema_id", nullable=false)
    private Cinema theCinema;
	
	@JsonIgnore
	//@JsonBackReference(value="fav")
	@OneToMany(mappedBy = "movie")
	private List<Favorite> favMovies;
//	@JoinTable(
//			name = "FAVORITES",
//			joinColumns = @JoinColumn(name="movieId"),
//			inverseJoinColumns = @JoinColumn(name="userId")
//			)

	
	


	public Movie(long movieId, String title, LocalDate startDate, LocalDate endDate, String cinemaName, String category,
		Cinema theCinema, List<Favorite> favMovies) {
	super();
	this.movieId = movieId;
	this.title = title;
	this.startDate = startDate;
	this.endDate = endDate;
	this.cinemaName = cinemaName;
	this.category = category;
	this.theCinema = theCinema;
	this.favMovies = favMovies;
}
	
	public Movie() {
	super();
}

	
	
	public Movie(Cinema theCinema) {
		super();
		this.theCinema = theCinema;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Cinema getTheCinema() {
		return theCinema;
	}

	public void setTheCinema(Cinema theCinema) {
		this.theCinema = theCinema;
	}

	public List<Favorite> getfavMovies() {
		return favMovies;
	}

	public void setfavMovies(List<Favorite> favMovies) {
		this.favMovies = favMovies;
	}
	
	
	
	
	



	
	
	
}
