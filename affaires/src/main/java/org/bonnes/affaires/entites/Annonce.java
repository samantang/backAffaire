/**
 * 
 */
package org.bonnes.affaires.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.utilisateurs.User;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.sym.Name;

/**
 * @author salioubah
 *
 */
@Entity
public class Annonce implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8914687204508556557L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String titre;
	private int prix;
	private int nombreDeVues;
	private String description;
	private String categorie;
	private String cheminImage;
	private String ville;
	private String region;
	private boolean telephoneVisible;
	private boolean publie;
	private String typeAnnonce;
	private String natureAnnonce;
//	les carasteristiques des produits
	private int surfaceMetreCarre;
	private int nbPieces;
	private String modele;
	private int anneeModele;
	private String carburant;
	private String boiteDeVitesse;
	private int cylindre;
	private int longueur;
	private int largeur;
	
	@Temporal(TemporalType.DATE)
	private Date dateDepot;
	
	@Temporal(TemporalType.DATE)
	private Date dateValidation;
	
	@OneToOne
	@JsonIgnore
	private Favori favori;
	
	@ElementCollection
	private Collection<String> nomsPhotos;
	
	private String nomPhoto;
	
	@ElementCollection
	private Collection<String> cheminsImages;
	
	private boolean visible; // modifié par l'annonceur, il doit etre par defaut 'true', s'il est mis à false
							 // cela rendra aussi la propriete 'publie' à false
	private String cheminImage1;
	private String cheminImage2;
	private String cheminImage3;
	private String cheminImage4;
	
	
//	@Enumerated(value=EnumType.STRING)
//	@JsonIgnore
//	private TypeAnnonce typeAnnonce;
	
	@ManyToOne
	@JoinColumn(name="user_annoncesPostees")
//	@JsonIgnore
	private User user_annoncesPostees;
	
	@ManyToOne
	@JoinColumn(name="user_annoncesPosteesEtValidees")
//	@JsonIgnore
	private User user_annoncesPosteesEtValidees;
	
	@ManyToOne
	@JoinColumn(name="user_annoncesPartagees")
//	@JsonIgnore
	private User user_annoncesPartagees;
	
	@ManyToOne
	@JoinColumn(name="user_annoncesSignalees")
//	@JsonIgnore
	private User user_annoncesSignalees;
	
	@ManyToOne 
	@JoinColumn(name="user_annoncesValidees")
//	@JsonIgnore
	private User user_annoncesValidees;
	
	@ManyToOne 
	@JoinColumn(name="user_annoncesFavorites")
//	@JsonIgnore
	private User user_annoncesFavorites;
	
//	@ElementCollection(fetch=FetchType.EAGER)
//	@Lob
//	@Fetch(FetchMode.SUBSELECT)
//	private Collection <Byte []> images;
	private boolean annonceSignalee;
	private String motifSignalement;
	private String messageSignalement;
	private String dateStringDepot;
	private String dateStringValidation;
	private int nbImages;
	private String etatPublication;
	private String dateStringModification;
	
	@ManyToOne 
	@JoinColumn(name="user_annoncesInvalidees")
//	@JsonIgnore
	private User user_annoncesInvalidees;
	private String motifInvalidation;
	private boolean invalidee;
	
	@ManyToOne 
	@JoinColumn(name="user_annoncesDepubliees")
//	@JsonIgnore
	private User user_annoncesDepubliees;
	private String motifDepublication;
	private boolean depubliee;
	
	private int kilometres;
	private int anneeDebutCirculation;
	private String marque;
	private boolean immobilierMaison;
	private boolean immobilierAppartement;
	private boolean immobilierTerrain;
	private boolean immobilierParking;
	
	
	
	public int getKilometres() {
		return kilometres;
	}



	public void setKilometres(int kilometres) {
		this.kilometres = kilometres;
	}



	public int getAnneeDebutCirculation() {
		return anneeDebutCirculation;
	}



	public void setAnneeDebutCirculation(int anneeDebutCirculation) {
		this.anneeDebutCirculation = anneeDebutCirculation;
	}



	public String getMarque() {
		return marque;
	}



	public void setMarque(String marque) {
		this.marque = marque;
	}



	public boolean isImmobilierMaison() {
		return immobilierMaison;
	}



	public void setImmobilierMaison(boolean immobilierMaison) {
		this.immobilierMaison = immobilierMaison;
	}



	public boolean isImmobilierAppartement() {
		return immobilierAppartement;
	}



	public void setImmobilierAppartement(boolean immobilierAppartement) {
		this.immobilierAppartement = immobilierAppartement;
	}



	public boolean isImmobilierTerrain() {
		return immobilierTerrain;
	}



	public void setImmobilierTerrain(boolean immobilierTerrain) {
		this.immobilierTerrain = immobilierTerrain;
	}



	public boolean isImmobilierParking() {
		return immobilierParking;
	}



	public void setImmobilierParking(boolean immobilierParking) {
		this.immobilierParking = immobilierParking;
	}



	public User getUser_annoncesDepubliees() {
		return user_annoncesDepubliees;
	}



	public void setUser_annoncesDepubliees(User user_annoncesDepubliees) {
		this.user_annoncesDepubliees = user_annoncesDepubliees;
	}



	public String getMotifDepublication() {
		return motifDepublication;
	}



	public void setMotifDepublication(String motifDepublication) {
		this.motifDepublication = motifDepublication;
	}



	public boolean isDepubliee() {
		return depubliee;
	}



	public void setDepubliee(boolean depubliee) {
		this.depubliee = depubliee;
	}



	public User getUser_annoncesInvalidees() {
		return user_annoncesInvalidees;
	}



	public void setUser_annoncesInvalidees(User user_annoncesInvalidees) {
		this.user_annoncesInvalidees = user_annoncesInvalidees;
	}



	public String getMotifInvalidation() {
		return motifInvalidation;
	}



	public void setMotifInvalidation(String motifInvalidation) {
		this.motifInvalidation = motifInvalidation;
	}



	public boolean isInvalidee() {
		return invalidee;
	}



	public void setInvalidee(boolean invalidee) {
		this.invalidee = invalidee;
	}



	public String getDateStringModification() {
		return dateStringModification;
	}



	public void setDateStringModification(String dateStringModification) {
		this.dateStringModification = dateStringModification;
	}



	public String getEtatPublication() {
		return etatPublication;
	}



	public void setEtatPublication(String etatPublication) {
		this.etatPublication = etatPublication;
	}



	public int getNbImages() {
		return nbImages;
	}



	public void setNbImages(int nbImages) {
		this.nbImages = nbImages;
	}



	public String getDateStringDepot() {
		return dateStringDepot;
	}



	public void setDateStringDepot(String dateStringDepot) {
		this.dateStringDepot = dateStringDepot;
	}



	public String getDateStringValidation() {
		return dateStringValidation;
	}



	public void setDateStringValidation(String dateStringValidation) {
		this.dateStringValidation = dateStringValidation;
	}



	public Date getDateDepot() {
		return dateDepot;
	}



	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}



	public Date getDateValidation() {
		return dateValidation;
	}



	public void setDateValidation(Date dateValidation) {
		this.dateValidation = dateValidation;
	}



	public boolean isAnnonceSignalee() {
		return annonceSignalee;
	}



	public void setAnnonceSignalee(boolean annonceSignalee) {
		this.annonceSignalee = annonceSignalee;
	}



	public String getMotifSignalement() {
		return motifSignalement;
	}



	public void setMotifSignalement(String motifSignalement) {
		this.motifSignalement = motifSignalement;
	}



	public String getMessageSignalement() {
		return messageSignalement;
	}



	public void setMessageSignalement(String messageSignalement) {
		this.messageSignalement = messageSignalement;
	}



	public String getTitre() {
		return titre;
	}



	public void setTitre(String titre) {
		this.titre = titre;
	}



	public String getCheminImage() {
		return cheminImage;
	}



	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}



	public Annonce() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public String getCategorie() {
		return categorie;
	}



	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}



	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Favori getFavori() {
		return favori;
	}



	public void setFavori(Favori favori) {
		this.favori = favori;
	}



	public Collection<String> getNomsPhotos() {
		return nomsPhotos;
	}



	public void setNomsPhotos(Collection<String> nomsPhotos) {
		this.nomsPhotos = nomsPhotos;
	}



	public String getNomPhoto() {
		return nomPhoto;
	}



	public void setNomPhoto(String nomPhoto) {
		this.nomPhoto = nomPhoto;
	}



	public Collection<String> getCheminsImages() {
		return cheminsImages;
	}



	public void setCheminsImages(Collection<String> cheminsImages) {
		this.cheminsImages = cheminsImages;
	}



	public String getVille() {
		return ville;
	}



	public void setVille(String ville) {
		this.ville = ville;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
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



	public String getTypeAnnonce() {
		return typeAnnonce;
	}



	public void setTypeAnnonce(String typeAnnonce) {
		this.typeAnnonce = typeAnnonce;
	}



	public String getNatureAnnonce() {
		return natureAnnonce;
	}



	public void setNatureAnnonce(String natureAnnonce) {
		this.natureAnnonce = natureAnnonce;
	}



	public int getSurfaceMetreCarre() {
		return surfaceMetreCarre;
	}



	public void setSurfaceMetreCarre(int surfaceMetreCarre) {
		this.surfaceMetreCarre = surfaceMetreCarre;
	}



	public int getNbPieces() {
		return nbPieces;
	}



	public void setNbPieces(int nbPieces) {
		this.nbPieces = nbPieces;
	}



	public String getModele() {
		return modele;
	}



	public void setModele(String modele) {
		this.modele = modele;
	}



	public int getAnneeModele() {
		return anneeModele;
	}



	public void setAnneeModele(int anneeModele) {
		this.anneeModele = anneeModele;
	}



	public String getCarburant() {
		return carburant;
	}



	public void setCarburant(String carburant) {
		this.carburant = carburant;
	}



	public String getBoiteDeVitesse() {
		return boiteDeVitesse;
	}



	public void setBoiteDeVitesse(String boiteDeVitesse) {
		this.boiteDeVitesse = boiteDeVitesse;
	}



	public int getCylindre() {
		return cylindre;
	}



	public void setCylindre(int cylindre) {
		this.cylindre = cylindre;
	}



	public int getLongueur() {
		return longueur;
	}



	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}



	public int getLargeur() {
		return largeur;
	}



	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}



	public User getUser_annoncesPostees() {
		return user_annoncesPostees;
	}



	public void setUser_annoncesPostees(User user_annoncesPostees) {
		this.user_annoncesPostees = user_annoncesPostees;
	}



	public User getUser_annoncesPosteesEtValidees() {
		return user_annoncesPosteesEtValidees;
	}



	public void setUser_annoncesPosteesEtValidees(User user_annoncesPosteesEtValidees) {
		this.user_annoncesPosteesEtValidees = user_annoncesPosteesEtValidees;
	}



	public User getUser_annoncesPartagees() {
		return user_annoncesPartagees;
	}



	public void setUser_annoncesPartagees(User user_annoncesPartagees) {
		this.user_annoncesPartagees = user_annoncesPartagees;
	}



	public User getUser_annoncesSignalees() {
		return user_annoncesSignalees;
	}



	public void setUser_annoncesSignalees(User user_annoncesSignalees) {
		this.user_annoncesSignalees = user_annoncesSignalees;
	}



	public User getUser_annoncesValidees() {
		return user_annoncesValidees;
	}



	public void setUser_annoncesValidees(User user_annoncesValidees) {
		this.user_annoncesValidees = user_annoncesValidees;
	}



	public User getUser_annoncesFavorites() {
		return user_annoncesFavorites;
	}



	public void setUser_annoncesFavorites(User user_annoncesFavorites) {
		this.user_annoncesFavorites = user_annoncesFavorites;
	}



	public Annonce(String titre, int prix, int nombreDeVues, String description, String categorie, Date dateDepot,
			Date dateValidation, Favori favori, Collection<String> nomsPhotos, String nomPhoto,
			Collection<String> cheminsImages, String cheminImage, String ville, String region, boolean telephoneVisible,
			boolean publie, String typeAnnonce, String natureAnnonce, int surfaceMetreCarre, int nbPieces,
			String modele, int anneeModele, String carburant, String boiteDeVitesse, int cylindre, int longueur,
			int largeur, User user_annoncesPostees, User user_annoncesPosteesEtValidees, User user_annoncesPartagees,
			User user_annoncesSignalees, User user_annoncesValidees, User user_annoncesFavorites) {
		super();
		this.titre = titre;
		this.prix = prix;
		this.nombreDeVues = nombreDeVues;
		this.description = description;
		this.categorie = categorie;
		this.dateDepot = dateDepot;
		this.dateValidation = dateValidation;
		this.favori = favori;
		this.nomsPhotos = nomsPhotos;
		this.nomPhoto = nomPhoto;
		this.cheminsImages = cheminsImages;
		this.cheminImage = cheminImage;
		this.ville = ville;
		this.region = region;
		this.telephoneVisible = telephoneVisible;
		this.publie = publie;
		this.typeAnnonce = typeAnnonce;
		this.natureAnnonce = natureAnnonce;
		this.surfaceMetreCarre = surfaceMetreCarre;
		this.nbPieces = nbPieces;
		this.modele = modele;
		this.anneeModele = anneeModele;
		this.carburant = carburant;
		this.boiteDeVitesse = boiteDeVitesse;
		this.cylindre = cylindre;
		this.longueur = longueur;
		this.largeur = largeur;
		this.user_annoncesPostees = user_annoncesPostees;
		this.user_annoncesPosteesEtValidees = user_annoncesPosteesEtValidees;
		this.user_annoncesPartagees = user_annoncesPartagees;
		this.user_annoncesSignalees = user_annoncesSignalees;
		this.user_annoncesValidees = user_annoncesValidees;
		this.user_annoncesFavorites = user_annoncesFavorites;
	}



	public boolean isVisible() {
		return visible;
	}



	public void setVisible(boolean visible) {
		this.visible = visible;
	}



	public String getCheminImage1() {
		return cheminImage1;
	}



	public void setCheminImage1(String cheminImage1) {
		this.cheminImage1 = cheminImage1;
	}



	public String getCheminImage2() {
		return cheminImage2;
	}



	public void setCheminImage2(String cheminImage2) {
		this.cheminImage2 = cheminImage2;
	}



	public String getCheminImage3() {
		return cheminImage3;
	}



	public void setCheminImage3(String cheminImage3) {
		this.cheminImage3 = cheminImage3;
	}



	public String getCheminImage4() {
		return cheminImage4;
	}



	public void setCheminImage4(String cheminImage4) {
		this.cheminImage4 = cheminImage4;
	}

	
	

		
}
