/**
 * 
 */
package org.bonnes.affaires.dao;

import org.bonnes.affaires.entites.utilisateurs.Particulier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author salioubah
 *
 */
public interface ModerateurRepository extends JpaRepository<Particulier, Long> {

}
