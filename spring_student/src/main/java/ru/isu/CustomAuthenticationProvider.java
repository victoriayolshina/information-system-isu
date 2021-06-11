package ru.isu;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.isu.model.AutoUser;
import ru.isu.model.Student;
import ru.isu.repository.AutoUserRepository;
import ru.isu.repository.StudentRepository;

@Component("CustomAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    AutoUserRepository autoUserRepository;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("custom");
        System.out.println(authentication);
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        AutoUser user = autoUserRepository.findAutoUserByUsername(token.getName());
        if(user==null) {
            throw new BadCredentialsException("No user or password");
        }
        String userPass = user.getPassword();

        if (user==null || !userPass.equals(token.getCredentials().toString())){
            throw new BadCredentialsException("No user or password");
        }

        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
