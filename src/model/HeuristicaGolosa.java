package model;

import java.util.ArrayList;
import java.util.List;

import model.comparators.OfertaMontoComparator;

public class HeuristicaGolosa {
	private static List<Oferta> ofertasSeleccionadas = new ArrayList<>();

	public static List<Oferta> seleccionarOfertasOptimas(List<Oferta> ofertas) {
		if (ofertas == null) {
			throw new IllegalArgumentException("La lista de ofertas no puede ser null");
		}
		if (ofertas.size() <= 1) {
			return ofertas;
		}
		
		ofertasSeleccionadas.clear();
        ofertas.sort(new OfertaMontoComparator());							//ordeno de mayor a menor segun el monto

        for (Oferta oferta : ofertas) {
        	if (esOfertaValida(oferta)) {
                ofertasSeleccionadas.add(oferta);
            }
        }

        return ofertasSeleccionadas;
    }

	private static boolean esOfertaValida(Oferta oferta) {
		for (Oferta ofertaSelec : ofertasSeleccionadas) {
	        if (esSolapamiento(oferta, ofertaSelec))
	        	return false;
		}
	    return true;
	}

	private static boolean esSolapamiento(Oferta oferta, Oferta ofertaSelec) {
		return oferta.getHoraInicio() < ofertaSelec.getHoraFin() && oferta.getHoraFin() > ofertaSelec.getHoraInicio();
	}
	
}
