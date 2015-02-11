package ca.bcit.a00057006.jpa.data;

import ca.bcit.a00057006.jpa.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Facade for the employee persistence unit
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

    public List<Employee> getEmployees() {
        return entityManager.createQuery("From A00057006_Employee ", Employee.class).getResultList();
    }

    public void addEmployee(Employee emp){
        entityManager.getTransaction().begin();
        entityManager.persist(emp);
        entityManager.getTransaction().commit();
    }
}
