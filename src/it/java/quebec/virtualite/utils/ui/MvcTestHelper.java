package quebec.virtualite.utils.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriver;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

@Component
public class MvcTestHelper
{
    private static final String HTTP_BASE = "http://localhost";

    private MockMvcHtmlUnitDriver html;
    private MockMvc web;
    private ResourceBundleMessageSource messageSource;

    public String dump()
    {
        return html.getPageSource();
    }

    public WebElement findElement(By by)
    {
        return html.findElement(by);
    }

    public List<WebElement> findElements(By by)
    {
        return html.findElements(by);
    }

    public void get(String url)
    {
        html.get(HTTP_BASE + url);
    }

    public String getElementText(By by)
    {
        return findElement(by).getText();
    }

    public List<String> getElementsText(By by)
    {
        return findElements(by).stream()
            .map(e -> e.getText())
            .collect(toList());
    }

    public String getMessage(String key)
    {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    public String getTitle()
    {
        return html.getTitle();
    }

    public void init(WebApplicationContext context)
    {
        web = MockMvcBuilders.webAppContextSetup(context).build();
        html = new MockMvcHtmlUnitDriver(web, true);

        messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
    }

    @PreDestroy
    private void _wipe()
    {
        if (html != null)
        {
            html.close();
        }
    }
}
