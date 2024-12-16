package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import util.PostDao;

@WebServlet("/input")
public class InputPostServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		// リクエストエンコーディングをUTF-8に設定
	    request.setCharacterEncoding("UTF-8");
		// フォームから送信されたデータを取得
        String title = request.getParameter("title");
        String message = request.getParameter("message");

        // 新しいPostオブジェクトを作成
        Post post = new Post();
        post.setTitle(title);
        post.setMessage(message);

        // PostDaoオブジェクトを作成して登録処理を呼び出す
        PostDao postDao = new PostDao();
        // addPostメソッドを使って登録
//        boolean isSuccess = postDao.addPost(post);
        postDao.addPost(post);

        // 登録結果メッセージをリクエストにセットしようと思ったがリダイレクトをすると意味ない
//        if (isSuccess) {
//            request.setAttribute("message", "登録成功しました");
//        } else {
//            request.setAttribute("message", "登録失敗しました");
//        }
        
        // 投稿一覧画面にリダイレクト
        response.sendRedirect("index");
	}
}
