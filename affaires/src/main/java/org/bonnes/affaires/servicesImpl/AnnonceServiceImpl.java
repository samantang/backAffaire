/**
 * 
 */
package org.bonnes.affaires.servicesImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bonnes.affaires.dao.AnnonceRepository;
import org.bonnes.affaires.dao.ConversationRepository;
import org.bonnes.affaires.dao.MessageRepository;
import org.bonnes.affaires.dao.UserDao;
import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Categorie;
import org.bonnes.affaires.entites.Conversation;
import org.bonnes.affaires.entites.Message;
import org.bonnes.affaires.entites.RechercheModele;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.services.AnnonceService;
import org.bonnes.affaires.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author salioubah
 *
 */
@Service
@Transactional
public class AnnonceServiceImpl implements AnnonceService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AnnonceRepository annonceRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ConversationRepository conversationRepository;
	
	@Autowired
	private UserService userService;
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	public AnnonceServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public boolean checkAnnonceValide(Annonce annonce) {
		
		return true;
	}

	/**
	 * @param prix
	 * @return
	 */
	private boolean checkPrixValide(int prix) {
		if(prix != 0){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * @param description
	 * @return
	 */
	private boolean checkDescriptionValide(String description) {
		if(description != null){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * @param nom
	 * @return
	 */
	private boolean checkNomValide(String nom) {
		if(nom != null){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * @param categorie
	 * @return
	 */
	private boolean checkCategorieValide(Categorie categorie) {
		if(categorie != null){
			return true;
		}else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#saveAnnonce(org.bonnes.affaires.entites.Annonce, java.lang.Long)
	 */
	@Override
	public Annonce saveAnnonce(Annonce annonce, Long userId) {
		 
		User user = userDao.findOne(userId);
		
		DateFormat df = new SimpleDateFormat("dd/MM/YYYY hh:mm", Locale.FRANCE);
		Date now = new Date();
		String dateString = df.format(now);
		
		annonce.setDateDepot(now);
		annonce.setDateStringDepot(dateString);
		annonce.setEtatPublication("nonPublie");
		annonce.setUser_annoncesPostees(user);
		Annonce annonceSaved = annonceRepository.saveAndFlush(annonce);
		user.getAnnoncesPostees().add(annonceSaved);
		userDao.save(user);
		System.out.println("user qui a posté l'annonce: "+ annonceSaved.getUser_annoncesPostees().getEmail());
		
		return annonceSaved;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getToutesAnnoncesPubliees()
	 */
	@Override
	public List<Annonce> getToutesAnnoncesPubliees() {
		return annonceRepository.getToutesAnnoncesPubliees(new PageRequest(0, 10));
	}
	
	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#invaliderAnnonce(java.lang.Long, java.lang.Long)
	 */
	@Override
	public void invaliderAnnonce(String username, Long id) {
		User user = userDao.findByUsername(username);
		Annonce annonce = annonceRepository.findOne(id);
		
		annonce.setUser_annoncesInvalidees(user);
		annonce.setInvalidee(true);
		annonce.setPublie(false);
		annonce.setEtatPublication("false");
		Annonce annonceSaved = annonceRepository.saveAndFlush(annonce);
		user.getMesAnnonceInValideesAdmin().add(annonceSaved);
		userDao.save(user);
		
		
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#valideAnnoce(java.lang.Long, java.lang.String)
	 */
	@Override
	public Annonce valideAnnoce(Long id, String username) {
		Annonce annonce = annonceRepository.findOne(id);
		User userAnnonce = annonce.getUser_annoncesPostees();
		User user = userDao.findByUsername(username);
//		si l'annonce est nulle, c'est que l'id fournit n'existe pas
		if(annonce.equals(null)){
			annonce = new Annonce();
			return annonce;
		}
//		sinon on valide l'annonce et l'ajoute dans les annonces validées par l'utilisateur
		annonce.setPublie(true);
		annonce.setEtatPublication("publie");
		annonce.setUser_annoncesPosteesEtValidees(user);
		Annonce annonceSaved = annonceRepository.saveAndFlush(annonce);
				
//		ajout de l'annonce dans la liste des annonces active de son propietaire, et suppression dans sa liste des annonces a valider
		userAnnonce.getAnnoncesPosteesEtValidees().add(annonceSaved);
		userAnnonce.getAnnoncesPostees().remove(annonceSaved);
		userDao.save(userAnnonce);
	
		
//		user.getAnnoncesValidees().add(annonceSaved);
//		userDao.save(user);
//		em.merge(user);

		return annonce;
	}
	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#addAnnonceDansListAnnoncesActivesUser(java.lang.Long)
	 */
	@Override
	public void addAnnonceDansListAnnoncesActivesUser(Long id) {
		Annonce annonce = annonceRepository.findOne(id);
		User userAnnonce = annonce.getUser_annoncesPostees();
		userAnnonce.getAnnoncesPosteesEtValidees().add(annonce);
		userAnnonce.getAnnoncesPostees().remove(annonce);
		userDao.saveAndFlush(userAnnonce);
		System.out.println("email du userAnnonce: "+userAnnonce.getEmail());
		
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#addAnnonceDansListAnnoncesValideesUser(java.lang.Long, java.lang.String)
	 */
	@Override
	public void addAnnonceDansListAnnoncesValideesUser(Long id, String username) {
		Annonce annonce = annonceRepository.findOne(id);
		User u = userDao.findByUsername(username);
		User user = userDao.findOne(u.getUserId());
		user.getMesAnnonceValideesAdmin().add(annonce);
//		annonceRepository.saveAndFlush(annonce);
		userDao.save(user);
		System.out.println("l'annonce est: " + annonce.getTitre() + "et le user est " + user.getUsername());
		for (Annonce an : user.getMesAnnonceValideesAdmin()) {
			System.out.println("titre de l'annonce validee:" + an.getTitre());
		}
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getToutesOffresPubliees()
	 */
	@Override 
	public List<Annonce> getToutesOffresPubliees() {
		return annonceRepository.getToutesOffresPubliees();
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getOffresParticuliersPubliees()
	 */
	@Override
	public List<Annonce> getOffresParticuliersPubliees() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getOffresProfessionnelsPubliees()
	 */
	@Override
	public List<Annonce> getOffresProfessionnelsPubliees() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getToutesDemandesPubliees()
	 */
	@Override
	public List<Annonce> getToutesDemandesPubliees() {
		return annonceRepository.getToutesDemandesPubliees();
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getDemandesParticuliersPubliees()
	 */
	@Override
	public List<Annonce> getDemandesParticuliersPubliees() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getDemandesProfessionnelsPubliees()
	 */
	@Override
	public List<Annonce> getDemandesProfessionnelsPubliees() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#updateAnnonce(java.lang.Long, org.bonnes.affaires.entites.Annonce, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public Annonce updateAnnonce(Long idAnnonce, Annonce annonce, User user) {
		Annonce localAnnonce = new Annonce();
		if(annonExite(idAnnonce) && annonceValide(annonce) && annonceAppartientAuser(idAnnonce, user)){
			annonce.setId(idAnnonce);
			localAnnonce = annonceRepository.save(annonce);
		}
		return localAnnonce;
	}

	/**
	 * @param annonce
	 * @return
	 */
	private boolean annonceValide(Annonce annonce) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param idAnnonce
	 * @param user
	 * @return
	 */
	private boolean annonceAppartientAuser(Long idAnnonce, User user) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param idAnnonce
	 * @return
	 */
	private boolean annonExite(Long idAnnonce) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#deleteAnnonce(java.lang.Long, java.lang.String)
	 */
	@Override
	public Annonce deleteAnnonce(Long idAnnonce, String username) {
		Annonce annonce = new Annonce();
		User user = userDao.findByUsername(username);
		if(annonceExiste(idAnnonce) && AnnonceAppartientAuser(idAnnonce, user)){
			annonce =  annonceRepository.findOne(idAnnonce);
//			user.getanno
//			System.out.println(annonce.getTitre());
			annonceRepository.delete(annonce);
		}
		return annonce;
	}

	/**
	 * @param idAnnonce
	 * @param user
	 * @return
	 */
	private boolean AnnonceAppartientAuser(Long idAnnonce, User user) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param idAnnonce
	 * @return
	 */
	private boolean annonceExiste(Long idAnnonce) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getAnnoncesAvalider()
	 */
	@Override
	public List<Annonce> getAnnoncesAvalider() {
		// TODO Auto-generated method stub
		return annonceRepository.getAnnoncesAvalider();
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#signalerAnnonce(java.lang.Long, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public Annonce signalerAnnonce(Annonce a, User user) {
		Annonce annonce = annonceRepository.findOne(a.getId());
		annonce.setAnnonceSignalee(true);
		annonce.setMotifSignalement(a.getMotifSignalement());
		annonce.setMessageSignalement(a.getMessageSignalement());
		annonce.setUser_annoncesSignalees(user);
		annonce = annonceRepository.saveAndFlush(annonce);
		
		user.getAnnoncesSignalees().add(annonce);
		userDao.save(user);
		return annonce;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getAnnoncesSignalees()
	 */
	@Override
	public List<Annonce> getAnnoncesSignalees() {
		// TODO Auto-generated method stub
		return annonceRepository.getToutesAnnoncesSignalees();
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getMesAnnonces(org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public List<Annonce> getMesAnnonces(User user) {
		List<Annonce> annoncesPosteesEtValidees = (List<Annonce>) user.getAnnoncesPosteesEtValidees();
		List<Annonce> annoncesPostees = (List<Annonce>) user.getAnnoncesPostees();
		List<Annonce> toutesMesAnnonces = new ArrayList<Annonce>();
		toutesMesAnnonces.addAll(annoncesPostees);
//		System.out.println("la taille de toutes mes annonces postees et validees: "+annoncesPosteesEtValidees.size());
//		System.out.println("la taille de toutes mes annonces postees: "+annoncesPostees.size());
		return (List<Annonce>) toutesMesAnnonces;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#getNombreToutesAnnoncesPubliees()
	 */
	@Override
	public Long getNombreToutesAnnoncesPubliees() {
		// TODO Auto-generated method stub
		return (long) annonceRepository.getNombreToutesAnnoncesPubliees().size();
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#changerPagination(java.lang.Long)
	 */
	@Override
	public List<Annonce> changerPagination(int id) {
		// TODO Auto-generated method stub
		int page = id-1 ;
		
		return annonceRepository.getToutesAnnoncesPubliees(new PageRequest(page, 10));
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#depublierAnnonce(java.lang.Long, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	
	@Override
	public Annonce depublierAnnonce(String username, Long idAnnonce) {
		Annonce annonce = annonceRepository.findOne(idAnnonce);
		User user = userDao.findByUsername(username);		
		
		annonce.setUser_annoncesDepubliees(user);
		annonce.setPublie(false);
		annonce.setEtatPublication("depublie");
		annonce.setDepubliee(true);
		Annonce annonceModifie = annonceRepository.saveAndFlush(annonce);
		
		user.getAnnoncesDepubliees().add(annonce);
		
		annonceRepository.save(annonce);
		userDao.save(user);
		
		return annonceModifie;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#republierAnnonce(java.lang.Long, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public Annonce republierAnnonce(Long id, User user) {
		Annonce annonce = annonceRepository.findOne(id);
		annonce.setPublie(true);
		annonce.setEtatPublication("publie");
		Annonce annonceModifie = annonceRepository.saveAndFlush(annonce);
		return annonceModifie;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#updateAnnonce(org.bonnes.affaires.entites.Annonce, java.lang.Long)
	 */
	@Override
	public Annonce updateAnnonce(Annonce annonce, User user) {
		Annonce localAnnonce = new Annonce();
		
		DateFormat df = new SimpleDateFormat("yyy/MM/dd hh:mm", Locale.FRANCE);
		Date now = new Date();
		String dateString = df.format(now);
		if(annonExite(annonce.getId()) && annonceValide(annonce) && annonceAppartientAuser(annonce.getId(), user)){
			annonce.setDateStringModification(dateString);
			annonce.setPublie(false);
			annonce.setEtatPublication("true");
			annonce.setUser_annoncesPostees(user);
			
			localAnnonce = annonceRepository.saveAndFlush(annonce); 
		}
		return localAnnonce;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#sendMessage(java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public void sendMessage(String usernameLogged, Long idAnnonce, String mess) {
		User userLogged = userDao.findByUsername(usernameLogged);
		Annonce annonce = annonceRepository.findOne(idAnnonce);
		User userAnnonce = annonce.getUser_annoncesPosteesEtValidees();
		
//		creation d'une conversation et de d'un message pour chacunes des deux personnes
		
//		Conversation conversation = new Conversation();
//		conversation.setAnnonce(annonce);
//		Conversation conversationSaved = conversationRepository.save(conversation);
//		
//		
//		Message messagerieRecu = new Message();
//		messagerieRecu.getMessagesRecus().add(mess);
//		
//		Message messagerieEnvoye= new Message();
//		messagerieEnvoye.getMessagesEnvoyes().add(mess);
		
		
//		conversation.set
		
	}

	

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#rechercheAnnonces(org.bonnes.affaires.entites.RechercheModele)
	 */
	@Override
	public List<Annonce> rechercheAnnonces(RechercheModele rechercheModele) {
		List<Annonce> annonces = new ArrayList<Annonce>();

		
			annonces = annonceRepository.findByCategorieAndNatureA(rechercheModele.getCategorie(), rechercheModele.getNatureAnnonce(), rechercheModele.getMarque(),rechercheModele.getCarburant(), rechercheModele.getBoiteDeVitesse(), rechercheModele.getPrixMin(), rechercheModele.getPrixMax(),
					rechercheModele.getSurfaceMetreCarreMin(), rechercheModele.getSurfaceMetreCarreMax(), rechercheModele.getNbPiecesMin(), rechercheModele.getNbPiecesMax(),
					rechercheModele.getAnneeModeleMin(), rechercheModele.getAnneeModeleMax(), rechercheModele.getCylindre(), rechercheModele.getLongueurMin(), rechercheModele.getLongueurMax(), rechercheModele.getLargeurMin(), rechercheModele.getLargeurMax());
			
		
//		System.out.println(rechercheModele.getCategorie()+""+ rechercheModele.getNatureAnnonce()+""+rechercheModele.getPrixMin()+""+ rechercheModele.getPrixMax()+""+
//				rechercheModele.getSurfaceMetreCarreMin()+""+ rechercheModele.getSurfaceMetreCarreMax()+""+ rechercheModele.getNbPiecesMin()+""+ rechercheModele.getNbPiecesMax()+""+
//				rechercheModele.getAnneeModeleMin()+""+ rechercheModele.getAnneeModeleMax()+"  ======  "+ rechercheModele.getCarburant()+""+ rechercheModele.getBoiteDeVitesse()+""+
//				rechercheModele.getMarque()+""+ rechercheModele.getCylindre()+""+ rechercheModele.getLongueurMin()+""+ rechercheModele.getLongueurMax()+""+ rechercheModele.getLargeurMin()+""+ rechercheModele.getLargeurMax());
//		
//			annonces = annonceRepository.findByCategorie(rechercheModele.getCategorie(), rechercheModele.getNatureAnnonce(),rechercheModele.getPrixMin(), rechercheModele.getPrixMax(),
//					rechercheModele.getSurfaceMetreCarreMin(), rechercheModele.getSurfaceMetreCarreMax(), rechercheModele.getNbPiecesMin(), rechercheModele.getNbPiecesMax(),
//					rechercheModele.getAnneeModeleMin(), rechercheModele.getAnneeModeleMax(), rechercheModele.getCarburant(), rechercheModele.getBoiteDeVitesse(),
//					rechercheModele.getMarque(), rechercheModele.getCylindre(), rechercheModele.getLongueurMin(), rechercheModele.getLongueurMax(), rechercheModele.getLargeurMin(), rechercheModele.getLargeurMax());
//			
			
//			System.out.println("le prix Min est : "+rechercheModele.getPrixMin());
//			for (Annonce annonce : annonces) {
//				System.out.println(annonce.getRegion()+ "-----------------"+annonce.getVille());
//			}
			

		return annonces;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AnnonceService#addNbVuesAnnonce(java.lang.Long)
	 */
	@Override
	public Annonce addNbVuesAnnonce(Long id) {
		// TODO Auto-generated method stub
		Annonce annonce = annonceRepository.findOne(id);
		annonce.setNombreDeVues(annonce.getNombreDeVues() + 1);
		annonceRepository.save(annonce);
		return annonce;
	}
}
