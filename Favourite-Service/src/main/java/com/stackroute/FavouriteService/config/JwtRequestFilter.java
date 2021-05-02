//package com.stackroute.FavouriteService.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtRequestFilter extends GenericFilterBean {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        final String authHeader = request.getHeader("Authorization");
//
//            /*
//             * Check if authHeader is null or does not start with "Bearer " then throw Exception
//             */
//            if(authHeader==null || !authHeader.startsWith("Bearer ")){
//                throw new ServletException("An exception occured");
//            }
//            /*
//             * Extract token from authHeader
//             */
//            final  String token=authHeader.substring(7);
//            /*
//             * Obtain Claims by parsing the token with the signing key value "secret key"
//             */
//            Claims claims= Jwts.parser().setSigningKey("foodapp").parseClaimsJws(token).getBody();
//            /*
//             * Set Claims object obtained in previous step with key "claims" as request attribute
//             */
//            request.setAttribute("claims",claims);
//            filterChain.doFilter(request, response);
//        }
//
//}
