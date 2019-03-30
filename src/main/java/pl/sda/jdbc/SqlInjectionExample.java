package pl.sda.jdbc;

import java.sql.*;

public class SqlInjectionExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String name = "1' '1'='1";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia?logger=com.mysql.cj.log.Slf4JLogger&profileSQL=true", "root", "Celinka88!");
        Statement statement = connection.createStatement();
        ResultSet resultSet2 = statement.executeQuery("select name,salary from employees where name = '"+name+"'");
        while (resultSet2.next()) {
            System.out.println(resultSet2.getString("name"));
            System.out.println(resultSet2.getInt("salary"));
        }



    }
}
