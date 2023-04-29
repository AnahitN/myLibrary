package com.example.mylibrary.manager;

import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private static Connection connection = DBConnectionProvider.getInstance().getConnection();
    private AuthorManager authorManager = new AuthorManager();

    public void save(Book book) {
        String sql = "INSERT INTO book (title,description,price,author_id) VALUES (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setInt(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
            System.out.println("book added into db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
            while (resultSet.next()) {

                books.add(getBookFromResultSet(resultSet));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public static void update(Book book) {
        String sql = "UPDATE book SET title = ?, description = ?, price =? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate(String.format(sql, book.getTitle(), book.getDescription(), book.getPrice(), book.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE id = " + id);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setPrice(resultSet.getInt("price"));
        int authorId = resultSet.getInt("author_id");
        book.setAuthor(authorManager.getByID(authorId));
        return book;
    }

    public void removeById(int bookId) {
        String sql = "DELETE FROM book WHERE id = " + bookId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> bookSearch(String keyword) {
        List<Book> books = new ArrayList<>();
        for (Book book : books) {
            String sql = "SELECT * FROM book WHERE title LIKE '%" + book.getTitle() + "%'";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, keyword);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    books.add(getBookFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return books;

    }

}
