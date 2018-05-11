/**
 * 
 */
package org.bonnes.affaires.entites;

/**
 * @author salioubah
 *
 */
public class UserMessage {
	
	private String username;
	private String message;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public UserMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserMessage(String username, String message) {
		super();
		this.username = username;
		this.message = message;
	}
	
	
	
	

}
