package com.testing.system_test.Login_Register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Access {

        public static void main(String[] args) {
                System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("http://localhost:5173/");

            // 2. Espera y clic en "Inicia sesión aquí" para ir al registro
            Thread.sleep(2000);
            WebElement registerLink = driver.findElement(By.xpath("//*[@id=\"root\"]/main/header/nav/button[2]"));
            registerLink.click();

            // 3. Espera a que cargue formulario de registro
            Thread.sleep(2000);

            // 4. Llenar los campos
            WebElement nombreInput = driver.findElement(By.xpath("//input[@placeholder='Nombre']"));
            WebElement correoInput = driver.findElement(By.xpath("//input[@placeholder='Correo']"));
            WebElement usuarioInput = driver.findElement(By.xpath("//input[@placeholder='Usuario']"));
            WebElement contrasenaInput = driver.findElement(By.xpath("//input[@placeholder='Contraseña']"));

            nombreInput.sendKeys("Juan Pérez");
            correoInput.sendKeys("test@gmail.com");
            usuarioInput.sendKeys("test");
            contrasenaInput.sendKeys("123456");

            // 5. Hacer clic en el botón "Registrarse"
            WebElement registrarBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button"));
            registrarBtn.click();

            // 6. Espera para ver resultado
            Thread.sleep(4000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
