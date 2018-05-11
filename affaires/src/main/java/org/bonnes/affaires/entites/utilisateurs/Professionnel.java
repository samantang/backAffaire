/**
 * 
 */
package org.bonnes.affaires.entites.utilisateurs;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.Message;
import org.bonnes.affaires.entites.Publicite;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * @author salioubah
 *
 */

@Entity
@DiscriminatorValue("professionel")
public class Professionnel extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4811774237622874094L;
	private String nomSociete;
	private String typeSociete;
	private String sitewebSocite;
	private String numeroSiretSocite;
	private String adresseSocite;
	
	@OneToMany(mappedBy="publicitesActifs")
	private Collection<Publicite> publicitesActifs;
	
	@OneToMany(mappedBy="publicitesInactifs")
	private Collection<Publicite> publicitesInactifs;
	
	
	public Professionnel() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public String getNomSociete() {
		return nomSociete;
	}
	public void setNomSociete(String nomSociete) {
		this.nomSociete = nomSociete;
	}
	public String getTypeSociete() {
		return typeSociete;
	}
	public void setTypeSociete(String typeSociete) {
		this.typeSociete = typeSociete;
	}
	public String getSitewebSocite() {
		return sitewebSocite;
	}
	public void setSitewebSocite(String sitewebSocite) {
		this.sitewebSocite = sitewebSocite;
	}
	public String getNumeroSiretSocite() {
		return numeroSiretSocite;
	}
	public void setNumeroSiretSocite(String numeroSiretSocite) {
		this.numeroSiretSocite = numeroSiretSocite;
	}
	public String getAdresseSocite() {
		return adresseSocite;
	}
	public void setAdresseSocite(String adresseSocite) {
		this.adresseSocite = adresseSocite;
	}
	public Collection<Publicite> getPublicitesActifs() {
		return publicitesActifs;
	}
	public void setPublicitesActifs(Collection<Publicite> publicitesActifs) {
		this.publicitesActifs = publicitesActifs;
	}
	public Collection<Publicite> getPublicitesInactifs() {
		return publicitesInactifs;
	}
	public void setPublicitesInactifs(Collection<Publicite> publicitesInactifs) {
		this.publicitesInactifs = publicitesInactifs;
	}
	
}
