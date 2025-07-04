package mk.ukim.finki.emt.web.filters;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import mk.ukim.finki.emt.constants.JwtConstants;
import mk.ukim.finki.emt.helpers.JwtHelper;
import mk.ukim.finki.emt.model.domain.User;
import mk.ukim.finki.emt.service.domain.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtHelper jwtHelper;
    private final UserService userService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtFilter(JwtHelper jwtHelper, UserService userService,
                     @Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtHelper = jwtHelper;
        this.userService = userService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String headerValue = request.getHeader(JwtConstants.HEADER);
        if (headerValue == null || !headerValue.startsWith(JwtConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = headerValue.substring(JwtConstants.TOKEN_PREFIX.length());

        try {
            String username = jwtHelper.extractUsername(token);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (username == null || authentication != null) {
                filterChain.doFilter(request, response);
                return;
            }

            User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
            if (jwtHelper.isValid(token, user)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (JwtException jwtException) {
//             TODO: Add logic for exception handling.
            handlerExceptionResolver.resolveException(
                    request,
                    response,
                    null,
                    jwtException
            );
            return;
        }

        filterChain.doFilter(request, response);
    }

}
