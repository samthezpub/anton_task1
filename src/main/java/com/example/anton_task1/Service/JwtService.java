package com.example.anton_task1.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
  private final String SECRET =
      "3b4b3746602d51d37bdbd9768eb0ed693a232a3dd3dd41fad57e2e671e94cf69f982de57a156e6e6075ec84e4688cc1453f5c6765e0e2e37974830fea81a6358807b45da6e71092c794bb337a85883d7bdc934aa10673363a8f1971e10e2924523effb9f9a5afdbe6479494b6972794dbb23508d71012ab87b237fe0f61f2130ab3274bac8d6620d19dbbdb5543c271e2750976c640c68e8b6fd15cd291df41e6348a07773b777c5f748062500e67929ef8511eb9eac1a68b74b8640fff074df69432071b629aedfb3a5ce45bb8672d81433e645daadbe69625a86bdf05319b9";

  public String extractUsername(String token) {
    return extractClaims(token, Claims::getSubject);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
        .signWith(getSecretKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaims(token, Claims::getExpiration);
  }

  public Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSecretKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Key getSecretKey() {
    return new SecretKeySpec(SECRET.getBytes(), "HmacSHA256");
  }
}
