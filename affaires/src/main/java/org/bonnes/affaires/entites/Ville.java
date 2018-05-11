/**
 * 
 */
package org.bonnes.affaires.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author salioubah
 *
 */
@Entity
public class Ville implements Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4498458852979426797L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String nom;
	@ManyToOne
	private Region region;
	@ManyToOne
	private Publicite publicite;
	@OneToOne
//	@JsonManagedReference
	@JsonIgnore
	private Annonce annonce;
	
	
	
	public Ville() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Ville(String nom, Region region, Publicite publicite) {
		super();
		this.nom = nom;
		this.region = region;
		this.publicite = publicite;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public Region getRegion() {
		return region;
	}



	public void setRegion(Region region) {
		this.region = region;
	}



	public Publicite getPublicite() {
		return publicite;
	}



	public void setPublicite(Publicite publicite) {
		this.publicite = publicite;
	}
	
	
}
