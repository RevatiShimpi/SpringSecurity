package com.example.SpringSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    UserRepository userrepo;
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException{
        Optional<Users> u=userrepo.findByUserName(s);
        if(u==null){
            throw new UsernameNotFoundException("This username or password does not exist");
        }
        return new MyUserDetails(u.get());
    }
}
