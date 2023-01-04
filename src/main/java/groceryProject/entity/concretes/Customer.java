package groceryProject.entity.concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "user_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User{

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

}
