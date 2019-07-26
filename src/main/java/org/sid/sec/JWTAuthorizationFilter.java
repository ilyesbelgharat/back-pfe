package org.sid.sec;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        httpServletResponse.addHeader("Access-Control-Allow-Origin","*");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", " Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");
        httpServletResponse.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials,Authorization");
        httpServletResponse.addHeader("Access-Control-Allow-Methods","GET,POST,PUT,DELETE,PATCH");
        if(httpServletRequest.getMethod().equals("OPTIONS")){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }
        else if(httpServletRequest.getRequestURI().equals("/login")){
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        return;
          }
          else

    {
        String jwtToken= httpServletRequest.getHeader(SecurityParams.JWT_HEADER_NAME);

        if ((jwtToken == null) || !jwtToken.startsWith(SecurityParams.HEADER_PREFIX)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);

            return;
        }

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();

        String jwt = jwtToken.substring(SecurityParams.HEADER_PREFIX.length());

        DecodedJWT decodedJWT = JWT.decode(jwt);


        System.out.println("jwt=" + jwt);

        String username = decodedJWT.getSubject();

        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);

        System.out.println("roles=" + roles);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(rn -> {
            authorities.add(new SimpleGrantedAuthority(rn));
        });
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(user);
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

    }
}
