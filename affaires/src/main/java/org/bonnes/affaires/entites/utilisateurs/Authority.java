/**
 * 
 */
package org.bonnes.affaires.entites.utilisateurs;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author salioubah
 *
 */
//@JsonIgnoreProperties
public class Authority  implements GrantedAuthority
{

	private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }


}
