/**
 * 
 */
package org.bonnes.affaires.services;

import java.util.List;

import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.utilisateurs.User;

/**
 * @author salioubah
 *
 */
public interface FavoriService {

	/**
	 * @param idAnnonce
	 * @param user
	 * @return
	 */
	Favori addFavori(Long idAnnonce, User user);

	/**
	 * @param user
	 * @return
	 */
	List<Favori> getFavoris(User user);
	/**
	 * @param idFavori
	 * @param user
	 * @return
	 */
	Favori deleteFavori(Long idFavori, User user);

}
