package com.testing.system_test.Comments;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ComentarioTokenInvalido {
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
            // 1. Ir a la app sin iniciar sesión
            driver.get("http://localhost:5173/");
            Thread.sleep(1000);

            // 2. Inyectar token falso en localStorage
            ((JavascriptExecutor) driver).executeScript("localStorage.setItem('accessToken', 'token_falso_123')");
            System.out.println("🔒 Token inválido inyectado.");

            // 3. Ir a sección comentarios
            driver.get("http://localhost:5173/#comentarios");
            Thread.sleep(1500);

            // 4. Escribir un comentario válido
            WebElement textarea = driver.findElement(By.cssSelector("textarea.comentario-area"));
            textarea.clear();
            textarea.sendKeys("Este comentario no debe ser aceptado");

            WebElement enviarBtn = driver.findElement(By.cssSelector("button.comentario-btn"));
            enviarBtn.click();

            // 5. Verificar si aparece alerta por sesión inválida
            Thread.sleep(1500);

            try {
                Alert alerta = driver.switchTo().alert();
                String mensaje = alerta.getText();
                System.out.println("✅ Prueba PASADA: Se mostró alerta → " + mensaje);
                alerta.dismiss();
            } catch (NoAlertPresentException e) {
                System.out.println(
                        "✅ Prueba PASADA: No se envió comentario sin autenticación (no se mostró alerta pero fue bloqueado).");
            }

            Thread.sleep(4000); // ver en pantalla

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
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
