package model.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.Oferta;
import model.comparators.OfertaHoraInicioComparator;

public class HoraInicioComparatorTest {
	Oferta ofertaIniciaPrimero;
	Oferta ofertaIniciaUltimo;
	Oferta ofertaIniciaIgual;
	Oferta ofertaNula;
	OfertaHoraInicioComparator comparator;

	@Before
	public void setUp() {
		ofertaIniciaPrimero = new Oferta("Of1", 10, 13, 1);
		ofertaIniciaUltimo =  new Oferta("Of2", 12, 15, 1);
		ofertaIniciaIgual = new Oferta("Of3", 10, 13, 1);
		ofertaNula = null;
		comparator = new OfertaHoraInicioComparator();
	}

	@Test
	public void ambasOfertasNulasTest() {
		assertEquals(comparator.compare(ofertaNula, ofertaNula), 0);
	}
	
	@Test
	public void primeraOfertaNulaTest() {
		assertEquals(comparator.compare(ofertaNula, ofertaIniciaPrimero), 1);
	}
	
	@Test
	public void segundaOfertaNulaTest() {
		assertEquals(comparator.compare(ofertaIniciaPrimero, ofertaNula), -1);
	}
	
	@Test
	public void ofertasEnOrdenTest() {
		assertEquals(comparator.compare(ofertaIniciaPrimero, ofertaIniciaUltimo), -2);
	}
	
	@Test
	public void ofertasAlRevesTest() {
		assertEquals(comparator.compare(ofertaIniciaUltimo, ofertaIniciaPrimero), 2);
	}
	
	@Test
	public void ofertasInicianIgualTest() {
		assertEquals(comparator.compare(ofertaIniciaPrimero, ofertaIniciaIgual), 0);
	}

}
