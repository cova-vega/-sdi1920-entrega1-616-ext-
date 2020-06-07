package com.uniovi.pageobjects;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.uniovi.util.SeleniumUtils;
public class PO_HomeView extends PO_NavView {
	/**
	 * Comprueba que se carga el saludo de bienvenida correctamente
	 */
	static public void checkWelcome(WebDriver driver, int language) {
		PO_View.checkElement(driver, "text", "Bienvenidos a MySocialNetwork");
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
