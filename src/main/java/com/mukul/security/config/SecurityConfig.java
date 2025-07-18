package com.mukul.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mukul.security.Service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JWTFilter jwtFilter;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(customizer  -> customizer.disable()) // it will disable csrf and csrf token won't be needed for CUD.
                    .authorizeHttpRequests(request -> request
                        .requestMatchers("/register","/login")
                        .permitAll()
                        .anyRequest().authenticated())
                    .httpBasic(Customizer.withDefaults()) // this will enable rest api tools to access the app
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();

        // for csrf and all the methods defined above which has lambda function , if we dont't use lambda function 
        // Customizer<CsrfConfigurer<HttpSecurity>> customizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
        //     @Override
        //     public void customize(CsrfConfigurer<HttpSecurity> customizer) {
        //         customizer.disable();
        //     }
        // };
        // httpSecurity.csrf(customizer);
        // this much we have to write in order to run a single method , so that's why we are using lambda function


        // httpSecurity.formLogin(Customizer.withDefaults());  // this will create a form for login from browser
        // it will create a problem as we have made the session stateless it will keep showing the login page again and again
        
    }

    // @Bean
    // public UserDetailsManager userDetailsManager(){
    //     PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //     UserDetails u1 = User.builder()
    //                          .username("Mukul")
    //                          .password("Koli")
    //                          .authorities("USER")
    //                          .passwordEncoder(passwordEncoder::encode).build();
    //     UserDetails u2 = User.builder()
    //                          .username("Jrm")
    //                          .password("HEHE")
    //                          .authorities("ADMIN")
    //                          .passwordEncoder(passwordEncoder::encode).build();
    //     return new InMemoryUserDetailsManager(u1,u2);
    // }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12); 
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder); // this will encode the password sent by the user
        return provider;                             // and it will match it with the db
    }
    

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
