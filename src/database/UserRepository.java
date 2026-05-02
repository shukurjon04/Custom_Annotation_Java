package database;

import model.User;

import java.sql.*;

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
           PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           ps.setString(1, user.getUsername());
           ps.setString(2, user.getEmail());
           ps.setString(3, user.getPassword());
           ps.setString(4, user.getPhone());

           int result = ps.executeUpdate();
           if (result>0){
               ResultSet resultSet = ps.getGeneratedKeys();

               if (resultSet.next()){
                   return findById(resultSet.getLong(1));
               }
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean delete(String username){
        String sql = "delete from users where username = ?";
        try(Connection cn = DBConnection.getInstance().getConnection()){
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,username);

            ResultSet resultSet = ps.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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