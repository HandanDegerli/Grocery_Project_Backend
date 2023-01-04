package groceryProject.entity.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "individual_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "individual_customer_id",   referencedColumnName = "customer_id")
public class IndividualCustomer extends Customer{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private  String lastName;

    @Column(name = "national_identity")
    private String nationalIdentity;

}
