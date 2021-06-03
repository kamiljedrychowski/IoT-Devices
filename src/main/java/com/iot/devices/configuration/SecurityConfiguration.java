package com.iot.devices.configuration;

import com.iot.devices.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceAuthorization userDetailsServiceAuthorization;

    @Autowired
    public SecurityConfiguration(UserDetailsServiceAuthorization userDetailsServiceAuthorization) {
        this.userDetailsServiceAuthorization = userDetailsServiceAuthorization;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceAuthorization);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
// TODO: Security Configuration
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/dashboard/**", "/user").hasAnyRole(UserRole.USER.name(), UserRole.ADMIN.name())
//                .antMatchers("/**").hasRole(UserRole.ADMIN.name())
//                .anyRequest().authenticated()
//                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/example").hasRole(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/user/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and().cors().disable()
                .csrf().disable();
    }

// TODO: CORS Configuration
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        final CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));
//        configuration.setAllowedMethods(List.of("HEAD",
//                "GET", "POST", "PUT", "DELETE", "PATCH"));
//        // setAllowCredentials(true) is important, otherwise:
//        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
//        configuration.setAllowCredentials(true);
//        // setAllowedHeaders is important! Without it, OPTIONS preflight request
//        // will fail with 403 Invalid CORS request
//        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}

