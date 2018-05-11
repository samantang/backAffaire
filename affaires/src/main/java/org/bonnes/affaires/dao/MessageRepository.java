/**
 * 
 */
package org.bonnes.affaires.dao;

import org.bonnes.affaires.entites.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author salioubah
 *
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

}
