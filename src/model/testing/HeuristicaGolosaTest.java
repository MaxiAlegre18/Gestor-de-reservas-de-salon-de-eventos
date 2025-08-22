package model.testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.GestorReservas;
import model.HeuristicaGolosa;
import model.Oferta;

public class HeuristicaGolosaTest {
	
	private GestorReservas gestor;
	private ArrayList<Oferta> ofertasOptimasEsperables;
	private ArrayList<Oferta> ofertasOptimasNoEsperables;
	private List<Oferta> ofertasOptimasObtenidas;
	
	private GestorReservas gestor2;
	private ArrayList<Oferta> ofertasOptimasEsperables2;
	private ArrayList<Oferta> ofertasOptimasNoEsperables2;
	private List<Oferta> ofertasOptimasObtenidas2;

	@Before
	public void setUp() {
		/*
		 * Ejemplo del enunciado del TP
		 */
    	inicializarEj1();
		inicializarEj2();
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
		/*
		 * Ejemplo del enunciado del TP
		 */
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
	
	@Test(expected = IllegalArgumentException.class)
	public void seleccionarOfertasNulasDaExcepcion() {
		HeuristicaGolosa.seleccionarOfertasOptimas(null);
	}
	
	@Test
	public void seleccionaOfertasOptimas() {    	
		ofertasOptimasObtenidas = gestor.seleccionarOfertasHeuristicas();
    	for (Oferta esperable : ofertasOptimasEsperables) {
    		assertTrue(ofertasOptimasObtenidas.contains(esperable));
    	}
	}
	
	@Test
	public void noSeleccionaOfertasInoptimas() {
		ofertasOptimasObtenidas = gestor.seleccionarOfertasHeuristicas();
    	for (Oferta noEsperable : ofertasOptimasNoEsperables) {
    		assertFalse(ofertasOptimasObtenidas.contains(noEsperable));
    	}
	}
	
	@Test
	public void seleccionaOfertas2Optimas() {    	
		ofertasOptimasObtenidas2 = gestor2.seleccionarOfertasHeuristicas();
    	for (Oferta esperable : ofertasOptimasEsperables2) {
    		assertTrue(ofertasOptimasObtenidas2.contains(esperable));
    	}
	}
	
	@Test
	public void noSeleccionaOfertas2Inoptimas() {
		ofertasOptimasObtenidas2 = gestor2.seleccionarOfertasHeuristicas();
    	for (Oferta noEsperable : ofertasOptimasNoEsperables2) {
    		assertFalse(ofertasOptimasObtenidas2.contains(noEsperable));
    	}
	}
}
