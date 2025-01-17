package com.employee.system.config;

import com.employee.system.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( request -> request
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/employee/**").hasAnyAuthority(Role.HR.name(),Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/api/employee/**").hasAnyAuthority(Role.HR.name(),Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/employee/**").hasAnyAuthority(Role.HR.name(),Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/employee/**").hasAnyAuthority(Role.HR.name(),Role.ADMIN.name())

                        .requestMatchers(HttpMethod.PUT, "/api/employee/**").hasAnyAuthority(Role.MANAGER.name(),Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/api/employee/**").hasAnyAuthority(Role.MANAGER.name(),Role.ADMIN.name())

                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html").permitAll()
                        .requestMatchers("/**").permitAll()

                        .anyRequest().authenticated()
                )

            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors(Customizer.withDefaults());
        http.httpBasic(AbstractHttpConfigurer::disable);
        return http.build();

    }
}
