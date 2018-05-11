/**
 * 
 */
package org.bonnes.affaires.entites.utilisateurs;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author salioubah
 *
 */
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
