package com.testing.system_test.Login_Register;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class RutaInexistente {
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
            String url = "http://localhost:5173/esto-no-existe";
            driver.get(url);
            Thread.sleep(2000);

            // Verificar texto de error 404
            if (driver.getPageSource().toLowerCase().contains("404")
                    || driver.getPageSource().toLowerCase().contains("no encontrado")) {
                System.out.println("✅ Prueba PASADA: Página de error mostrada correctamente para " + url);
            } else {
                System.out.println("❌ Prueba FALLIDA: No se detectó mensaje de error en " + url);
            }

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
