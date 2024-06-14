import java.beans.Statement;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

class JobAdvertiseMent {
    int vacancy;
    String c_name;
    String discrription;
    String eligibility;

    public JobAdvertiseMent(int vacancy, String c_name, String discrription, String eligibility) {
        this.vacancy = vacancy;
        this.c_name = c_name;
        this.discrription = discrription;
        this.eligibility = eligibility;
    }

    public int getVacancy() {
        return vacancy;
    }

    public String getC_name() {
        return c_name;
    }

    public String getDiscrription() {
        return discrription;
    }

    public String geteligibility() {
        return eligibility;
    }

    @Override
    public String toString() {
        return "JobAdvertiseMent [vacancy=" + vacancy + ", c_name=" + c_name + ", discrription=" + discrription + "]";
    }

}

class Jobs {
    ArrayList<JobAdvertiseMent> jobs;
    Scanner sc = new Scanner(System.in);

    Jobs() throws Exception {
        jobs = new ArrayList<>();
    }

    public void addAdvertisement() throws Exception {
        JobAdvertiseMent j = addList();
        if (j == null) {
            System.out.println("Your advertisement is not done please try again");
        } else {
            System.out.println("Submisstion done ");
            System.out.println("--------------Advertisement-----------------");
            System.out.println("Company name := " + j.getC_name());
            System.out.println("Availble Vacancy := " + j.getVacancy());
            System.out.println("Descrption := " + j.getDiscrription());
            System.out.println("Eligibility := " + j.geteligibility());
            System.out.println("--------------------------------------------");
        }
        // addFile(j);
    }

    // private void addFile(JobAdvertiseMent j) throws Exception {
    // BufferedWriter bw = new BufferedWriter(new FileWriter("src//files//res.txt",
    // true));
    // String line = j.toString();
    // bw.write(line);
    // bw.newLine();
    // bw.close();
    // }
    static String f_name;
    static String c_name;

    public JobAdvertiseMent addList() throws Exception {

        System.out.println("Enter Name Your Company");
        String name = sc.nextLine();

        if (new Apply().checName(name)) {
            System.out.println("Enter Vaccancy For Your company");
            int vac = sc.nextInt();
            sc.nextLine();
            String dis = sc.nextLine();

            System.out.println("Enter Suiteble eligibility ");
            String eligibility = sc.nextLine();
            JobAdvertiseMent j = new JobAdvertiseMent(vac, name, dis, eligibility);
            jobs.add(j);
            new EmployeeDataBase().addJobDetails(sc, j);
            return j;
        } else {
            System.out.println("Check Compnay Name");
            return null;
        }

    }

    public void deleteAdvertise() throws Exception {
        new EmployeeDataBase().Delete(sc);
    }

    static int adno;

    public static void apperovels(Connection con, Scanner sc) throws Exception {
        System.out.println("Enter Your Advertisement Number To See Data ");
        adno = sc.nextInt();
        String sql = "{call seeAppication(?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setInt(1, adno);
        ResultSet rs = cst.executeQuery();
        int i = 0;
        if (!rs.equals(null)) {
            while (rs.next()) {
                System.out.println("-------Appliction For " + rs.getString(5) + " Compnay---------");
                System.out.println("Application No := " + rs.getInt(1));
                System.out.println("Employee Id := " + rs.getInt(2));
                System.out.println("Advertisement Id := " + rs.getInt(3));
                System.out.println("Name := " + rs.getString(4));
                System.out.println("Status := " + rs.getString(6));
                f_name = rs.getString(4);
                c_name = rs.getString(5);
                System.out.println("--------------------------------------------------------");
                if (i > 0) {
                    return;
                }
                TakeAction(con, sc);
                i++;
            }
        } else {
            System.out.println("----------No Data Found-----------");
        }

    }

    private static void TakeAction(Connection con, Scanner sc) throws Exception {
        System.out.println("Press 1 : For Approvel");
        System.out.println("Press 2 : Remove Application");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                Hire(sc, con);
                break;
            case 2:
                unHire(sc, con);
            default:
                System.out.println("Make Correct Choice And Try  Again Later...........................");
        }
    }

    private static void unHire(Scanner sc2, Connection con) throws Exception {
        String sql = "{call DisSelect(?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, f_name);
        cst.setString(2, c_name);
        int k = cst.executeUpdate();
        if (k != 0) {
            System.out.println("Done");
        } else {
            System.out.println("Error IN....................");
        }
    }

    private static void Hire(Scanner sc, Connection con) throws Exception {
        String sql = "{call HireEmployee(?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setInt(1, adno);
        int k = cst.executeUpdate();
        if (k != 0) {
            new Apply().UPdateEmployee(f_name);
            setComp(con);
        } else {
            System.out.println("Error In handling ..........");
            return;
        }
    }

    private static void setComp(Connection con) throws Exception {
        String sql = "{call UpdateCom(?,?)}";
        CallableStatement cst = con.prepareCall(sql);
        cst.setString(1, f_name);
        cst.setString(2, c_name);
        int k = cst.executeUpdate();
        if (k != 0) {
            System.out.println("Done");
        } else {
            System.out.println("Error In Handling................");
        }
    }

}