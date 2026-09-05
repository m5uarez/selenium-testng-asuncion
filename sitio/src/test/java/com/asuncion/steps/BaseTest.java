package com.asuncion.steps;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.asuncion.utils.ExtentTestListener;

@Listeners(ExtentTestListener.class)
public class BaseTest {

    protected WebDriver driver;

    /*
    * Inicializa el navegador Chrome antes de cada test, con las opciones
    * necesarias para la automatización (modo incógnito, sin notificaciones,
    * sin gestor de contraseñas, maximizado, etc.).
    * alwaysRun = true: sin esto, TestNG no ejecuta este @BeforeMethod cuando
    * el suite XML filtra por <groups><run><include>, ya que el método no
    * pertenece a ningún grupo (bug conocido de TestNG, issue #1574).
    */
    @BeforeMethod(alwaysRun = true)
	public void iniciarNavegador() {

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--incognito");
		// Abre el navegador en modo incógnito para evitar cache, cookies y sesiones previas

		options.addArguments("--disable-notifications");
		// Deshabilita las notificaciones emergentes del navegador

		options.addArguments("--disable-infobars");
		// Oculta la barra informativa de Chrome ("Chrome está siendo controlado por software automatizado")

		options.addArguments("--disable-save-password-bubble");
		// Evita el popup de Chrome para guardar contraseñas

		options.addArguments("start-maximized");
		// Inicia el navegador maximizado

		options.addArguments("--disable-gpu");
		// Deshabilita el uso de GPU (útil en algunos entornos de automatización o CI/CD)

		options.addArguments("--disable-extensions");
		// Deshabilita todas las extensiones instaladas en Chrome

		options.setAcceptInsecureCerts(true);
		// Permite acceder a sitios con certificados SSL inválidos o inseguros

		Map<String, Object> prefs = new HashMap<>();

		prefs.put("credentials_enable_service", false);
		// Deshabilita el servicio de guardado de credenciales de Chrome

		prefs.put("profile.password_manager_enabled", false);
		// Desactiva el administrador de contraseñas del navegador

		options.setExperimentalOption("prefs", prefs);
		// Aplica las preferencias personalizadas configuradas en el mapa prefs

		driver = new ChromeDriver(options);
	}

    /*
    * Obtiene la instancia de WebDriver del test en ejecución.
    * return: driver actual, o null si todavía no se inicializó.
    */
    public WebDriver getDriver() {
        return driver;
    }

    /*
    * Cierra el navegador y finaliza la sesión de WebDriver
    * al terminar cada test.
    */
    @AfterMethod(alwaysRun = true)
	public void cerrarNavegador() {
		driver.quit();
		System.out.println("Se cierra el navegador de pruebas");
	}

}
