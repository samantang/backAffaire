/**
 * 
 */
package org.bonnes.affaires.servicesImpl;

import java.util.List;
import java.util.Set;

import org.bonnes.affaires.dao.RoleDao;
import org.bonnes.affaires.dao.UserDao;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.entites.utilisateurs.UserRole;
import org.bonnes.affaires.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author salioubah
 *
 */
@Service
@Transactional
public class UserServiceImpl 
implements UserService 
{
private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    private String userName = "";

	
	public void save(User user) {
        userDao.save(user);
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
    
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());
        
        if (localUser != null) {
            LOG.info("Ce nom d'utlisateur existe dejà . ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for (UserRole ur : userRoles) {
            	System.out.println("les roles du user " + ur);
                roleDao.save(ur.getRole());
            }
            System.out.println("le user à enregistrer " + user.getEmail());
            user.getUserRoles().addAll(userRoles);


            localUser = userDao.save(user);
        }

        return localUser;
    }
    
    public boolean checkUserExists(String username, String email){
        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }
    
    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

    public User saveUser (User user) {
        return userDao.save(user);
    }
    
    public List<User> findUserList() {
        return userDao.findAll();
    }

    public void enableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    public void disableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " est désactivé.");
    }

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#setModerateur(org.bonnes.affaires.entites.utilisateurs.User, java.util.Set)
	 */
	@Override
	public void setNouveauRole(User userToChange, Set<UserRole> userRoles) {
		// TODO Auto-generated method stub
		userToChange.setUserRoles(userRoles);
		userDao.save(userToChange);
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

