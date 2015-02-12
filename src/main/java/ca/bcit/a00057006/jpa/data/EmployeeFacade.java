package ca.bcit.a00057006.jpa.data;

import ca.bcit.a00057006.jpa.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * Facade for the employee persistence unit
 *
 * @author Mark Doucette
 */
public class EmployeeFacade {
    private static final String PERSISTENCE_UNIT = "EmployeePU";
    private static EmployeeFacade empFacade; // singleton usage
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ConstraintViolationException cve;

    private EmployeeFacade() { // private ctor for singleton
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
        cve = null;
    }

    /**
     * get an instance of the Employee Facade as a singleton, synchronized for thread safety
     *
     * @return EmployeeFacade instance
     */
    public static synchronized EmployeeFacade getInstance() {
        if (empFacade != null) {
            return empFacade;
        }

        return new EmployeeFacade();
    }

    /**
     * Get a list of all Employees in the A00057006_Employee table
     *
     * @return a List<Employee>
     */
    public List<Employee> getEmployees() {
        return entityManager.createNamedQuery("Employee.getEmployees", Employee.class).getResultList();
    }

    /**
     * Add an Employee to the database by simply persisting the object
     *
     * @param emp the Employee to persist
     * @throws javax.validation.ConstraintViolationException
     */
    public void addEmployee(Employee emp) throws RollbackException {
        entityManager.getTransaction().begin();
        entityManager.persist(emp);
        entityManager.getTransaction().commit();
    }

    /**
     * Get a single Employee identified by id
     *
     * @param id the id to use as search criteria
     * @return the found Employee
     */

    public Employee getEmployeeById(String id) {
        Employee emp;
        emp = entityManager.find(Employee.class, id);
        if (null != emp) {
            return emp;
        }
        return null;
    }

    /**
     * Remove a single Employee from the database
     *
     * @param id the id to use as a search criteria
     */
    public void removeEmployeeById(String id) {
        // use the findById query to first retrieve the Employee
        Employee employee = getEmployeeById(id);

        entityManager.getTransaction().begin();
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
    }

    /**
     * Get a set of validation violations for display in another area
     *
     * @param cve
     * @return
     */
    public Set<ConstraintViolation<?>> getValidationViolations(ConstraintViolationException cve) {
        return cve.getConstraintViolations();
    }
}
