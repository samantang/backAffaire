/**
 * 
 */
package org.bonnes.affaires.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author salioubah
 *
 */
@Entity
public class AnnonceBis implements Serializable{
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String nom;
	private int prix;
	private int nombreDeVues;
	private String description;

	private boolean telephoneVisible;
	private boolean publie;
	private String type;
	
	
	public AnnonceBis() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getNombreDeVues() {
		return nombreDeVues;
	}
	public void setNombreDeVues(int nombreDeVues) {
		this.nombreDeVues = nombreDeVues;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isTelephoneVisible() {
		return telephoneVisible;
	}
	public void setTelephoneVisible(boolean telephoneVisible) {
		this.telephoneVisible = telephoneVisible;
	}
	public boolean isPublie() {
		return publie;
	}
	public void setPublie(boolean publie) {
		this.publie = publie;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	


}
