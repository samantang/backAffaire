/**
 * 
 */
package org.bonnes.affaires.servicesImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.bonnes.affaires.dao.AnnonceRepository;
import org.bonnes.affaires.dao.FavoriRepository;
import org.bonnes.affaires.dao.UserDao;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.TypeAnnonce;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.services.FavoriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author salioubah
 *
 */
@Transactional
@Service
public class FavoriServiceImpl implements FavoriService{
	
	@Autowired
	private FavoriRepository favoriRepository;
	
	@Autowired
	private AnnonceRepository annonceRepository;
	
	@Autowired
	private UserDao userDao;

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.FavoriService#addFavori(java.lang.Long, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public Favori addFavori(Long idAnnonce, User user) {
		Annonce annonce = new Annonce();
		Favori favori = new Favori();
		if(annonceValide(idAnnonce)){
			
//			DateFormat df = new SimpleDateFormat("yyy/MM/dd hh:mm", Locale.FRANCE);
//			Date now = new Date();
//			String dateString = df.format(now);
			
			annonce = annonceRepository.findOne(idAnnonce);
//			if(annonce.getNatureAnnonce().equals("demande")){
//				favori = new Favori(annonce, null, user, new Date());
//				favori = favoriRepository.saveAndFlush(favori);
//				user.getDemandeFavorites().add(favori);
//				userDao.save(user);
//			}else{
//				favori = new Favori(annonce, null, user, new Date());
//				favori = favoriRepository.saveAndFlush(favori);
//				user.getOffresFavorites().add(favori);
//				userDao.save(user);
//			}
			favori = new Favori(annonce,  new Date(), user);
			favori = favoriRepository.saveAndFlush(favori);
			user.getMesFavoris().add(favori);
			userDao.save(user);
			
		}
		return favori;
	}

	/**
	 * @param idAnnonce
	 * @return
	 */
	private boolean annonceValide(Long idAnnonce) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.FavoriService#getFavoris(org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public List<Favori> getFavoris(User user) {
//		List<Favori> demandes = (List) user.getDemandeFavorites();
//		List<Favori> offres = (List) user.getOffresFavorites();
		return (List<Favori>) user.getMesFavoris();
	}
	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AlerteService#deleteFavori(java.lang.Long, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public Favori deleteFavori(Long idFavori, User user) {
		Favori favori = new Favori();
		if(favoriExiste(idFavori) && favoriAppartientAuser(idFavori, user)){
			System.out.println("l'id de l'annonce est "+idFavori);
			favori =  favoriRepository.findOne(idFavori);
			user.getMesFavoris().remove(favori);
			favoriRepository.delete(favori);
		}
		return favori;
	}

	/**
	 * @param idFavori
	 * @param user
	 * @return
	 */
	private boolean favoriAppartientAuser(Long idFavori, User user) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param idFavori
	 * @return
	 */
	private boolean favoriExiste(Long idFavori) {
		// TODO Auto-generated method stub
		return true;
	}

}
