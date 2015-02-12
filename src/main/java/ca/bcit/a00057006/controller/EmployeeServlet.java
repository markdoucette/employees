package ca.bcit.a00057006.controller;

import ca.bcit.a00057006.jpa.data.EmployeeFacade;
import ca.bcit.a00057006.jpa.entity.Employee;

import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Servlet for the Employee Entity
 *
 * @author Mark Doucette
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = "/Employees")
public class EmployeeServlet extends HttpServlet {

    public static final String CODE_000 = "Result Code: 000 Description: Success";
    public static final String CODE_001 = "Result Code: 001 Description: Delete Successful";
    public static final String CODE_801 = "Result Code: 801 Description: No match found";
    public static final String CODE_902 = "Result Code: 902 Description: Employee already exists in the list";
    public static final String CODE_901 = "Result Code: 901 Description: Invalid user data!";
    public static final String CODE_903 = "Result Code: 903 Description: Invalid date format. Usage: YYYY-MM-DD";
    public static final String CODE_904 = "Result Code: 904 Description: Delete unsuccessful";
    public static final String DATE_FORMAT = "yyyy-MM-dd";


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeFacade employeeFacade = EmployeeFacade.getInstance();

        /*
        Handle the 'add employee' use case to persist a new Employee to the database.
         */
        if (null != request.getParameter("add")) {
            String id = request.getParameter("id");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dob = request.getParameter("dob");

            if ((null == id) || (null == firstName) || (null == lastName) || (null == dob)) {
                request.setAttribute("addViolation", CODE_901);
            } else if ("".equals(id) || "".equals(firstName) || "".equals(lastName) || "".equals(dob)) {
                request.setAttribute("addViolation", CODE_901);
            } else {

                Date date = null;
                try {
                    // convert input to SimpleDateFormat to be used with Date
                    date = new SimpleDateFormat(DATE_FORMAT).parse(dob);
                    Employee emp = new Employee();
                    emp.setId(id);
                    emp.setFirstName(firstName);
                    emp.setLastName(lastName);
                    // Date must be converted to java.sql.Date for compliance with ms-sql Date type
                    emp.setDateOfBirth(new java.sql.Date(date.getTime()));
                    employeeFacade.addEmployee(emp);

                } catch (ParseException e) {
                    request.setAttribute("addViolation", CODE_903);
                } catch (NullPointerException ex) {
                    request.setAttribute("addViolation", CODE_901);
                } catch (ConstraintViolationException ex) {
                    request.setAttribute("addViolation", CODE_901);
                } catch (RollbackException ex) {
                    request.setAttribute("addViolation", CODE_902);
                }
            }

        } else if (null != request.getParameter("find")) { // Handle the 'find employee' use case
            String id = request.getParameter("id");
            Employee foundEmp = employeeFacade.getEmployeeById(id);
            if (null != foundEmp) {
                request.setAttribute("foundEmp", foundEmp);
                request.setAttribute("findSuccess", CODE_000);
            } else {
                request.setAttribute("foundViolation", CODE_801);
            }
        } else if (null != request.getParameter("remove")) { // Handle the 'remove employee' use case
            String id = request.getParameter("id");

            if (null == id || "".equals(id)) {
                request.setAttribute("removeViolation", CODE_904);
            } else {
                // remove the Employee
                try {
                    employeeFacade.removeEmployeeById(id);
                    request.setAttribute("removeSuccess", CODE_001);
                } catch (Exception e) {
                    request.setAttribute("removeViolation", CODE_904);
                }
            }
        }

        List<Employee> employees = employeeFacade.getEmployees();
        request.getSession().setAttribute("employees", employees); // add List to session to be displayed in index.jsp
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}


