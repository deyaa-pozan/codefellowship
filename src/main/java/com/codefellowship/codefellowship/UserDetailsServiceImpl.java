package com.codefellowship.codefellowship;

import com.codefellowship.codefellowship.models.ApplicationUser;
import com.codefellowship.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        ApplicationUser user = applicationUserRepository.findByUsername(username);

        if(user == null){
            System.out.println(username+" not exist");
            throw new UsernameNotFoundException(username+" not exist");
        }
        else {
            System.out.println( username + " exist");
            return user;
        }
    }
}