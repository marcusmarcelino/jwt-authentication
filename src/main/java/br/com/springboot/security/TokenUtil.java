package br.com.springboot.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import br.com.springboot.modules.user.models.JWTToken;
import br.com.springboot.modules.user.models.Credentials;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {
  private static final int SEGUNDOS = 1000;
  private static final int MINUTOS = 60 * SEGUNDOS;
  private static final int HORAS = 60 * MINUTOS;
  private static final int DIAS = 24 * HORAS;
  private static final int EXPIRATION = 3 * DIAS;
  private static final String SECRET_KEY = "1234567890123456789012345678901234";

  private static final String PREFIX = "Bearer ";

  public static JWTToken encodeToken(Credentials credentials) {
    Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    String jwt = Jwts.builder()
        .setSubject(credentials.getUsername())
        .setIssuer(credentials.getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();

    return new JWTToken(PREFIX + jwt);
  }

  public static Authentication decodeToken(HttpServletRequest request) {

    try {
      String jwtToken = request.getHeader("Authorization");
      jwtToken = jwtToken.replace(PREFIX, "");

      Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build()
          .parseClaimsJws(jwtToken);

      String user = jwsClaims.getBody().getSubject();
      String issuer = jwsClaims.getBody().getIssuer();
      Date validate = jwsClaims.getBody().getExpiration();

      if (user.length() > 0) {
        return new UsernamePasswordAuthenticationToken("user", null, Collections.emptyList());
      }
    } catch (Exception e) {
      System.out.println("Erro na autenticação");
    }
    return null;
  }
}
