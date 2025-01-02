package com.example.SpringSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SpringConfiguration {
    //////CONFIGURE SPRING SECURITY AUTHENTICATION

    @Autowired
    DataSource datasource;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
       //set your configuration on auth object
        //auth.inMemoryAuthentication().withUser("reva").password(getPasswordEncoder().encode("reva")).roles("USER");
        auth.jdbcAuthentication()
                .dataSource(datasource);


        //if want to make Default Schema and populate here itself
//                .withDefaultSchema()
//                .withUser(
//                        User.withUsername("user")
//                                .password("pass")
//                                .roles("USER")
//                )
//                .withUser(
//                    User.withUsername("admin")
//                            .password("pass")
//                            .roles("ADMIN")
//                );
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //authorize the user  (works in form of chain)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").hasAuthority("USERS")
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/signin")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );


        return http.build();
    }
}
