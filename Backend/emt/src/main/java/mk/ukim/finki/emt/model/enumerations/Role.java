package mk.ukim.finki.emt.model.enumerations;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_HOST;

    @Override
    public String getAuthority() {  
        return name();
    }
}
