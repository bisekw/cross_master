package framework.core;

import static org.openqa.selenium.support.PageFactory.initElements;

public class Base {

    public <TPage extends BasePage> TPage GetInstance(Class<TPage> page)
    {
        Object obj = null;
        obj = initElements(DriverContext.getRemoteWebDriver(), page);
        return page.cast(obj);
    }

}
