package com.asuncion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.asuncion.core.BasePage;

public class PortalFamiliasPage extends BasePage {

    private final By institucion = By.xpath("//div[@class='institution']//p[normalize-space()='La Asuncion - Santiago del Estero']");

     public PortalFamiliasPage (WebDriver driver) {
		super(driver);
	}

    /*
    * Valida que se haya llegado a la página de login del Portal para
    * Familias (Aulica), verificando la URL y que el bloque de
    * institución esté visible.
    */
    public boolean esPaginaPortalFamilias() {

        boolean institucionVisible = visible(institucion).isDisplayed();
        boolean urlCorrecta = driver.getCurrentUrl().contains("familia.aulica.com.ar/login");

        return institucionVisible && urlCorrecta;
    }

}
