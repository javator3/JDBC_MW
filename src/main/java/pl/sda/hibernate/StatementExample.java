package pl.sda.hibernate;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class StatementExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia", "root", "Celinka88!");


        //createTable(connection);
        //addEmployee(connection);
        //removeEmployeeWhenSalaryIsLessThanGivenByUser(connection);
        //dropTable(connection);
        //addEmployeesWithPreparedStatement(connection);
        //removeEmployeeWhenSalaryIsLessThanGivenByUser(connection, 2000);
        //removeEmployeePreparedStatement(connection, 1000);
        //showEmployees(connection);
        //showEmployeesByNameAsc(connection);
        //showEmployeesWhereSalary2000(connection);
        showEmployeesWhereNameBeginsWithA(connection);

    }

    static void createTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE employees (id smallint not null auto_increment, name varchar (50), salary int, primary key (id))");


    }

    static void addEmployee(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO employees (id, name, salary) VALUES (6, 'Antoni', 2000)");
      // statement.executeUpdate("INSERT INTO employees (id, name, salary) VALUES (2, 'Marta', 2000)");
       // statement.executeUpdate("INSERT INTO employees (id, name, salary) VALUES (3, 'Celinka', 4000)");
      //  statement.executeUpdate("INSERT INTO employees (id, name, salary) VALUES (4, 'Fryderyk', 4000)");
      //  statement.executeUpdate("INSERT INTO employees (id, name, salary) VALUES (5, 'Pikus', 20000)");
    }

    static void addEmployeesWithPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees(name, salary) VALUES (?, ?)");

        for (int i = 0; i < 5; i++) {
            preparedStatement.setString(1, "imię" + i);
            preparedStatement.setInt(2, new Random().nextInt(2000));
            preparedStatement.executeUpdate();
        }
    }

    static void removeEmployeeWhenSalaryIsLessThanGivenByUser(Connection connection, int salary) throws SQLException {
        Statement statement = connection.createStatement();
        String formattedString = String.format("delete from employees where salary < %d", salary);
        statement.execute(formattedString);

    }

    static void removeEmployeePreparedStatement(Connection connection, int salary) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from employees where salary < ?");
        preparedStatement.setInt(1, salary);
        preparedStatement.executeUpdate();

    }

    static void showEmployees(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name, salary from employees");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            System.out.println(name + " " + salary);

        }


    }

    static void showEmployeesByNameAsc(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name, salary from employees order by name asc");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            System.out.println(name + " " + salary);

        }


    }

    static void showEmployeesWhereSalary2000(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name, salary from employees where salary > 2000");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            System.out.println(name + " " + salary);

        }


    }

    static void showEmployeesWhereNameBeginsWithA(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select name, salary from employees where name like 'a%'");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int salary = resultSet.getInt("salary");
            System.out.println(name + " " + salary);

        }


    }




    static void dropTable(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE employees");

    }
}
