package fr.univ_poitiers.dptinfo.aaw.mybankweb.service;


import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.User;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Objects.requireNonNull(username);
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user;
    }

    public Optional<User> findById(Integer id){
        return userRepository.findById(id);
    }

}