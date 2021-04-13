package com.ps.myMdb.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="FAVORITES")
public class Favorite {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional = false)
    @Column(name = "id")
	private long id;
	@ManyToOne
    @JoinColumn(name = "user_id")
	User user;
	@ManyToOne
    @JoinColumn(name = "movie_id")
	Movie movie;
	
	
	
	public Favorite() {
		super();
	}
	public Favorite( User user, Movie movie) {
		super();
		this.user = user;
		this.movie = movie;
	}


	public Favorite(long id, User user, Movie movie) {
		super();
		this.id = id;
		this.user = user;
		this.movie = movie;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Movie getMovie() {
		return movie;
	}



	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	

	
	
}
