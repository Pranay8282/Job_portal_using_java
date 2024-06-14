import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.mysql.cj.xdevapi.PreparableStatement;

class Apply {
    Connection con;
    Employee e;
    int id;
    String F_name, l_name, email, pass, mobile, status, compn;
    Scanner sc;
    String comnayGet;
    int ck;

    Apply() throws Exception {
        String s1 = "com.mysql.cj.jdbc.Driver";
        Class.forName(s1);
        String dbname = "root";
        String dburl = "jdbc:mysql://localhost:3306/jobportals";
        con = DriverManager.getConnection(dburl, dbname, "");
    }

    public void ApplyJob(Scanner sc) throws Exception {
        ck = 0;
        this.sc = sc;
        if (ck != 0) {
            return;
        }
        System.out.println("Enter Your Email To Get Details");
        String email1 = sc.nextLine();
        System.out.println("Enter Pass");
        String pass1 = sc.nextLine();
        searchDetails(email1, pass1);
        AvailbleLisT();

        System.out.println("Enter Number From List");
        int num = sc.nextInt();
        // next Line.................................
        sc.nextLine();
        System.out.println("Enter Your Graduations");
        String grad = sc.nextLine();
        GetJob(num, grad);
    }

    void ApplyLoging(String emil, String pass, Scanner sc) throws Exception {
        ck = 0;
        if (ck != 0) {
            return;
        }
        searchDetails(emil, pass);
        AvailbleLisT();

        System.out.println("Enter Number From List");
        int num = sc.nextInt();
        // next Line.................................
        sc.nextLine();
        System.out.println("Enter Your Graduations");
        String grad = sc.nextLine();
        GetJob(num, grad);
    }

    String Elegibilty;
    int vac;

    public void GetJob(int num, String grad) throws Exception {

        String sql = "call getJob(?,?,?)";
        CallableStatement cst = con.prepareCall(sql);
        cst.setInt(1, num);
        cst.executeQuery();
        Elegibilty = cst.getString(3);
        vac = cst.getInt(2);
        if (Elegibilty == null) {
            System.out.println("Choose Correct Option .............");
            return;
        }
        check(num, grad);
    }

    public void getAllUnEmployee() throws Exception {
        String sql = "call getUnemploee()";
        CallableStatement cst = con.prepareCall(sql);
        ResultSet rs = cst.executeQuery();
        if (!rs.equals(null)) {
            while (rs.next()) {
                System.out.println("Name := " + rs.getString(2) + " " + rs.getString(3));
                System.out.println("Mobile No := " + rs.getString(5));
                System.out.println("Email := " + rs.getString(4));
                System.out.println("Status:= " + rs.getString(7));
                System.out.println("Compnay := " + rs.getString(8));
                System.out.println("-----------------------------------------------------------------");
            }
        } else {
            System.out.println("----------------------No Unemployee List --------------------------");
        }

    }

    public void check(int num, String grad) throws Exception {
        if (grad == null) {
            System.out.println("Try Again.......");
        } else {
            if (Elegibilty.equals(grad)) {
                if (vac > 1) {
                    vac = vac - 1;
                    String sql = "{call getJobs(?,?,?)}";
                    CallableStatement cst = con.prepareCall(sql);
                    cst.setInt(1, num);
                    cst.setInt(2, vac);
                    cst.executeQuery();
                    comnayGet = cst.getString(3);
                    ApplyTablePendig(sc, num, grad, comnayGet);
                } else {
                    System.out.println("Vacany Is Over");
                }
            } else {
                System.out.println("You are Not Eligible");
            }
        }
    }

    private void ApplyTablePendig(Scanner sc, int num, String Grad, String comget) throws Exception {
        String sql = "{call ApplyPendingValues(?,?,?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setInt(1, id);
        cst.setInt(2, num);
        cst.setString(3, F_name);
        cst.setString(4, comget);
        int k = cst.executeUpdate();
        if (k != 0) {
            System.out.println("Your Status  Is Pending");
        } else {
            System.out.println("Values NOt Found");
        }
    }

    public void UPdateEmployee(String firstName) throws Exception {
        ck = 1;
        String sql = "call getUpdateEmployee(?)";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, firstName);
        cst.execute();

        sql = "call getEmployeeDetails(?)";

        CallableStatement ct = con.prepareCall(sql);
        ct.setString(1, firstName);
        ResultSet rs = ct.executeQuery();
        if (!rs.equals(null)) {

            while (rs.next()) {
                System.out.println("----------------------Updated Account IS-----------------------");
                System.out.println("Name := " + rs.getString(2) + " " + rs.getString(3));
                System.out.println("Mobile No := " + rs.getString(5));
                System.out.println("Email := " + rs.getString(4));
                System.out.println("Status:= " + rs.getString(7));
                System.out.println("Compnay := " + rs.getString(8));

                System.out.println("-----------------------------------------------------------------");
            }
        } else {
            System.out.println("Details Not Found............");
            return;
        }
    }

    public void searchDetails(String email1, String pass1) throws Exception {
        String sql = "{call getAllEmployee(?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, email1);
        cst.setString(2, pass1);

        ResultSet rs = cst.executeQuery();
        ResultSet rs1 = rs;
        if (!rs1.next()) {
            System.out.println("Details Not Found");
            ApplyJob(sc);
        } else {
            id = rs.getInt(1);
            F_name = rs.getString(2);
            l_name = rs.getString(3);
            email = rs.getString(4);
            mobile = rs.getString(5);
            pass = rs.getString(6);
            status = rs.getString(7);
            compn = rs.getString(8);

            System.out.println("----------------------------------------------------");
            System.out.println("-----------------Welcome := " + F_name + "------------");
            System.out.println("----------------------------------------------------");

            e = new Employee(id, F_name, l_name, email, mobile, pass1);
        }
    }

    public void AvailbleLisT() throws Exception {
        String sql = "{call getListAvailableJob()}";
        CallableStatement cst = con.prepareCall(sql);
        ResultSet rs = cst.executeQuery();
        ResultSet rs1 = rs;
        if (rs1.next()) {
            while (rs.next()) {
                System.out.println("--------------------**---------------");
                System.out.println("No := " + rs.getInt(1));
                System.out.println("Name := " + rs.getString(3));
                System.out.println("Vacancy := " + rs.getInt(2));
                System.out.println("Caption := " + rs.getString(4));
                System.out.println("Elegibilty := " + rs.getString(5));
                System.out.println("--------------------**---------------");
            }
        } else {
            System.out.println("-----------No Data Found------------");
        }
    }

    ResultSet Login(String emi, String pass) throws Exception {
        String sql = "{call getAllEmployee(?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, emi);
        cst.setString(2, pass);
        ResultSet rst = cst.executeQuery();
        if (rst.equals(null)) {
            return null;
        } else {
            return rst;
        }
    }

    boolean checName(String name) throws Exception {

        String sql = "select * from job where C_name = '" + name + "';";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if (!rs.next()) {
            return true;
        } else {
            return false;
        }
    }

}