/**
 * 
 */
package org.bonnes.affaires.entites.utilisateurs;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.Message;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author salioubah
 *
 */

public class Utilisateur implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6240608737979186055L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String pseudonyme;
	private String email;
	private String MotDePasse;
	private String ville;
	private String telephone;
	@Temporal(TemporalType.DATE)
	private Date date;
	@OneToMany(mappedBy="utilisateur")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection <Annonce> annoncesActifs;
	@OneToMany(mappedBy="utilisateurInactif")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection <Annonce> annoncesInactifs;
	@OneToMany(mappedBy="utilisateur")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Alerte> alertes;
	@OneToMany(mappedBy="utilisateur")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Favori> favoris;
	@OneToMany(mappedBy="utilisateurEnvoi")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection <Message> messagesEnvoyes;
	@OneToMany(mappedBy="utilisateurRecoit")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection <Message> messagesRecus;
	@OneToMany(mappedBy="utilisateurPartage")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Annonce> annoncesPartagees;
	@OneToMany(mappedBy="utilisateurSignale")
//	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Annonce> annoncesSignalees;
	
	public Utilisateur(String pseudonyme, String email, String motDePasse, String ville, String telephone, Date date,
			Collection<Annonce> annoncesActifs, Collection<Annonce> annoncesInactifs, Collection<Alerte> alertes,
			Collection<Favori> favoris, Collection<Message> messagesEnvoyes, Collection<Message> messagesRecus,
			Collection<Annonce> annoncesPartagees, Collection<Annonce> annoncesSignalees) {
		super();
		this.pseudonyme = pseudonyme;
		this.email = email;
		MotDePasse = motDePasse;
		this.ville = ville;
		this.telephone = telephone;
		this.date = date;
		this.annoncesActifs = annoncesActifs;
		this.annoncesInactifs = annoncesInactifs;
		this.alertes = alertes;
		this.favoris = favoris;
		this.messagesEnvoyes = messagesEnvoyes;
		this.messagesRecus = messagesRecus;
		this.annoncesPartagees = annoncesPartagees;
		this.annoncesSignalees = annoncesSignalees;
	}
	public Utilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPseudonyme() {
		return pseudonyme;
	}
	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return MotDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		MotDePasse = motDePasse;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Collection<Annonce> getAnnoncesActifs() {
		return annoncesActifs;
	}
	public void setAnnoncesActifs(Collection<Annonce> annoncesActifs) {
		this.annoncesActifs = annoncesActifs;
	}
	public Collection<Annonce> getAnnoncesInactifs() {
		return annoncesInactifs;
	}
	public void setAnnoncesInactifs(Collection<Annonce> annoncesInactifs) {
		this.annoncesInactifs = annoncesInactifs;
	}
	public Collection<Alerte> getAlertes() {
		return alertes;
	}
	public void setAlertes(Collection<Alerte> alertes) {
		this.alertes = alertes;
	}
	public Collection<Favori> getFavoris() {
		return favoris;
	}
	public void setFavoris(Collection<Favori> favoris) {
		this.favoris = favoris;
	}
	public Collection<Message> getMessagesEnvoyes() {
		return messagesEnvoyes;
	}
	public void setMessagesEnvoyes(Collection<Message> messagesEnvoyes) {
		this.messagesEnvoyes = messagesEnvoyes;
	}
	public Collection<Message> getMessagesRecus() {
		return messagesRecus;
	}
	public void setMessagesRecus(Collection<Message> messagesRecus) {
		this.messagesRecus = messagesRecus;
	}
	public Collection<Annonce> getAnnoncesPartagees() {
		return annoncesPartagees;
	}
	public void setAnnoncesPartagees(Collection<Annonce> annoncesPartagees) {
		this.annoncesPartagees = annoncesPartagees;
	}
	public Collection<Annonce> getAnnoncesSignalees() {
		return annoncesSignalees;
	}
	public void setAnnoncesSignalees(Collection<Annonce> annoncesSignalees) {
		this.annoncesSignalees = annoncesSignalees;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
