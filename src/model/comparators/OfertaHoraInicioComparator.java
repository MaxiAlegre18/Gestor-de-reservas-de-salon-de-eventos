package model.comparators;

import java.util.Comparator;

import model.Oferta;

public class OfertaHoraInicioComparator implements Comparator<Oferta> {

	@Override
	public int compare(Oferta o1, Oferta o2) {
		if (o1 == null && o2 == null) {
			return 0;
		}	
	    if (o1 == null) {
	    	return 1;
	    }
	    if (o2 == null) {
	    	return -1;
	    }
	    	
	    return o1.getHoraInicio() - o2.getHoraInicio();
	}


}
