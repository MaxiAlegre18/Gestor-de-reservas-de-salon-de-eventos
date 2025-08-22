package model.testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.GestorReservas;
import model.Oferta;

public class GestorReservasTest {

	private GestorReservas gestor;

    @Before
    public void setUp() {
        gestor = new GestorReservas();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void registrarOfertaNulaTest() {
        gestor.registrarOferta(null);
    }

    @Test
    public void cantCorrectaDeOfertasRegistradasTest() {
        gestor.registrarOferta(new Oferta("Of1",8, 12, 16000));
        gestor.registrarOferta(new Oferta("Of2",12, 15, 14000));
        
        List<Oferta> ofertas = gestor.getOfertas();
        assertEquals(2, ofertas.size());
    }

    @Test
    public void cantCorrectaDeSeleccionarOfertasHeuristicasTest() {
    	gestor.registrarOferta(new Oferta("Of1",8, 12, 16000));
    	gestor.registrarOferta(new Oferta("Of2",12, 15, 14000));
    	gestor.registrarOferta(new Oferta("Of3",11, 16, 10000));
    	gestor.registrarOferta(new Oferta("Of4",17, 21, 14000));
    	gestor.registrarOferta(new Oferta("Of5",7, 11, 14000));

        List<Oferta> seleccionadas = gestor.seleccionarOfertasHeuristicas();
        assertEquals(3, seleccionadas.size());
    }
	

}
