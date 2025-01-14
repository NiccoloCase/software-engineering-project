package org.swe.core.orm;

import java.sql.*;
import java.util.ArrayList;
import org.swe.model.User;

//i campi sono degli esempi andranno quasi certamente modificati con quelli reali 

public class UserDAO {
     private DBManager dbManager;

     public UserDAO() {
          dbManager = DBManager.getInstance();
     }

     public User getGuestById(int id) {
          try {
               Connection connection = dbManager.getConnection();
               PreparedStatement statement = connection.prepareStatement("SELECT * FROM guests WHERE id = ?");
               statement.setInt(1, id);
               ResultSet resultSet = statement.executeQuery();
               if (resultSet.next()) {
                    return new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("passwordHash"), resultSet.getString("email"), resultSet.getInt("id"));
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return null;
     }

     public ArrayList<User> getAllGuests() {
          ArrayList<User> guests = new ArrayList<>();
          try {
               Connection connection = dbManager.getConnection();
               Statement statement = connection.createStatement();
               ResultSet resultSet = statement.executeQuery("SELECT * FROM guests");
               while (resultSet.next()) {
                    guests.add(new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("passwordHash"), resultSet.getString("email"), resultSet.getInt("id")));
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return guests;
     }

     public User findGuestByEmail(String email) {
          try {
               Connection connection = dbManager.getConnection();
               PreparedStatement statement = connection.prepareStatement("SELECT * FROM guests WHERE email = ?");
               statement.setString(1, email);
               ResultSet resultSet = statement.executeQuery();
               if (resultSet.next()) {
                    return new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("passwordHash"), resultSet.getString("email"), resultSet.getInt("id"));
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return null;
     }

     public boolean addGuest(User guest) {
          try {
               Connection connection = dbManager.getConnection();
               PreparedStatement statement = connection.prepareStatement("INSERT INTO guests (name, email) VALUES (?, ?)");
               statement.setString(1, guest.getName());
               statement.setString(2, guest.getEmail());
               statement.executeUpdate();
               return true;
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return false;
     }

     public boolean updateGuest(User guest) {
          try {
               Connection connection = dbManager.getConnection();
               PreparedStatement statement = connection.prepareStatement("UPDATE guests SET name = ?, email = ? WHERE id = ?");
               statement.setString(1, guest.getName());
               statement.setString(2, guest.getEmail());
               statement.setInt(3, guest.getId());
               statement.executeUpdate();
               return true;
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return false;
     }

    
     
}
