/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.services;

import com.salchichon.lavadero.controllers.CustomerUserDetails;
import com.salchichon.lavadero.models.Client;
import com.salchichon.lavadero.models.UserM;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.function.Function;
import java.util.Date;


@Service
public class JwtUtilService {
    private static final String JWT_SECRET_KEY = "TExBVkVfTVVZX1NFQ1JFVEzE3Zmxu7BSGSJx72BSBXM";
    private static final long JWT_TIME_VALIDITY = 1000 * 60  * 15;
    private static final long JWT_TIME_REFRESH_VALIDATE = 1000 * 60  * 60 * 24;

  public String generateToken(CustomerUserDetails userDetails) {
     String dni;
    String role;
    Long id;

    if (userDetails.getUserM() != null) {
        UserM user = userDetails.getUserM();
        dni = user.getDni();
        role = user.getRole() != null ? user.getRole() : "USER";
        id = user.getId();
    } else {
        Client client = userDetails.getClient();
        dni = client.getDni();
        role = "CLIENT";
        id = client.getId();
    }


    var claims = new HashMap<String, Object>();
    claims.put("role", role);
    claims.put("id",id);


    return Jwts.builder()
            .setClaims(claims)
            .setSubject(dni)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TIME_VALIDITY))
            .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
            .compact();
}

    public String generateRefreshToken(CustomerUserDetails userDetails) {
    return Jwts.builder()
            .setSubject(userDetails.getUserM().getDni())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TIME_REFRESH_VALIDATE))
            .signWith(SignatureAlgorithm.HS256, JWT_SECRET_KEY)
            .compact();
}

   public boolean validateToken(String token, CustomerUserDetails userDetails) {
    String usernameFromToken = extractClaim(token, Claims::getSubject);
    String userDni = userDetails.getUserM().getDni();

    return usernameFromToken.equals(userDni)
            && !extractClaim(token, Claims::getExpiration).before(new Date());
}

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    Claims claims = Jwts.parser()
            .setSigningKey(JWT_SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();

    return claimsResolver.apply(claims);
}
    public Claims extractAllClaims(String token) {
    return Jwts.parser()
            .setSigningKey(JWT_SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
}

    public String extractDni(String token) {
    return extractClaim(token, Claims::getSubject);
}

}
