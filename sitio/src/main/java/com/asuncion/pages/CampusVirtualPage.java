package com.asuncion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.asuncion.core.BasePage;

public class CampusVirtualPage extends BasePage {

    private final By txtUsuario  = By.id("username");
    private final By txtPassword = By.id("password");
    private final By btnAcceder  = By.id("loginbtn");
    private final By mensajeAccesoInvalido =  By.xpath("//div[@role='alert' and contains(text(),'Acceso inválido')]");
    // Se busca por el emoji de saludo (👋) en vez del texto ("Hola"/
    // "Bienvenido/a" varían), ya que el emoji se mantiene fijo en el h2
    // de bienvenida del Campus.
    private final By saludoBienvenida = By.xpath("//h2[contains(., '👋')]");

    public CampusVirtualPage (WebDriver driver) {
		super(driver);
	}


    /*
    * Escribe el usuario en el campo de login.
    * usuario: nombre de usuario a ingresar.
    */
    public void escribirUsuario(String usuario) {
		type(txtUsuario, usuario);
	}
	
	/*
	* Escribe la contraseña en el campo de login.
	* contrasena: contraseña a ingresar.
	*/
	public void escribirContrasena(String contrasena) {
		type(txtPassword, contrasena);
	}

	/*
	* Hace clic en el botón "Acceder" del formulario de login.
	*/
	public void hacerClickAcceder() {
		click(btnAcceder);
	}

	/*
	* Completa el login del Campus Virtual: escribe usuario y
	* contraseña, y hace clic en "Acceder".
	* usuario: nombre de usuario a ingresar.
	* contrasena: contraseña a ingresar.
	*/
	public void loginCompleto(String usuario, String contrasena) {
		type(txtUsuario, usuario);
		type(txtPassword, contrasena);
		click(btnAcceder);
	}
	
    /*
    * Obtiene el texto del mensaje de error de acceso inválido.
    * return: texto del mensaje de error.
    */
    public String obtenerMensajeError() {
        return getText(mensajeAccesoInvalido);
    }

    /*
    * Valida que se haya llegado a la plataforma del Campus Virtual,
    * verificando que el saludo de bienvenida esté visible
    * (solo aparece una vez logueado).
    */
    public boolean esPaginaCampusVirtual() {
        return visible(saludoBienvenida).isDisplayed();
    }

}
