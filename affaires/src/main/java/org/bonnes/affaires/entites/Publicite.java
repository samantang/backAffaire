/**
 * 
 */
package org.bonnes.affaires.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.utilisateurs.Professionnel;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author salioubah
 *
 */
@Entity
public class Publicite implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8883758994384405443L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String nom;
	private byte [] photo;
	private int prix;
	private String description;

	@Enumerated(EnumType.STRING)
	private Categorie categorie;
	
	@OneToMany(mappedBy="publicite")
	private Collection<Ville> villes;
	
	@OneToMany(mappedBy="publicite", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Region> collection;
	
	@ManyToOne
	@JoinColumn(name="pub_inactifs")
	private Professionnel publicitesInactifs;
	
	@ManyToOne
	@JoinColumn(name="pub_actifs")
	private Professionnel publicitesActifs;
	
	private boolean actif;
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToMany (mappedBy="publicite")
	private Collection<Region> regions;
	
	
	public Publicite() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Publicite(String nom, Categorie categorie, byte[] photo, int prix, Collection<Ville> villes,
			Collection<Region> collection, String description, Professionnel publicitesInactifs,
			Professionnel publicitesActifs, boolean actif) {
		super();
		this.nom = nom;
		this.categorie = categorie;
		this.photo = photo;
		this.prix = prix;
		this.villes = villes;
		this.collection = collection;
		this.description = description;
		this.publicitesInactifs = publicitesInactifs;
		this.publicitesActifs = publicitesActifs;
		this.actif = actif;
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


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public int getPrix() {
		return prix;
	}


	public void setPrix(int prix) {
		this.prix = prix;
	}


	public Collection<Ville> getVilles() {
		return villes;
	}


	public void setVilles(Collection<Ville> villes) {
		this.villes = villes;
	}


	public Collection<Region> getCollection() {
		return collection;
	}


	public void setCollection(Collection<Region> collection) {
		this.collection = collection;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Professionnel getPublicitesInactifs() {
		return publicitesInactifs;
	}


	public void setPublicitesInactifs(Professionnel publicitesInactifs) {
		this.publicitesInactifs = publicitesInactifs;
	}


	public Professionnel getPublicitesActifs() {
		return publicitesActifs;
	}


	public void setPublicitesActifs(Professionnel publicitesActifs) {
		this.publicitesActifs = publicitesActifs;
	}


	public boolean isActif() {
		return actif;
	}


	public void setActif(boolean actif) {
		this.actif = actif;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Collection<Region> getRegions() {
		return regions;
	}


	public void setRegions(Collection<Region> region) {
		this.regions = region;
	}
	
	
	
}
