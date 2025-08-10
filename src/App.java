import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // Setup opsi Chrome agar kompatibel di GitHub Actions (headless mode)
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
        // Inisialisasi driver dengan opsi
        WebDriver driver = new ChromeDriver(options);

        // Buka URL
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

        // Interaksi dropdown autocomplete
        driver.findElement(By.id("autosuggest")).sendKeys("Ind");
        Thread.sleep(3000);

        List<WebElement> optionsList = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
        for (WebElement option : optionsList) {
            if (option.getText().equalsIgnoreCase("Indonesia")) {
                option.click();
                System.out.println("Berhasil memilih: Indonesia");
                break;
            }
        }

        Thread.sleep(5000); // Tunggu 5 detik agar bisa melihat hasil sebelum close
        driver.quit();
    }
}


