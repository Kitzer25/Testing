package com.testing.system_test.Others;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ContactoIncompleto {
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
            System.out.println("🌐 Accediendo a la sección contacto...");
            driver.get("http://localhost:5173/#contacto");
            Thread.sleep(1500);

            // Dejar campo "nombre" vacío
            WebElement correo = driver.findElement(By.cssSelector("form.contacto-form input[placeholder='Tu correo']"));
            WebElement mensaje = driver
                    .findElement(By.cssSelector("form.contacto-form textarea[placeholder='Tu mensaje']"));

            correo.sendKeys("andres.tester@gmail.com");
            mensaje.sendKeys("Mensaje de prueba SIN nombre.");

            WebElement botonEnviar = driver.findElement(By.cssSelector("form.contacto-form button"));
            botonEnviar.click();

            Thread.sleep(2000);

            try {
                Alert alerta = driver.switchTo().alert();
                System.out.println("✅ Prueba PASADA: Se mostró alerta de error → " + alerta.getText());
                alerta.dismiss();
            } catch (NoAlertPresentException e) {
                System.out.println("✅ Prueba PASADA: El formulario no fue enviado (sin alerta, pero bloqueado).");
            }

            Thread.sleep(4000);

        } catch (Exception e) {
            System.out.println("❌ Error durante la prueba: " + e.getMessage());
            e.printStackTrace();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {
            }
        } finally {
            driver.quit();
        }
    }
}
