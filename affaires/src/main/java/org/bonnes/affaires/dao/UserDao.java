/**
 * 
 */
package org.bonnes.affaires.dao;

import java.util.List;

import org.bonnes.affaires.entites.utilisateurs.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author salioubah
 *
 */
public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAll();
}
