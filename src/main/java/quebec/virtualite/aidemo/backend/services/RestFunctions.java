package quebec.virtualite.aidemo.backend.services;

import quebec.virtualite.aidemo.backend.data.Item;

public interface RestFunctions
{
    Item addItem(Item item);

    void deleteItem(long id);

    Item getItem(long id);

    Item[] getItems();

    void updateItem(Item item);
}
