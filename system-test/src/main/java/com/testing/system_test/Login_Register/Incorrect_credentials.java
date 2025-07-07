package com.testing.system_test.Login_Register;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Incorrect_credentials {

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

            Thread.sleep(2000);
            WebElement loginbtn = driver.findElement(By.xpath("//*[@id=\"root\"]/main/header/nav/button[1]"));
            loginbtn.click();

            Thread.sleep(2000);
            WebElement usuarioInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[1]"));
            WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[2]"));
            WebElement loguearse = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button"));
            WebElement newpasswordInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[2]"));

            /* Valores iniciales */
            usuarioInput.sendKeys("test");
            passwordInput.sendKeys("SSS123456");
            loguearse.click();

            /* Aceptar alerta */
            Thread.sleep(2000);
            Alert alert = driver.switchTo().alert();
            System.out.println("Alerta 1: " + alert.getText());
            alert.accept();

            Thread.sleep(1000);

            usuarioInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[1]"));
            newpasswordInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/input[2]"));
            loguearse = driver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/form/button"));
            
            /* Limpiar Inputs */
            Actions useractions = new Actions(driver);
            useractions.click(usuarioInput)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .perform();
            Actions passwordaction = new Actions(driver);
            passwordaction.click(newpasswordInput)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .perform();


            /* Nueva Inserción de datos */
            Thread.sleep(2000);
            usuarioInput.sendKeys("test");
            newpasswordInput.sendKeys("123456");
            loguearse.click();

            // Manejo de alerta final
            Thread.sleep(2000);
            alert = driver.switchTo().alert();  // buscar de nuevo la alerta
            System.out.println("Alerta 2: " + alert.getText());
            alert.accept();

            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}