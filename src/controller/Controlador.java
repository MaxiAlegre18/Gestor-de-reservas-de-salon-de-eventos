package controller;
 
import java.util.ArrayList;
import java.util.List;

import model.GestorReservas;
import model.Oferta;
import model.Observer;

public class Controlador {
	private GestorReservas gestor;
	private Observer observer;

    public Controlador(Observer observer) {
    	this.observer = observer;
    	this.gestor = new GestorReservas();
    	gestor.cargarOfertasSerializadas();
    }

    public void registrarOferta(String nombreOferente, int horarioInicio, int horarioFin, int monto, ArrayList<String> equipamiento) {
    	Oferta ofertaAgregada = new Oferta(nombreOferente, horarioInicio, horarioFin, monto, equipamiento); 
        gestor.registrarOferta(ofertaAgregada);
        observer.notificar(gestor.getOfertas());
    }
   
    public List<Oferta> obtenerOfertas() {
        return gestor.getOfertas();
    }
    
    public List<Oferta> resolverAdjudicacion(String metodo) {
    	switch(metodo) {
    	case "Heuristica":
    		return gestor.seleccionarOfertasHeuristicas();
    	case "Optimo Polinomial":
    		return gestor.seleccionarOfertasOptimas();
		default:
			throw new IllegalArgumentException("Metodo invalido.");
    	}
    }
    
	public int calcularMontoTotal() {
		return gestor.calcularMontoTotalOfertasOptimas();
	}
	
    public void borrarTodasLasOfertas() {
        gestor.borrarTodasLasOfertas();  // Llama al método del modelo para borrar las ofertas
        observer.notificar(gestor.getOfertas());  // Notifica a la vista con las ofertas actualizadas (vacías)
    }
    
    public String[] equipamientoDisponible() {
    	return gestor.getEquipamientoDisponible();
    }
}
