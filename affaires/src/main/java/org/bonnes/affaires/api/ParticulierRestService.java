/**
 * 
 */
package org.bonnes.affaires.api;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.bonnes.affaires.dao.AnnonceRepository;
import org.bonnes.affaires.dao.RoleDao;
import org.bonnes.affaires.dao.UserDao;
import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.Message;
import org.bonnes.affaires.entites.RechercheModele;
import org.bonnes.affaires.entites.TypeAnnonce;
import org.bonnes.affaires.entites.utilisateurs.Particulier;
import org.bonnes.affaires.entites.utilisateurs.Professionnel;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.entites.utilisateurs.UserRole;
import org.bonnes.affaires.services.AlerteService;
import org.bonnes.affaires.services.AnnonceService;
import org.bonnes.affaires.services.FavoriService;
import org.bonnes.affaires.services.UserService;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author salioubah
 *
 */
@RestController
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class ParticulierRestService {
	
	@Autowired
	private AnnonceService annonceService;
	
	@Autowired
	private AnnonceRepository annonceDao;
	
	@Autowired
	private FavoriService favoriService;
	
	@Autowired
	private AlerteService alerteService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	public static Long ID = 1L; // uniquement pour contruire les methodes afin de recuperer l'utilisateur sur lequel 
								// on fait le test avant l'utilisation de SPRING SECURITY avec 'Principal'
	
	private String nomPhoto;
	private String nomPhoto1;
	private String nomPhoto2;
	private String nomPhoto3;
	private String nomPhoto4;
	private int nbImages = 0;
	
	
	
	@RequestMapping(value="/testRecupertionUser", method = RequestMethod.GET)
	public void testRecupertionUser(Principal principal){
		User utilisateur =userDao.findOne(1L);
		System.out.println("l'id de l'utilisateur en sesion est: "+utilisateur.getUserId());
	}
	
	@RequestMapping(value = "/inscription", method = RequestMethod.POST)
    public User signupPost(@RequestBody User user,  Model model) {
		System.out.println("le email est "+ user.getEmail());
		System.out.println("le type  est "+ user.getType());
//		String json =null;

				
//		si le nom d'utilisateur ou le mot de passe existe deja
        if(userService.checkUserExists(user.getUsername(), user.getEmail()))  {
        	
            if (userService.checkEmailExists(user.getEmail())) {
            	user.setErreur("Email existe en base de donnees");
            	System.out.println(" le email  existe");
            }

            if (userService.checkUsernameExists(user.getUsername())) {
            	user.setErreur("Nom Utilisateur existe en base de donnees");
            	System.out.println(" le nom   du user existe");
//            	obj.put("erreur", "le nom du user existe");            	
            }
            user.setErreur("Email ou nom utilisateur existe deja");
        }
            else {
////        	sinon, alors inscription de l'utilisateur en fonction de son profil ...
//        	
////        	si c'est un particuler
	        	if(user.getType().equals("particulier")){
	        		User userParticulier = new Particulier();
	        		userParticulier.setPassword(user.getPassword());
	        		userParticulier.setUsername(user.getUsername());
	        		userParticulier.setEmail(user.getEmail());
	        		
	        		Set<UserRole> userRoles = new HashSet<>();
	                userRoles.add(new UserRole(userParticulier, roleDao.findByName("ROLE_PARTICULIER")));
	
	               userService.createUser(userParticulier, userRoles);
	               System.out.println("insertion du particuler en base de donnee");
	//               return json;
	        	}
	////        	si c'est pour la creation d'un professionnel
	        	if(user.getType().equals("professionnel")){
	        		User userProfessionnel = new Professionnel();
	        		userProfessionnel.setPassword(user.getPassword());
	        		userProfessionnel.setUsername(user.getUsername());
	        		userProfessionnel.setEmail(user.getEmail());
	        		
	        		Set<UserRole> userRoles = new HashSet<>();
	                userRoles.add(new UserRole(userProfessionnel, roleDao.findByName("ROLE_PROFESSIONNEL")));
	
	               userService.createUser(userProfessionnel, userRoles);
	               System.out.println("insertion du Professionnel en base de donnee");
	//               return json;
	        	}

        }
        return user;
    }
	
	
//	les methodes de consultation ==================================================================================================================
	@RequestMapping(value="/get-principal", method= RequestMethod.GET)
	public String getPrincipal(Principal principal){
//		User user = userService.findByUsername(principal.getName());
//		System.out.println("le USERNAME du principale:  "+user.getUsername());
		
		return Jwts.builder().setSubject(userService.getUserName()).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		
	}
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User findByUserName(@RequestBody String userName) {
		System.out.println("le userName qui doit aider à recuper l'utilisateur connecté " + userName);

		return userService.findByUsername(userName);
	}
	
	@RequestMapping(value="/get-nb-nnonces", method= RequestMethod.GET)
	public Long getNombreAnnonces(){
		return annonceService.getNombreToutesAnnoncesPubliees();
		
	}
	
	@RequestMapping(value="/get-annonces", method= RequestMethod.GET)
	public List<Annonce> getAnnonces(){
		return annonceService.getToutesAnnoncesPubliees();
		
	}
	@RequestMapping(value="/annonces/page/{page}", method= RequestMethod.GET)
	public List<Annonce> changerPagination(@PathVariable int page){
//		Long nbTotalA = getNombreAnnonces();
		System.out.println("la page qui doit être chargé est : "+page);
		List<Annonce> recup = annonceService.changerPagination(page);
		for(Annonce a : recup){
			System.out.println("le titre recupéré: "+a.getTitre());
		}
		return annonceService.changerPagination(page);
		
	}
	@RequestMapping(value="/getAnnonce/{id}", method= RequestMethod.POST)
	public Annonce getAnnonce(@PathVariable Long id){
		
//		System.out.println("l'utilisateur connecté est: --- "+ s);
//		System.out.println("id --- "+id);
		return annonceDao.findOne(id);
		
	}
	
	
	@RequestMapping(value="/offres", method= RequestMethod.GET)
	public List<Annonce> getToutesLesOffres(){
		return annonceService.getToutesOffresPubliees();
		
	}
	@RequestMapping(value="/offres-particuliers", method= RequestMethod.GET)
	public List<Annonce> getOffresParticuliers(){
		return annonceService.getOffresParticuliersPubliees();
		
	}
	@RequestMapping(value="/offres-professionnels", method= RequestMethod.GET)
	public List<Annonce> getOffresProfessionnels(){
		return annonceService.getOffresProfessionnelsPubliees();
		
	}
	@RequestMapping(value="/demandes", method= RequestMethod.GET)
	public List<Annonce> getToutesLesDeamandes(){
		return annonceService.getToutesDemandesPubliees();
		
	}
	@RequestMapping(value="/demandes-particuliers", method= RequestMethod.GET)
	public List<Annonce> getDemandesParticuliers(){
		return annonceService.getDemandesParticuliersPubliees();
		
	}
	@RequestMapping(value="/demandes-professionnels", method= RequestMethod.GET)
	public List<Annonce> getDemandesProfessionnels(){
		return annonceService.getDemandesProfessionnelsPubliees();
		
	}
//	Ajout annonce ---------------------------------------------------------------------
	
	@RequestMapping(value="/add-photo",  method = RequestMethod.POST )
	public List<Annonce> addPhoto(HttpServletResponse response, HttpServletRequest request ){
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
	@RequestMapping(value="/add-photo1",  method = RequestMethod.POST )
	public List<Annonce> addPhot1(HttpServletResponse response, HttpServletRequest request ){
		System.out.println("dans addPhoto1 .....");
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
	
	@RequestMapping(value="/add-photo2",  method = RequestMethod.POST )
	public List<Annonce> addPhoto2(HttpServletResponse response, HttpServletRequest request ){
		System.out.println("dans addPhoto2 .....");
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
	
	@RequestMapping(value="/add-photo3",  method = RequestMethod.POST )
	public List<Annonce> addPhoto3(HttpServletResponse response, HttpServletRequest request ){
		System.out.println("dans addPhoto3 .....");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(it.next());
        String fileName = multipartFile.getOriginalFilename();
        nomPhoto3 = fileName;
        
        String path = new File("src/main/resources/static/images").getAbsolutePath()+"\\"+fileName;
        try {
            multipartFile.transferTo(new File(path));
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }	
		return annonceService.getToutesOffresPubliees(); 
	}
	
	@RequestMapping(value="/add-photo4",  method = RequestMethod.POST )
	public List<Annonce> addPhoto4(HttpServletResponse response, HttpServletRequest request ){
		System.out.println("dans addPhoto4 .....");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Iterator<String> it = multipartRequest.getFileNames();
        MultipartFile multipartFile = multipartRequest.getFile(it.next());
        String fileName = multipartFile.getOriginalFilename();
        nomPhoto4 = fileName;
        
        String path = new File("src/main/resources/static/images").getAbsolutePath()+"\\"+fileName;
        try {
            multipartFile.transferTo(new File(path));
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }	
        
		return annonceService.getToutesOffresPubliees(); 
	}
	
	@RequestMapping(value="/add-annonce/{username}" ,  method = RequestMethod.POST )
	public Annonce addAnnonce(@PathVariable ("username") String username, @RequestBody Annonce annonce, Principal principal){
		System.out.println("dans addAnnonce .....");
		User user = userDao.findByUsername(username); 
		
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
			return annonceService.saveAnnonce(annonce, user.getUserId());
		}
		return annonce;
		
	}
	
	@RequestMapping(value="/chercher-annonces", method= RequestMethod.POST)
	public List<Annonce> chercherAnnonces(@RequestBody RechercheModele rechercheModele){
		List<Annonce> annonces = annonceService.rechercheAnnonces(rechercheModele);
		return annonces;
		
	}
	
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
	public Annonce updateAnnonce(@PathVariable("username") String username, @RequestBody Annonce annonce){
		User user =userDao.findByUsername(username); 
//		vu que dans cette methode l'annonce recue de la part du front de comporte pas les images, alors on recupère l'annonce dans la BDD
//		pour ensuite recupérer ses images afin de changer celles qui auront été changées
		
		Long id = annonce.getId();
		Annonce annonceBDD = annonceDao.findOne(id);
		annonce.setCheminImage(annonceBDD.getCheminImage());
		annonce.setCheminImage1(annonceBDD.getCheminImage1());
		annonce.setCheminImage2(annonceBDD.getCheminImage2());
		System.out.println("dans la methode update annonce et les chemins pour les images sont: "+annonce.getCheminImage() + " et le titre est: "+annonce.getTitre()+" et lID est: "+annonce.getId());
//		annonce.getNomsPhotos().add(nomPhoto);
		if(nomPhoto != null){
			annonce.setCheminImage(nomPhoto); 
//			this.nbImages ++;
		}
		if(nomPhoto1 != null){
			annonce.setCheminImage1(nomPhoto1); 
//			this.nbImages ++;
		}
		if(nomPhoto2 != null){
			annonce.setCheminImage2(nomPhoto2); 
//			this.nbImages ++;
		}
		if(nomPhoto3 != null){
			annonce.setCheminImage3(nomPhoto3); 
//			this.nbImages ++;
		}
		if(nomPhoto4 != null){
			annonce.setCheminImage4(nomPhoto4); 
//			this.nbImages ++;
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

	
	
}
