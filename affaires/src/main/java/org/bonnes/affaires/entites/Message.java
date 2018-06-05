/**
 * 
 */
package org.bonnes.affaires.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bonnes.affaires.entites.utilisateurs.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author salioubah
 *
 */
@Entity
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7785093708908828330L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
//	private String titre;
	private String message;
//	private Annonce annonce;
	
//	@Temporal(TemporalType.DATE)
//	private Date date;
	@OneToOne
	private User auteurMessage;
	
//	@ManyToOne
//	@JoinColumn(name="messages_envoyes")
//	private User messages_envoyes;
//	
//	@ManyToOne
//	@JoinColumn(name="messages_recus")
//	private User messages_recus;
	private String dateString;
//	
//	@ManyToOne
//	@JoinColumn(name="messages")
//	private Conversation messages;
//	
//	@ElementCollection
//	@JsonIgnore
//	private Collection<String> messagesEnvoyes;
//	@ElementCollection
//	@JsonIgnore
//	private Collection<String> messagesRecus;



//	public void setMessages(Conversation messages) {
//		this.messages = messages;
//	}
//
//
//
//
//	public Collection<String> getMessagesEnvoyes() {
//		return messagesEnvoyes;
//	}
//
//
//
//
//	public void setMessagesEnvoyes(Collection<String> messagesEnvoyes) {
//		this.messagesEnvoyes = messagesEnvoyes;
//	}
//
//
//
//
//	public Collection<String> getMessagesRecus() {
//		return messagesRecus;
//	}
//
//
//
//
//	public void setMessagesRecus(Collection<String> messagesRecus) {
//		this.messagesRecus = messagesRecus;
//	}
//
//
	public String getDateString() {
		return dateString;
	}


	

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}




	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




//	public String getTitre() {
//		return titre;
//	}
//
//
//
//
//	public void setTitre(String titre) {
//		this.titre = titre;
//	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}




	public User getAuteurMessage() {
		return auteurMessage;
	}




	public void setAuteurMessage(User auteurMessage) {
		this.auteurMessage = auteurMessage;
	}




//	public Annonce getAnnonce() {
//		return annonce;
//	}
//
//
//
//
//	public void setAnnonce(Annonce annonce) {
//		this.annonce = annonce;
//	}
//
//
//
//
//	public Date getDate() {
//		return date;
//	}
//
//
//
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//
//
//
//	public User getMessages_envoyes() {
//		return messages_envoyes;
//	}
//
//
//
//
//	public void setMessages_envoyes(User messages_envoyes) {
//		this.messages_envoyes = messages_envoyes;
//	}
//
//
//
//
//	public User getMessages_recus() {
//		return messages_recus;
//	}
//
//
//
//
//	public void setMessages_recus(User messages_recus) {
//		this.messages_recus = messages_recus;
//	}
	
		
}
