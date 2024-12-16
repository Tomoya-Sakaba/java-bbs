<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@page import="model.Post"%>
<%@page import="java.util.List"%>
<html lang="ja">
<head>
	<meta charset="UTF-8">
    <title>投稿一覧</title>
</head>
<body>
    <h1>掲示板</h1>
    
    <% 
    	/* 現状意味ない */
	    String message = (String) request.getAttribute("message");
	    if (message != null) {
	        out.println("<p>" + message + "</p>");
	    }
	%>
    
	<form action="input" method="post" accept-charset="UTF-8">
		<label for="title">Title:</label><br>
		<input type="text" id="title" name="title" required><br><br>
		
		<label for="message">Message:</label><br>
		<textarea id="message" name="message" required></textarea><br><br>
		
		<input type="submit" value="投稿">
	</form>
    
<% 
    List<Post> posts = (List<Post>) request.getAttribute("posts");
    if (posts == null || posts.isEmpty()) { 
%>
    <p>No posts found.</p>
<% 
    } else { 
%>
	<% for (Post post : posts) { %>
		<h2><%= post.getId() %>.<%= post.getTitle() %></h2>
		<div><%= post.getMessage() %></div>
		<form action="delete" method="post">
            <input type="hidden" name="postId" value="<%= post.getId() %>">
            <input type="submit" value="削除">
        </form>
	<% 
		} 
	%>
    </table>
<% 
    } 
%>
</body>
</html>
