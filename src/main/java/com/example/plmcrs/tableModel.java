package com.example.plmcrs;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.sql.*;


public class tableModel {

    //TableView Variables
    static String sem, sy, strSearch="%", strSubjCode, strSubjName, strSched, strBlock, strDay, strTime, strRoom, strType;

    //Database setup
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/enrollment", "root", "1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
    static ResultSet tblrs = null;

    public static void resultSetToTableView(ResultSet rs, TableView<ObservableList<String>> tableView) throws SQLException {
        try {
            ObservableList<String> columnNames = FXCollections.observableArrayList();

            //Clears the table from existing data
            tableView.getColumns().clear();
            tableView.getItems().clear();

            // Get the column names
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();

            for (int column = 1; column <= numberOfColumns; column++) {
                columnNames.add(metaData.getColumnLabel(column));
            }

            // Create columns
            for (String columnName : columnNames) {
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                column.setCellValueFactory(param -> {
                    int columnIndex = columnNames.indexOf(param.getTableColumn().getText());
                    String cellValue = "";
                    try {
                        if (param.getValue() != null && columnIndex >= 0 && columnIndex < param.getValue().size()) {
                            cellValue = param.getValue().get(columnIndex);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return new SimpleStringProperty(cellValue);
                });
                tableView.getColumns().add(column);
            }

            // Get the data
            ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= numberOfColumns; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }

            // Set items to the TableView
            tableView.setItems(data);

            // Resize columns based on content
            resizeColumns(tableView);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void resizeColumns(TableView<ObservableList<String>> tableView) {
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        int totalColumns = tableView.getColumns().size();
        double tableViewWidth = tableView.getWidth();
        double columnWidth = tableViewWidth / totalColumns;

        for (TableColumn<ObservableList<String>, ?> column : tableView.getColumns()) {
            column.setPrefWidth(columnWidth);
        }
    }

    public static void getStdntGrade() throws SQLException {
        ps = conn.prepareStatement("SELECT school_year, semester, block," +
                "subject_code, description, grade, remark" +
                " FROM vwgradereport WHERE student_no = ? AND semester = ? AND school_year = ?");
        ps.setString(1, userModel.strIDFormat);
        ps.setString(2, sem);
        ps.setString(3, sy);
        tblrs = ps.executeQuery();
    }

    public static void getSubjectList() throws SQLException {
        ps = conn.prepareStatement("SELECT * FROM vwstudentreg WHERE block LIKE ? AND subject_description LIKE ?");
        ps.setString(1, userModel.strCourseFormat);
        ps.setString(2, strSearch);
        tblrs = ps.executeQuery();
    }

    public static void getApprvTbl() throws SQLException {
        ps = conn.prepareStatement("SELECT subject_code, subject_description, day, time, room, type " +
                "FROM sched_approval WHERE student_no = ?");
        ps.setString(1,userModel.strIDFormat);
        tblrs = ps.executeQuery();
    }


    public static void addToSched() throws SQLException {
        ps = conn.prepareStatement("INSERT INTO sched_approval VALUES (?,?,?,?,?,?,?,?,?)");
        ps.setString(1, userModel.strIDFormat);
        ps.setString(2, userModel.strLastName);
        ps.setString(3, userModel.strFirstName);
        ps.setString(4, strSubjCode);
        ps.setString(5, strSubjName);
        ps.setString(6, strDay);
        ps.setString(7, strTime);
        ps.setString(8, strRoom);
        ps.setString(9, strType);
        ps.execute();
        Mainscreen.popDialogue("Please see schedule to review changes.", "Success", "Subject successfully registered!");
    }

    public static void removeFromSched() throws SQLException{
        ps = conn.prepareStatement("DELETE FROM sched_approval WHERE student_no = ? AND subject_code = ? " +
                "AND subject_description = ? AND day = ? AND time = ? AND room = ? AND type = ?");
        ps.setString(1, userModel.strIDFormat);
        ps.setString(2, strSubjCode);
        ps.setString(3, strSubjName);
        ps.setString(4, strDay);
        ps.setString(5, strTime);
        ps.setString(6, strRoom);
        ps.setString(7, strType);
        ps.execute();
        Mainscreen.popDialogue("Please navigate to REGISTER to add more subjects.", "Success", "Subject successfully removed.");
    }
}