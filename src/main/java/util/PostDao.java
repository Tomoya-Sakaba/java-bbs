package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Post;

public class PostDao {
    // 投稿一覧を取得するメソッド
    public List<Post> showAllPosts() {
        // 投稿を格納するリスト
        List<Post> posts = new ArrayList<>();

        // SQL文
        String sql = "SELECT * FROM posts";

        // try-with-resourcesを使ってリソースを自動でクローズ
        try (Connection con = Database.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            // SQL実行結果を処理
            while (rs.next()) {
                // 投稿データをセット
                Post post = new Post();
                post.setId(rs.getInt("id"));          // IDの取得
                post.setTitle(rs.getString("title")); // タイトルの取得
                post.setMessage(rs.getString("message")); // メッセージの取得

                // リストに追加
                posts.add(post);
            }

        } catch (SQLException e) {
            System.err.println("Database connection or query failed!");
            e.printStackTrace();
        }

        return posts;
    }
    
    public boolean addPost(Post post) {
        // SQL文の準備
        String sql = "INSERT INTO posts (title, message) VALUES (?, ?)";

        // try-with-resourcesを使って自動的にリソースをクローズ
        try (Connection con = Database.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            // 投稿内容をPreparedStatementにセット
            ps.setString(1, post.getTitle());   // タイトルを設定
            ps.setString(2, post.getMessage()); // メッセージを設定

            // SQLを実行して、結果を確認
            int result = ps.executeUpdate(); // 実行結果
            return result > 0; // 登録成功 → true
        } catch (SQLException e) {
            System.err.println("Database connection or query failed!");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error occurred!");
            e.printStackTrace();
        }
        
        return false; // 登録失敗 → false
    }
    
    public boolean deletePost(int postId) {
        // データベース接続の設定を行い、指定されたIDの投稿を削除するSQLを実行
        String sql = "DELETE FROM posts WHERE id = ?";
        try (Connection conn = Database.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, postId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;  // 削除成功 → true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // 削除失敗 → false
        }
    }
}

