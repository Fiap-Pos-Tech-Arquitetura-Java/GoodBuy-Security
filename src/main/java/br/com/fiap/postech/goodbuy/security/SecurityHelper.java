package br.com.fiap.postech.goodbuy.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityHelper {

    public String getLoggedUser() {
        Authentication authentication = getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    private static Authentication getAuthentication() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

    public String getToken() {
        Authentication authentication = getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return String.valueOf(authentication.getCredentials());
    }
}
