
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class App {
    public static void main(String[] args) throws InterruptedException {
        // Path ke chromedriver (ubah sesuai lokasi kamu)
        //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

       driver.findElement(By.id ("autosuggest")).sendKeys("Ind");
       Thread.sleep(3000);

       List<WebElement> options = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));


        for (WebElement option :options)
        {
            if(option.getText().equalsIgnoreCase("Indonesia"))
            {
                option.click();
                break;
            }
        }

        try {
            Thread.sleep(5000); // Tunggu 5 detik
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();

    }
}
