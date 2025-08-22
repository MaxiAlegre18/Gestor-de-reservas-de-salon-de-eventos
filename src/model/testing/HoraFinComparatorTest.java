package model.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Oferta;
import model.comparators.OfertaHoraFinComparator;

public class HoraFinComparatorTest {
	Oferta ofertaTerminaPrimero;
	Oferta ofertaTerminaUltimo;
	Oferta ofertaTerminaIgual;
	Oferta ofertaNula;
	OfertaHoraFinComparator comparator;

	@Before
	public void setUp() {
		ofertaTerminaPrimero = new Oferta("Of1", 10, 13, 1);
		ofertaTerminaUltimo =  new Oferta("Of2", 12, 15, 1);
		ofertaTerminaIgual = new Oferta("Of3", 10, 13, 1);
		ofertaNula = null;
		comparator = new OfertaHoraFinComparator();
	}

	@Test
	public void ambasOfertasNulasTest() {
		assertEquals(comparator.compare(ofertaNula, ofertaNula), 0);
	}
	
	@Test
	public void primeraOfertaNulaTest() {
		assertEquals(comparator.compare(ofertaNula, ofertaTerminaPrimero), 1);
	}
	
	@Test
	public void segundaOfertaNulaTest() {
		assertEquals(comparator.compare(ofertaTerminaPrimero, ofertaNula), -1);
	}
	
	@Test
	public void ofertasEnOrdenTest() {
		assertEquals(comparator.compare(ofertaTerminaPrimero, ofertaTerminaUltimo), -2);
	}
	
	@Test
	public void ofertasAlRevesTest() {
		assertEquals(comparator.compare(ofertaTerminaUltimo, ofertaTerminaPrimero), 2);
	}
	
	@Test
	public void ofertasTerminanIgualTest() {
		assertEquals(comparator.compare(ofertaTerminaPrimero, ofertaTerminaIgual), 0);
	}
}
