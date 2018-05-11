/**
 * 
 */
package org.bonnes.affaires.api;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bonnes.affaires.dao.AnnonceRepository;
import org.bonnes.affaires.dao.RoleDao;
import org.bonnes.affaires.dao.UserDao;
import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.Message;
import org.bonnes.affaires.entites.UserMessage;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.entites.utilisateurs.UserRole;
import org.bonnes.affaires.services.AlerteService;
import org.bonnes.affaires.services.AnnonceService;
import org.bonnes.affaires.services.FavoriService;
import org.bonnes.affaires.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author salioubah
 *
 */
@RestController
@RequestMapping("/get-user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
	private AnnonceService annonceService;
	
	@Autowired
	private AnnonceRepository annonceDao;
	
	@Autowired
	private FavoriService favoriService;
	
	@Autowired
	private AlerteService alerteService;
	
	@Autowired
    private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	private String nomPhoto;
	private String nomPhoto1;
	private String nomPhoto2;
	private String nomPhoto3;
	private String nomPhoto4;
	private int nbImages = 0;
	
	public static Long ID = 1L;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		System.out.println("erreur de connexion --------------- ");
		return "Erreur de connexion";
	}

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user", user);

        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePost(@ModelAttribute("user") User newUser, Model model) {
        User user = userService.findByUsername(newUser.getUsername());
        user.setUsername(newUser.getUsername());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        user.setPhone(newUser.getPhone());

        model.addAttribute("user", user);

        userService.saveUser(user);

        return "profile";
    }
    
    @RequestMapping("/users")
	public List<User> findAllUsers() {
		System.out.println("dans get-user/users");
		return null;
	}
	
	
	@RequestMapping(value ="/set-moderateur/{id}", method = RequestMethod.GET)
	public String setModerateur (@PathVariable("id") Long id){
		
		Set<UserRole> userRoles = new HashSet<>();
		User userToChange = userDao.findOne(id);
        userRoles.add(new UserRole(userToChange, roleDao.findByName("ROLE_MODERATEUR")));
		userService.setNouveauRole(userToChange, userRoles);
        System.out.println("mise a jour du role de l'utilisateur "+userToChange.getUsername()+" ");
		return "mise a jour du role de l'utilisateur: MODERATEUR";
	}
	
	@RequestMapping(value ="/set-admin/{id}", method = RequestMethod.GET)
	public String setAdmin (@PathVariable("id") Long id){
		
		Set<UserRole> userRoles = new HashSet<>();
		User userToChange = userDao.findOne(id);
        userRoles.add(new UserRole(userToChange, roleDao.findByName("ROLE_ADMIN")));
		userService.setNouveauRole(userToChange, userRoles);
        System.out.println("mise a jour du role de l'utilisateur "+userToChange.getUsername()+" ");
		return "mise a jour du role de l'utilisateur: ADMIN";
	}
	
	@RequestMapping(value ="/set-super-admin/{id}", method = RequestMethod.GET)
	public String setSuperAdmin (@PathVariable("id") Long id){
		
		Set<UserRole> userRoles = new HashSet<>();
		User userToChange = userDao.findOne(id);
        userRoles.add(new UserRole(userToChange, roleDao.findByName("ROLE_SUPER_ADMIN")));
		userService.setNouveauRole(userToChange, userRoles);
        System.out.println("mise a jour du role de l'utilisateur "+userToChange.getUsername()+" ");
		return "mise a jour du role de l'utilisateur: SUPER_ADMIN";
	}
		
	
	@RequestMapping(value="/add-alerte", method = RequestMethod.POST)
	public Alerte addAlerte(@RequestBody Alerte alerte){
		User user =userDao.findOne(ID); // qui sera recuperer par la suite par Principal de spring security
		System.out.println(alerte.getCategorie());
		return alerteService.addAlerte(alerte, user);
	}
	@RequestMapping(value="/add-favori/{id}", method = RequestMethod.POST)
	public Favori addFavori(@PathVariable("id") Long idAnnonce , @RequestBody String username){
		User user =userDao.findByUsername(username); 
		System.out.println("ajout à mes favoris ...");
		return favoriService.addFavori(idAnnonce, user);		
	}
	@RequestMapping(value="/valider-annonce/{id}", method= RequestMethod.POST)
	public Annonce validerAnnonce(@PathVariable("id") Long id, @RequestBody String username){
		System.out.println("valider annonce --- ");
		User user = userService.findByUsername(username);
		System.out.println(" l'email du user --- "+user.getEmail());
//		System.out.println("le USERNAME du principale dans valider annonce:  "+user.getUsername());
//		annonceService.valideAnnoce(id, user);
		return annonceService.valideAnnoce(id, user);
		
	}
	
	
	
	@RequestMapping(value="/mes-annonces", method= RequestMethod.POST)
	public List<Annonce> getMesAnnonces(@RequestBody String username){
		User user =userDao.findByUsername(username);
		return annonceService.getMesAnnonces(user);
		
	}
	
	@RequestMapping(value="/mes-infos", method= RequestMethod.POST)
	public User getMesInfos(@RequestBody String username){
		User user =userDao.findByUsername(username);
		System.out.println("-------------------------------");
		for (int i = 0; i < user.getAnnoncesValidees().size(); i++) {
			System.out.println("annonce validée: "+i);
		}
		return user;
		
	}
	@RequestMapping(value="/mes-activites", method= RequestMethod.POST)
	public User getMesActivites(@RequestBody String username){
		User user =userDao.findByUsername(username);
		List<Annonce> mesAValidees = (List<Annonce>) user.getAnnoncesValidees();
		return user;
		
	}
	
	@RequestMapping(value="/mes-annonces-validees", method= RequestMethod.POST)
	public List<Annonce> getMesAnnoncesValidees(@RequestBody String username){
		User user =userDao.findByUsername(username);
		System.out.println("mes annonces validee ........................."+user.getUsername());
		List<Annonce> mesAValidees = (List<Annonce>) user.getAnnoncesValidees();
		for (Annonce annonce : mesAValidees) {
			System.out.println("validee "+annonce.getTitre());
		}
		return mesAValidees;
		
	}
	
	@RequestMapping(value="/mes-annonces-invalidees", method= RequestMethod.POST)
	public List<Annonce> getMesAnnoncesInvalidees(@RequestBody String username){
		User user =userDao.findByUsername(username);
		List<Annonce> mesAInvalidees = (List<Annonce>) user.getAnnoncesInvalidees();
		return mesAInvalidees;
		
	}
	@RequestMapping(value="/annonces-signalees", method= RequestMethod.POST)
	public List<Annonce> annonceSignalees(@RequestBody String username){
		User user = userDao.findByUsername(username);
		System.out.println("le username dans mes annonces signalee: "+username);
		return (List<Annonce>) user.getAnnoncesSignalees();
		
	}
	
	public Map<Annonce, Message> mesMessages(){
		return null;
		
	}
	@RequestMapping(value="/mes-alertes", method= RequestMethod.POST)
	public List<Alerte> mesAlertes (@RequestBody String username){
		User user =userDao.findByUsername(username);
		return alerteService.getAlertes(user);
		
	}
	@RequestMapping(value="/mes-favoris", method= RequestMethod.POST)
	public List<Favori> mesFavoris(@RequestBody String username){
		User user =userDao.findByUsername(username);
		return favoriService.getFavoris(user);
		
	}
	
	@RequestMapping(value="/annonces-a-valider", method= RequestMethod.POST)
	public List<Annonce> getAnnoncesAvalider(){
		
		return annonceService.getAnnoncesAvalider();
	}
	
//	LES METHODES DE MODIFICATION =============================================================================================================
	@RequestMapping(value="/update-photo",  method = RequestMethod.POST )
	public List<Annonce> updatePhoto(HttpServletResponse response, HttpServletRequest request ){
//		System.out.println("dans addPhoto ....."+request.getParts());
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(it.next());
        String fileName = multipartFile.getOriginalFilename();
        nomPhoto = fileName;
        
        System.out.println("le nom de la photo: "+nomPhoto);
        
        String path = new File("src/main/resources/static/images").getAbsolutePath()+"\\"+fileName;
        try {
            multipartFile.transferTo(new File(path));
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }		
		return annonceService.getToutesOffresPubliees(); 
	}
	@RequestMapping(value="/update-photo1",  method = RequestMethod.POST )
	public List<Annonce> updatePhot1(HttpServletResponse response, HttpServletRequest request ){
		System.out.println("dans updatePhoto1 .....");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(it.next());
        String fileName = multipartFile.getOriginalFilename();
        nomPhoto1 = fileName;
        
        System.out.println("le nom de la photo1: "+nomPhoto1);
        
        String path = new File("src/main/resources/static/images").getAbsolutePath()+"\\"+fileName;
        try {
            multipartFile.transferTo(new File(path));
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }		
		return annonceService.getToutesOffresPubliees(); 
	}
	
	@RequestMapping(value="/update-photo2",  method = RequestMethod.POST )
	public List<Annonce> updatePhoto2(HttpServletResponse response, HttpServletRequest request ){
		System.out.println("dans updatePhoto2 .....");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(it.next());
        String fileName = multipartFile.getOriginalFilename();
        nomPhoto2 = fileName;
        
        System.out.println("le nom de la photo2: "+nomPhoto2);
        
        String path = new File("src/main/resources/static/images").getAbsolutePath()+"\\"+fileName;
        try {
            multipartFile.transferTo(new File(path));
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }		
		return annonceService.getToutesOffresPubliees(); 
	}
	
	
	@RequestMapping(value="/update-annonce/{username}" ,  method = RequestMethod.POST )
	public Annonce updateAnnonce(@PathVariable("username") String username, @RequestBody Annonce an){
		User user =userDao.findByUsername(username); 
		Long id = an.getId();
		Annonce annonce = annonceDao.findOne(id);
		System.out.println("dans la methode update annonce et les chemins pour les images sont: "+annonce.getCheminImage() + " et le titre est: "+annonce.getTitre()+" et lID est: "+annonce.getId());
//		annonce.getNomsPhotos().add(nomPhoto);
		if(nomPhoto != null){
			annonce.setCheminImage(nomPhoto); 
			this.nbImages ++;
		}
		if(nomPhoto1 != null){
			annonce.setCheminImage1(nomPhoto1); 
			this.nbImages ++;
		}
		if(nomPhoto2 != null){
			annonce.setCheminImage2(nomPhoto2); 
			this.nbImages ++;
		}
		if(nomPhoto3 != null){
			annonce.setCheminImage3(nomPhoto3); 
			this.nbImages ++;
		}
		if(nomPhoto4 != null){
			annonce.setCheminImage4(nomPhoto4); 
			this.nbImages ++;
		}
		if(annonceService.checkAnnonceValide(annonce)){
			annonce.setNbImages(nbImages);
			nomPhoto = null;
			nomPhoto1 = null;
			nomPhoto2 = null;
			nomPhoto3 = null;
			nomPhoto4 = null;
			
			return annonceService.updateAnnonce(annonce, user);
		}
		return annonce;
		
	}
	
	@RequestMapping(value="/update-alerte/{idAlerte}", method= RequestMethod.PUT)
	public Alerte updateAlerte(@PathVariable Long idAlerte, @RequestBody Alerte alerte ){
		User user =userDao.findOne(ID); // qui sera recuperer par la suite par Principal de spring security
		return alerteService.updateAlerte(idAlerte, alerte, user);
		
	}
//	@RequestMapping(value="/depublier-annonce/{id}", method= RequestMethod.GET)
//	public Annonce depubierAnnonce(@PathVariable Long id ){
//		User user =userDao.findOne(ID); // qui sera recuperer par la suite par Principal de spring security
//		return annonceService.depublierAnnonce(id, user);
//		
//	}
	@RequestMapping(value="/republier-annonce/{id}", method= RequestMethod.POST)
	public Annonce repubierAnnonce(@PathVariable Long id, @RequestBody String username ){
		User user =userDao.findByUsername(username);
		return annonceService.republierAnnonce(id, user);
		
	}
	
	
//	LES METHODES DE SUPPRESSION =============================================================================================================
	
	@RequestMapping(value="/delete-annonce/{idAnnonce}", method= RequestMethod.DELETE)
	public Annonce deleteAnnonce(@PathVariable("idAnnonce") Long idAnnonce){
		User user =userDao.findOne(ID); // qui sera recuperer par la suite par Principal de spring security
		return annonceService.deleteAnnonce(idAnnonce, user);
	}
	@RequestMapping(value="/delete-alerte/{idAlerte}", method= RequestMethod.DELETE)
	public Alerte deleteAlerte(@PathVariable("idAlerte")Long idAlerte){
		User user =userDao.findOne(ID); // qui sera recuperer par la suite par Principal de spring security
		return alerteService.deleteAlerte(idAlerte, user);
		
	}
	@RequestMapping(value="/delete-favori/{id}", method= RequestMethod.POST)
	public Favori deleteFavori(@PathVariable("id")Long id){
		User user =userDao.findOne(ID); // qui sera recuperer par la suite par Principal de spring security
		return favoriService.deleteFavori(id, user);
	}
	@RequestMapping(value="/signaler-annonce/{username}", method= RequestMethod.POST)
	public Annonce signalerAnnonce(@RequestBody Annonce annonce, @PathVariable ("username") String username){
		User user =userDao.findByUsername(username); 
		return annonceService.signalerAnnonce(annonce, user);
	}
	@RequestMapping(value="/send-message/{id}", method= RequestMethod.POST)
	public Annonce sendMessage(@RequestBody UserMessage userMessage, @PathVariable ("id") Long id){
		Annonce annonce =annonceDao.findOne(id);
		
		annonceService.sendMessage(userMessage.getUsername(), id, userMessage.getMessage());
		
		System.out.println("le message est: "+ userMessage.getMessage()+" id: "+id+" le username: "+ userMessage.getUsername());
		return annonceDao.findOne(id);
	}
	
	@RequestMapping(value="/invalider-annonce/{id}", method= RequestMethod.POST)
	public Annonce invaliderAnnonce(@RequestBody String username, @PathVariable ("id") Long id){
		Annonce annonce =annonceDao.findOne(id);
				
		annonceService.invaliderAnnonce(username, id);
		return annonceDao.findOne(id);
	}
	
	@RequestMapping(value="/depublier-annonce/{id}", method= RequestMethod.POST)
	public Annonce depublierAnnonce(@RequestBody String username, @PathVariable ("id") Long id){
		Annonce annonce =annonceDao.findOne(id);
		System.out.println("depublication de l'annonce dans le controlleur");
		annonceService.depublierAnnonce(username, id);
		return annonceDao.findOne(id);
	}
	


}

