/**
 * 
 */
package org.bonnes.affaires.dao;

import org.bonnes.affaires.entites.utilisateurs.Role;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author salioubah
 *
 */
public interface RoleDao extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}

