<%@ page import="java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>MySQLからデータを表示</title>
</head>
<body>
    <h1>データベースからのデータ表示</h1>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>タイトル</th>
            <th>メッセージ</th>
        </tr>
        <%
            // MySQL接続情報
            String url = "jdbc:mysql://localhost:3306/test_db";
            String user = "root";
            String password = "password";

            // データベース接続とクエリ実行
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                // JDBCドライバのロード（JDBC 4.0以降は省略可能）
                Class.forName("com.mysql.cj.jdbc.Driver");

                // 接続を確立
                conn = DriverManager.getConnection(url, user, password);

                // SQLを実行
                String query = "SELECT id, title, message FROM posts";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                // 結果を表示
                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt("id") %></td>
            <td><%= rs.getString("title") %></td>
            <td><%= rs.getString("message") %></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                out.println("<p>Error: " + e.getMessage() + "</p>");
                e.printStackTrace();
            } finally {
                // リソースを閉じる
                if (rs != null) try { rs.close(); } catch (SQLException ignored) {}
                if (stmt != null) try { stmt.close(); } catch (SQLException ignored) {}
                if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
            }
        %>
    </table>
</body>
</html>
