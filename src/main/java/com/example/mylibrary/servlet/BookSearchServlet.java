package com.example.mylibrary.servlet;

import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class BookSearchServlet extends HttpServlet {

  private   BookManager bookManager = new BookManager();
  private User user =new User();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String title = req.getParameter("title");
        List<Book> books = bookManager.bookSearch(title);
        req.setAttribute("books",books);
        req.getRequestDispatcher("WEB-INF/search.jsp");


        }
    }
