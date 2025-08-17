package com.example;
import java.util.List;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

// ✅ Tambahkan listener Allure di sini
@Listeners({AllureTestNg.class})
public class AppTest {

    @Test(description = "Memilih Indonesia dari dropdown autocomplete")
    public void testSelectCountry() throws InterruptedException {
        // Setup opsi Chrome agar kompatibel di GitHub Actions (headless mode)
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        // Inisialisasi driver
        WebDriver driver = new ChromeDriver(options);

        try {
            // Buka URL
            driver.get("https://rahulshettyacademy.com/dropdownsPractise/");

            // Interaksi dropdown autocomplete
            driver.findElement(By.id("autosuggest")).sendKeys("Ind");
            Thread.sleep(3000);

            List<WebElement> optionsList = driver.findElements(By.cssSelector("li[class='ui-menu-item'] a"));
            boolean found = false;
            for (WebElement option : optionsList) {
                if (option.getText().equalsIgnoreCase("Indonesia")) {
                    option.click();
                    System.out.println("Berhasil memilih: Indonesia");
                    found = true;
                    break;
                }
            }

            // ✅ Assert untuk validasi (biar Allure tahu PASS/FAIL)
            Assert.assertTrue(found, "Indonesia berhasil dipilih dari dropdown");

        } finally {
            Thread.sleep(2000);
            driver.quit();
        }
    }
}
