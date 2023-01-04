package groceryProject.entity.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "corporate_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "corporate_customer_id", referencedColumnName = "customer_id")
public class CorporateCustomer extends Customer{

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "tax_number")
    private String taxNumber;
}
