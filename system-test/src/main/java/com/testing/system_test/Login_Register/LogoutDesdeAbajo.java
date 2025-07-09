package com.testing.system_test.Login_Register;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LogoutDesdeAbajo {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        try {
            driver.get("http://localhost:5173/");
            Thread.sleep(1000);

            // Login
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form.login-form input[type='text']")))
                    .sendKeys("test");
            driver.findElement(By.cssSelector("form.login-form input[type='password']")).sendKeys("123");
            driver.findElement(By.cssSelector("form.login-form button[type='submit']")).click();

            // Esperar texto de bienvenida
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Bienvenido')]")));
            System.out.println("✅ Bienvenida detectada.");

            // Scroll hasta el fondo de la página para asegurar render
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            // Buscar el botón por su texto exacto "Cerrar sesión"
            WebElement cerrarSesionBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space(text())='Cerrar sesión']")));

            // Hacer clic (normal o forzado)
            try {
                cerrarSesionBtn.click();
                System.out.println("✅ Click normal en 'Cerrar sesión'.");
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cerrarSesionBtn);
                System.out.println("⚠️ Click forzado por JS en 'Cerrar sesión'.");
            }

            Thread.sleep(1000);

            // Verificar que el token desapareció
            String token = (String) ((JavascriptExecutor) driver)
                    .executeScript("return localStorage.getItem('accessToken');");
            boolean tokenEliminado = (token == null || token.isEmpty());

            // Verificar que el botón ya no está
            boolean botonDesaparecio;
            try {
                driver.findElement(By.xpath("//button[normalize-space(text())='Cerrar sesión']"));
                botonDesaparecio = false;
            } catch (NoSuchElementException e) {
                botonDesaparecio = true;
            }

            if (tokenEliminado && botonDesaparecio) {
                System.out.println("✅ PRUEBA PASADA: Logout exitoso.");
            } else {
                System.out.println("❌ PRUEBA FALLIDA: Token sigue o botón no desapareció.");
            }

            Thread.sleep(4000);

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ignored) {
            }
        } finally {
            driver.quit();
        }
    }
}
