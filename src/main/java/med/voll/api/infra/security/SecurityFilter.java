package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.user2.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {
        var authHeader = request.getHeader("Authorization");
        if(authHeader != null)  {
            var token = authHeader.replace("Bearer", "");
            filterChain.doFilter(request, response);
            var username = tokenService.getSubject(token); // extraems el nombre del usuario
            if(username != null) {
                //token valido
                var user = userRepository.findByLogin(username);
                var authentication = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()); // forzamos un inicio de sesion
                SecurityContextHolder.getContext().getAuthentication();

            }
        }

        filterChain.doFilter(request, response);


    }
}
