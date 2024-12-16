package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.PostDao;

@WebServlet("/delete")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストエンコーディングをUTF-8に設定
        request.setCharacterEncoding("UTF-8");

        // 削除する投稿のIDを取得
        String postIdStr = request.getParameter("postId");

        if (postIdStr != null) {
            int postId = Integer.parseInt(postIdStr);

            // PostDaoオブジェクトを作成して削除処理を呼び出す
            PostDao postDao = new PostDao();
            boolean isDeleted = postDao.deletePost(postId);

            // 削除結果をリクエストにセット
            String resultMessage = isDeleted ? "削除しました" : "削除に失敗しました";
            request.setAttribute("message", resultMessage);
        }

        // 投稿一覧画面にリダイレクト
        response.sendRedirect("index");
    }
}
