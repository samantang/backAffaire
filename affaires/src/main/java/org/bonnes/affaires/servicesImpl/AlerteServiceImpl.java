/**
 * 
 */
package org.bonnes.affaires.servicesImpl;

import java.util.List;

import org.bonnes.affaires.dao.AlerteRepository;
import org.bonnes.affaires.dao.UserDao;
import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.utilisateurs.User;
import org.bonnes.affaires.services.AlerteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author salioubah
 *
 */
@Service
@Transactional
public class AlerteServiceImpl implements AlerteService {

	@Autowired
	private AlerteRepository alerteRepository;
	
	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AlerteService#addAlerte(org.bonnes.affaires.entites.Alerte, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public Alerte addAlerte(Alerte alerte, User user) {
		Alerte alerteLocale = new Alerte();
		if(alerteValide(alerte)){
			alerte.setMes_alertes(user);
			alerteLocale = alerteRepository.saveAndFlush(alerte);
			user.getMesAlertes().add(alerteLocale);
			userDao.save(user);
		}
		return alerteLocale;

	}

	/**
	 * @param alerte
	 * @return
	 */
	private boolean alerteValide(Alerte alerte) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AlerteService#getAlertes(org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public List<Alerte> getAlertes(User user) {
		return (List<Alerte>) user.getMesAlertes();
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AlerteService#updateAlerte(java.lang.Long, org.bonnes.affaires.entites.Alerte, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public Alerte updateAlerte(Long idAlerte, Alerte alerte, User user) {
		if(alerteExite(idAlerte) && alerteValide(alerte) && alerteAppartientAuser(idAlerte, user) ){
			alerte.setId(idAlerte);
			alerteRepository.save(alerte);
		}
		return alerte;
	}

	/**
	 * @param idAlerte
	 * @param user
	 * @return
	 */
	private boolean alerteAppartientAuser(Long idAlerte, User user) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param idAlerte
	 * @return
	 */
	private boolean alerteExite(Long idAlerte) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.bonnes.affaires.services.AlerteService#deleteAlerte(java.lang.Long, org.bonnes.affaires.entites.utilisateurs.User)
	 */
	@Override
	public Alerte deleteAlerte(Long idAlerte, User user) {
		Alerte alerte = new Alerte();
		if(alerteExiste(idAlerte) && AlerteAppartientAuser(idAlerte, user)){
			System.out.println("l'id de l'annonce est "+idAlerte);
			alerte =  alerteRepository.findOne(idAlerte);
			alerteRepository.delete(alerte);
		}
		return alerte;
	}

	/**
	 * @param idAlerte
	 * @param user
	 * @return
	 */
	private boolean AlerteAppartientAuser(Long idAlerte, User user) {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * @param idAlerte
	 * @return
	 */
	private boolean alerteExiste(Long idAlerte) {
		// TODO Auto-generated method stub
		return true;
	}


}
