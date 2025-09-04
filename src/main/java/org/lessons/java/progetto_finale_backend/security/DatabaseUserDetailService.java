package org.lessons.java.progetto_finale_backend.security;

import java.util.Optional;
import org.lessons.java.progetto_finale_backend.model.User;
import org.lessons.java.progetto_finale_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Non trovo nessuno con questo username" + username);
        }
        return new DatabaseUserDetails(user.get());
    }

}
