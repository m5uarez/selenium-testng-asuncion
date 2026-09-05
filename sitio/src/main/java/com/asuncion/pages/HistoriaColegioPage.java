package com.asuncion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.asuncion.core.BasePage;

public class HistoriaColegioPage extends BasePage {

    private final By tituloNuestraHistoria = By.xpath("//h2[normalize-space()='Nuestra Historia']");

    private final By tituloGaleriaFotos = By.xpath("//h2[normalize-space()='Galería de fotos históricas']");

     public HistoriaColegioPage (WebDriver driver) {
		super(driver);
	}

    

     /*
     * Valida que el bloque Nuestra Historia
     * esté presente en la página.
     */
    public boolean validarNuestraHistoria() {
        return visible(tituloNuestraHistoria).isDisplayed();
    }

    /*
     * Valida que el bloque Galería de fotos
     * históricas esté presente.
     */
    public boolean validarGaleriaFotos() {
        return visible(tituloGaleriaFotos).isDisplayed();
    }
    
}
