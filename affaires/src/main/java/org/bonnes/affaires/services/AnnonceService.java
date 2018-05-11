/**
 * 
 */
package org.bonnes.affaires.services;

import java.util.List;

import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Categorie;
import org.bonnes.affaires.entites.RechercheModele;
import org.bonnes.affaires.entites.utilisateurs.User;

/**
 * @author salioubah
 *
 */
public interface AnnonceService {

	
	boolean checkAnnonceValide(Annonce annonce);

	/**
	 * @param annonce
	 * @param userId
	 * @return
	 */
	Annonce saveAnnonce(Annonce annonce, Long userId);

	/**
	 * @return
	 */
	List<Annonce> getToutesAnnoncesPubliees();

	/**
	 * @param id
	 * @param user
	 * @return
	 */
	Annonce valideAnnoce(Long id, User user);

	/**
	 * @return
	 */
	List<Annonce> getToutesOffresPubliees();

	/**
	 * @return
	 */
	List<Annonce> getOffresParticuliersPubliees();

	/**
	 * @return
	 */
	List<Annonce> getOffresProfessionnelsPubliees();

	/**
	 * @return
	 */
	List<Annonce> getToutesDemandesPubliees();

	/**
	 * @return
	 */
	List<Annonce> getDemandesParticuliersPubliees();

	/**
	 * @return
	 */
	List<Annonce> getDemandesProfessionnelsPubliees();

	/**
	 * @param user
	 * @return
	 */
	List<Annonce> getMesAnnonces(User user);

	/**
	 * @param idAnnonce
	 * @param annonce 
	 * @param user
	 * @return
	 */
	Annonce updateAnnonce(Long idAnnonce, Annonce annonce, User user);

	/**
	 * @param idAlerte
	 * @param user
	 * @return
	 */
	Annonce deleteAnnonce(Long idAnnonce, User user);

	/**
	 * @return
	 */
	List<Annonce> getAnnoncesAvalider();

	/**
	 * @param annonce
	 * @param user
	 * @return
	 */
	Annonce signalerAnnonce(Annonce annonce, User user);

	/**
	 * @return
	 */
	List<Annonce> getAnnoncesSignalees();

	/**
	 * @return
	 */
	Long getNombreToutesAnnoncesPubliees();

	/**
	 * @param page
	 * @return
	 */
	List<Annonce> changerPagination(int page);

	/**
	 * @param username
	 * @param id
	 * @return
	 */
	Annonce depublierAnnonce(String username, Long id);

	/**
	 * @param id
	 * @param user
	 * @return
	 */
	Annonce republierAnnonce(Long id, User user);

	/**
	 * @param annonce
	 * @param user
	 * @return
	 */
	Annonce updateAnnonce(Annonce annonce, User user);

	/**
	 * @param username
	 * @param id
	 * @param message
	 */
	void sendMessage(String username, Long id, String message);

	/**
	 * @param username
	 * @param id
	 */
	void invaliderAnnonce(String username, Long id);

	/**
	 * @param rechercheModele
	 * @return
	 */
	List<Annonce> rechercheAnnonces(RechercheModele rechercheModele);

	
	
	
}
