package com.school.students.config;

import com.school.students.model.AppUser;
import com.school.students.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Wojciech Wasilewski
 * @date Created on 31.07.2019
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsServiceImpl;
    private AppUserRepository appUserRepository;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, AppUserRepository appUserRepository) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/add").hasAnyRole("USER", "ADMIN")
                .antMatchers("/delete/**").hasRole("ADMIN")
                .and()
                .formLogin().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // create user in DB - OFF for now because I have created them in DB
//    @EventListener(ApplicationReadyEvent.class)
//    public void getUser() {
//        AppUser appUserUser = new AppUser(1l, "user", passwordEncoder().encode("pass"), "ROLE_USER");
//        AppUser appUserAdmin = new AppUser(1l, "admin", passwordEncoder().encode("hujas"), "ROLE_ADMIN");
//        appUserRepository.save(appUserUser);
//        appUserRepository.save(appUserAdmin);
//    }
}
