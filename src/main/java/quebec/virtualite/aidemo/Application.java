package quebec.virtualite.aidemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import quebec.virtualite.aidemo.backend.data.Item;
import quebec.virtualite.aidemo.backend.data.ItemRepository;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

@SpringBootApplication(scanBasePackages =
    {
        "quebec.virtualite.aidemo.*",
        "quebec.virtualite.utils.*"
    })
public class Application
{
    public static final String BASE_URL = "/springDemo";

    @Autowired
    private ItemRepository itemRepository;

    public static void main(final String[] args)
        throws InterruptedException
    {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init()
    {
        itemRepository.save(asList
            (
                new Item("B"),
                new Item("A"),
                new Item("C")
            ));
    }
}