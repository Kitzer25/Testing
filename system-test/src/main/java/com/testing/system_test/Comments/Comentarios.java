package com.testing.system_test.Comments;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Comentarios {
    public static void main(String[] args) {
                System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("http://localhost:5173/");

            // 4. Llenar los campos
            WebElement comentario = driver.findElement(By.xpath("//*[@id=\"comentarios\"]/form/textarea"));
            WebElement enviarbtn = driver.findElement(By.xpath("//*[@id=\"comentarios\"]/form/button"));
        

            comentario.sendKeys("Prueba de comentatios");
            Thread.sleep(1700);

            enviarbtn.click();
            Thread.sleep(2000);

            Alert alert = driver.switchTo().alert();
            System.out.println("Alerta: " + alert.getText());  // opcional
            alert.accept();

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
