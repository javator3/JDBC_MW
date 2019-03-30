package pl.sda.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Employees {
    private int id;
    private String name;
    private int salary;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "Celinka88!");
        showEmployees(connection);
        showEmployeesByNameAsc(connection);
        showEmployeesWhereSalary2000(connection);

    }

    public Employees(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    static void showEmployees(Connection connection) throws SQLException {
        List<Employees> employeeList = new ArrayList<Employees>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id, name, salary from employees");

        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            int id = resultSet.getInt("id");
            Employees employee = new Employees(id, name, salary);
            employeeList.add(employee);

        }

        for (Employees e : employeeList) {
            System.out.println(e.getId() + " " + e.getName() + " " + e.salary);
        }


    }

    static void showEmployeesByNameAsc(Connection connection) throws SQLException {
        List<Employees> employeeList = new ArrayList<Employees>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id, name, salary from employees order by name asc");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            int id = resultSet.getInt("id");
            Employees employee = new Employees(id, name, salary);
            employeeList.add(employee);

        }

        for (Employees e : employeeList) {
            System.out.println(e.getId() + " " + e.getName() + " " + e.salary);
        }


    }

    static void showEmployeesWhereSalary2000(Connection connection) throws SQLException {
        List<Employees> employeeList = new ArrayList<Employees>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id, name, salary from employees where salary > 2000");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            int id = resultSet.getInt("id");
            Employees employee = new Employees(id, name, salary);
            employeeList.add(employee);

        }

        for (Employees e : employeeList) {
            System.out.println(e.getId() + " " + e.getName() + " " + e.salary);
        }


    }


}
