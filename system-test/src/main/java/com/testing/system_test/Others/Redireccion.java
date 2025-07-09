package com.testing.system_test.Others;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Redireccion {
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
            Thread.sleep(2000);

            // Lista de secciones por texto visible (ajustado a tu header)
            String[] secciones = {
                    "Inicio", "Sobre", "Funcionalidades", "¿Cómo funciona?",
                    "Beneficios", "¿Por qué elegirnos?", "Contáctanos"
            };

            String[] anchorsEsperados = {
                    "#inicio", "#sobre", "#funcionalidades", "#funcionamiento",
                    "#beneficios", "#porque-elegirnos", "#contacto"
            };

            for (int i = 0; i < secciones.length; i++) {
                String texto = secciones[i];
                String anchor = anchorsEsperados[i];

                try {
                    WebElement link = driver.findElement(By.linkText(texto));
                    link.click();
                    Thread.sleep(1000); // esperar scroll

                    String currentUrl = driver.getCurrentUrl();
                    if (currentUrl.contains(anchor)) {
                        System.out.println("✅ Redirección a " + texto + " exitosa → " + anchor);
                    } else {
                        System.out.println("❌ Falló redirección a " + texto + " (URL: " + currentUrl + ")");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("❌ No se encontró enlace con texto: " + texto);
                }
            }

            Thread.sleep(5000); // para ver resultado antes de cerrar

        } catch (Exception e) {
            System.out.println("❌ Error general: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
