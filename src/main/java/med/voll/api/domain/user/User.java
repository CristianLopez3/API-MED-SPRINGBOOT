package med.voll.api.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


/**
 * Clase usuarios para permitir y autenticar quient entra a nuestra api,
 * tener en cuenta que para que spring haga todo esto es muy necesario tener
 * el bean de password encode configurado y a su vez esta clase halla implementado
 * la interfaz userdetails con sus respectivos metodos.
 * @Author Cristian Lopez
 */

@Entity(name = "user")
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;


    /**
     * Asignacion de roles para el usuario
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * Le decimos a spring cual va a ser el campo encargado de los userDetails que estan
     * en el repository
     * @return
     */
    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
