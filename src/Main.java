import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        Jobs j = new Jobs();
        Apply a = new Apply();
        ArrayList<Employee> emp = new ArrayList<>();
        EmployeeDataBase de = new EmployeeDataBase();
        boolean k1 = true;
        String orangeColor = "\u001B[38;5;208m";
        String redColor = "\u001B[31m";
        String YelloColor = "\u001B[33m";
        String blueColor = "\u001B[34m";
        String perpleColor = "\u001B[35m";

        String resetColor = "\u001B[0m";
        System.out.println(redColor + "--------------Welcome To JobPortal System------------------- "
                + resetColor);
        while (k1) {

            System.out.println(orangeColor + "╔═══════════════════════════════════════════════════════════╗");
            System.out.println("║ Press 1 : for Add advertisement for job                   ║");
            System.out.println("║ Press 2 : for register as user                            ║");
            System.out.println("║ Press 3 : For See And Approval Of Pending Status          ║");
            System.out.println("║ Press 4 : Apply For                                       ║");
            System.out.println("║ Press 5 : Exit                                            ║");
            System.out.println("╚═══════════════════════════════════════════════════════════╝" + resetColor);
            System.out.print("\nMake Your Choice : "
            );
            int i = sc.nextInt();
            sc.nextLine();
            if (i == 1) {

                // boolean b = true;
                boolean b1 = true;
                while (b1) {
                    listAction();
                    System.out.print("\nMake Your Choice : ");
                    int num = sc.nextInt();

                    switch (num) {
                        case 1:
                            j.addAdvertisement();
                            break;
                        case 2:
                            j.deleteAdvertise();
                            break;

                        case 3:
                            a.getAllUnEmployee();
                            break;
                        case 4:
                            b1 = false;
                            break;
                        default:
                            System.out.println("\nMake Correct Choice");
                            break;
                    }
                }
            } else if (i == 2) {

                boolean b = true;
                while (b) {
                    System.out.println(perpleColor + "\n-----------------------------------------");
                    System.out.println("-----:Employee Registration & Login:-----");
                    System.out.println("-----------------------------------------");
                    System.out.println("1. Register as an employee");
                    System.out.println("2. Employee Login");
                    System.out.println("3. get Your Details");
                    System.out.println("4. Update Status");
                    System.out.println("5. Exit");
                    System.out.println("-----------------------------------------" + resetColor);
                    System.out.print("\nMake Your Choice : ");
                    int empChoice = sc.nextInt();
                    sc.nextLine();

                    switch (empChoice) {
                        case 1:

                            System.out.print("\nEnter First name : ");
                            String first_name = sc.nextLine();
                            System.out.print("\nEnter Last name : ");
                            String last_name = sc.nextLine();
                            System.out.print("\nEnter Email id : ");
                            String email = sc.nextLine();
                            String mobile_no = "";
                            String password = "";
                            int cnt0 = 0;
                            int cnt1 = 0;
                            int cnt2 = 0;

                            while (true) {
                                System.out.print("Enter Mobile no. : ");
                                mobile_no = sc.nextLine();
                                if (mobile_no.length() == 10) {
                                    for (int k = 0; k < 10; k++) {
                                        if ((mobile_no.charAt(k) >= 48) && (mobile_no.charAt(k) <= 57)) {
                                            cnt0++;
                                        }
                                    }
                                    if (cnt0 == 10) {
                                        cnt1 = 1;
                                        break;
                                    } else {
                                        System.out.println("Invalid Mobile No.!");
                                    }
                                } else {
                                    System.out.println("Invalid number!");
                                    System.out.println("Please enter 10 digit number!");
                                }
                            }

                            if (cnt1 == 1) {
                                while (true) {
                                    int cnt3 = 0;
                                    System.out.print("\nCreate Password : ");
                                    password = sc.nextLine();
                                    int lower_case = 0;
                                    int upper_case = 0;
                                    int digit_case = 0;
                                    for (int y = 0; y < password.length(); y++) {
                                        for (char z = 65; z <= 90; z++) {
                                            if (password.charAt(y) == z) {
                                                upper_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (upper_case == 1) {
                                        cnt3++;
                                    }
                                    for (int y = 0; y < password.length(); y++) {
                                        for (char z = 97; z <= 122; z++) {
                                            if (password.charAt(y) == z) {
                                                lower_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (lower_case == 1) {
                                        cnt3++;
                                    }
                                    for (int y = 0; y < password.length(); y++) {
                                        for (char z = 48; z <= 57; z++) {
                                            if (password.charAt(y) == z) {
                                                digit_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (digit_case == 1) {
                                        cnt3++;
                                    }
                                    if ((password.length() >= 8) && (password.length() <= 15)) {
                                        cnt3++;
                                    }
                                    System.out.print("Enter Confirm Password : ");
                                    String con_pass = sc.nextLine();
                                    if ((con_pass.equals(password)) && (cnt3 == 4)) {
                                        cnt2++;
                                        System.out.println("\nYour password must be created.");
                                        break;
                                    } else {
                                        System.out.println("\nIncorrect Password!");
                                        System.out.println("Please try again\n");
                                    }
                                }
                            }

                            if (cnt2 == 1) {
                                int ix = r.nextInt(100);
                                int iy = r.nextInt(100);
                                int iz = r.nextInt(100);
                                String e = iz + "" + "" + iy + "" + ix;
                                int e_id = Integer.parseInt(e);
                                System.out.println("\nGenerated your Employee-Id : " + e);

                                Employee emp_detail = new Employee(e_id, first_name, last_name, email, mobile_no,
                                        password);
                                emp.add(emp_detail);
                                de.addEmployeeData(emp_detail);
                                System.out.println("Employee registered successfully!");

                            } else {
                                System.out.println("Employee registered unsuccessful,Please try again!");
                            }

                            break;

                        case 2:

                            boolean loginIn = false;
                            int attempts = 0;
                            String eemail, pass;
                            while (!loginIn && attempts < 3) {
                                System.out.println("Enter Email ");
                                eemail = sc.nextLine();
                                System.out.println("Enter Pass");
                                pass = sc.nextLine();
                                boolean ch = false;

                                ResultSet rst = a.Login(eemail, pass);
                                if (!rst.equals(null)) {
                                    while (rst.next()) {
                                        System.out.println(redColor
                                                + "--------------------Your Details------------------" + resetColor);
                                        System.out.println(blueColor + "Id := " + rst.getInt(1));
                                        System.out.println("First Name := " + rst.getString(2));
                                        System.out.println("Last Name := " + rst.getString(3));
                                        System.out.println("Email := " + rst.getString(4));
                                        System.out.println("Mobile := " + rst.getString(5));
                                        System.out.println("Status := " + rst.getString(7));
                                        System.out.println("Compnay := " + rst.getString(8));
                                        System.out.println(
                                                "---------------------------------------------------" + resetColor);
                                        ch = true;
                                    }
                                    boolean o = true;
                                    while (o && ch) {
                                        System.out.println(redColor +
                                                "------------------Action IN Login You Can Perform------------------"
                                                + resetColor);
                                        System.out.println(YelloColor + "1. For Apply Jobb ");
                                        System.out.println("2. For See Your Details");
                                        System.out.println("3. For Update  Email");
                                        System.out.println("4. For Update Pass");
                                        System.out.println("5. Exit" + resetColor);
                                        int n = sc.nextInt();
                                        sc.nextLine();
                                        switch (n) {
                                            case 1:
                                                a.ApplyLoging(eemail, pass, sc);
                                                break;
                                            case 2:
                                                de.loginDetails(eemail, pass);
                                                break;

                                            case 3:
                                                de.updateEmail(sc, eemail);
                                                break;
                                            case 4:
                                                de.updayePass(sc);
                                                break;
                                            case 5:
                                                System.out.println(
                                                        redColor + "----------------------------------------------");
                                                System.out.println("----------------Logging Out------------------");
                                                System.out.println(
                                                        "----------------------------------------------" + resetColor);
                                                o = false;
                                            default:
                                                System.out.println(
                                                        redColor + "------------------Make Correct Choice--------------"
                                                                + resetColor);
                                                break;
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.println("recourd Not Found");
                                    attempts--;
                                }
                            }

                            break;
                        case 3:
                            de.getYourDetails(sc);
                            break;

                        case 4:
                            de.updateYourStatus(sc);
                            break;
                        case 5:
                            b = false;
                            break;

                        default:
                            System.out.println(redColor
                                    + "----------------------Make the correct choice-----------------" + resetColor);
                            break;

                    }
                }
            } else if (i == 3) {
                de.Apporovel(sc);
            } else if (i == 4) {
                a.ApplyJob(sc);
            } else if (i == 5) {
                System.out.println(redColor + "--------------------Thank You-------------------------" + resetColor);
                break;
            } else {
                System.out.println("----------------------------------------------");
                System.out.println("Make Correct Choice");
                System.out.println("----------------------------------------------");
            }
        }
    }

    static void listAction() {
        String Color = "\u001B[36m";
        String resetColor = "\u001B[0m";
        System.out.println(Color + "-------------------------------------");
        System.out.println("1. Enter Job Advertisement");
        System.out.println("2. delete Advertisement");
        System.out.println("3.Get All unEmployee List");
        System.out.println("4.Exit");
        System.out.println("------------------------------------- " + resetColor);

    }

}

class Employee {
    String first_name;
    String last_name;
    String mobile_no;
    String email;
    String password;
    int e_id;

    public Employee(int e_id, String first_name, String last_name, String email, String mobile_no, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_no = mobile_no;
        this.email = email;
        this.password = password;
        this.e_id = e_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getE_id() {
        return e_id;
    }

    public String toString() {
        return "Employee [ " + "e_id = " + e_id + ", first_name = " + first_name + ", last_name = " + last_name
                + ", mobile_no = " + mobile_no
                + ", email = " + email + ", password = " + password + " ]";
    }

}
