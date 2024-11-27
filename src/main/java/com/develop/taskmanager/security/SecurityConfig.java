package com.develop.taskmanager.security;

import com.develop.taskmanager.model.Constants;
import com.develop.taskmanager.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthoritiesAuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf( csrf -> csrf.disable());
        http.authorizeHttpRequests(authz ->
            authz.anyRequest()/*requestMatchers(Constants.ROUTE_GET_USERS)*/.authenticated()); // require authentication for get-users
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return provider;
    }
//    @Bean
//    public UserService userManagementService() {
//
//        UserDetails user1 = User.withUsername("nkc").password("nkc@1234").roles("ADMIN").build();
//        UserDetails user2 = User.withUsername("tj").password("tj@1234").roles("USER").build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        // To ignore certain
//        // paths...
//        return (web -> web.ignoring().anyRequest().requestMatchers("/h2/console/**"));
//    }
}
