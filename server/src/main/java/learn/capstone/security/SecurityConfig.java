package learn.capstone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationConfiguration config) throws Exception {

        http.csrf().disable();
        http.cors();

        http.authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/create_account").permitAll()
                .antMatchers("/refresh_token").authenticated()
                .antMatchers(HttpMethod.GET, "/api/travelgenie", "/api/travelgenie/*", "/api/travelgenie/wish", "/api/travelgenie/wish/*", "/api/travelgenie/wish/user","/api/travelgenie/wish/user/*", "/api/travelgenie/entertainment/city/*", "/api/travelgenie/city/*").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.POST, "/api/travelgenie/wish", "/api/travelgenie/wish/match").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.PUT, "/api/travelgenie/user", "/api/travelgenie/user/updateAccount/*").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.DELETE, "/api/travelgenie/wish/*").hasAnyAuthority("USER")
                .antMatchers(HttpMethod.POST, "/refresh").authenticated()
                .antMatchers("/**").denyAll()
                .and()
                .addFilter(new JwtRequestFilter(manager(config), converter))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    public AuthenticationManager manager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}