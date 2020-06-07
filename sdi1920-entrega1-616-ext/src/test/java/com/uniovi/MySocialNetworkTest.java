package com.uniovi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.pageobjects.PO_HomeView;
import com.uniovi.pageobjects.PO_RegisterView;
import com.uniovi.pageobjects.PO_View;

class MySocialNetworkTest {

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Cova\\Desktop\\PL-SDI-Sesio╠ün5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "https://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);

		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciÃ³nn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// DespuÃ©s de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}

	// Al finalizar la Ãºltima prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// [Prueba1] Registro de Usuario con datos válidos.
	@Test
	public void PR1() {
		// Voy a la pagina
		PO_HomeView.clickOption(driver, "/registrarse", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "tejera@tejera.com", "Sergio", "Tejera", "123456", "123456");
		// Comprobamos que se registro correctamente
		PO_HomeView.checkElement(driver, "text", "Registro realizado correctamente");
		// Nos desconectamos
		PO_HomeView.clickOption(driver, "/desconectarse", "text", "Identificar usuario");
	}

	// [Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos).
	@Test
	public void PR2() {
		// Voy a la pagina
		PO_HomeView.clickOption(driver, "/registrarse", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "", "", "", "12345", "12345");
		// Comprobamos que no pasa nada porque es required a true
		// Seguimos en la misma pagina
		PO_View.checkElement(driver, "text", "Campos vacios");

	}

	// [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida)
	@Test
	public void PR3() {
		// Voy a la pagina
		PO_HomeView.clickOption(driver, "/registrarse", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "juan@gmail.com", "Juan", "Fernandez", "12345", "123456");
		// Comprobamos que las contraseÃ±as no coinciden
		PO_View.checkElement(driver, "text", "Contraseñas no coinciden");
	}

	// [Prueba4] Registro de Usuario con datos inválidos (email existente).
	@Test
	public void PR4() {
		// Voy a la pagina
		PO_HomeView.clickOption(driver, "/registrarse", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "michu@michu.com", "Juan", "Fernandez", "12345", "12345");
		// Comprobamos que las contraseÃ±as no coinciden
		PO_View.checkElement(driver, "text", "Ya existe un usuario con este email.");
	}
}
