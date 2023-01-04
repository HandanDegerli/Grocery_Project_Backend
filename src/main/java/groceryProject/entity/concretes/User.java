package groceryProject.entity.concretes;

import jakarta.persistence.*;
import lombok.*;


@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    //order add!
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name ="email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;


}
