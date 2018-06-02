/**
 * 
 */
package org.bonnes.affaires.servicesImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.bonnes.affaires.dao.RoleDao;
import org.bonnes.affaires.dao.UserDao;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.utilisateurs.Role;
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
public class UserServiceImpl implements UserService {
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
    /* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#updatePassword(java.lang.String, java.lang.String)
	 */
	@Override
	public void updatePassword(String password, String username) {
		// TODO Auto-generated method stub
		User user = userDao.findByUsername(username);
		String encryptedPassword = passwordEncoder.encode(password);
		System.out.println("le password  " + user.getPassword());
		System.out.println("le passwordentype: " + encryptedPassword);
//		user.setPassword(encryptedPassword);
//		userDao.save(user);
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

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#checkUserModerateurRole(java.lang.String)
	 */
	@Override
	public boolean checkUserModerateurRole(String usernameToSetModerateur) {
		boolean b = true;
		Set<UserRole> userRoles = new HashSet<>();
		User userToChange = userDao.findByUsername(usernameToSetModerateur);
//		recuperation des roles du user
		List<UserRole> listUserRole= new ArrayList<>();
		List<Role> listRoles= new ArrayList<>();
       userRoles = userToChange.getUserRoles();
       Iterator it = userRoles.iterator();
	     while(it.hasNext()){
	    	 listUserRole.add((UserRole) it.next());
	     }
       for(UserRole u: listUserRole){
    	   listRoles.add(u.getRole());
       }
       for(Role r: listRoles){
    	   System.out.println("un role: "+r.getName());
    	   if(r.getName().equals("ROLE_MODERATEUR")){
    		   b = false;
    		   break;
    	   }
       }
		return b;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#checkUserAdminrRole(java.lang.String)
	 */
	@Override
	public boolean checkUserAdminrRole(String usernameToSetModerateur) {
		boolean b = true;
		Set<UserRole> userRoles = new HashSet<>();
		User userToChange = userDao.findByUsername(usernameToSetModerateur);
//		recuperation des roles du user
		List<UserRole> listUserRole= new ArrayList<>();
		List<Role> listRoles= new ArrayList<>();
       userRoles = userToChange.getUserRoles();
       Iterator it = userRoles.iterator();
	     while(it.hasNext()){
	    	 listUserRole.add((UserRole) it.next());
	     }
       for(UserRole u: listUserRole){
    	   listRoles.add(u.getRole());
       }
       for(Role r: listRoles){
    	   System.out.println("un role: "+r.getName());
    	   if(r.getName().equals("ROLE_ADMIN")){
    		   b = false;
    		   break;
    	   }
       }
		return b;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#checkUserSuperAdminrRole(java.lang.String)
	 */
	@Override
	public boolean checkUserSuperAdminrRole(String usernameToSetModerateur) {
		boolean b = true;
		Set<UserRole> userRoles = new HashSet<>();
		User userToChange = userDao.findByUsername(usernameToSetModerateur);
//		recuperation des roles du user
		List<UserRole> listUserRole= new ArrayList<>();
		List<Role> listRoles= new ArrayList<>();
       userRoles = userToChange.getUserRoles();
       Iterator it = userRoles.iterator();
	     while(it.hasNext()){
	    	 listUserRole.add((UserRole) it.next());
	     }
       for(UserRole u: listUserRole){
    	   listRoles.add(u.getRole());
       }
       for(Role r: listRoles){
    	   System.out.println("un role: "+r.getName());
    	   if(r.getName().equals("ROLE_SUPER_ADMIN")){
    		   b = false;
    		   break;
    	   }
       }
		return b;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#compteActif(java.lang.Long)
	 */
	@Override
	public boolean compteActif(Long id) {
		boolean b = false;
		User user = userDao.findOne(id);
		if(user.isEnabled()){
			b = true;
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#suspendreCompte(java.lang.Long, java.lang.String)
	 */
	@Override
	public void suspendreCompte(Long id, String username) {
		User user = userDao.findOne(id);
		user.setEnabled(false);
		userDao.save(user);
		
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#compteInActif(java.lang.Long)
	 */
	@Override
	public boolean compteInActif(Long id) {
		boolean b = false;
		User user = userDao.findOne(id);
		if(!user.isEnabled()){
			b = true;
		}
		return b;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#activerCompte(java.lang.Long, java.lang.String)
	 */
	@Override
	public void activerCompte(Long id, String username) {
		User user = userDao.findOne(id);
		user.setEnabled(true);
		userDao.save(user);
		
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#retireRole(org.bonnes.affaires.entites.utilisateurs.User, java.util.Set)
	 */
	@Override
	public void retireRole(User userToChange, Set<UserRole> userRoles) {
		userToChange.getUserRoles().remove(userRoles);
		userDao.save(userToChange);
		
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#retireRoleModerateur(org.bonnes.affaires.entites.utilisateurs.User, java.util.Set)
	 */
	@Override
	public void retireRoleModerateur(User userToChange, Set<UserRole> userRoles) {
		userToChange.getUserRoles().remove(userRoles);
		userDao.save(userToChange);
		
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#retireRoleAdmin(org.bonnes.affaires.entites.utilisateurs.User, java.util.Set)
	 */
	@Override
	public void retireRoleAdmin(User userToChange, Set<UserRole> userRoles) {
		userToChange.getUserRoles().remove(userRoles);
		userDao.save(userToChange);
		
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.UserService#retireRoleSuperAdmin(org.bonnes.affaires.entites.utilisateurs.User, java.util.Set)
	 */
	@Override
	public void retireRoleSuperAdmin(User userToChange, Set<UserRole> userRoles) {
		userToChange.getUserRoles().remove(userRoles);
		userDao.save(userToChange);
		
	}	
}

