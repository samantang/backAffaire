/**
 * 
 */
package org.bonnes.affaires.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.entites.utilisateurs.Utilisateur;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author salioubah
 *
 */
@Entity
public class Alerte implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5469573902617446375L;
	/**
	 * 
	 */
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String titre;
	private String categorie;
	private String region;
	private boolean particuliers;
	private boolean professionnels;
	private boolean offre;
	private boolean demande;
	private String ville;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="mes_alertes")
	@JsonIgnore
	private User mes_alertes;
	
	
	public Alerte() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		this.titre = titre;
	}


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public boolean isParticuliers() {
		return particuliers;
	}


	public void setParticuliers(boolean particuliers) {
		this.particuliers = particuliers;
	}


	public boolean isProfessionnels() {
		return professionnels;
	}


	public void setProfessionnels(boolean professionnels) {
		this.professionnels = professionnels;
	}


	public boolean isOffre() {
		return offre;
	}


	public void setOffre(boolean offre) {
		this.offre = offre;
	}


	public boolean isDemande() {
		return demande;
	}


	public void setDemande(boolean demande) {
		this.demande = demande;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public User getMes_alertes() {
		return mes_alertes;
	}


	public void setMes_alertes(User mes_alertes) {
		this.mes_alertes = mes_alertes;
	}


	public Alerte(String titre, String categorie, String region, boolean particuliers, boolean professionnels,
			boolean offre, boolean demande, String ville, Date date, User mes_alertes) {
		super();
		this.titre = titre;
		this.categorie = categorie;
		this.region = region;
		this.particuliers = particuliers;
		this.professionnels = professionnels;
		this.offre = offre;
		this.demande = demande;
		this.ville = ville;
		this.date = date;
		this.mes_alertes = mes_alertes;
	}


	
	
	
}
