package com.example.vet_mvc_app.authentication.JWT;

import com.example.vet_mvc_app.users.dto.UserResponse;
import com.example.vet_mvc_app.users.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private UserResponse userResponse;
    @Value("${jwt.secret}")
    private String secretKey;

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(Long userId, String userName, String email, User.Role role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("id", userId)
                .claim("userName", userName)
                .claim("role", role)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public JwtUserPrincipal extractUserDataFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return new JwtUserPrincipal(
                claims.get("id", Long.class),
                claims.getSubject(),
                claims.get("userName", String.class),
                User.Role.valueOf(claims.get("role", String.class))
        );
    }

    /**
     * Checks whether the authenticated user is authorized to access or modify
     * a resource that belongs to the given user.
     * Authorization is granted if:
     * <ul>
     *   <li>The authenticated user's ID matches the target user ID, or</li>
     *   <li>The authenticated user has the ROLE_ADMIN role.</li>
     * </ul>
     *
     * @param userID the ID of the resource owner
     * @return true if the user is authorized, false otherwise
     */
    public Boolean isUserAdminOrOwner(Long userID) {
        JwtUserPrincipal userDataFromToken = getUserDataFromToken();
        return (userDataFromToken.getId().equals(userID) || (userDataFromToken.getRole().name().equals("admin")));

    }
    /**
     * returns User data from Auth
     *
     * @return JwtUserPrincipal object that contains:
     * <ul>
     *   <li>User ID</li>
     *   <li>Username</li>
     *   <li>Email</li>
     *   <li>Role</li>
     * </ul>
     */
    public JwtUserPrincipal getUserDataFromToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        assert auth != null;
        return (JwtUserPrincipal) auth.getPrincipal();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}