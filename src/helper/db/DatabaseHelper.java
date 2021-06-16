package helper.db;


import java.sql.*;

public class DatabaseHelper {

    private Connection conn = null;
    private final String url = "jdbc:mysql://localhost:3306/coffee_cafe_data?serverTimezone=UTC";

    public DatabaseHelper() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeSelectQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet temp = stmt.executeQuery(query);
        return temp;
    }

    public int executeQuery(String query) throws SQLException {
        Statement stmt = conn.createStatement();
        int result = stmt.executeUpdate(query);
        return result;
    }


    public boolean checkLogin(String username, String password) {

        String query = "SELECT * FROM `users` WHERE username = '" + username + "' AND password = '" + password + "'";

        try {
            Statement stmt = conn.createStatement();
            ResultSet temp = stmt.executeQuery(query);

            boolean isFound = false;
            while (temp.next()) {
                isFound = true;
                break;
            }
            return isFound;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public Connection getConnection() {
        return conn;
    }
}
