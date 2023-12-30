    package com.example.plmcrs;

    import java.io.IOException;
    import java.sql.*;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;

    public class userModel {

        //User Model Variables
        static String strName, strEmail, strFirstName, strLastName, strAge, strGender, strContact,
                strBirthdate, strBlock, strID, strAddress, strCourse, strIDFormat, strSY, strCourseFormat,
                strYr, strFreshmanYr, strStdntType, strStatus;

        //Login Variables
        public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public static LocalDate currentDate = LocalDate.now();
        static String strUsername = null, strPassword = null, strDate = currentDate.format(dateFormatter);
        static String currentYear = strDate.substring(0,4);


        //Database setup
        private static Connection conn = null;
        private static PreparedStatement ps = null;
        private static ResultSet rs = null;


        //Clears user's information from all local variables upon log out
        public static void logOutUser() throws IOException {
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
            strIDFormat = null;
            strFreshmanYr = null;
            strBlock = null;
            strYr = null;
            strStdntType = null;
            strStatus = null;
            strUsername = null;
            strPassword = null;
            strDate = null;
            currentYear = null;
            Mainscreen.changeScene("login.fxml", "Login");
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
            try {
                ps = conn.prepareStatement("SELECT * FROM student WHERE student_no = ?");
                ps.setString(1, strIDFormat);
                rs = ps.executeQuery();
                while (rs.next()) {
                    strEmail = rs.getString("EMAIL");
                    strFirstName = rs.getString("FIRSTNAME");
                    strLastName = rs.getString("LASTNAME");
                    strName = strFirstName + " " + strLastName;
                    strAge = rs.getString("AGE");
                    strGender = rs.getString("GENDER");
                    strContact = rs.getString("CP_NUM");
                    strBirthdate = rs.getString("BDAY");
                    strAddress = rs.getString("ADDRESS");
                    strCourse = rs.getString("COURSE_CODE");
                    strCourseFormat = strCourse.substring(2, 4) + "%";
                    strFreshmanYr = strID.substring(0, 4);
                    strYr = String.valueOf(Integer.valueOf(currentYear) - Integer.valueOf(strFreshmanYr));
                }
                ps = conn.prepareStatement("SELECT CASE " +
                        "WHEN EXISTS (SELECT 1 FROM grade WHERE student_no = ? AND grade = 5) THEN 'Irregular' " +
                        "ELSE 'Regular' " +
                        "END AS student_type");
                ps.setString(1,strIDFormat);
                rs = ps.executeQuery();
                if (rs.next()) {
                    strStdntType = rs.getString("student_type");
                } else {
                    System.out.println("Student not found.");
                }

                if (strStdntType.equals("Regular")){
                    ps = conn.prepareStatement("SELECT * FROM vwstudentlist WHERE student_no = ?");
                    ps.setString(1, strIDFormat);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        strBlock = rs.getString("BLOCK_NO");
                    } else {
                        System.out.println("Student not found.");
                    }
                }

            } catch (SQLException | NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }


        //Label setters for admin dashboard
        private static void fetchAdminInfo() throws SQLException {
            try {
                ps = conn.prepareStatement("SELECT * FROM employee WHERE employee_id = ?");
                ps.setString(1,strID);
                rs = ps.executeQuery();
                while(rs.next()){
                    strEmail = rs.getString("EMAIL");
                    strFirstName= rs.getString("FIRSTNAME");
                    strLastName = rs.getString("LASTNAME");
                    strName = strLastName + ", " + strFirstName;
                    strGender = rs.getString("GENDER");
                    strBirthdate = rs.getString("BDAY");
                    strAddress = rs.getString("ADDRESS");
                    strContact = rs.getString("CP_NUM");
                    strStatus = rs.getString("STATUS");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        //Checks if the Irregular Student has an Existing Schedule
        public static boolean hasExistingSchedule() throws SQLException {
            ps = conn.prepareStatement("SELECT COUNT(*) FROM irreg_student_sched WHERE student_no = ?");
            ps.setString(1, userModel.strIDFormat);
            tableModel.tblrs = ps.executeQuery();
            tableModel.tblrs.next();
            int count = tableModel.tblrs.getInt(1);
            return count > 0;
        }


    }





