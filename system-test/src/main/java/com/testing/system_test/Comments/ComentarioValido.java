package com.testing.system_test.Comments;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComentarioValido {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            driver.get("http://localhost:5173/");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form.login-form input[type='text']")))
                    .sendKeys("test");
            driver.findElement(By.cssSelector("form.login-form input[type='password']")).sendKeys("123");
            driver.findElement(By.cssSelector("form.login-form button[type='submit']")).click();

            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//button[contains(text(),'Cerrar sesión')]")));

            driver.get("http://localhost:5173/#comentarios");
            Thread.sleep(2000);

            List<WebElement> antes = driver.findElements(By.cssSelector(".comentario-card"));

            WebElement textarea = driver.findElement(By.cssSelector("textarea.comentario-area"));
            textarea.clear();
            textarea.sendKeys("Este es un comentario válido automático 🧪");

            driver.findElement(By.cssSelector("button.comentario-btn")).click();

            Thread.sleep(3000); // esperar render del nuevo comentario
            List<WebElement> despues = driver.findElements(By.cssSelector(".comentario-card"));

            if (despues.size() > antes.size()) {
                System.out.println("✅ Prueba PASADA: Comentario válido registrado.");
            } else {
                System.out.println("❌ Prueba FALLIDA: No se registró el comentario.");
            }

            Thread.sleep(4000);

        } catch (Exception e) {
            System.out.println("❌ Error durante la prueba: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
