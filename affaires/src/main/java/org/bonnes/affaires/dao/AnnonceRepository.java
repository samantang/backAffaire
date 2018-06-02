/**
 * 
 */
package org.bonnes.affaires.dao;

import java.util.List;

import org.bonnes.affaires.entites.Annonce;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author salioubah
 *
 */
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

	public String parametreOffre = "OFFRE";
	public String parametreDemande = "DEMANDE";
	public String paramNull = "null";

	/**
	 * @return
	 */
	@Query("select a from Annonce a where a.etatPublication = 'publie' order by a.dateStringDepot desc")
	List<Annonce> getNombreToutesAnnoncesPubliees();
	/**
	 * @return
	 */
	@Query("select a from Annonce a where a.publie = true order by a.dateStringDepot desc")
	List<Annonce> getToutesAnnoncesPubliees(Pageable pageble);
	
	/**
	 * @return
	 */
	@Query("select a from Annonce a where a.publie = false")
	List<Annonce> getAnnoncesAvalider();
	
	/**
	 * @return
	 */
	@Query("select a from Annonce a where a.publie = true and typeAnnonce like"+"'"+parametreOffre+"%'  ")
	List<Annonce> getToutesOffresPubliees();

	/**
	 * @return
	 */
	@Query("select a from Annonce a where a.publie = true and typeAnnonce like"+"'"+parametreDemande+"%'  ")
	List<Annonce> getToutesDemandesPubliees();
	
	/**
	 * @return
	 */
	@Query("select a from Annonce a where a.publie = true and annonceSignalee = true  ")
	List<Annonce> getToutesAnnoncesSignalees();
	
	/**
	 * @param categorie
	 * @param region
	 * @param ville
	 * @return
	 */
	@Query("select a from Annonce a where a.categorie = :categorie and a.region =:region and (:ville = '' or a.ville = :ville)")
//	@Query("select a from Annonce a where a.categorie = :categorie and a.region = :region and ville like :ville% ")
	List<Annonce> findByCategorie(
			@Param("categorie")String categorie,
			@Param("region") String region,
			@Param("ville") String ville);
	/**
	 * @param categorie
	 * @return
	 */
	List<Annonce> findByCategorie(String categorie);

	
	/**
	 * @param categorie
	 * @param natureAnnonce
	 * @param region
	 * @return
	 */
//	@Query("select a from Annonce a where a.categorie = :categorie and a.natureAnnonce = :natureAnnonce and (:region = '' or a.region = :region) ")
	@Query("select a from Annonce a where a.categorie = :categorie and (:natureAnnonce = 'null' or a.natureAnnonce = :natureAnnonce) and (:marque = 'null' or a.marque = :marque) and (:carburant = 'null' or a.carburant = :carburant) and (:boiteDeVitesse = 'null' or a.boiteDeVitesse = :boiteDeVitesse) and (:prixMin = 0 or a.prix >= :prixMin) and"
			+ "(:prixMax = 0 or a.prix <= :prixMax) and (:surfaceMetreCarreMin = 0 or a.surfaceMetreCarre >= :surfaceMetreCarreMin) and (:surfaceMetreCarreMax = 0 or a.surfaceMetreCarre <= :surfaceMetreCarreMax) and"
			+ "(:nbPiecesMin = 0 or a.nbPieces >= :nbPiecesMin) and (:nbPiecesMax = 0 or a.nbPieces <= :nbPiecesMax)and (:anneeModeleMin = 0 or a.anneeModele >= :anneeModeleMin) and (:anneeModeleMax = 0 or a.anneeModele <= :anneeModeleMax) and (:cylindre = 0 or a.cylindre = :cylindre) and"
			+ "(:longueurMin = 0 or a.longueur >= :longueurMin) and (:longueurMax = 0 or a.longueur <= :longueurMax) and (:largeurMin = 0 or a.largeur >= :largeurMin) and (:largeurMax = 0 or a.largeur <= :largeurMax)")
	List<Annonce> findByCategorieAndNatureA(@Param("categorie")String categorie, @Param("natureAnnonce")String natureAnnonce, @Param("marque") String marque , @Param("carburant")String carburant, @Param("boiteDeVitesse")String boiteDeVitesse, @Param("prixMin")int prixMin, @Param("prixMax")int prixMax,
	@Param("surfaceMetreCarreMin")int surfaceMetreCarreMin, @Param("surfaceMetreCarreMax")int surfaceMetreCarreMax, @Param("nbPiecesMin")int nbPiecesMin, @Param("nbPiecesMax")int nbPiecesMax,
	@Param("anneeModeleMin")int anneeModeleMin, @Param("anneeModeleMax")int anneeModeleMax, @Param("cylindre")int cylindre, @Param("longueurMin")int longueurMin, @Param("longueurMax")int longueurMax, @Param("largeurMin")int largeurMin, @Param("largeurMax")int largeurMax);
	
	
	
}
