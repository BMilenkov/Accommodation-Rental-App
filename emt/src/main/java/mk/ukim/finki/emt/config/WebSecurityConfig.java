package mk.ukim.finki.emt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    public WebSecurityConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .headers((headers) -> headers
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                        corsConfigurationSource()))
                .authorizeHttpRequests(requests -> requests.requestMatchers(
                        "/api/accommodations",
                        "/api/countries",
                        "/api/hosts",
                        "/api/hosts/login",
                        "/api/hosts/add",
                        "api/reviews/**",
                        "/h2"
                ).permitAll().requestMatchers("/api/hosts/**").hasRole("ADMIN").anyRequest().hasRole("ADMIN"))
                .formLogin((form) -> form.loginProcessingUrl(
                                "/api/hosts/login")
                        .permitAll()
                        .failureUrl("/api/hosts/login?error=BadCredentials")
                        .defaultSuccessUrl(
                                "/swagger-ui/index.html",
                                true
                        ))
                .logout((logout) -> logout.logoutUrl("/api/hosts/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(
                                true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/api/hosts/login"))
                .exceptionHandling((ex) -> ex.accessDeniedPage(
                        "/access_denied"));
        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        return authenticationManagerBuilder.build();
    }
}
