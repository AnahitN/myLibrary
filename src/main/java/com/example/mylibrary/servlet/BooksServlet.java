package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;
import com.example.mylibrary.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksServlet extends HttpServlet {
   private static BookManager bookManager = new BookManager();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String keyword = req.getParameter("keyword");
        List<Book> result = null;
        if (keyword == null || keyword.equals("")) {
          result  = bookManager.getAll();
        } else {
            result = bookManager.bookSearch(keyword);
        }
        if (UserType.USER.equals(user.getUserType())){
            result = bookManager.getByUser(user);
        }


        req.setAttribute("books",result);
        req.getRequestDispatcher("WEB-INF/books.jsp").forward(req,resp);
    }
}
