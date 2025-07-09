package com.testing.system_test.Login_Register;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class SesionPersistenteTest {
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
            Thread.sleep(1500);

            // Simular que ya hay un token guardado (copiado de una sesión anterior válida)
            String tokenValido = "aquí_va_un_token_valido_real"; // si usas JWT, puedes sacarlo del navegador
            ((JavascriptExecutor) driver).executeScript("localStorage.setItem('accessToken', arguments[0]);",
                    tokenValido);
            System.out.println("🔐 Token simulado insertado.");

            // Refrescar para que la app lea el token y se loguee automáticamente
            driver.navigate().refresh();
            Thread.sleep(2000);

            // Verificar si aparece "Bienvenido"
            try {
                WebElement bienvenido = driver.findElement(By.xpath("//*[contains(text(),'Bienvenido')]"));
                System.out.println("✅ PRUEBA PASADA: Sesión persistente detectada → " + bienvenido.getText());
            } catch (NoSuchElementException e) {
                System.out.println("❌ PRUEBA FALLIDA: No se detectó sesión activa.");
            }

            Thread.sleep(4000);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
