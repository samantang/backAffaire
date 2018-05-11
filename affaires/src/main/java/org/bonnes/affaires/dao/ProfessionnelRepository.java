/**
 * 
 */
package org.bonnes.affaires.dao;

import org.bonnes.affaires.entites.utilisateurs.Professionnel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author salioubah
 *
 */
public interface ProfessionnelRepository extends JpaRepository<Professionnel, Long> {

}
