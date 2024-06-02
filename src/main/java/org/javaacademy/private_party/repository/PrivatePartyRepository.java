package org.javaacademy.private_party.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
@Slf4j
public class PrivatePartyRepository {

    public void addGuest(String login, String pass, String guestName, String guestEmail, String guestPhone) {
        try (Connection connection = createConnection(login, pass)) {
            String sqlAddGuest = "insert into guests (name, email, phone) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlAddGuest);
            preparedStatement.setString(1, guestName);
            preparedStatement.setString(2, guestEmail);
            preparedStatement.setString(3, guestPhone);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public List<String> takeAllGuests(String login, String pass) {
        List<String> guests = new LinkedList<>();
        try (Connection connection = createConnection(login, pass)) {
            String sqlTakeAllGuest = "select * from guest_name";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlTakeAllGuest);
            while (resultSet.next()){
                guests.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return guests;
    }

    private Connection createConnection(String login, String pass) throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://usdb.test.local:5432/private_party",
                login,
                pass);
    }
}
