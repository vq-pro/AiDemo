package quebec.virtualite.aidemo.backend.services.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import quebec.virtualite.aidemo.backend.data.Item;
import quebec.virtualite.aidemo.backend.data.ItemRepository;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RestServerImplTest
{
    @InjectMocks
    private RestServerImpl restServer;

    @Mock
    private ItemRepository mockedItemRepository;

    @Test
    public void addItem() throws Exception
    {
        // Given
        Item inputItem = new Item("A");

        when(mockedItemRepository.save(inputItem))
            .thenReturn(inputItem);

        // When
        Item item = restServer.addItem(inputItem);

        // Then
        assertThat(item, is(inputItem));
    }

    @Test
    public void deleteItem() throws Exception
    {
        // When
        restServer.deleteItem(123);

        // Then
        verify(mockedItemRepository).delete(123L);
    }

    @Test
    public void getItem() throws Exception
    {
        // Given
        Item inputItem = new Item(123, "A");

        when(mockedItemRepository.findOne(inputItem.getId()))
            .thenReturn(inputItem);

        // When
        Item item = restServer.getItem(inputItem.getId());

        // Then
        assertThat(item, is(inputItem));
    }

    @Test
    public void getItems() throws Exception
    {
        // Given
        Item[] inputItems = {
            new Item("A"),
            new Item("B")};

        when(mockedItemRepository.findAllByOrderByTextAsc())
            .thenReturn(asList(inputItems));

        // When
        Item[] items = restServer.getItems();

        // Then
        assertThat(items, is(inputItems));
    }

    @Test
    public void updateItem() throws Exception
    {
        // Given
        Item inputItem = new Item("A");

        // When
        restServer.updateItem(inputItem);

        // Then
        verify(mockedItemRepository).save(inputItem);
    }
}