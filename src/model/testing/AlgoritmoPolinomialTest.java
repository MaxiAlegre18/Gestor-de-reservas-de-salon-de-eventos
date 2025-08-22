package model.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.AlgoritmoPolinomial;
import model.GestorReservas;
import model.Oferta;

public class AlgoritmoPolinomialTest {
	
	private GestorReservas gestor;
	private ArrayList<Oferta> ofertasOptimasEsperables;
	private ArrayList<Oferta> ofertasOptimasNoEsperables;
	private List<Oferta> ofertasOptimasObtenidas;
	
	private GestorReservas gestor2;
	private ArrayList<Oferta> ofertasOptimasEsperables2;
	private ArrayList<Oferta> ofertasOptimasNoEsperables2;
	private List<Oferta> ofertasOptimasObtenidas2;
	
	private GestorReservas gestor3;
	private ArrayList<Oferta> ofertasOptimasEsperables3;
	private ArrayList<Oferta> ofertasOptimasNoEsperables3;
	private List<Oferta> ofertasOptimasObtenidas3;

	private GestorReservas gestor4;
	private ArrayList<Oferta> ofertasOptimasEsperables4;
	private ArrayList<Oferta> ofertasOptimasNoEsperables4;
	private List<Oferta> ofertasOptimasObtenidas4;
	
	@Before
	public void setUp() {
    	inicializarEj1();
    	inicializarEj2();
    	inicializarEj3();
    	inicializarEj4();
	}
	
	private void inicializarEj1() {
		Oferta of1 = new Oferta("Of1", 8, 12, 16000);
    	Oferta of2 = new Oferta("Of2", 12, 15, 14000);
    	Oferta of3 = new Oferta("Of3", 11, 16, 10000);
    	Oferta of4 = new Oferta("Of4", 17, 21, 14000);
    	Oferta of5 = new Oferta("Of5", 7, 11, 14000);
    	
    	gestor = new GestorReservas();
    	gestor.registrarOferta(of1);
		gestor.registrarOferta(of2);
		gestor.registrarOferta(of3);
		gestor.registrarOferta(of4);
		gestor.registrarOferta(of5);
		
		ofertasOptimasEsperables = new ArrayList<>(3);
		ofertasOptimasEsperables.add(of1);
		ofertasOptimasEsperables.add(of2);
		ofertasOptimasEsperables.add(of4);
		
		ofertasOptimasNoEsperables = new ArrayList<>(2);
		ofertasOptimasNoEsperables.add(of3);
		ofertasOptimasNoEsperables.add(of5);
	}
	
	private void inicializarEj2() {
    	Oferta of1 = new Oferta("Of1", 8, 12, 16000);
    	Oferta of2 = new Oferta("Of2", 12, 15, 14000);
    	Oferta of3 = new Oferta("Of3", 11, 16, 10000);
    	Oferta of4 = new Oferta("Of4", 17, 21, 14000);
    	Oferta of5 = new Oferta("Of5", 7, 11, 14000);
    	Oferta of6 = new Oferta("Of6", 19, 21, 15000);
    	Oferta of7 = new Oferta("Of7", 9, 13, 16000);
    	Oferta of8 = new Oferta("Of8", 21, 23, 10000);
    	
    	gestor2 = new GestorReservas();
    	gestor2.registrarOferta(of1);
		gestor2.registrarOferta(of2);
		gestor2.registrarOferta(of3);
		gestor2.registrarOferta(of4);
		gestor2.registrarOferta(of5);
		gestor2.registrarOferta(of6);
		gestor2.registrarOferta(of7);
		gestor2.registrarOferta(of8);
		
		ofertasOptimasEsperables2 = new ArrayList<>(3);
		ofertasOptimasEsperables2.add(of1);
		ofertasOptimasEsperables2.add(of6);
		ofertasOptimasEsperables2.add(of8);
		
		ofertasOptimasNoEsperables2 = new ArrayList<>(2);
		ofertasOptimasNoEsperables2.add(of3);
		ofertasOptimasNoEsperables2.add(of5);
	}
	
	private void inicializarEj3() {
		Oferta of1 = new Oferta("Of1", 8, 12, 16000);
    	Oferta of2 = new Oferta("Of2", 12, 15, 14000);
    	Oferta of3 = new Oferta("Of3", 11, 16, 10000);
    	
    	gestor3 = new GestorReservas();
    	gestor3.registrarOferta(of1);
		gestor3.registrarOferta(of2);
		gestor3.registrarOferta(of3);
		
		ofertasOptimasEsperables3 = new ArrayList<>(2);
		ofertasOptimasEsperables3.add(of1);
		ofertasOptimasEsperables3.add(of2);
		
		ofertasOptimasNoEsperables3 = new ArrayList<>(1);
		ofertasOptimasNoEsperables3.add(of3);
	}
	
	private void inicializarEj4() {
		Oferta of1 = new Oferta("Of1", 1, 5, 50000);
    	Oferta of2 = new Oferta("Of2", 6, 8, 20000);
    	Oferta of3 = new Oferta("Of3", 1, 10, 60000);
    	
    	gestor4 = new GestorReservas();
    	gestor4.registrarOferta(of1);
		gestor4.registrarOferta(of2);
		gestor4.registrarOferta(of3);
		
		ofertasOptimasEsperables4 = new ArrayList<>(2);
		ofertasOptimasEsperables4.add(of1);
		ofertasOptimasEsperables4.add(of2);
		
		ofertasOptimasNoEsperables4 = new ArrayList<>(1);
		ofertasOptimasNoEsperables4.add(of3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void seleccionarOfertasNulasDaExcepcion() {
		AlgoritmoPolinomial.seleccionarOfertasOptimas(null);
	}
	  
	@Test
	public void seleccionaOfertasOptimas() {    	
		ofertasOptimasObtenidas = gestor.seleccionarOfertasOptimas();
    	for (Oferta esperable : ofertasOptimasEsperables) {
    		assertTrue(ofertasOptimasObtenidas.contains(esperable));
    	}
	}
	
	@Test
	public void noSeleccionaOfertasInoptimas() {
		ofertasOptimasObtenidas = gestor.seleccionarOfertasOptimas();
    	for (Oferta noEsperable : ofertasOptimasNoEsperables) {
    		assertFalse(ofertasOptimasObtenidas.contains(noEsperable));
    	}
	}
	
	@Test
	public void seleccionaOfertas2Optimas() {    	
		ofertasOptimasObtenidas2 = gestor2.seleccionarOfertasOptimas();
    	for (Oferta esperable : ofertasOptimasEsperables2) {
    		assertTrue(ofertasOptimasObtenidas2.contains(esperable));
    	}
	}
	
	@Test
	public void noSeleccionaOfertas2Inoptimas() {
		ofertasOptimasObtenidas2 = gestor2.seleccionarOfertasOptimas();
    	for (Oferta noEsperable : ofertasOptimasNoEsperables2) {
    		assertFalse(ofertasOptimasObtenidas2.contains(noEsperable));
    	}
	}
	
	@Test
	public void seleccionaOfertas3Optimas() {    	
		ofertasOptimasObtenidas3 = gestor3.seleccionarOfertasOptimas();
    	for (Oferta esperable : ofertasOptimasEsperables3) {
    		assertTrue(ofertasOptimasObtenidas3.contains(esperable));
    	}
	}
	
	@Test
	public void noSeleccionaOfertas3Inoptimas() {
		ofertasOptimasObtenidas3 = gestor3.seleccionarOfertasOptimas();
    	for (Oferta noEsperable : ofertasOptimasNoEsperables3) {
    		assertFalse(ofertasOptimasObtenidas3.contains(noEsperable));
    	}
	}
	
	@Test
	public void seleccionaOfertas4Optimas() {    	
		ofertasOptimasObtenidas4 = gestor4.seleccionarOfertasOptimas();
    	for (Oferta esperable : ofertasOptimasEsperables4) {
    		assertTrue(ofertasOptimasObtenidas4.contains(esperable));
    	}
	}
	
	@Test
	public void noSeleccionaOfertas4Inoptimas() {
		ofertasOptimasObtenidas4 = gestor4.seleccionarOfertasOptimas();
    	for (Oferta noEsperable : ofertasOptimasNoEsperables4) {
    		assertFalse(ofertasOptimasObtenidas4.contains(noEsperable));
    	}
	}

}
