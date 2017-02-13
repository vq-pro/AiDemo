package quebec.virtualite.aidemo.ui.vues;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;
import quebec.virtualite.aidemo.backend.data.Item;
import quebec.virtualite.aidemo.backend.services.RestClient;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainViewControllerTest
{
    @InjectMocks
    private MainViewController mainViewController = new MainViewController();

    @Mock
    private RestClient mockedRestClient;

    @Test
    public void enterView() throws Exception
    {
        // Given
        Item[] items =
            {
                new Item("A"),
                new Item("B"),
                new Item("C")
            };

        when(mockedRestClient.getItems())
            .thenReturn(items);

        ModelMap modelMap = new ModelMap();

        // When
        String viewName = mainViewController.enterView(modelMap);

        // Then
        assertThat(viewName, is(MainView.VIEW_NAME));

        assertThat(modelMap.get(MainView.PROP_ITEMS), is(items));
    }
}