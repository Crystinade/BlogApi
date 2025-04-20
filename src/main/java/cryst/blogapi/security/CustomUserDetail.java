package cryst.blogapi.security;

import cryst.blogapi.entities.User;
import cryst.blogapi.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetail implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  this.userRepo.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username: " + username));

        return user;
    }
}
