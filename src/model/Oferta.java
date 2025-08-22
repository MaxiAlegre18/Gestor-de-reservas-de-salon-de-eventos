package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Oferta implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nombreOferente;
	private int horaInicio;
	private int horaFin;
	private int monto;
	private ArrayList<String> equipamiento;
	
	public Oferta(String nombreOferente, int horaInicio, int horaFin, int monto) {
		verificarHoras(horaInicio, horaFin);
		verificarMonto(monto);
		
		this.nombreOferente = nombreOferente;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.monto = monto;
		this.equipamiento = new ArrayList<>();	
	}
	
	public Oferta(String nombreOferente, int horaInicio, int horaFin, int monto, ArrayList<String> equipamiento) {
		verificarHoras(horaInicio, horaFin);
		verificarMonto(monto);
		
		this.nombreOferente = nombreOferente;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.monto = monto;
		this.equipamiento = equipamiento;	
	}
	
	public String getNombreOferente() {
		return this.nombreOferente;
	}
	
	public int getHoraInicio() {
		return this.horaInicio;
	}

	public int getHoraFin() {
		return this.horaFin;
	}

	public int getMonto() {
		return this.monto;
	}
	
	public ArrayList<String> getEquipamiento() {
		return new ArrayList<String>(equipamiento);
	}
	
	@Override
	public boolean equals(Object obj) {										// Necesario por el comparator
	   if (this == obj) 
	   	return true; 														// Si se quiere autocomparar
	   if (obj == null || getClass() != obj.getClass()) 
		   return false; 														// Verifico nulos y clases

	   Oferta oferta = (Oferta) obj;
	   return horaInicio == oferta.getHoraInicio() && horaFin == oferta.getHoraFin() && monto == oferta.getMonto();
	}
	
	private void verificarHoras(int inicio, int fin) {
		if (inicio < 0  || inicio >= 24 || fin < 0 || fin > 24) 
            throw new IllegalArgumentException("Las horas deben estar entre 0 y 24");
        
		if (fin <= inicio )
			throw new IllegalArgumentException("Hora fin debe ser mayor que la hora de inicio");
	}
	
	private void verificarMonto(int monto) {
		if (monto <= 0)
			throw new IllegalArgumentException("El monto debe ser mayor a 0");
	}
}
