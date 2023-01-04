package groceryProject.entity.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "employee_id", referencedColumnName = "user_id")
public class Employee extends User{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "national_identity")
    private String nationalIdentity;

    @Column(name = "year_of_birth")
    private LocalDate birthDate;

    @Column(name = "salary")
    private double salary;

    /*
    @Builder(builderMethodName = "childBuilder")
    public Employee(int id, String email, String password, String firstName, String lastName, long nationalIdentity, long birthDate, int salary) {
        super(id, email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalIdentity = nationalIdentity;
        this.birthDate = birthDate;
        this.salary = salary;
    }

     */
}
