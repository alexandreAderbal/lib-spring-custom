package br.com.lib.custom.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
public class UsuarioUtil {

    public static String usuariologado(){
        try{
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails)principal).getUsername();
            }
            return principal.toString();
        }catch (Exception e){
            return null;
        }
    }

}
