package com.example.mylibrary.manager;

import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorManager {
    private static Connection connection = DBConnectionProvider.getInstance().getConnection();
    public static void  save(Author author) {
        String sql = "INSERT INTO author(name,surname,email,age) VALUES(?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, author.getName());
            ps.setString(2, author.getSurname());
            ps.setString(3, author.getEmail());
            ps.setInt(4, author.getAge());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getInt(1));
            }
            System.out.println("Author added into db");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(Author author) {
        String sql = "UPDATE author SET name= ?, surname = ?, email = ?, age = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
           ps.setString(1,author.getName());
           ps.setString(2,author.getSurname());
           ps.setString(3,author.getEmail());
           ps.setInt(4,author.getAge());
           ps.setInt(5,author.getId());
           ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author");
            while (resultSet.next()) {
                authors.add(getAuthorFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    private Author getAuthorFromResultSet(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id"));
        author.setName(resultSet.getString("name"));
        author.setSurname(resultSet.getString("surname"));
        author.setEmail(resultSet.getString("email"));
        author.setAge(resultSet.getInt("age"));
        return author;
    }

    public void removeById(int authorId){
        String sql = "DELETE FROM author WHERE id = " + authorId;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author getByID (int id) {
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author WHERE id = " + id);
            if (resultSet.next()) {
                return getAuthorFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
