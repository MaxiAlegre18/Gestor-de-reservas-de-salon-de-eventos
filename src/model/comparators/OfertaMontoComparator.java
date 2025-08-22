package model.comparators;

import java.util.Comparator;

import model.Oferta;

public class OfertaMontoComparator implements Comparator<Oferta>{
	
	@Override
    public int compare(Oferta a, Oferta b) {
		if (a == null && b == null) 
			return 0;
	    if (a == null) 
	    	return 1;
	    if (b == null) 
	    	return -1;
	    
        return Integer.compare(b.getMonto(), a.getMonto()); 						// Orden descendente
    }
}
