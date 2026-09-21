package com.platinum.steps;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AutenticacionSteps {

    private WebDriver driver;

    @Dado("que el usuario navega a la página de inicio de sesión {string}")
    public void navegarLoginPage(String url) {
        // Selenium abre el navegador Chrome
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Cuando("ingresa el nombre de usuario {string} y la contraseña {string}")
    public void ingresarCredenciales(String user, String pass) {
        driver.findElement(By.name("usuario")).sendKeys(user);
        driver.findElement(By.name("password")).sendKeys(pass);
    }

    @Cuando("hace clic en el botón {string}")
    public void hacerClicBoton(String boton) {
        driver.findElement(By.id("btnSubmit")).click();
    }

    @Entonces("el sistema muestra el panel principal con el mensaje {string}")
    public void verificarLoginExitoso(String mensajeEsperado) {
        String textoPagina = driver.findElement(By.id("mensajeBienvenida")).getText();
        assertTrue(textoPagina.contains(mensajeEsperado));
        driver.quit(); // Cierra el navegador
    }

    @Entonces("el sistema muestra un mensaje de error {string}")
    public void verificarLoginFallido(String mensajeError) {
        String textoError = driver.findElement(By.id("lblError")).getText();
        assertTrue(textoError.contains(mensajeError));
        driver.quit();
    }
}
