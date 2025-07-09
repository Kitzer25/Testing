package com.testing.system_test.Comments;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerComentariosSinAuth {
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
            System.out.println("🌐 Cargando sitio sin login...");
            driver.get("http://localhost:5173/");
            Thread.sleep(1000);

            // Limpiar cualquier token previo
            ((JavascriptExecutor) driver).executeScript("localStorage.clear()");
            System.out.println("🧹 Tokens limpiados");

            // Ir a comentarios
            driver.get("http://localhost:5173/#comentarios");
            Thread.sleep(2000);

            // Buscar comentarios visibles
            List<WebElement> comentarios = driver.findElements(By.cssSelector(".comentario-card"));
            System.out.println("🔍 Comentarios encontrados: " + comentarios.size());

            if (comentarios.size() > 0) {
                System.out.println("✅ Prueba PASADA: Se mostraron los comentarios sin autenticación.");
            } else {
                System.out.println("❌ Prueba FALLIDA: No se mostraron comentarios.");
            }

            Thread.sleep(4000); // dejar visible

        } catch (Exception e) {
            System.out.println("❌ Error durante la prueba: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
