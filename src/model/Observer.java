package model;

import java.util.List;

public interface Observer {

	void notificar(List<Oferta> ofertas);
}
