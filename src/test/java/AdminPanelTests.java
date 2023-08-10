import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPanelTests extends BaseTests{
    @BeforeEach
    public void login(){
        driver.get(baseUrl + "/login");
        wait.until(driver -> driver.findElement(By.className("get-app__continue"))).click();

        wait.until(driver -> driver.findElement(By.id("input_loginId"))).sendKeys("ledzior");
        driver.findElement(By.id("input_password-input")).sendKeys("Ledzior123!");
        driver.findElement(By.id("saveSetting")).click();
    }

    @Test
    public void no_updates_at_admin_console_should_leave_save_button_disabled(){
        wait.until(ExpectedConditions.numberOfElementsToBe(By.id("loadingSpinner"), 0));
        driver.get(baseUrl + "/admin_console/user_management/permissions/system_scheme");

        WebElement saveSettingsButton = wait.until(driver -> driver.findElement(By.id("saveSetting")));
        String whatUrl = driver.getCurrentUrl();
        Assertions.assertFalse(saveSettingsButton.isEnabled(),
                "The save button is enabled although no settings was updated");
    }

    @Test
    public void add_to_favourites_should_set_star_active(){
        By toggleElement = By.id("toggleFavorite");
        wait.until(ExpectedConditions.presenceOfElementLocated(toggleElement)).click();
        Assertions.assertTrue(driver.findElement(toggleElement).getDomAttribute("class").contains(" active"),
                "Star next to channel name is not active");
        driver.findElement(toggleElement).click();
    }
}
