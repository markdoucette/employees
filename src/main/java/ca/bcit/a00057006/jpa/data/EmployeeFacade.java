package ca.bcit.a00057006.jpa.data;

import ca.bcit.a00057006.jpa.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Facade for the employee persistence unit
 * @author Mark Doucette
 */
public class EmployeeFacade {
    private static final String PERSISTENCE_UNIT = "EmployeePU";
    private static EmployeeFacade empFacade; // singleton usage
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private EmployeeFacade() { // private ctor for singleton
        entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * get an instance of the Employee Facade as a singleton, synchronized for thread safety
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
     * @return a List<Employee>
     */
    public List<Employee> getEmployees() {
        return entityManager.createNamedQuery("Employee.getEmployees", Employee.class).getResultList();
    }

    /**
     * Add an Employee to the database by simply persisting the object
     * @param emp the Employee to persist
     */
    public void addEmployee(Employee emp){
        entityManager.getTransaction().begin();
        entityManager.persist(emp);
        entityManager.getTransaction().commit();
    }

    /**
     * Get a single Employee identified by id
     * @param id the id to use as search criteria
     * @return the found Employee
     */
    public Employee getEmployeeById(String id) {
        TypedQuery<Employee> query = entityManager.createNamedQuery("Employee.findById", Employee.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Remove a single Employee from the database
     * @param id the id to use as a search criteria
     */
    public void removeEmployeeById(String id) {
        // use the findById query to first retrieve the Employee
        Employee employee = getEmployeeById(id);

        entityManager.getTransaction().begin();
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
    }
}
