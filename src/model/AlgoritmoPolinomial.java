package model;

import java.util.ArrayList;
import java.util.List;

import model.comparators.OfertaHoraFinComparator;
import model.comparators.OfertaHoraInicioComparator;

public class AlgoritmoPolinomial {
	private static List<Oferta> ofertasIngresadas = new ArrayList<>();
	private static List<Oferta> ofertasOptimas = new ArrayList<>();
	private static List<Oferta> ofertasOrdenadasPorHoraFin;
	private static int[] gananciasMaximasPorOferta;
	private static boolean[] ofertasSeleccionadas;

	public static List<Oferta> seleccionarOfertasOptimas(List<Oferta> ofertas) {
		if (ofertas == null) {
			throw new IllegalArgumentException("La lista de ofertas no puede ser null");
		}
		if (ofertas.size() <= 1) {
			return ofertas;
		}
		
		ofertasOptimas.clear();
		ofertasIngresadas = ofertas;

		ordenarOfertasPorHoraFin();
		
		inicializarArreglos();
		
		encontrarGananciaMaximaEnOfertas(ofertasOrdenadasPorHoraFin.size());

		reconstruirOfertasOptimas();
		
		ordenarOfertasOptimasPorHoraInicio();

		return ofertasOptimas;
		
    }

	private static void ordenarOfertasPorHoraFin() {
		ofertasOrdenadasPorHoraFin = new ArrayList<>(ofertasIngresadas);
		ofertasOrdenadasPorHoraFin.sort(new OfertaHoraFinComparator()); // usa el compareTo de ofertas
	}

	private static void inicializarArreglos() {
		gananciasMaximasPorOferta = new int[ofertasOrdenadasPorHoraFin.size()];
		ofertasSeleccionadas = new boolean[ofertasOrdenadasPorHoraFin.size()];
	}
	
	private static int encontrarGananciaMaximaEnOfertas(int cantidadOfertas) {	
		// Caso base
		if (cantidadOfertas == 1) {
			gananciasMaximasPorOferta[0] = ofertasOrdenadasPorHoraFin.get(0).getMonto();
			ofertasSeleccionadas[0] = true;
			return gananciasMaximasPorOferta[0];
		}
		
		int ofertaActual = cantidadOfertas - 1;
		
		
		// Evita que se opere sobre ofertas que ya se analizaron, mejorando la complejidad
		if (gananciasMaximasPorOferta[ofertaActual] != 0) {
			return gananciasMaximasPorOferta[ofertaActual];
		}
		
		int gananciaIncluyendoOfertaActual = ofertasOrdenadasPorHoraFin.get(ofertaActual).getMonto();
		int indiceUltimoNoConflictivo = ultimoNoConflictivo(ofertasOrdenadasPorHoraFin, cantidadOfertas);
		
		// Si no hay ganancias que no hagan conflicto con la actual, entonces no se puede agregar otras ofertas
		if (indiceUltimoNoConflictivo != -1) {
			gananciaIncluyendoOfertaActual += encontrarGananciaMaximaEnOfertas(indiceUltimoNoConflictivo+1);
		}
		
		int gananciaExcluyendoOfertaActual = encontrarGananciaMaximaEnOfertas(ofertaActual);
		
		
		if (gananciaIncluyendoOfertaActual > gananciaExcluyendoOfertaActual) {
			gananciasMaximasPorOferta[ofertaActual] = gananciaIncluyendoOfertaActual;
			ofertasSeleccionadas[ofertaActual] = true;
		} else {
			gananciasMaximasPorOferta[ofertaActual] = gananciaExcluyendoOfertaActual;
			ofertasSeleccionadas[ofertaActual] = false;
		}
		
		return gananciasMaximasPorOferta[ofertaActual];
	}

	private static void reconstruirOfertasOptimas() {
		int indiceOfertaActual = ofertasSeleccionadas.length - 1;
		while (indiceOfertaActual >=0) {
			if (ofertasSeleccionadas[indiceOfertaActual]) {
				ofertasOptimas.add(ofertasOrdenadasPorHoraFin.get(indiceOfertaActual));
				indiceOfertaActual = ultimoNoConflictivo(ofertasOrdenadasPorHoraFin, indiceOfertaActual+1);
			} else {
				indiceOfertaActual--;
			}
		}
	}
	
	private static int ultimoNoConflictivo(List<Oferta> ofertas, int cantidadOfertasConsideradas) {
		int ofertaFija = cantidadOfertasConsideradas - 1;
		// La oferta fija es ofertas[i-1] entonces las ofertas a comparar deben ser a partir de ofertas[i-2]
		for (int j = cantidadOfertasConsideradas-2; j >= 0; j-- ) {
			if (ofertas.get(j).getHoraFin() <= ofertas.get(ofertaFija).getHoraInicio()) {
				return j;
			}
		}
		return -1;
	}
	
	private static void ordenarOfertasOptimasPorHoraInicio() {
		ofertasOptimas.sort(new OfertaHoraInicioComparator());
	}
}