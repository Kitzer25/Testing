package com.testing.system_test.Login_Register;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Inccorect_Register {
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

            nombreInput.sendKeys("");
            correoInput.sendKeys("mauricio2511@gmail.com"); //Cambiar contraseña
            usuarioInput.sendKeys("");
            contrasenaInput.sendKeys("MCV2511");

            Thread.sleep(1800);
            WebElement registrarBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button"));
            registrarBtn.click();

            Thread.sleep(2000);
            nombreInput.sendKeys("Mauricio Alexander");
            usuarioInput.sendKeys("kitzer2511");  //Cambiar usuario

            registrarBtn.click();

            Thread.sleep(2000);
            Alert alert = driver.switchTo().alert();
            System.out.println("Alerta: " + alert.getText());
            alert.accept();

            Thread.sleep(3000);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
