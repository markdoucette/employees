package ca.bcit.a00057006.jpa.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Employee class - representation of an Employee that will have an id,
 * first name, last name and date of birth to match the database table.
 *
 * @author Mark Doucette
 */
@Entity
@Table(name = "A00057006_Employee")
@NamedQueries({
        @NamedQuery(name = "Employee.getEmployees", query = "select e from Employee e"),
        @NamedQuery(name = "Employee.findById", query = "select e from Employee e where e.id = :id")
})
public class Employee {
    @Id
    private String id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date dateOfBirth;

    public Employee() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
