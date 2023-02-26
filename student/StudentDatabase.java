import java.sql.*;
import java.util.Scanner;

public class StudentDatabase {
    public static void main(String args[]) throws Exception {

        try {
            Scanner sc = new Scanner(System.in);
            String url = "jdbc:mysql://localhost:3306/student_db";
            String uname = "root";
            String password = "***********";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, password);
            System.out.println("DATABASE CONNECTED..........\n");
            System.out.println("ENTER YOUR REG_N0:");
            Long regno = sc.nextLong();
            CallableStatement cst = con.prepareCall(" SELECT * FROM personal_infomation WHERE REG_NO=" + regno + " ");
            CallableStatement st = con.prepareCall("SELECT * FROM marks_information WHERE REG_NO=" + regno + "");
            ResultSet rst = cst.executeQuery();
            ResultSet rs = st.executeQuery();
            while (rst.next() & rs.next()) {
                System.out.println("\nREG_N0: " + rst.getLong(1) + "\n" + "NAME: " + rst.getString(2) + "\n" + "DEPARTMENT: " + rst.getString(3) + "\n" + "COLLEGE: " + rst.getString(4) + "\n");
                System.out.println("*******MARKS*****");
                System.out.println("SUBJECT-1: " + rs.getString(2) + "\n" + "SUBJECT-2: " + rs.getString(3) + "\n" + "SUBJECT-3: " + rs.getString(4) + "\n" + "SUBJECT-4: " + rs.getString(5) + "\n" + "SUBJECT-5: " + rs.getString(6) + "\n");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
