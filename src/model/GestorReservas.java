package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class GestorReservas implements Serializable{
	
	private List<Oferta> ofertas;
	private List<Oferta> ofertasOptimas;
	private List<Observer> observers;
	private String[] equipamientoDisponible;
	
	// Serializacion
	private static final long serialVersionUID = 1L;
	private final String archivoDatos = "ofertas.ser";
	private boolean persistenciaHabilitada;
	
	public GestorReservas() {
		this.persistenciaHabilitada = false; // Por defecto, la persistencia está deshabilitada (se debe activar manualmente)
		this.ofertas = cargarOfertas();
		this.observers = new ArrayList<>();
		
		inicializarEquipamientoDisponible();
	}
	
	private void inicializarEquipamientoDisponible() {
		equipamientoDisponible = new String[] {
    			"Amplificadores",
    			"Bajo",
    			"Batería",
    			"Guitarra",
    			"Mesa de mezclas",
    			"Microfonos",
    			"Parlantes",
    			"Teclado"
    	};
	}

	/*
	 * Cargar/Registrar/Borrar Ofertas
	 */
	
	public void cargarOfertasSerializadas() {
		habilitarPersistencia();
		this.ofertas = cargarOfertas();
	}
	
	private void habilitarPersistencia() {
		this.persistenciaHabilitada = true;
	}
	
    private List<Oferta> cargarOfertas() {   
        if (persistenciaHabilitada) {
        	return leerArchivoSerializado();
        } else {
        	return new ArrayList<>();
        }
    }

	@SuppressWarnings("unchecked")
	private List<Oferta> leerArchivoSerializado() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoDatos))) {
            return (List<Oferta>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
	}
	
	
	public void registrarOferta(Oferta oferta) {
		verificarOfertaNoNula(oferta);
        
        ofertas.add(oferta);
        guardarOfertasEnArchivo();
        notificarObservadores();
    }
	
	public void borrarTodasLasOfertas() {
	    ofertas.clear();  // Limpiamos la lista de ofertas
	    guardarOfertasEnArchivo(); // Guardamos los cambios
	    notificarObservadores();  // Notificamos a los observadores
	}
	
	/*
	 * Serialización de ofertas
	 */
	 
    private void guardarOfertasEnArchivo() {
    	if (persistenciaHabilitada) {
    		guardarEnArchivoSerializado();
    	}
    }

	private void guardarEnArchivoSerializado() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoDatos))) {
            oos.writeObject(ofertas);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/*
	 * Observadores
	 */
	
	private void notificarObservadores() {
        for (Observer observer : observers) {
            observer.notificar(new ArrayList<>(ofertas));  // Notifica a cada observador con los datos actualizados
        }
    }

	/*
	 * Ofertas optimas
	 */
	
	public List<Oferta> seleccionarOfertasHeuristicas() {
		ofertasOptimas = HeuristicaGolosa.seleccionarOfertasOptimas(ofertas);
        return duplicarOfertasOptimas();
    }

	public List<Oferta> seleccionarOfertasOptimas() {
		ofertasOptimas = AlgoritmoPolinomial.seleccionarOfertasOptimas(ofertas);
        return duplicarOfertasOptimas();
    }
	
	private List<Oferta> duplicarOfertasOptimas() {
		return new ArrayList<>(ofertasOptimas);
	}
	
	public int calcularMontoTotalOfertasOptimas() {
		if (ofertasOptimas == null) {
			throw new RuntimeException("No se han seleccionado las ofertas optimas aun.");
		}
		
		int montoTotal = 0;
		for (Oferta o : ofertasOptimas) {
			montoTotal += o.getMonto();
		}
		return montoTotal;
	}
	
	/*
	 *  Getters
	 */
	
	public List<Oferta> getOfertas() {
        return new ArrayList<>(ofertas);
    }
	
	public String[] getEquipamientoDisponible() {
		String[] ret = new String[equipamientoDisponible.length];
		for (int i=0; i<ret.length; i++) {
			ret[i] = equipamientoDisponible[i];
		}
		return ret;
	}
	
	/*
	 * Otros
	 */
	
	private void verificarOfertaNoNula(Oferta oferta) {
		if (oferta == null) 
            throw new IllegalArgumentException("La oferta no puede ser nula");
	}
}
