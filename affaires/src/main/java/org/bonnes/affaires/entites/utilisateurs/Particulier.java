/**
 * 
 */
package org.bonnes.affaires.entites.utilisateurs;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.Message;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author salioubah
 *
 */
@Entity
@DiscriminatorValue("particulier")
@JsonIgnoreProperties
public class Particulier extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3211691537831490699L;
	private String civilite;
	private String nom;
	private String penom;
	private String categoriePro;
	private String adresse;
	
	@ElementCollection
	private Collection<String> centreDinterets;
	
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	
	public Particulier() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getCivilite() {
		return civilite;
	}


	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPenom() {
		return penom;
	}


	public void setPenom(String penom) {
		this.penom = penom;
	}


	public String getCategoriePro() {
		return categoriePro;
	}


	public void setCategoriePro(String categoriePro) {
		this.categoriePro = categoriePro;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	public Collection<String> getCentreDinterets() {
		return centreDinterets;
	}


	public void setCentreDinterets(Collection<String> centreDinterets) {
		this.centreDinterets = centreDinterets;
	}


	public Date getDateNaissance() {
		return dateNaissance;
	}


	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
		
	
}
