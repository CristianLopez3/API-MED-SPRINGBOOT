package med.voll.api.domain.user;

import jakarta.persistence.*;
import lombok.*;


@Entity(name = "user")
@Table(name ="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;



}
