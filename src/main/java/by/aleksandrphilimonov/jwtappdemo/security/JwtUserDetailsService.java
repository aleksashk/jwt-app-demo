package by.aleksandrphilimonov.jwtappdemo.security;

import by.aleksandrphilimonov.jwtappdemo.model.User;
import by.aleksandrphilimonov.jwtappdemo.security.jwt.JwtUser;
import by.aleksandrphilimonov.jwtappdemo.security.jwt.JwtUserFactory;
import by.aleksandrphilimonov.jwtappdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("In loadByUsername - user with username: {} successfully loaded", username);

        return jwtUser;
    }
}
