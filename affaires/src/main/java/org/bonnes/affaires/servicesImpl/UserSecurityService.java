/**
 * 
 */
package org.bonnes.affaires.servicesImpl;

import org.bonnes.affaires.dao.UserDao;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author salioubah
 *
 */
@Service
public class UserSecurityService
implements UserDetailsService 
{

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (null == user) {
            LOG.warn("nom d'ulisateur non trouv ", username);
            throw new UsernameNotFoundException("Nom utlisateur " + username + " non trouvee");
        }
        userService.setUserName(username);
        System.out.println("le nom d'utilisateur: "+ username);
        return user;
    }
}

