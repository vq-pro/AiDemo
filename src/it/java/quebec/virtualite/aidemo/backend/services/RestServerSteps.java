package quebec.virtualite.aidemo.backend.services;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import quebec.virtualite.aidemo.Application;
import quebec.virtualite.aidemo.backend.data.Item;
import quebec.virtualite.aidemo.backend.data.ItemRepository;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ContextConfiguration(classes = Application.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RestServerSteps
{
    private static final String UPDATED_TEXT = "AAA";

    private final Item itemA = new Item("A");
    private final Item itemB = new Item("B");
    private final Item itemC = new Item("C");

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RestClient restClient;

    private Item responseItem;
    private Item[] responseItems;

    @Before
    public void _init() throws Throwable
    {
        itemRepository.deleteAll();
    }

    @Given("^an item in the DB$")
    public void anItemInTheDB() throws Throwable
    {
        itemRepository.save(itemA);

        assertThat(itemRepository.findOne(itemA.getId()), is(itemA));
    }

    @Given("^some items in the DB$")
    public void someItemsInTheDB() throws Throwable
    {
        itemRepository.save(asList(itemB, itemA, itemC));
    }

    @Then("^the item has been added to the DB$")
    public void theItemHasBeenAddedToTheDB() throws Throwable
    {
        assertThat(itemRepository.findOne(responseItem.getId()), is(itemA));
    }

    @Then("^the item has been updated in the DB$")
    public void theItemHasBeenUpdatedInTheDB() throws Throwable
    {
        Item updatedItem = itemRepository.findOne(itemA.getId());

        assertThat(updatedItem, not(nullValue()));
        assertThat(updatedItem.getText(), is(UPDATED_TEXT));
    }

    @Then("^the item is gone from the DB$")
    public void theItemIsGoneFromTheDB() throws Throwable
    {
        assertThat(itemRepository.findOne(itemA.getId()), is(nullValue()));
    }

    @When("^we add an item$")
    public void weAddAnItem() throws Throwable
    {
        responseItem = restClient.addItem(itemA);
    }

    @When("^we delete the item$")
    public void weDeleteTheItem() throws Throwable
    {
        restClient.deleteItem(itemA.getId());
    }

    @Then("^we get an array with the same items$")
    public void weGetAnArrayWithTheSameItems() throws Throwable
    {
        assertThat(responseItems, is(new Item[]
            {
                itemA,
                itemB,
                itemC
            }));
    }

    @Then("^we get the same item$")
    public void weGetTheSameItem() throws Throwable
    {
        assertThat(responseItem, is(itemA));
    }

    @When("^we request that item$")
    public void weRequestThatItem() throws Throwable
    {
        responseItem = restClient.getItem(itemA.getId());
    }

    @When("^we request the items$")
    public void weRequestTheItems() throws Throwable
    {
        responseItems = restClient.getItems();
    }

    @When("^we update the item$")
    public void weUpdateTheItem() throws Throwable
    {
        itemA.setText(UPDATED_TEXT);

        restClient.updateItem(itemA);
    }
}
