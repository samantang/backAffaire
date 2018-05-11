/**
 * 
 */
package org.bonnes.affaires.entites;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.bonnes.affaires.entites.utilisateurs.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author salioubah
 *
 */
@Entity
public class Conversation {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	private String dateString;
	
	@ManyToOne
	@JoinColumn(name="mes_conversations_vendeur")
	private User mes_conversations_vendeur;
	
	@ManyToOne
	@JoinColumn(name="mes_conversations_acheteur")
	private User mes_conversations_acheteur;
	
	@OneToOne
	private Annonce annonce;
	
	@OneToMany(mappedBy="messages")
	@JsonIgnore
	private Collection<Message>  messages;
	
	
	
	
	
	
	public User getMes_conversations_vendeur() {
		return mes_conversations_vendeur;
	}
	public void setMes_conversations_vendeur(User mes_conversations_vendeur) {
		this.mes_conversations_vendeur = mes_conversations_vendeur;
	}
	public User getMes_conversations_acheteur() {
		return mes_conversations_acheteur;
	}
	public void setMes_conversations_acheteur(User mes_conversations_acheteur) {
		this.mes_conversations_acheteur = mes_conversations_acheteur;
	}
	public Collection<Message> getMessages() {
		return messages;
	}
	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	
	public Annonce getAnnonce() {
		return annonce;
	}
	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
	
	public Conversation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
