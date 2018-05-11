/**
 * 
 */
package org.bonnes.affaires.controlleurs;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.bonnes.affaires.dao.RoleDao;
import org.bonnes.affaires.entites.utilisateurs.Particulier;
import org.bonnes.affaires.entites.utilisateurs.Professionnel;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.entites.utilisateurs.UserRole;
import org.bonnes.affaires.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author salioubah
 *
 */
@Controller
public class HomeControlleur {

	@Autowired
	private UserService userService;
	
	@Autowired
    private RoleDao roleDao;
		
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
    public String index() {
        return "index";
    }
	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "signup";
    }
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPost(@ModelAttribute("user") User user,  Model model) {

        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {

            if (userService.checkEmailExists(user.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (userService.checkUsernameExists(user.getUsername())) {
                model.addAttribute("usernameExists", true);
            }

            return "signup";
        } else {
        	 Set<UserRole> userRoles = new HashSet<>();
             userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);

            return "redirect:/";
        }
    }
	@RequestMapping("/userFront")
	public String userFront(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
//        PrimaryAccount primaryAccount = user.getPrimaryAccount();
//        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("primaryAccount", null);
        model.addAttribute("savingsAccount", null);

        return "userFront";
    }
	@RequestMapping(value="/testRecupertionUse", method = RequestMethod.GET)
	public String testRecupertionUse(Principal principal){
		User utilisateur =userService.findByUsername(principal.getName());
		System.out.println("l'id de l'utilisateur en sesion est: "+utilisateur.getUserId());
		return "/";
	}
}
