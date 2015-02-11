package ca.bcit.a00057006.controller;

import ca.bcit.a00057006.jpa.data.EmployeeFacade;
import ca.bcit.a00057006.jpa.entity.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Servlet for the Employee Entity
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = "/Employees")
public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EmployeeFacade employeeFacade = EmployeeFacade.getInstance();



        /*
        Handle the 'add employee' use case to persist a new Employee to the database.
         */
        if (null != request.getParameter("add")) {
            String id = request.getParameter("id");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            Date date = null;
            try {
                // convert input to SimpleDateFormat to be used with Date
                date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dob"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Employee emp = new Employee();
            emp.setId(id);
            emp.setFirstName(firstName);
            emp.setLastName(lastName);
            // Date must be converted to java.sql.Date for compliance with ms-sql Date type
            emp.setDateOfBirth(new java.sql.Date(date.getTime()));

            employeeFacade.addEmployee(emp);
        }

        /*
        Handle the 'find employee' use case
         */
        if (null != request.getParameter("find")) {
            String id = request.getParameter("id");
        }

        List<Employee> employees = employeeFacade.getEmployees();
        for (Employee emp : employees) { // just for testing print to JBoss console
            System.out.println(emp);
        }
        request.getSession().setAttribute("employees", employees); // add List to session to be displayed in index.jsp
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}


