package com.testing.system_test.Login_Register;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Loguin_Close {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        /* Desactiva guardado de constraseñas */
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        /* Evita detección de automatización */
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            driver.get("http://localhost:5173/");

            // 2. Espera y clic en "Inicia sesión aquí" para ir al registro
            Thread.sleep(2000);
            WebElement loginbtn = driver.findElement(By.xpath("//*[@id=\"root\"]/main/header/nav/button[1]"));
            loginbtn.click();

            // 3. Espera a que cargue formulario de registro
            Thread.sleep(2000);

            // 4. Llenar los campos
            WebElement usuarioInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[1]"));
            WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[2]"));

            usuarioInput.sendKeys("test");
            passwordInput.sendKeys("123456");

            // 5. Hacer clic en el botón "Registrarse"
            WebElement loguearse = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button"));
            loguearse.click();

            // 6. Espera para ver resultado
            Thread.sleep(2000);
            Alert alert = driver.switchTo().alert();
            System.out.println("Alerta: " + alert.getText());  // opcional
            alert.accept();

            /* Escrito al final para ubicarlo al registrarse */
            Thread.sleep(2000);
            WebElement close_account = driver.findElement(By.xpath("//button[text()='Cerrar sesión']"));
            close_account.click();

            Thread.sleep(4000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
