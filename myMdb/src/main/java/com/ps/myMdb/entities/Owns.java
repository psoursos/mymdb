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
@Table(name="OWNS")
public class Owns {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional = false)
    @Column(name = "id")
	private long id;
	//@Column(name = "user_id")
	//private long userId;
	//private long cinemaId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    Cinema cinema;
	
	
	
	public Owns() {
		super();
	}



	public Owns(long id, User user, Cinema cinema) {
		super();
		this.id = id;
		this.user = user;
		this.cinema = cinema;
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



	public Cinema getCinema() {
		return cinema;
	}



	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	
	

	

}
