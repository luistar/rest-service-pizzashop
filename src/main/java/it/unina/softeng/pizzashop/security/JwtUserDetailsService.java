package it.unina.softeng.pizzashop.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import it.unina.softeng.pizzashop.data.PizzaShopUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    // password is saved as Bcrypt-hash (password is the same as the username in this case)
    List<PizzaShopUser> users = new ArrayList<>(Arrays.asList(
       new PizzaShopUser("luigi","$2a$12$y//AxP7v4BY.tw1FMbCGP.TzsH9Lz9yMD3pozrTZFic7jNZW/IcK6", "ROLE_ADMIN"),
            new PizzaShopUser("marco","$2a$12$YFiF/K4x1ZxZ9w5GuMoV8.KI4BPrkDSh/znWN2j6zg2RwDI1UFmp2", "ROLE_USER")
    ));

    @Override
    public UserDetails loadUserByUsername(final String username) {
        PizzaShopUser user = users.stream().filter(u -> u.getUsername().equals(username)).findFirst().orElseThrow(
                () -> new UsernameNotFoundException("User " + username + " not found")
        );
        return new User(username, user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}