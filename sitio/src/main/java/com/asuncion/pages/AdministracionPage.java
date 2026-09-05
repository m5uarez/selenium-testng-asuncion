package com.asuncion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.asuncion.core.BasePage;

public class AdministracionPage extends BasePage {

    // Nota: se busca por el texto visible y no por el href porque el sitio
    // tiene un typo en producción ("malito:" en vez de "mailto:").
    private final By correoAdministracion = By.xpath("//a[normalize-space()='administracion@colegiolaasuncion.com.ar']");
    private final By footer = By.cssSelector("section.piedepagina");

     public AdministracionPage (WebDriver driver) {
		super(driver);
	}

    /*
     * Valida que el correo de Administración
     * esté visible en la página.
     * Se hace scroll al footer primero para disparar la animación
     * de entrada (fadeInRight) que Elementor aplica a este bloque.
     */
    public boolean validarCorreoVisible() {
        scrollToElement(footer);
        scrollToElement(correoAdministracion);
        return visible(correoAdministracion).isDisplayed();
    }

    /*
     * Obtiene el texto del correo de Administración.
     */
    public String obtenerTextoCorreo() {
        return getText(correoAdministracion);
    }

}
