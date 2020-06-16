package com.uniovi.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.pageobjects.PO_HomeView;
import com.uniovi.pageobjects.PO_ListUser;
import com.uniovi.pageobjects.PO_LoginView;
import com.uniovi.pageobjects.PO_NavView;
import com.uniovi.pageobjects.PO_Properties;
import com.uniovi.pageobjects.PO_RegisterView;
import com.uniovi.pageobjects.PO_View;
import com.uniovi.util.SeleniumUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SocialNetworkTest {

	

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
//			// Voy a la pagina
			PO_HomeView.clickOption(driver, "signup", "id", "botonRegistrar");
			// Rellenamos el formulario.
			PO_RegisterView.fillForm(driver, "Sergio", "Tejera", "tejera@tejera.com", "123456", "123456");
			// Comprobamos que se registro correctamente
			PO_HomeView.checkElement(driver, "text", "Usuarios");
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

		}

		// [Prueba3] Registro de Usuario con datos inválidos (repetición de contraseña
		// inválida)
		@Test
		public void PR3() {
			// Voy a la pagina
			PO_HomeView.clickOption(driver, "signup", "id", "botonRegistrar");
			// Rellenamos el formulario
			PO_RegisterView.fillForm(driver,  "Juan", "Fernandez","juan@gmail.com", "12345", "123456");
			// Comprobamos que las contraseÃ±as no coinciden
			PO_View.checkElement(driver, "text", "Las contraseñas no coinciden.");
		}

		// [Prueba4] Registro de Usuario con datos inválidos (email existente).
		@Test
		public void PR4() {
//			// Voy a la pagina
			PO_HomeView.clickOption(driver, "signup", "id", "botonRegistrar");
			// Rellenamos el formulario
			PO_RegisterView.fillForm(driver,  "Juan", "Fernandez","alfonso@alfonso.com", "123456", "123456");
			// Comprobamos que el email ya existe
			PO_View.checkElement(driver, "text", "Email está duplicado.");
		}

		// [Prueba5] Inicio de sesión con datos válidos (administrador).
		@Test
		public void PR5() {
			// Formulario de login
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "admin@email.com", "admin");
			//Vamos a la lista de admin
			driver.navigate().to("http://localhost:8090/user/admin");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		}

		// [Prueba6] Inicio de sesión con datos válidos (usuario estándar).
		@Test
		public void PR6() {
			// Formulario de login
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");
			// Comprobamos que entramos en la pagina privada del usuario
			PO_View.checkElement(driver, "text", "Bienvenidos a MySocialNetwork");
			
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		}

		// [Prueba7] Inicio de sesión con datos inválidos (usuario estándar, campo email
		// y contraseña vacíos).
		@Test
		public void PR7() {
			// Formulario de login
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "", "");
			// Salta el error
			// Comprobamos el error de campos vacios
			PO_RegisterView.checkKey(driver, "Error.log", PO_Properties.getSPANISH());

		}

		// [Prueba8] Inicio de sesión con datos válidos (usuario estándar, email
		// existente, pero contraseña incorrecta).
		@Test
		public void PR8() {
			// Formulario de login
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "tejera@tejera.com", "1234567");
			// Comprobamos que muestra el mensaje de error correspondiente
			PO_RegisterView.checkKey(driver, "Error.log", PO_Properties.getSPANISH());

		}

		// [Prueba9] Hacer click en la opción de salir de sesión y comprobar que se
		// redirige a la página de inicio de sesión (Login).
		@Test
		public void PR9() {
			// Formulario
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario de login
			PO_LoginView.fillForm(driver, "grippo@grippo.com", "123456");
			// Comprobamos que entramos en la pagina privada del usuario
			PO_View.checkElement(driver, "text", "Bienvenidos a MySocialNetwork");
			// Seleccionamos el boton de cerrar sesion
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
			// Comprobamos que estamos en la pagina de login
			PO_View.checkElement(driver, "text", "Identifícate");

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
		@Test
		public void PR11() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/user/list");
			// Primera pagina
			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
					PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/user/list?page=0");
			// Segunda pagina
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/user/list?page=1");
			// Ultima pagina
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/user/list?page=2");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		}

		// [Prueba12] Hacer una búsqueda con el campo vacío y comprobar que se muestra
		// la página que corresponde con el listado usuarios existentes en el sistema.
		@Test
		public void PR12() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/user/list");
			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
					PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			// Busqueda
			// Busqueda
			PO_ListUser.fillForm(driver, "");
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);

			driver.navigate().to("http://localhost:8090/user/list?page=0");
			// Ultima pagina
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/user/list?page=1");
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/user/list?page=2");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		}

		// [Prueba13] Hacer una búsqueda escribiendo en el campo
		// un texto que no exista y comprobar que se muestra la página que corresponde
		// , con la lista de usuarios vacía.
		@Test
		public void PR13() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "berjon@berjon.com", "123456");
			PO_View.getP();
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/user/list");
			// Busqueda
			PO_ListUser.fillForm(driver, "cova");
			// Comprobamos que no esta en la busqueda
			SeleniumUtils.textoNoPresentePagina(driver, "cova");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		}

		// [Prueba14] Hacer una búsqueda con un texto específico y comprobar que se
		// muestra
		// la página que corresponde, con la lista de usuarios en los que el texto
		// especificados sea parte de su nombre, apellidos o de su email.
		@Test
		public void PR14() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");
			PO_View.getP();
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/user/list");
			// Busqueda
			PO_ListUser.fillForm(driver, "car");
			// Comprobamos que esta en la busqueda
			SeleniumUtils.textoPresentePagina(driver, "Carlos");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		}
		// [Prueba15] Desde el listado de usuarios de la aplicación, enviar una
		// invitación de amistad a un usuario.
		// Comprobar que la solicitud de amistad aparece en el listado de invitaciones
		// (punto siguiente).
		@Test
		public void PR15() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");
			PO_View.getP();
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/user/list");
			// Enviamos peticion		
			PO_ListUser.clickOption(driver, "user/send/2", "text", "Agregar amigo");
			SeleniumUtils.esperarSegundos(driver, 5);
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
			// Nos conectamos con el otro usuario
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "arribas@arribas.com", "123456");
			// Vamos a la pestaña de lista de peticiones
			driver.navigate().to("http://localhost:8090/request/list");		
			// Comprobamos que hay una solicitud de el usuario ejemplo1: Pedro
			List<WebElement> peticiones = SeleniumUtils.EsperaCargaPagina(driver, "text", "Aceptar", PO_View.getTimeout());
			assertTrue(peticiones.size() == 1);
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		}

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
		@Test
		public void PR20() {
			// Comprobamos el texto HOME en español
			SeleniumUtils.EsperaCargaPagina(driver, "text", "Bienvenidos a MySocialNetwork", PO_View.getTimeout());

			SeleniumUtils.textoPresentePagina(driver, "Bienvenidos a MySocialNetwork");

			// Cambiamos de idioma
			driver.navigate().to("http://localhost:8090/?lang=EN");

			SeleniumUtils.EsperaCargaPagina(driver, "text", "Welcome to MySocialNetwork", PO_View.getTimeout());

			// Comprobamos el texto en HOME en ingles
			SeleniumUtils.textoPresentePagina(driver, "Welcome to MySocialNetwork");
			
			// Formulario de login
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");
			// Comprobamos que entramos en la pagina privada del usuario
			PO_View.checkElement(driver, "text", "Welcome to MySocialNetwork");
			
			driver.navigate().to("http://localhost:8090/user/list");
			// Comprobamos el texto lista usuarios en ingles
			SeleniumUtils.textoPresentePagina(driver, "Users");
			SeleniumUtils.textoPresentePagina(driver, "Name");
			SeleniumUtils.textoPresentePagina(driver, "Email");

			// Cambiamos de idioma
			PO_NavView.changeIdiom(driver, "Spanish");

			// Comprobamos el texto lista usuarios en español
			SeleniumUtils.textoPresentePagina(driver, "Usuarios");
			SeleniumUtils.textoPresentePagina(driver, "Nombre");
			SeleniumUtils.textoPresentePagina(driver, "Email");
			
			// Vamos a las solicitudes de amistad
			driver.navigate().to("http://localhost:8090/request/list");
			SeleniumUtils.textoPresentePagina(driver, "Peticiones de amistad");
			SeleniumUtils.textoPresentePagina(driver, "Nombre");
			SeleniumUtils.textoPresentePagina(driver, "Email");

			// Cambiamos de idioma
			PO_NavView.changeIdiom(driver, "English");
			SeleniumUtils.textoPresentePagina(driver, "Friend request");
			SeleniumUtils.textoPresentePagina(driver, "Name");
			SeleniumUtils.textoPresentePagina(driver, "Email");

			// Vamos a la lista de amigos
			driver.navigate().to("http://localhost:8090/friend/list");
			SeleniumUtils.textoPresentePagina(driver, "Friends");
			SeleniumUtils.textoPresentePagina(driver, "Name");
			SeleniumUtils.textoPresentePagina(driver, "LastName");
			SeleniumUtils.textoPresentePagina(driver, "Email");
			// Cambiamos de idioma
			PO_NavView.changeIdiom(driver, "Spanish");
			SeleniumUtils.textoPresentePagina(driver, "Amigos");
			SeleniumUtils.textoPresentePagina(driver, "Nombre");
			SeleniumUtils.textoPresentePagina(driver, "Apellidos");
			SeleniumUtils.textoPresentePagina(driver, "Email");
		}

		// [Prueba21] Intentar acceder sin estar autenticado a la opción de listado de
		// usuarios. Se deberá volver al formulario de login.
		@Test
		public void PR21() {
			driver.navigate().to(URL + "/user/list");
			PO_View.checkElement(driver, "text", "Identifícate");
		}

		// [Prueba22] Intentar acceder sin estar autenticado a la opción de listado de
		// invitaciones de amistad recibida de un usuario estándar. Se deberá volver al
		// formulario de login.
		@Test
		public void PR22() {
			driver.navigate().to(URL + "/request/list");
			PO_View.checkElement(driver, "text", "Identifícate");
		}

		// [Prueba23] Estando autenticado como usuario estándar intentar acceder a una
		// opción disponible solo para usuarios administradores (Se puede añadir una
		// opción cualquiera en el menú).
		// Se deberá indicar un mensaje de acción prohibida.
		@Test
		public void PR23() {
			//Nos logueamos
			PO_HomeView.clickOption(driver, "login", "id", "botonIdentificar");
			PO_LoginView.fillForm(driver, "alfonso@alfonso.com", "123456");
			// Comprobamos que NO entramos en la pagina privada de Admin
			driver.navigate().to("http://localhost:8090/user/admin");
			//Comprobamos que esta prohibido
			assertTrue(driver.getTitle().toLowerCase().contains("forbidden"));
		}
		//[Prueba31] Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el sistema.
		@Test
		public void PR31() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "admin@email.com", "admin");
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/admin/list");
			// Primera pagina
			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
					PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=0");
			// Segunda pagina
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=1");
			// Ultima pagina
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=2");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		}
		//[Prueba32] Ir a la lista de usuarios, borrar el primer usuario de la lista, 
		//comprobar que la lista se actualiza y dicho usuario desaparece. 
		@Test
		public void PR32() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "admin@email.com", "admin");
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/admin/list");
			// Primera pagina
			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
					PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=0");
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Borrar usuario", PO_View.getTimeout());
			elementos.get(0).click();
			driver.navigate().to("http://localhost:8090/admin/list?page=0");
			SeleniumUtils.textoNoPresentePagina(driver, "Alfonso");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		}
		//[Prueba33] Ir a la lista de usuarios,borrar el último usuario de la lista, 
		//comprobar que la lista se actualiza y dicho usuario desaparece. 
		@Test
		public void PR33() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "admin@email.com", "admin");
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/admin/list");
			// Primera pagina
			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
					PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=0");
			// Segunda pagina
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=1");
			// Ultima pagina
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=2");
			//Borramos el ultimo usuario
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Borrar usuario", PO_View.getTimeout());
			elementos.get(elementos.size()-1).click();
			driver.navigate().to("http://localhost:8090/admin/list?page=2");
			SeleniumUtils.textoNoPresentePagina(driver, "Sergio");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		}
		//[Prueba34] Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se actualiza y dichos usuarios desaparecen. 
		@Test
		public void PR34() {
			// Vamos al formulario de logueo.
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
			// Rellenamos el formulario
			PO_LoginView.fillForm(driver, "admin@email.com", "admin");
			// Vamos al menu de usuarios
			driver.navigate().to("http://localhost:8090/admin/list");
			// Primera pagina
			List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
					PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=0");
			// Segunda pagina
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertTrue(elementos.size() == 5);
			driver.navigate().to("http://localhost:8090/admin/list?page=1");
			//Borramos los 3 usuarios
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Borrar usuario", PO_View.getTimeout());
			elementos.get(0).click();
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Borrar usuario", PO_View.getTimeout());
			elementos.get(1).click();
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "text", "Borrar usuario", PO_View.getTimeout());
			elementos.get(2).click();			
			driver.navigate().to("http://localhost:8090/admin/list?page=1");
			SeleniumUtils.textoNoPresentePagina(driver, "Rodri");
			SeleniumUtils.textoNoPresentePagina(driver, "Saul");
			SeleniumUtils.textoNoPresentePagina(driver, "Luismi");
			// Nos desconectamos
			PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		}
	}
