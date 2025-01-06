package otusPro.pattern2;

import java.sql.SQLException;
import java.util.List;

public class ItemsService {
    private ItemsDao itemsDao;

    public ItemsService() {
        this.itemsDao = new ItemsDao();
    }

    public void save100Items() throws SQLException {
        for (int i = 0; i < 100; i++) {
            Item item = new Item(0, "Item " + i, (i + 1) * 10.0);
            itemsDao.save(item);
        }
    }

    public void doublePrices() throws SQLException {
        List<Item> items = itemsDao.findAll();
        for (Item item : items) {
            item.setPrice(item.getPrice() * 2);
            itemsDao.update(item);
        }
    }
}
