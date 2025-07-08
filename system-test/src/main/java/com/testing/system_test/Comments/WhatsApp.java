package com.testing.system_test.Comments;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WhatsApp {

        public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        // Desactiva guardado de contraseñas
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("http://localhost:5173/");

            // 1. Ir a login
            Thread.sleep(2000);
            WebElement loginbtn = driver.findElement(By.xpath("//*[@id=\"root\"]/main/header/nav/button[1]"));
            loginbtn.click();

            // 2. Llenar formulario
            Thread.sleep(2000);
            WebElement usuarioInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[1]"));
            WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[2]"));

            usuarioInput.sendKeys("test");
            passwordInput.sendKeys("123456");

            // 3. Clic en botón de login
            WebElement loguearse = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button"));
            loguearse.click();

            Thread.sleep(2000);

            Alert alert = driver.switchTo().alert();
            System.out.println("Alerta: " + alert.getText());  // opcional
            alert.accept();

            Thread.sleep(1000);
            
            WebElement whats = driver.findElement(By.xpath("//*[@id=\"root\"]/a"));
            whats.click();

            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}