/**
 * 
 */
package org.bonnes.affaires.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.utilisateurs.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author salioubah
 *
 */
@Entity
public class Favori implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7393310495217211548L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@OneToOne
	private Annonce annonce;
	
	@ManyToOne
	@JoinColumn(name="offres_favorites")
	private User offres_favorites;
	
	@ManyToOne
	@JoinColumn(name="demandes_favorites")
	private User demandes_favorites;
	
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="mes_favoris")
	private User mes_favoris;
	
	
	
	public Favori() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public Favori(Annonce annonce, Date date, User mes_favoris) {
		super();
		this.annonce = annonce;
		this.date = date;
		this.mes_favoris = mes_favoris;
	}




	public Favori(Annonce annonce, User offres_favorites, User demandes_favorites, Date date) {
		super();
		this.annonce = annonce;
		this.offres_favorites = offres_favorites;
		this.demandes_favorites = demandes_favorites;
		this.date = date;
	}




	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}


	

	public Annonce getAnnonce() {
		return annonce;
	}



	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}



	public User getOffres_favorites() {
		return offres_favorites;
	}



	public void setOffres_favorites(User offres_favorites) {
		this.offres_favorites = offres_favorites;
	}



	public User getDemandes_favorites() {
		return demandes_favorites;
	}



	public void setDemandes_favorites(User demandes_favorites) {
		this.demandes_favorites = demandes_favorites;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}




	public User getMes_favoris() {
		return mes_favoris;
	}




	public void setMes_favoris(User mes_favoris) {
		this.mes_favoris = mes_favoris;
	}
	
	
}
