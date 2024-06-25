package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");  // com.mysql.jdbc.Driver is deprecated
        /*
        Behind the scene of Class.forName(...Driver)
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        because the Driver class do the above on static block, so its same as previous line
        */

        //  public static Class<T> forName(String className) throws ClassNotFoundException (For get the instance)
        // Scanner scanner = new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/student"; // db(student) -> table(students)
        String username = "root";
        String password = "password";

        Connection con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement(); // Statement,
        ResultSet rs = st.executeQuery("select * from students;");
        while (rs.next()) {
            System.out.println("id: " + rs.getInt(1) + " name:" + rs.getString(2));
        }


        int id = 6;
        String name = "John";
        String query = "insert into students values(?, ?)";  // Prepared Statement(value keep change so use ? instead)
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id); // (question mark location, value)
        ps.setString(2, name);
        int count = ps.executeUpdate();


        rs.close();
        con.close();
    }
}
