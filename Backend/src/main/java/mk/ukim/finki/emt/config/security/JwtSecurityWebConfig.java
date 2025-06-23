package mk.ukim.finki.emt.config.security;

import mk.ukim.finki.emt.security.CustomUsernamePasswordAuthenticationProvider;
import mk.ukim.finki.emt.web.filters.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Profile({"dev","prod"})
@Configuration
@EnableWebSecurity
public class JwtSecurityWebConfig {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    public JwtSecurityWebConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider, JwtFilter jwtFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtFilter = jwtFilter;
    }
    @Value("${cors.allowed-origin}")
    private String allowedOrigin;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of(allowedOrigin)); // now dynamic
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsCustomizer ->
                        corsCustomizer.configurationSource(corsConfigurationSource())
                )
                .authorizeHttpRequests(authorizeHttpRequestsCustomizer ->
                                authorizeHttpRequestsCustomizer
                                        .requestMatchers(
                                                "/swagger-ui/**",
                                                "/v3/api-docs/**",
                                                "/api/user/register",
                                                "/api/user/login"
                                        )
                                        .permitAll()
                                        .requestMatchers(
                                                "/api/accommodations",
                                                "/api/accommodations/paginated",
                                                "/api/accommodations/categories",
//                                                "/api/accommodations/edit/**",
//                                                "/api/accommodations/delete/**",
                                                "/api/accommodations/{id}",
//                                                "/api/accommodations/add",
                                                "/api/countries",
                                                "/api/countries/{id}",
//                                                "api/user/nonHost",
                                                "/api/hostProfiles",
//                                                "/api/hostProfiles/add",
//                                                "/api/hostProfiles/edit/**",
//                                                "/api/hostProfiles/delete/**",
                                                "api/hostProfiles/{id}",
                                                "api/reservation-cart",
                                                "api/reservation-cart/add-accommodation/{id}",
                                                "api/reservation-cart/remove-accommodation/{id}",
                                                "api/reservation-cart/confirm",
                                                "api/reservation-cart/cancel"
                                        )
                                        .hasAnyRole("USER", "HOST")
//                                        .permitAll()
                                        .anyRequest()
//                                .permitAll()
                                        .hasRole("HOST")
                )
                .sessionManagement(sessionManagementConfigurer ->
                        sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
