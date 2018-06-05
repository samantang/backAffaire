/**
 * 
 */
package org.bonnes.affaires.entites.utilisateurs;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.Alerte;
import org.bonnes.affaires.entites.Annonce;
import org.bonnes.affaires.entites.Conversation;
import org.bonnes.affaires.entites.Favori;
import org.bonnes.affaires.entites.Message;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author salioubah
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_user", discriminatorType=DiscriminatorType.STRING, length=20)
@JsonIgnoreProperties
public class User implements UserDetails
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false, updatable = false)
    private Long userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String ville;
    private String phone;
    private boolean enabled = true;
    private String type;
    @JsonIgnore
    private byte [] photo;
    
	@Temporal(TemporalType.DATE)
	private Date dateInscription;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy="user_annoncesPostees")
    @JsonIgnore
	private Collection <Annonce> annoncesPostees;
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy="user_annoncesPosteesEtValidees")
	@JsonIgnore
	private Collection <Annonce> annoncesPosteesEtValidees;
	
	@OneToMany(mappedBy="user_annoncesPartagees")
	@JsonIgnore
	private Collection<Annonce> annoncesPartagees;
	
	@OneToMany(mappedBy="user_annoncesSignalees")
	@JsonIgnore
	private Collection<Annonce> annoncesSignalees;
    
    @OneToMany(mappedBy="user_annoncesValidees")
    @JsonIgnore
    private Collection<Annonce> annoncesValidees; // par un admin ou moderateur
    
    @OneToMany(mappedBy="user_annoncesFavorites")
    @JsonIgnore
    private Collection<Annonce> annoncesFavorites;
	
	@OneToMany(mappedBy="mes_alertes")
	@JsonIgnore
	private Collection<Alerte> mesAlertes;
	
	@OneToMany(mappedBy="offres_favorites")
	@JsonIgnore
	private Collection<Favori> offresFavorites;
	
	@OneToMany(mappedBy="demandes_favorites")
	@JsonIgnore
	private Collection<Favori> demandeFavorites;
	
//	@OneToMany(mappedBy="messages_envoyes")
//	@JsonIgnore
//	private Collection <Message> messagesEnvoyes;
////													vu que les messages concernent des annonces, il va faloir les changer
//	@OneToMany(mappedBy="messages_recus")
//	@JsonIgnore
//	private Collection <Message> messages_recus;
	
	@ElementCollection
	@JsonIgnore
	private Map<Annonce, Message> mesMesagesEnvoyes;
	
	@ElementCollection
	@JsonIgnore
	private Map<Annonce, Message> mesMesagesRecus;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserRole> userRoles = new HashSet<>();
    
    @OneToMany(mappedBy="mes_favoris")
	@JsonIgnore
	private Collection<Favori> mesFavoris;
    
    private String erreur;
    
//    @OneToMany(mappedBy="mes_conversations_vendeur")
//	@JsonIgnore
//    private Collection<Conversation> MesConversationsVendeur;
//    
//    @OneToMany(mappedBy="mes_conversations_acheteur")
//	@JsonIgnore
//    private Collection<Conversation> conversationsAcheteur;
    
    @OneToMany(mappedBy="user_annoncesInvalidees")
    @JsonIgnore
    private Collection<Annonce> annoncesInvalidees;
    
    @OneToMany(mappedBy="user_annoncesDepubliees")
    @JsonIgnore
    private Collection<Annonce> annoncesDepubliees;
    
	private String dateInscriptionString;
	private String success;
//	@OneToMany(mappedBy="user_mesAnnonceValideesAdmin")
	@ElementCollection
    @JsonIgnore
	private Collection<Annonce> mesAnnonceValideesAdmin;
	@ElementCollection
    @JsonIgnore
	private Collection<Annonce> mesAnnonceInValideesAdmin;
	@ElementCollection
    @JsonIgnore
	private Collection<Conversation> mesConversations;
		
    
    
	
    public Collection<Conversation> getMesConversations() {
		return mesConversations;
	}

	public void setMesConversations(Collection<Conversation> mesConversations) {
		this.mesConversations = mesConversations;
	}

	public Collection<Annonce> getMesAnnonceInValideesAdmin() {
		return mesAnnonceInValideesAdmin;
	}

	public void setMesAnnonceInValideesAdmin(Collection<Annonce> mesAnnonceInValideesAdmin) {
		this.mesAnnonceInValideesAdmin = mesAnnonceInValideesAdmin;
	}

	public Collection<Annonce> getMesAnnonceValideesAdmin() {
		return mesAnnonceValideesAdmin;
	}

	public void setMesAnnonceValideesAdmin(Collection<Annonce> mesAnnonceValideesAdmin) {
		this.mesAnnonceValideesAdmin = mesAnnonceValideesAdmin;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getDateInscriptionString() {
		return dateInscriptionString;
	}

	public void setDateInscriptionString(String dateInscriptionString) {
		this.dateInscriptionString = dateInscriptionString;
	}

	public Collection<Annonce> getAnnoncesDepubliees() {
		return annoncesDepubliees;
	}

	public void setAnnoncesDepubliees(Collection<Annonce> annoncesDepubliees) {
		this.annoncesDepubliees = annoncesDepubliees;
	}

//	public Collection<Conversation> getMesConversationsVendeur() {
//		return MesConversationsVendeur;
//	}
//
//	public void setMesConversationsVendeur(Collection<Conversation> mesConversationsVendeur) {
//		MesConversationsVendeur = mesConversationsVendeur;
//	}
//
//	public Collection<Conversation> getConversationsAcheteur() {
//		return conversationsAcheteur;
//	}
//
//	public void setConversationsAcheteur(Collection<Conversation> conversationsAcheteur) {
//		this.conversationsAcheteur = conversationsAcheteur;
//	}

	public Collection<Annonce> getAnnoncesInvalidees() {
		return annoncesInvalidees;
	}

	public void setAnnoncesInvalidees(Collection<Annonce> annoncesInvalidees) {
		this.annoncesInvalidees = annoncesInvalidees;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Collection<Annonce> getAnnoncesPartagees() {
		return annoncesPartagees;
	}

	public void setAnnoncesPartagees(Collection<Annonce> annoncesPartagees) {
		this.annoncesPartagees = annoncesPartagees;
	}

	public Collection<Annonce> getAnnoncesSignalees() {
		return annoncesSignalees;
	}

	public void setAnnoncesSignalees(Collection<Annonce> annoncesSignalees) {
		this.annoncesSignalees = annoncesSignalees;
	}

	public Collection<Annonce> getAnnoncesFavorites() {
		return annoncesFavorites;
	}

	public void setAnnoncesFavorites(Collection<Annonce> annoncesFavorites) {
		this.annoncesFavorites = annoncesFavorites;
	}

	public Collection<Alerte> getMesAlertes() {
		return mesAlertes;
	}

	public void setMesAlertes(Collection<Alerte> mesAlertes) {
		this.mesAlertes = mesAlertes;
	}

	public Collection<Favori> getOffresFavorites() {
		return offresFavorites;
	}

	public void setOffresFavorites(Collection<Favori> offresFavorites) {
		this.offresFavorites = offresFavorites;
	}

	public Collection<Favori> getDemandeFavorites() {
		return demandeFavorites;
	}

	public void setDemandeFavorites(Collection<Favori> demandeFavorites) {
		this.demandeFavorites = demandeFavorites;
	}

//	public Collection<Message> getMessagesEnvoyes() {
//		return messagesEnvoyes;
//	}
//
//	public void setMessagesEnvoyes(Collection<Message> messagesEnvoyes) {
//		this.messagesEnvoyes = messagesEnvoyes;
//	}
//
//	public Collection<Message> getMessages_recus() {
//		return messages_recus;
//	}
//
//	public void setMessages_recus(Collection<Message> messages_recus) {
//		this.messages_recus = messages_recus;
//	}

	public boolean isEnabled() {
		return enabled;
	}

	public Collection<Annonce> getAnnoncesValidees() {
		return annoncesValidees;
	}

	public void setAnnoncesValidees(Collection<Annonce> annoncesValidees) {
		this.annoncesValidees = annoncesValidees;
	}

	public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userRoles=" + userRoles +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Collection<Annonce> getAnnoncesPostees() {
		return annoncesPostees;
	}

	public void setAnnoncesPostees(Collection<Annonce> annoncesPostees) {
		this.annoncesPostees = annoncesPostees;
	}

	public Collection<Annonce> getAnnoncesPosteesEtValidees() {
		return annoncesPosteesEtValidees;
	}

	public void setAnnoncesPosteesEtValidees(Collection<Annonce> annoncesPosteesEtValidees) {
		this.annoncesPosteesEtValidees = annoncesPosteesEtValidees;
	}

	public Map<Annonce, Message> getMesMesagesEnvoyes() {
		return mesMesagesEnvoyes;
	}

	public void setMesMesagesEnvoyes(Map<Annonce, Message> mesMesagesEnvoyes) {
		this.mesMesagesEnvoyes = mesMesagesEnvoyes;
	}

	public Map<Annonce, Message> getMesMesagesRecus() {
		return mesMesagesRecus;
	}

	public void setMesMesagesRecus(Map<Annonce, Message> mesMesagesRecus) {
		this.mesMesagesRecus = mesMesagesRecus;
	}

	public Collection<Favori> getMesFavoris() {
		return mesFavoris;
	}

	public void setMesFavoris(Collection<Favori> mesFavoris) {
		this.mesFavoris = mesFavoris;
	}
    
	
}
