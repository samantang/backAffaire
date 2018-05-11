/**
 * 
 */
package org.bonnes.affaires.dao;

import java.util.List;

import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.utilisateurs.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author salioubah
 *
 */
public interface ParticulierRepository extends JpaRepository<Particulier, Long> {

//	/**
//	 * @return
//	 */
//	List<Annonce> getAnnonces();
//	/**
//	 * @return
//	 */
//	List getAnnonceActif();
//
//	/**
//	 * @return
//	 */
//	List getAnnonceInactif();

	

	
	
}
