package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // JDBC接続情報
	private static final String url = "jdbc:mysql://localhost:3306/test_db";
    private static final String user = "root";
    private static final String pass = "password";

    // データベース接続を取得するメソッド
    public static Connection getConnection() throws SQLException {
        try {
            // JDBCドライバのロード（MySQLの場合）
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }

        // データベース接続を返す
        return DriverManager.getConnection(url, user, pass);
    }
}
