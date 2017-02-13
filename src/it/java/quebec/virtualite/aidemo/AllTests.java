package quebec.virtualite.aidemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import quebec.virtualite.aidemo.backend.data.ItemTest;
import quebec.virtualite.aidemo.backend.services.impl.RestClientImplTest;
import quebec.virtualite.aidemo.backend.services.impl.RestServerImplTest;
import quebec.virtualite.aidemo.ui.vues.MainViewControllerTest;
import quebec.virtualite.utils.ui.JUnitSuiteUtils;

@RunWith(JUnitSuiteUtils.StopOnFirstFailureSuite.class)
@Suite.SuiteClasses
    ({
        ItemTest.class,
        RestClientImplTest.class,
        RestServerImplTest.class,
        MainViewControllerTest.class,
        // === Integration ===
        CucumberIT.class
    })
public class AllTests extends JUnitSuiteUtils
{
}
