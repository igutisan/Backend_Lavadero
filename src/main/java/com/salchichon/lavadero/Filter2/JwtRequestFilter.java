

package com.salchichon.lavadero.Filter2;


import com.salchichon.lavadero.controllers.CustomerUserDetails;
import com.salchichon.lavadero.services.JwtUtilService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;


import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtUtilService jwtUtilService;

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");

    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        String jwt = authorizationHeader.substring(7);
        String dni = jwtUtilService.extractDni(jwt);  // Extraer DNI desde el token

        if (dni != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomerUserDetails userDetails = (CustomerUserDetails) userDetailsService.loadUserByUsername(dni);

            if (jwtUtilService.validateToken(jwt, userDetails)) {
                // Extraer todas las claims para obtener el rol
                Claims claims = jwtUtilService.extractAllClaims(jwt);
                String role = (String) claims.get("role");

                // Asignar autoridad basada en el rol
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }
    filterChain.doFilter(request, response);
}
    
    
}
