package com.testing.system_test.Comments;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ComentarioNulo {
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
            // 4. Esperar y aceptar alerta si aparece
            try {
                WebDriverWait waitAlert = new WebDriverWait(driver, Duration.ofSeconds(5));
                Alert alert = waitAlert.until(ExpectedConditions.alertIsPresent());
                System.out.println("Alerta: " + alert.getText());
                alert.accept();
            } catch (TimeoutException e) {
                System.out.println("No apareció alerta después del login.");
            }

            Thread.sleep(2000);

            // 5. Interactuar con comentario
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement comentario = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"comentarios\"]/form/textarea")));
            
            /* Sin comentarios */
            comentario.sendKeys();

            Thread.sleep(2000);

            WebElement enviarbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"comentarios\"]/form/button")));
            enviarbtn.click();

            Thread.sleep(2000);

            // 6. Enviar comentario otra vez como parte de prueba
            enviarbtn.click();
            Thread.sleep(4000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
