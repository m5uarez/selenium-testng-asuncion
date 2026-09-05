package com.asuncion.core;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    
    protected WebDriver driver;
	protected WebDriverWait wait;


	/*
	 * Constructor de la clase BasePage.
	 * Inicializa la instancia de WebDriver y configura una espera explícita
	 * de hasta 30 segundos para la localización de elementos.
	 */
    public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	/*
	 * Redirecciona el navegador a la URL enviada por parámetro.
	 * url: dirección web a la que se desea acceder.
	 */
	public void goTo(String url) {
		driver.get(url);
		System.out.println("Ingresa a la URL: " + url);
	}
	
	/*
	 * Busca un elemento en la página y espera hasta que esté presente
	 * en el DOM.
	 * locator: localizador del elemento.
	 * return: elemento encontrado.
	 */
	public WebElement findElemn(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	/*
	 * Espera hasta que el elemento sea visible para el usuario.
	 * locator: localizador del elemento.
	 * return: elemento visible.
	 */
	public WebElement visible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/*
	 * Espera hasta que el elemento sea clickeable.
	 * locator: localizador del elemento.
	 * return: elemento listo para recibir un clic.
	 */
	public WebElement clickable(By locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	/*
	 * Ingresa texto en un campo de entrada.
	 * Primero limpia el contenido existente y luego escribe el texto recibido.
	 * locator: localizador del campo.
	 * texto: valor a ingresar.
	 */
	public void type(By locator, String texto) {
		WebElement element = this.visible(locator);
		element.clear();
		element.sendKeys(texto);
	}
	
	/*
	 * Realiza clic sobre un elemento cuando se encuentra habilitado.
	 * Si el clic nativo es interceptado (por ejemplo, un footer que en
	 * páginas cortas queda superpuesto al centrar el elemento en el
	 * viewport), se reintenta con un clic vía JavaScript.
	 * locator: localizador del elemento.
	 */
	public void click(By locator) {
		WebElement element = this.clickable(locator);
		try {
			element.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}
	
	/*
	 * Obtiene el texto visible de un elemento.
	 * locator: localizador del elemento.
	 * return: texto recuperado.
	 */
	public String getText(By locator) {
		WebElement element = findElemn(locator);
		String texto = element.getText();
		System.out.println("El texto visible en el localizador " + locator + " es: " + texto);
		return texto;
	}

	/*
	 * Busca todos los elementos que coincidan con el localizador,
	 * sin esperar a que estén presentes ni visibles.
	 * locator: localizador de los elementos.
	 * return: lista de elementos encontrados (vacía si no hay coincidencias).
	 */
	public List<WebElement> findElements(By locator) {
    	return driver.findElements(locator);
	}

	/*
	 * Hace scroll hasta el elemento indicado.
	 * Se espera solo la presencia en el DOM (no la visibilidad) antes de
	 * scrollear, porque algunos bloques de Elementor arrancan ocultos
	 * (clase "elementor-invisible") y recién se hacen visibles cuando
	 * el scroll los lleva al viewport y dispara su animación de entrada
	 * (ej: fadeInRight). Si se esperara visibilidad antes de scrollear,
	 * el scroll que activa la animación nunca llegaría a ejecutarse.
	 */
	public void scrollToElement(By locator) {

    	WebElement element = findElemn(locator);

    	((JavascriptExecutor) driver).executeScript(
        "arguments[0].scrollIntoView({block:'center'});",
        element
    	);

		
	}

	/*
	* Posiciona el mouse sobre un elemento.
	*/
	public void hover(By locator) {

		Actions actions = new Actions(driver);

		actions.moveToElement(
				visible(locator))
				.perform();
	}
	
	/*
	 * Cierra todas las ventanas del navegador y finaliza la sesión WebDriver.
	 */
	public void closeBrowser() {
		driver.quit();
		System.out.println("Se cierra el navegador de pruebas");
	}


}
