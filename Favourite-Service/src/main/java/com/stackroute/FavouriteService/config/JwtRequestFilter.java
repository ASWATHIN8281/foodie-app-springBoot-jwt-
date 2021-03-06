package com.stackroute.FavouriteService.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.stackroute.FavouriteService.Exception.UnAuthorizedAccesException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean.
 * Override the doFilter method with ServletRequest, ServletResponse and FilterChain.
 * This is used to authorize the API access for the application.
 */

public class JwtRequestFilter extends GenericFilterBean {
    /**
     * The method checks the authorisation of user login based on the token and Bearer
     */
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        final String authHeader = request.getHeader("Authorization");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        }
        else {
            /**
             * Check if authHeader is null or does not start with "Bearer " then throw Exception
             */
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                //throw new ServletException("An exception occured -Missing or Invalid Authorization header");
                 throw new UnAuthorizedAccesException();
            }
            /**
             * Extract token from authHeader
             */
            final String token = authHeader.substring(7);
            try {
                /**
                 * Obtain Claims by parsing the token with the signing key value "secret key"
                 */
                Claims claims = Jwts.parser().setSigningKey("foodapp").parseClaimsJws(token).getBody();
                /**
                 * Set Claims object obtained in previous step with key "claims" as request attribute
                 */
                request.setAttribute("claims", claims);
                filterChain.doFilter(request, response);
            }
            catch(JsonParseException j) {
                System.out.println(j.getMessage());
            }
        }
    }
}
