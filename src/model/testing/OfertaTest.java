package model.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import model.Oferta;

public class OfertaTest {
	
	@Test(expected= IllegalArgumentException.class)
	public void horaInicioInvalidaTest() {
		new Oferta("Of", -1,0,0);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void horaFinInvalidaTest() {
		new Oferta("Of",0,-1,0);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void horaInicioMayorHoraFinInvalidaTest() {
		new Oferta("Of",5,4,0);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void montoInvalidaTest() {
		new Oferta("Of",1,4,-1);
	}
	
	@Test
	public void ofertaIngresaDatosCorrectamenteTest() {
		Oferta oferta = new Oferta("Oferente", 12, 18, 22000);
		assertEquals("Oferente", oferta.getNombreOferente());
		assertEquals(12, oferta.getHoraInicio());
		assertEquals(18, oferta.getHoraFin());
		assertEquals(22000, oferta.getMonto());
	}
	
	@Test
	public void ofertaEqualsOtra() {
		Oferta oferta = new Oferta("Oferente1", 12, 18, 19000);
		Oferta otra = new Oferta("Oferente2", 12, 18, 18000);
		assertFalse(oferta.equals(otra));
	}
	
	@Test
	public void ofertaNotEqualsAOtra() {
		Oferta oferta = new Oferta("Oferente1", 12, 18, 19000);
		Oferta otra = new Oferta("Oferente2", 12, 18, 19000);
		assertTrue(oferta.equals(otra));
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void ofertaNotEqualsOtraClase() {
		Oferta oferta = new Oferta("Oferente1", 12, 18, 19000);
		String str = "Oferente2, 12, 18, 19000";
		assertFalse(oferta.equals(str));
	}
	
	@Test
	public void agregarEquipamientoTest() {
		ArrayList<String> equip = agregarEquipamientoAlterno();
		Oferta oferta = new Oferta("Oferente1", 12, 18, 19000, equip);
		
		assertEquals(oferta.getEquipamiento(), equip);
	}

	private ArrayList<String> agregarEquipamientoAlterno() {
		ArrayList<String> equip = new ArrayList<>();
		
		equip.add("Parlantes");
		equip.add("Microfono");
		equip.add("Amplificadores");
		equip.add("Guitarra");
		equip.add("Bajo");
		equip.add("Batería");
		
		return equip;
	}

}
