package view;

import java.util.ArrayList;

import model.Oferta;

public class AuxiliarOfertaFormatter {
	public static String obtenerEquipamientoFormateado(Oferta oferta) {
		ArrayList<String> equipamiento = oferta.getEquipamiento();
		StringBuilder sb = new StringBuilder();
		for (String equipo : equipamiento) {
			sb.append(equipo);
			sb.append(", ");
		}
		if (tieneComoMinimoUnEquipo(sb)) {
			borrarUltimosDosCaracteres(sb);
		}
		return sb.toString();
	}

	private static boolean tieneComoMinimoUnEquipo(StringBuilder sb) {
		return sb.length() >= 2;
	}
	
	private static void borrarUltimosDosCaracteres(StringBuilder sb) {
		sb.delete(sb.length()-2, sb.length());
	}
}
