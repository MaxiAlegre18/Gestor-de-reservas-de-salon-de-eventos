package model.testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Oferta;
import model.comparators.OfertaMontoComparator;

public class MontoComparatorTest {
	Oferta ofertaMontoMasBajo;
	Oferta ofertaMontoMasAlto;
	Oferta ofertaMontoIgual;
	Oferta ofertaNula;
	OfertaMontoComparator comparator;

	@Before
	public void setUp() {
		ofertaMontoMasBajo = new Oferta("Of1", 10, 13, 1000);
		ofertaMontoMasAlto =  new Oferta("Of2", 12, 15, 2000);
		ofertaMontoIgual = new Oferta("Of3", 10, 13, 1000);
		ofertaNula = null;
		comparator = new OfertaMontoComparator();
	}

	@Test
	public void ambasOfertasNulasTest() {
		assertEquals(comparator.compare(ofertaNula, ofertaNula), 0);
	}
	
	@Test
	public void primeraOfertaNulaTest() {
		assertEquals(comparator.compare(ofertaNula, ofertaMontoMasBajo), 1);
	}
	
	@Test
	public void segundaOfertaNulaTest() {
		assertEquals(comparator.compare(ofertaMontoMasBajo, ofertaNula), -1);
	}
	
	@Test
	public void ofertasEnOrdenTest() {
		assertEquals(comparator.compare(ofertaMontoMasBajo, ofertaMontoMasAlto), 1);
	}
	
	@Test
	public void ofertasAlRevesTest() {
		assertEquals(comparator.compare(ofertaMontoMasAlto, ofertaMontoMasBajo), -1);
	}
	
	@Test
	public void ofertasMontosIgualesTest() {
		assertEquals(comparator.compare(ofertaMontoMasBajo, ofertaMontoIgual), 0);
	}

}
