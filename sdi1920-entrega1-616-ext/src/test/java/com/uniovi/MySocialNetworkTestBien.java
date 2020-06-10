package com.uniovi;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.net.UrlChecker.TimeoutException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.uniovi.pageobjects.PO_HomeView;
import com.uniovi.pageobjects.PO_LoginView;
import com.uniovi.pageobjects.PO_RegisterView;
import com.uniovi.pageobjects.PO_View;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
class MySocialNetworkTestBien {

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Cova\\Desktop\\PL-SDI-Sesio╠ün5-material\\geckodriver024win64.exe";

	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

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
		driver.navigate().to("http://localhost:8090/signup");
//		// Voy a la pagina
//		PO_HomeView.clickOption(driver, "signup", "id", "botonRegistrar");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "Sergio", "Tejera", "tejera@tejera.com", "123456", "123456");
		// Comprobamos que se registro correctamente
		PO_HomeView.checkElement(driver, "text", "Bienvenidos a MySocialNetwork");
	}

	// [Prueba2] Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos).
	@Test
	public void PR2() {
		// Formulario
		PO_HomeView.clickOption(driver, "signup", "id", "botonRegistrar");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "", "", "", "12345", "12345");
		// Comprobamos que salen los errores
		PO_View.checkElement(driver, "text", "Este campo no puede ser vacío.");
		PO_View.checkElement(driver, "text", "Este campo no puede ser vacío.");
		PO_View.checkElement(driver, "text", "Este campo no puede ser vacío.");

	}

	// [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña
	// inválida)
	@Test
	public void PR3() {
		driver.navigate().to("http://localhost:8090/signup");
//		// Voy a la pagina
//		PO_HomeView.clickOption(driver, "/registrarse", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "juan@gmail.com", "Juan", "Fernandez", "12345", "123456");
		// Comprobamos que las contraseÃ±as no coinciden
		PO_View.checkElement(driver, "text", "Las contraseñas no coinciden.");
	}

	// [Prueba4] Registro de Usuario con datos inválidos (email existente).
	@Test
	public void PR4() {
		// Voy a la pagina
		PO_HomeView.clickOption(driver, "/registrarse", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_RegisterView.fillForm(driver, "alfonso@alfonso.com", "Juan", "Fernandez", "12345", "12345");
		// Comprobamos que las contraseÃ±as no coinciden
		PO_View.checkElement(driver, "text", "Ya existe un usuario con este email.");
	}

	// [Prueba5] Inicio de sesión con datos válidos (administrador).
	@Test
	public void PR5() {
		// Formulario de login
		PO_HomeView.clickOption(driver, "login", "id", "botonEnviar");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@admin.com", "admin");
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Bienvenido a MySocialNetwork");
		// Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void PR6() {
		// Formulario de login
		PO_HomeView.clickOption(driver, "login", "id", "botonEnviar");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Bienvenido a MySocialNetwork");
		// Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
	}

	// [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email
	// y contraseña vacíos).
	@Test
	public void PR7() {
		// Formulario de login
		PO_HomeView.clickOption(driver, "login", "id", "botonEnviar");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "");
		// Salta el error
		assertTrue(driver.getCurrentUrl().toLowerCase().contains("error"));

	}

	// [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email
	// existente, pero contraseña incorrecta).
	@Test
	public void PR8() {
		// Formulario de login
		PO_HomeView.clickOption(driver, "login", "id", "botonEnviar");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "maria@gmail.com", "1234567");
		// Comprobamos que muestra el mensaje de error correspondiente
		assertTrue(driver.getCurrentUrl().toLowerCase().contains("error"));

	}

	// [Prueba9] Hacer click en la opción de salir de sesión y comprobar que se
	// redirige a la página de inicio de sesión (Login).
	@Test
	public void PR9() {
		// Formulario
		PO_HomeView.clickOption(driver, "login", "id", "botonEnviar");
		// Rellenamos el formulario de login
		PO_LoginView.fillForm(driver, "grippo@grippo.com", "123456");
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Bienvenidos a MySocialNetwork");
		// Seleccionamos el boton de cerrar sesion
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Comprobamos que estamos en la pagina de login
		PO_View.checkElement(driver, "text", "Identificate");

	}

	// [Prueba10] Comprobar que el botón cerrar sesión no está visible si el usuario
	// no está autenticado.
	@Test
	public void PR10() {
		// De primeras estamos sin identificar, por lo que el botón de logout no debería
		// de estar disponible
		PO_HomeView.checkNoElement(driver, "logout");
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "arribas@arribas.com", "123456");
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Bienvenidos a MySocialNetwork");
		// Seleccionamos el botón de cerrar sesión
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Comprobamos que no sale el botón
		PO_HomeView.checkNoElement(driver, "logout");
	}

	// [Prueba11] Mostrar el listado de usuarios y comprobar que se muestran todos
	// los que existen en el sistema.
	// [Prueba12] Hacer una búsqueda con el campo vacío y comprobar que se muestra
	// la página que corresponde con el listado usuarios existentes en el sistema.
	// [Prueba13] Hacer una búsqueda escribiendo en el campo
	// un texto que no exista y comprobar que se muestra la página que corresponde
	// , con la lista de usuarios vacía.
	// [Prueba14] Hacer una búsqueda con un texto específico y comprobar que se
	// muestra
	// la página que corresponde, con la lista de usuarios en los que el texto
	// especificados sea parte de su nombre, apellidos o de su email.
	// [Prueba15] Desde el listado de usuarios de la aplicación, enviar una
	// invitación de amistad a un usuario.
	// Comprobar que la solicitud de amistad aparece en el listado de invitaciones
	// (punto siguiente).
	// [Prueba16] Desde el listado de usuarios de la aplicación, enviar una
	// invitación de amistad a un usuario al que ya le habíamos enviado la
	// invitación previamente. No debería dejarnos enviar la invitación,
	// se podría ocultar el botón de enviar invitación o notificar que ya había sido
	// enviada previamente.
	// [Prueba17] Mostrar el listado de invitaciones de amistad recibidas. Comprobar
	// con un listado que contenga varias invitaciones recibidas.
	// [Prueba18] Sobre el listado de invitaciones recibidas. Hacer click en el
	// botón/enlace de una de ellas y comprobar que dicha solicitud desaparece del
	// listado de invitaciones.
	// [Prueba19] Mostrar el listado de amigos de un usuario. Comprobar que el
	// listado contiene los amigos que deben ser.
	// [Prueba20] Visualizar al menos cuatro páginas en Español/Inglés/Español
	// (comprobando que algunas de las etiquetas cambian al idioma correspondiente).
	// Ejemplo, Página principal/Opciones Principales de Usuario/Listado de
	// Usuarios.
	// [Prueba21] Intentar acceder sin estar autenticado a la opción de listado de
	// usuarios. Se deberá volver al formulario de login.
	@Test
	public void PR21() {
		driver.navigate().to(URL + "/user/list");
		PO_View.checkElement(driver, "text", "Identificate");
	}

	// [Prueba22] Intentar acceder sin estar autenticado a la opción de listado de
	// invitaciones de amistad recibida de un usuario estándar. Se deberá volver al
	// formulario de login.
	@Test
	public void PR22() {
		driver.navigate().to(URL + "/user/request");
		PO_View.checkElement(driver, "text", "Identificate");
	}

	// [Prueba23] Estando autenticado como usuario estándar intentar acceder a una
	// opción disponible solo para usuarios administradores (Se puede añadir una
	// opción cualquiera en el menú).
	// Se deberá indicar un mensaje de acción prohibida.
	@Test
	public void PR23() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");

		assertTrue(driver.getTitle().toLowerCase().contains("forbidden"));
	}
}