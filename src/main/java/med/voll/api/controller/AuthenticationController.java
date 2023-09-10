package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.user2.DataAuthenticateUser;
import med.voll.api.domain.user2.User;
import med.voll.api.infra.security.DataJWTToken;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid DataAuthenticateUser authenticateData){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                authenticateData.login(), authenticateData.password()
        );
        var userAuthenticad = authenticationManager.authenticate(authToken);
        var JWToken = tokenService.generateToken((User) userAuthenticad.getPrincipal());
        return ResponseEntity.ok(new DataJWTToken(JWToken));
    }
}
