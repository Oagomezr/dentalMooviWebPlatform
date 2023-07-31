package com.dentalmoovi.webpage.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dentalmoovi.webpage.services.JwtUserDetailsSer;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUserDetailsSer jwtUserDetailsService;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        final String requestTokenHeader = request.getHeader("Authorization");

        String emailJwt = null;
        String jwtToken = null;

        // JWT Token is in the form of a "Bearer token". Remove Bearer word and get only the Token
        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")){
            jwtToken = requestTokenHeader.substring(7);
            try {
				emailJwt = jwtTokenUtil.getEmailFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Cannot get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
        }
        else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

        // Once we get the token, we proceed to validate it
		if (emailJwt != null && SecurityContextHolder.getContext().getAuthentication() == null &&
			isUserHasAccess(request,emailJwt) ) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(emailJwt);

			// if token is valid configure Spring Security to manually set authentication
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails))) {

				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		filterChain.doFilter(request, response);
    }

	private boolean isUserHasAccess(HttpServletRequest request, String emailJwt){
		String[] urlSegments = request.getRequestURI().split("/");
		if("user".equals(urlSegments[1])){
			return urlSegments[2].equals(emailJwt);
		}else if("admin".equals(urlSegments[1])){
			return true;
		}
		return false;
	}
    
}
