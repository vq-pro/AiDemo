package quebec.virtualite.aidemo.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quebec.virtualite.aidemo.backend.data.Item;
import quebec.virtualite.aidemo.backend.data.ItemRepository;
import quebec.virtualite.aidemo.backend.services.RestFunctions;

@RestController
public class RestServerImpl implements RestFunctions
{
    static final String URL_ADD_ITEM = "/REST/addItem";
    static final String URL_DELETE_ITEM = "/REST/deleteItem";
    static final String URL_GET_ITEM = "/REST/getItem";
    static final String URL_GET_ITEMS = "/REST/getItems";
    static final String URL_UPDATE_ITEM = "/REST/updateItem";

    static final String PARAM_ID = "id";

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @RequestMapping(URL_ADD_ITEM)
    public Item addItem(@RequestBody Item item)
    {
        return itemRepository.save(item);
    }

    @Override
    @RequestMapping(URL_DELETE_ITEM)
    public void deleteItem(@RequestParam(PARAM_ID) long id)
    {
        itemRepository.delete(id);
    }

    @Override
    @RequestMapping(URL_GET_ITEM)
    public Item getItem(@RequestParam(PARAM_ID) long id)
    {
        return itemRepository.findOne(id);
    }

    @Override
    @RequestMapping(URL_GET_ITEMS)
    public Item[] getItems()
    {
        return itemRepository.findAllByOrderByTextAsc()
            .toArray(new Item[0]);
    }

    @Override
    @RequestMapping(URL_UPDATE_ITEM)
    public void updateItem(@RequestBody Item item)
    {
        itemRepository.save(item);
    }
}
