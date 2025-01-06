package otusPro.pattern2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsDao {
    private DataSource dataSource;

    public ItemsDao() {
        this.dataSource = DataSource.getInstance();
    }

    public void save(Item item) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "INSERT INTO items (title, price) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, item.getTitle());
                statement.setDouble(2, item.getPrice());
                statement.executeUpdate();
            }
        }
    }

    public void update(Item item) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            String query = "UPDATE items SET price = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDouble(1, item.getPrice());
                statement.setInt(2, item.getId());
                statement.executeUpdate();
            }
        }
    }

    public List<Item> findAll() throws SQLException {
        List<Item> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM items";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    double price = resultSet.getDouble("price");
                    items.add(new Item(id, title, price));
                }
            }
        }
        return items;
    }
}
