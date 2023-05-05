package com.example.mylibrary.servlet;

import com.example.mylibrary.constants.SharedConstants;
import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/updateBook")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class UpdateBookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();
    private UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Author> authors = authorManager.getAll();
        Book book = bookManager.getById(id);
        List<User> users = userManager.getAll();
        req.setAttribute("book", book);
        req.setAttribute("authorList", authors);
        req.setAttribute("users",users);
        req.getRequestDispatcher("WEB-INF/updateBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        Author author = authorManager.getByID(Integer.parseInt(req.getParameter("authorId")));
        User user = userManager.getByID(Integer.parseInt(req.getParameter("userId")));
        Part picturePart = req.getPart("picture");
        String picName = null;
        if (picturePart != null && picturePart.getSize() > 0) {
            picName = System.nanoTime() + "_" + picturePart.getSubmittedFileName();
            picturePart.write(SharedConstants.UPLOAD_FOLDER + picName);
        }
        Book book = new Book(id, title, description, price, picName,author,user);
        bookManager.update(book);
        resp.sendRedirect("/books");
    }
}
