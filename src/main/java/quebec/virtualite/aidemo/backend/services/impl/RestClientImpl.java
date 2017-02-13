package quebec.virtualite.aidemo.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quebec.virtualite.aidemo.backend.data.Item;
import quebec.virtualite.aidemo.backend.services.RestClient;
import quebec.virtualite.utils.backend.RestClientUtil;

import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.PARAM_ID;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_ADD_ITEM;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_DELETE_ITEM;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_GET_ITEM;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_GET_ITEMS;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_UPDATE_ITEM;

@Service
class RestClientImpl implements RestClient
{
    @Autowired
    private RestClientUtil restClientUtil;

    @Override
    public Item addItem(Item item)
    {
        return restClientUtil.post(URL_ADD_ITEM, item, Item.class);
    }

    @Override
    public void deleteItem(long id)
    {
        restClientUtil.delete(URL_DELETE_ITEM, PARAM_ID, id);
    }

    @Override
    public Item getItem(long id)
    {
        return restClientUtil.get(URL_GET_ITEM, PARAM_ID, id, Item.class);
    }

    @Override
    public Item[] getItems()
    {
        return restClientUtil.get(URL_GET_ITEMS, Item[].class);
    }

    @Override
    public void updateItem(Item item)
    {
        restClientUtil.put(URL_UPDATE_ITEM, item);
    }
}
