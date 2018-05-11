/**
 * 
 */
package org.bonnes.affaires.services;

import java.util.List;

import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.utilisateurs.User;

/**
 * @author salioubah
 *
 */
public interface AlerteService {

	/**
	 * @param alerte
	 * @param user
	 */
	Alerte addAlerte(Alerte alerte, User user);

	/**
	 * @param user
	 * @return
	 */
	List<Alerte> getAlertes(User user);

	/**
	 * @param idAlerte
	 * @param alerte 
	 * @param user
	 * @return
	 */
	Alerte updateAlerte(Long idAlerte, Alerte alerte, User user);

	/**
	 * @param idAlerte
	 * @param user
	 * @return
	 */
	Alerte deleteAlerte(Long idAlerte, User user);

	


}
