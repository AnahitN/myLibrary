package com.example.mylibrary.manager;

import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private static Connection connection = DBConnectionProvider.getInstance().getConnection();
    private  static AuthorManager authorManager = new AuthorManager();
    private static UserManager userManager = new UserManager();

    public void save(Book book) {
        String sql = "INSERT INTO book (title,description,price,pic_name,author_id,user_id) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setInt(3, book.getPrice());
            ps.setString(4,book.getPicName());
            ps.setInt(5, book.getAuthor().getId());
            ps.setInt(6,book.getUser().getId());
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
        String sql = "UPDATE book SET title = ?, description = ?, price = ?, author_id = ?,user_id = ? ,pic_name = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1,book.getTitle());
            ps.setString(2,book.getDescription());
            ps.setInt(3,book.getPrice());
            ps.setInt(4,book.getAuthor().getId());
            ps.setInt(5,book.getUser().getId());
            ps.setString(6,book.getPicName());
            ps.setInt(7,book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static  List<Book> getByUser(User user){
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1,user.getId());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
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

    private  static Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getString("description"));
        book.setPrice(resultSet.getInt("price"));
        book.setPicName(resultSet.getString("pic_name"));
        int authorId = resultSet.getInt("author_id");
        book.setAuthor(authorManager.getByID(authorId));
        int userId = resultSet.getInt("user_id");
        book.setUser(userManager.getByID(userId));
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

            try  {
                String sql = "SELECT * FROM book WHERE title LIKE ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                keyword = "%" + keyword + "%";
                preparedStatement.setString(1, keyword);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    books.add(getBookFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return books;

    }

}
