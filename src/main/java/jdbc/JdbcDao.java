package jdbc;

import java.sql.*;

public class JdbcDao  {
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        Student s1 = dao.getStudent(3);
        System.out.println("Student:" + s1.getName() + " id:"+ s1.getId());

        Student s2 = new Student();
        s2.id = 7;
        s2.name = "Bob";
        dao.addStudent(s2);
    }
}

class StudentDAO {
    Connection con = null;
    public void createConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "password");

    }
    public Student getStudent(int id)  {
        Student s = new Student();
        s.id = id;
        try {
            String query = "select name from students where id = ?";
            createConnection();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            String name = rs.getString(1);  // colIdx start at 1
            s.name = name;
            rs.close();
            con.close();
        } catch (Exception ex){
            System.out.println(ex);
        }
        return s;
    }
    public void addStudent(Student s) {
        try {
            String query = "insert into students values (?, ?)";
            createConnection();
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, s.id);
            st.setString(2, s.name);
            int affected = st.executeUpdate();
            System.out.println(affected+" row/col affected");
            con.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }

    }
}
class Student {
    int id;
    String name;

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
}
