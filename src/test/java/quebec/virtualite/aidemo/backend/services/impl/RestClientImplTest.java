package quebec.virtualite.aidemo.backend.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import quebec.virtualite.aidemo.backend.data.Item;
import quebec.virtualite.utils.backend.RestClientUtil;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.PARAM_ID;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_ADD_ITEM;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_DELETE_ITEM;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_GET_ITEM;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_GET_ITEMS;
import static quebec.virtualite.aidemo.backend.services.impl.RestServerImpl.URL_UPDATE_ITEM;

@RunWith(MockitoJUnitRunner.class)
public class RestClientImplTest
{
    @InjectMocks
    private RestClientImpl restClient;

    @Mock
    private RestClientUtil mockedRestClientUtil;

    @Test
    public void addItem() throws Exception
    {
        // Given
        Item inputItem = new Item("A");

        when(mockedRestClientUtil.post(URL_ADD_ITEM, inputItem, Item.class))
            .thenReturn(inputItem);

        // When
        Item item = restClient.addItem(inputItem);

        // Then
        assertThat(item, is(inputItem));
    }

    @Test
    public void deleteItem() throws Exception
    {
        // When
        restClient.deleteItem(1);

        // Then
        verify(mockedRestClientUtil).delete(URL_DELETE_ITEM, PARAM_ID, 1L);
    }

    @Test
    public void getItem() throws Exception
    {
        // When
        restClient.getItem(1);

        // Then
        verify(mockedRestClientUtil)
            .get(URL_GET_ITEM, PARAM_ID, 1L, Item.class);
    }

    @Test
    public void getItems() throws Exception
    {
        // When
        restClient.getItems();

        // Then
        verify(mockedRestClientUtil).get(URL_GET_ITEMS, Item[].class);
    }

    @Test
    public void updateItem() throws Exception
    {
        // Given
        Item inputItem = new Item("A");

        // When
        restClient.updateItem(inputItem);

        // Then
        verify(mockedRestClientUtil).put(URL_UPDATE_ITEM, inputItem);
    }
}