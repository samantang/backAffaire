/**
 * 
 */
package org.bonnes.affaires.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author salioubah
 *
 */
@Entity
public class Region implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -562485018409785906L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	@OneToMany (mappedBy="region")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Ville> villes;
	private String nom;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Publicite> publicite;
	@OneToOne
//	@JsonManagedReference
	@JsonIgnore
	private Annonce annonce;
	
	
	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Region(Collection<Ville> villes, String nom, Collection<Publicite> publicite) {
		super();
		this.villes = villes;
		this.nom = nom;
		this.publicite = publicite;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Collection<Ville> getVilles() {
		return villes;
	}


	public void setVilles(Collection<Ville> villes) {
		this.villes = villes;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Collection<Publicite> getPublicite() {
		return publicite;
	}


	public void setPublicite(Collection<Publicite> publicite) {
		this.publicite = publicite;
	}
	
	
	
	

}
