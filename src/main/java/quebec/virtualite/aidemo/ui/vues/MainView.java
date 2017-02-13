package quebec.virtualite.aidemo.ui.vues;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quebec.virtualite.aidemo.Application;

@Configuration
public class MainView
{
    public static final String URL = Application.BASE_URL + "/";
    public static final String VIEW_NAME = "MainView";

    public static final String ID_ITEM = "item";

    public static final String PROP_ITEMS = "items";

    public static final String MSG_TITLE = "view.main.title";

    @Bean(name = VIEW_NAME)
    public MainView bean()
    {
        return this;
    }
}
