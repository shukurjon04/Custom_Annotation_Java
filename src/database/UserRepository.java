package database;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public boolean existsUserName(String userName) {
        String sql = """
                select 1 from users
                where userName = ?
                limit 1
                """;

        try (Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userName);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User insert(User user) {
        String sql = """
                insert into users(
                username,email,password,phone
                )
                values(
                ?,?,?,?
                )
                """;
        try (Connection connection = DBConnection.getInstance().getConnection()) {
           PreparedStatement ps = connection.prepareStatement(sql);
           ps.setString(1, user.getUsername());
           ps.setString(2, user.getEmail());
           ps.setString(3, user.getPassword());
           ps.setString(4, user.getPhone());

           int result = ps.executeUpdate();
           ResultSet resultSet = ps.getGeneratedKeys();
           if (result>0){
               return findById(resultSet.getLong(1));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private User findById(Long id){
        String sql = "select * from users where id = ?";
        try(Connection connection = DBConnection.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()){
                User user = new User();
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setPhone(result.getString("phone"));

                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}