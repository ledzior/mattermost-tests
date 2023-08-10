import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginTests extends BaseTests{
    @Test
    public void click_do_not_have_account_should_show_access_problem(){
        driver.get(baseUrl + "/login");
        //driver.findElement(By.className("get-app__continue")).click();
        wait.until(driver -> driver.findElement(By.className("get-app__continue"))).click();
        //driver.findElement(By.className("alternate-link__link")).click();
        wait.until(driver -> driver.findElement(By.className("alternate-link__link"))).click();

        String noAccessPage = driver.getCurrentUrl();
        Assertions.assertEquals("http://localhost:8065/access_problem", noAccessPage,
                "We are not in access problem page");
    }

    @Test
    public void successful_login_should_display_town_square_page(){
        driver.get(baseUrl + "/login");
        //driver.findElement(By.className("get-app__continue")).click();
        wait.until(driver -> driver.findElement(By.className("get-app__continue"))).click();

        //driver.findElement(By.id("input_loginId")).sendKeys("ledzior");
        wait.until(driver -> driver.findElement(By.id("input_loginId"))).sendKeys("ledzior");
        driver.findElement(By.id("input_password-input")).sendKeys("Ledzior123!");
        //wait.until(driver -> driver.findElement(By.id("input_password-input"))).sendKeys("Ledzior123!");
        driver.findElement(By.id("saveSetting")).click();

        //driver.findElement(By.id("channelHeaderTitle"));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("channelHeaderTitle")));
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("fa-spinner"), 0));
        String townSquarePage = driver.getCurrentUrl();
        Assertions.assertEquals("http://localhost:8065/team-rocket/channels/town-square", townSquarePage,
                "Logging doesn't succeed");
    }
}
