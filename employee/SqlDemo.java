package sqlpackage;

import java.awt.desktop.SystemSleepEvent;
import java.sql.*;
import java.util.Scanner;
import java.lang.Exception;

public class SqlDemo {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.INSERT DATA" + "\n" + "2.VIEW DATA" + "\n" +"3.UPDATE DATA"+"\n"+ "4.EXIT");
            System.out.println("ENTER CHOICE:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    InsertData();
                    break;
                case 2:
                    ViewData();
                    break;
                case 3:
                    UpdateData();
                    break;
                case 4:
                    System.exit(0);
                    System.out.println("EXITED SUCCESSFULLY");
                    break;
            }
        }
    }





    public static void InsertData()throws Exception{
        Scanner sc=new Scanner(System.in);
        int EMPLOYEE_ID;
        String FIRST_NAME,LAST_NAME;
        int DEPARTMENT_ID , SALARY;

        try {
            Connection con=dbConnection();
            System.out.println("HOW MANY DATA TO INSERT:");
            int n = sc.nextInt();
            PreparedStatement st = con.prepareStatement("INSERT INTO my_employee(EMPLOYEE_ID, FIRST_NAME,LAST_NAME,  DEPARTMENT_ID , SALARY) VALUES (?,?,?,?,?)");
            for (int i = 0; i < n; i++) {
                System.out.println("\nEMPLOYEE_ID:");
                EMPLOYEE_ID = sc.nextInt();
                System.out.println("FIRST_NAME:");
                FIRST_NAME=sc.next();
                System.out.println("LAST_NAME:");
                LAST_NAME=sc.next();
                System.out.println("DEPARTMENT_ID:");
                DEPARTMENT_ID=sc.nextInt();
                System.out.println("SALARY:");
                SALARY=sc.nextInt();

                st.setInt(1, EMPLOYEE_ID);
                st.setString(2, FIRST_NAME);
                st.setString(3, LAST_NAME);
                st.setInt(4, DEPARTMENT_ID);
                st.setInt(5, SALARY);
                st.executeUpdate();
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("Inserted successfully");
        } }


    public static void UpdateData()throws Exception{
        try{
            Scanner sc=new Scanner(System.in);
            Connection conn=dbConnection();
            System.out.println("SALARY:");
            int salary=sc.nextInt();
            System.out.println("ID:");
            int id=sc.nextInt();
            CallableStatement st=conn.prepareCall("UPDATE my_employee SET SALARY="+salary+" WHERE EMPLOYEE_ID="+id+" ");
            st.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("Update successfully");
        }
    }

    public static void ViewData()throws Exception{
        try{

            Connection con=dbConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM my_employee");
            String udata=" ";
            while (rs.next()){
                udata=rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getInt(5);
                System.out.println(udata);
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }
    }





    public static Connection dbConnection() throws Exception{
        String url = "jdbc:mysql://localhost:3306/DatabaseName";
        String uname = "root";
        String password = "********";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, password);
            System.out.println("DATABASE CONNNECTED");
            return con;

        } catch (Exception e) {

            System.out.println(e);
        }
        return null;
    }

}
