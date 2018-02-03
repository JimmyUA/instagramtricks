package instatricks.autopost.pagevalidate;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebElement;

@Getter
@Setter
public class PageValidatableData {
    private String followers;
    private String description;
    private String followedOrNot;
    private String errorMessage;

}
