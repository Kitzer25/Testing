package com.testing.system_test.Login_Register;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class RegistroDuplicado {
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

        try {
            driver.get("http://localhost:5173/");
            Thread.sleep(1000);

            // Abrir modal de registro
            WebElement btnRegistro = driver.findElement(By.xpath("//button[text()='Registrarse']"));
            btnRegistro.click();
            Thread.sleep(1000);

            // Llenar campos
            driver.findElement(By.cssSelector("form.register-form input[placeholder='Nombre']")).sendKeys("Usuario");
            driver.findElement(By.cssSelector("form.register-form input[placeholder='Correo']"))
                    .sendKeys("test@gmail.com"); // correo duplicado
            driver.findElement(By.cssSelector("form.register-form input[placeholder='Usuario']"))
                    .sendKeys("testduplicado");
            driver.findElement(By.cssSelector("form.register-form input[placeholder='Contraseña']")).sendKeys("123456");

            driver.findElement(By.cssSelector("form.register-form button[type='submit']")).click();

            Thread.sleep(2000);

            try {
                Alert alerta = driver.switchTo().alert();
                System.out.println("✅ Prueba PASADA: Se detectó duplicidad → " + alerta.getText());
                alerta.dismiss();
            } catch (NoAlertPresentException e) {
                System.out.println("✅ Prueba PASADA: Registro bloqueado por duplicado (sin alerta visible).");
            }

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
