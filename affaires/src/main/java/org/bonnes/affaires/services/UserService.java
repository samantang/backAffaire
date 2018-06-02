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

	/**
	 * @param password
	 * @param username
	 */
	void updatePassword(String password, String username);

	/**
	 * @param usernameToSetModerateur
	 * @return
	 */
	boolean checkUserModerateurRole(String usernameToSetModerateur);

	/**
	 * @param usernameToSetModerateur
	 * @return
	 */
	boolean checkUserAdminrRole(String usernameToSetModerateur);

	/**
	 * @param usernameToSetModerateur
	 * @return
	 */
	boolean checkUserSuperAdminrRole(String usernameToSetModerateur);

	/**
	 * @param id
	 * @return
	 */
	boolean compteActif(Long id);

	/**
	 * @param id
	 * @param username
	 */
	void suspendreCompte(Long id, String username);

	/**
	 * @param id
	 * @return
	 */
	boolean compteInActif(Long id);

	/**
	 * @param id
	 * @param username
	 */
	void activerCompte(Long id, String username);

	/**
	 * @param userToChange
	 * @param userRoles
	 */
	void retireRole(User userToChange, Set<UserRole> userRoles);

	/**
	 * @param userToChange
	 * @param userRoles
	 */
	void retireRoleModerateur(User userToChange, Set<UserRole> userRoles);

	/**
	 * @param userToChange
	 * @param userRoles
	 */
	void retireRoleAdmin(User userToChange, Set<UserRole> userRoles);

	/**
	 * @param userToChange
	 * @param userRoles
	 */
	void retireRoleSuperAdmin(User userToChange, Set<UserRole> userRoles);

}
