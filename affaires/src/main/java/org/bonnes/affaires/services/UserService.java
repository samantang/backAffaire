/**
 * 
 */
package org.bonnes.affaires.services;

import java.util.List;
import java.util.Set;

import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.entites.utilisateurs.UserRole;


/**
 * @author salioubah
 *
 */
public interface UserService {

	User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);
    
    void save (User user);
    
    User createUser(User user, Set<UserRole> userRoles);
    
    User saveUser (User user); 
    
    List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);

	/**
	 * @param userToChange
	 * @param userRoles
	 */
	void setNouveauRole(User userToChange, Set<UserRole> userRoles);
	
	public String getUserName();
	public void setUserName(String userName);

}
