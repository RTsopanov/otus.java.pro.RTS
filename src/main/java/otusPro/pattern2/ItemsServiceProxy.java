package otusPro.pattern2;

import java.sql.Connection;
import java.sql.SQLException;

public class ItemsServiceProxy {
    private ItemsService itemsService;

    public ItemsServiceProxy() {
        this.itemsService = new ItemsService();
    }

    public void save100ItemsWithTransaction() throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false); // Begin transaction
            itemsService.save100Items();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            connection.rollback(); // Rollback transaction in case of error
            throw e;
        } finally {
            connection.close();
        }
    }

    public void doublePricesWithTransaction() throws SQLException {
        Connection connection = DataSource.getInstance().getConnection();
        try {
            connection.setAutoCommit(false); // Begin transaction
            itemsService.doublePrices();
            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            connection.rollback(); // Rollback transaction in case of error
            throw e;
        } finally {
            connection.close();
        }
    }
}
