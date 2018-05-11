package org.bonnes.affaires;

import org.bonnes.affaires.config.JwtFilter;
import org.bonnes.affaires.dao.AnnonceRepository;
import org.bonnes.affaires.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AffairesApplication implements CommandLineRunner {
	@Autowired
	private AnnonceRepository annonceRepository;
	@Autowired
	private UserDao userDao;

	public static void main(String[] args) {
		SpringApplication.run(AffairesApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/get-user/*");
		return registrationBean;
	}

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
//		User user = userDao.findOne(1L);
//		annonceRepository.save(new Annonce("bonneAnnonce", 100, 50, "la description de l'annonce", Categorie.MAISON, true, true, "Demande", TypeAnnonce.DEMANDE, user));
	}
}
