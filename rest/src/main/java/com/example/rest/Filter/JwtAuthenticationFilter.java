package com.example.rest.Filter;

import com.example.rest.Config.JwtService;
import com.example.rest.Config.MyUserDetailsService;
import com.example.rest.Config.MyUserPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwtService;
  private final MyUserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String token;
    final String username;

    try {

      if (authHeader == null) {
        filterChain.doFilter(request, response);
        return;
      }

      token = authHeader.substring(7);
      username = jwtService.extractUsername(token);

      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        MyUserPrincipal userDetails =
            (MyUserPrincipal) this.userDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(token, userDetails)) {
          UsernamePasswordAuthenticationToken authToken =
              new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities());

          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          System.out.println(authToken);

          SecurityContextHolder.getContext().setAuthentication(authToken);
          System.out.println(SecurityContextHolder.getContext());
        }
      }

    } catch (SignatureException | MalformedJwtException e) {
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"code\": 400, \"message\": \"Invalid JWT signature\"}");
      return;
    } catch (ExpiredJwtException e) {
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      response.getWriter().write("{\"code\": 403, \"message\": \"Jwt expired\"}");
      return;
    }
    filterChain.doFilter(request, response);
  }
}
