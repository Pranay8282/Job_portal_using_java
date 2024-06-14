import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class EmployeeDataBase {
    Connection con;

    EmployeeDataBase() throws Exception {
        String s1 = "com.mysql.cj.jdbc.Driver";
        Class.forName(s1);
        String dbname = "root";
        String dburl = "jdbc:mysql://localhost:3306/jobportals";
        con = DriverManager.getConnection(dburl, dbname, "");

        /*
         * TO Cheak Connection
         * -----------------------------------------------------------------------------
         * ---------
         */
        // if (con != null) {
        // System.out.println("Connection Done ");
        // } else {
        // System.out.println("Connecion Not Done");
        // }
    }

    public void addEmployeeData(Employee e) throws Exception {
        String sql = "insert into employee (f_name,l_name,email,mobile,pass,status,comp) values (?,?,?,?,?,?,?);";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, e.getFirst_name());
        pst.setString(2, e.getLast_name());
        pst.setString(3, e.getEmail());
        pst.setString(4, e.getMobile_no());
        pst.setString(5, e.getPassword());
        pst.setString(6, "unemployee");
        pst.setString(7, "not join");
        pst.executeUpdate();
    }

    public void getYourDetails(Scanner sc) throws SQLException {
        System.out.println("Enter Your Email");
        String emi = sc.nextLine();
        System.out.println("Enter Your Pass");
        String pass = sc.nextLine();

        String sql = "{call getAllEmployee(?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, emi);
        cst.setString(2, pass);
        ResultSet rst = cst.executeQuery();

        if (rst.equals(null)) {
            System.out.println("Employee Not Found");
        } else {
            while (rst.next()) {
                System.out.println("--------------------Your Details------------------");
                System.out.println("Id := " + rst.getInt(1));
                System.out.println("First Name := " + rst.getString(2));
                System.out.println("Last Name := " + rst.getString(3));
                System.out.println("Email := " + rst.getString(4));
                System.out.println("Mobile := " + rst.getString(5));
                System.out.println("Status := " + rst.getString(7));
                System.out.println("Compnay := " + rst.getString(8));
                System.out.println("---------------------------------------------------");
            }
        }
    }

    void loginDetails(String emi, String pass) throws Exception {
        String sql = "{call getAllEmployee(?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, emi);
        cst.setString(2, pass);
        ResultSet rst = cst.executeQuery();
        String blueColor = "\u001B[34m";
        String resetColor = "\u001B[0m";
        if (rst.equals(null)) {
            System.out.println("Employee Not Found");
        } else {
            while (rst.next()) {
                System.out.println("--------------------Your Details------------------");
                System.out.println(blueColor + "Id := " + rst.getInt(1));
                System.out.println("First Name := " + rst.getString(2));
                System.out.println("Last Name := " + rst.getString(3));
                System.out.println("Email := " + rst.getString(4));
                System.out.println("Mobile := " + rst.getString(5));
                System.out.println("Status := " + rst.getString(7));
                System.out.println("Compnay := " + rst.getString(8));
                System.out.println("---------------------------------------------------" + resetColor);
            }
        }
    }

    public void updateYourStatus(Scanner sc) throws Exception {
        System.out.println("Enter Your Email.");
        String email = sc.nextLine();
        System.out.println("Enter PassWord To Uppdate Your Status");
        String pass = sc.nextLine();
        System.out.println("Enter Your New Status unemployee/Employee");
        String status = sc.nextLine();

        String sql = "{call getUpdate(?,?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, email);
        cst.setString(2, pass);
        cst.setString(3, status);
        ResultSet rs = cst.executeQuery();

        if (rs != null) {
            System.out.println("Update Done");
        } else {
            System.out.println("Update Not Done");
        }
    }

    public void addJobDetails(Scanner sc, JobAdvertiseMent j) throws Exception {
        String sql = "insert into job (vacancy,C_name,Dsc,Eligibility) values (?,?,?,?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1, j.getVacancy());
        pst.setString(2, j.getC_name());
        pst.setString(3, j.getDiscrription());
        pst.setString(4, j.geteligibility());
        pst.executeUpdate();
        System.out.println("Insertion Done.");

    }

    public void Delete(Scanner sc) throws Exception {
        System.out.println("Enter Numner Of Your Advertisement");
        int i = sc.nextInt();
        String sql = "call DeleteRow(?)";
        CallableStatement cst = con.prepareCall(sql);
        cst.setInt(1, i);
        cst.executeQuery();
    }

    public void updateEmail(Scanner sc, String email) throws Exception {
        String sql = " {call UpdateEmail(?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        System.out.println("Enter New Email");
        cst.setString(1, sc.nextLine());
        cst.setString(2, email);
        int k = cst.executeUpdate();
        if (k != 0) {
            System.out.println("Email Updated ");
        } else {
            System.out.println("Error In Updation");
        }
    }

    public void updayePass(Scanner sc) throws Exception {
        System.out.println("Enter Your Old Password");
        String oldPass = sc.nextLine();

        System.out.println("Enter Your New Password");
        String newPass = sc.nextLine();

        String sql = "{Call UpdatePass (?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, oldPass);
        cst.setString(2, newPass);
        int k = cst.executeUpdate();
        if (k != 0)
            System.out.println("Update Done");
        else
            System.out.println("Try Again With Correct Pass");
    }

    public void Apporovel(Scanner sc) throws Exception {
        Jobs.apperovels(con, sc);
    }

}