package quebec.virtualite.aidemo.ui;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.context.WebApplicationContext;
import quebec.virtualite.aidemo.Application;
import quebec.virtualite.aidemo.backend.data.Item;
import quebec.virtualite.aidemo.backend.data.ItemRepository;
import quebec.virtualite.aidemo.ui.vues.MainView;
import quebec.virtualite.utils.ui.MvcTestHelper;

import javax.annotation.Resource;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.openqa.selenium.By.id;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UISteps
{
    @Resource
    private WebApplicationContext context;

    @Autowired
    private MvcTestHelper mvc;

    @Autowired
    private ItemRepository itemRepository;

    @Before
    public void _init()
    {
        mvc.init(context);
    }

    @Given("^some items$")
    public void someItems() throws Throwable
    {
        itemRepository.deleteAll();
        itemRepository.save(asList
            (
                new Item("B"),
                new Item("A"),
                new Item("C")
            ));
    }

    @When("^the page is displayed$")
    public void thePageIsDisplayed() throws Throwable
    {
        mvc.get(MainView.URL);

        assertThat("Page was not found: " + MainView.URL,
            mvc.getTitle(),
            not(nullValue()));
    }

    @Then("^we are on the main page$")
    public void weAreOnTheMainPage() throws Throwable
    {
        assertThat(mvc.getElementText(id("VIEW_NAME")),
            is(MainView.VIEW_NAME));
    }

    @Then("^we see the items$")
    public void weSeeTheItems() throws Throwable
    {
        assertThat(mvc.getElementsText(id(MainView.ID_ITEM)),
            contains("A", "B", "C"));
    }
}
