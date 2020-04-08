package pl.coderslab.charity.User;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CurrentUser extends User {

    private final pl.coderslab.charity.User.User user;

    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.charity.User.User user) {
        super(username, password, authorities);
        this.user = user;
    }
}
