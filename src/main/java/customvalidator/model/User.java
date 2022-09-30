package customvalidator.model;

import customvalidator.validators.DateOfBirthValidation;
import customvalidator.validators.SalaryValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_tbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @DateOfBirthValidation
    private Date dateOfBirth;
    @Column
    private char gender;
    @Column
    @SalaryValidation
    private Double salary;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

}
