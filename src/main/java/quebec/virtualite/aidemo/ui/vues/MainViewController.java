package quebec.virtualite.aidemo.ui.vues;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quebec.virtualite.aidemo.backend.services.RestClient;

import static quebec.virtualite.aidemo.ui.vues.MainView.PROP_ITEMS;
import static quebec.virtualite.aidemo.ui.vues.MainView.URL;
import static quebec.virtualite.aidemo.ui.vues.MainView.VIEW_NAME;

@Controller
public class MainViewController
{
    @Autowired
    private RestClient restClient;

    @RequestMapping(value = URL, method = RequestMethod.GET)
    public String enterView(final ModelMap modelMap)
    {
        modelMap.addAttribute(PROP_ITEMS, restClient.getItems());

        return VIEW_NAME;
    }
}
