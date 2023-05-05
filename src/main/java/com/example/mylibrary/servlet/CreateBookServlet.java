package com.example.mylibrary.servlet;

import com.example.mylibrary.constants.SharedConstants;
import com.example.mylibrary.manager.AuthorManager;
import com.example.mylibrary.manager.BookManager;
import com.example.mylibrary.manager.UserManager;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/createBook")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class CreateBookServlet extends HttpServlet {
    private BookManager bookManager = new BookManager();
    private AuthorManager authorManager = new AuthorManager();
    private UserManager userManager = new UserManager();
    private User user = new User();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorManager.getAll();
        req.setAttribute("authorList", authors);
        List<User> users = userManager.getAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("WEB-INF/createBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));
        int authorId = Integer.parseInt(req.getParameter("authorId"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        Part picturePart = req.getPart("picture");
        String picName = null;
        if (picturePart != null && picturePart.getSize() > 0) {
            picName = System.nanoTime() + "_" + picturePart.getSubmittedFileName();
            picturePart.write(SharedConstants.UPLOAD_FOLDER + picName);
        }
        Book book = Book.builder()
                .title(title)
                .description(description)
                .price(price)
                .picName(picName)
                .author(authorManager.getByID(authorId))
                .user(userManager.getByID(userId))
                .build();
        bookManager.save(book);
        resp.sendRedirect("/books");
    }
}
