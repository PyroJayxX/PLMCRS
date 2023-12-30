    package com.example.plmcrs;

    import java.io.IOException;
    import java.sql.*;

    import static java.lang.Integer.parseInt;
    import static java.lang.Integer.valueOf;

    public class userModel {

        //User Model Variables
        static String strName, strEmail, strFirstName, strLastName, strAge, strGender, strContact,
                strBirthdate, strID, strAddress, strCourse, strIDFormat, strSY, strCourseFormat, strYr, strFreshmanYr;
        static int currentYear = 2025;


        //Login Variables
        static String strUsername = null, strPassword = null;


        //Database setup
        private static Connection conn = null;
        private static PreparedStatement ps = null;
        private static ResultSet rs = null;


        //Clears user's information from all local variables upon log out
        public static void logOutUser() {
            strName = null;
            strFirstName = null;
            strLastName = null;
            strAge = null;
            strGender = null;
            strContact = null;
            strBirthdate = null;
            strID = null;
            strAddress = null;
            strCourse = null;
            strCourseFormat = null;
            strSY = null;
        }

        //Username and Password Checker
        public static void logInUser() throws SQLException {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enrollment", "root", "1234");
                ps = conn.prepareStatement("SELECT * FROM users WHERE userID = ?");
                ps.setString(1, strID);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String storedPassword = rs.getString("userPassword");
                    if (storedPassword != null && storedPassword.equals(strPassword)) {
                        handleSuccessfulLogin(rs.getString("userType"));
                    } else {
                        Mainscreen.popDialogue("Please re-enter your password.", "Warning!", "Incorrect Password.");
                    }
                } else {
                    Mainscreen.popDialogue("Please re-enter your username.", "Warning!", "Username does not exist in the database.");
                }
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        // Directs which dashboard to open based on user type
        private static void handleSuccessfulLogin(String userType) throws SQLException, IOException {
            switch (userType) {
                case "student":
                    fetchStudentInfo();
                    Mainscreen.changeScene("studentDashboard.fxml", "Student Dashboard");
                    break;
                case "admin":
                    fetchAdminInfo();
                    Mainscreen.changeScene("adminDashboard.fxml", "Admin Dashboard");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + userType);
            }
        }

        // Label setters for student dashboard
        private static void fetchStudentInfo() throws SQLException {
            try (PreparedStatement studentPs = conn.prepareStatement("SELECT * FROM student WHERE student_no = ?")) {
                studentPs.setString(1, strIDFormat);

                try (ResultSet studentRs = studentPs.executeQuery()) {
                    while (studentRs.next()) {
                        strEmail = studentRs.getString("EMAIL");
                        strFirstName = studentRs.getString("FIRSTNAME");
                        strLastName = studentRs.getString("LASTNAME");
                        strName = strFirstName + " " + strLastName;
                        strAge = studentRs.getString("AGE");
                        strGender = studentRs.getString("GENDER");
                        strContact = studentRs.getString("CP_NUM");
                        strBirthdate = studentRs.getString("BDAY");
                        strAddress = studentRs.getString("ADDRESS");
                        strCourse = studentRs.getString("COURSE_CODE");
                        strCourseFormat = strCourse.substring(2, 4) + "%";
                        strFreshmanYr = strID.substring(0,4);
                        strYr = String.valueOf(currentYear-Integer.valueOf(strFreshmanYr));
                    }
                }
            }
        }

        //Label setters for admin dashboard
        private static void fetchAdminInfo() throws SQLException {
        }


    }





