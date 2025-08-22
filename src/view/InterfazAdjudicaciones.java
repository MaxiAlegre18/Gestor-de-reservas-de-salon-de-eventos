package view;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controlador;
import model.Observer;
import model.Oferta;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class InterfazAdjudicaciones implements Observer {

	private JFrame ventana;
	private Controlador controlador;
	private JTable tablaAdjudicacion;
	private JScrollPane scrollTablaAdjudicacion;
	private DefaultTableModel modelTablaAdjudicacion;
	private JLabel lblMontoTotal;
	private String metodoElegido;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazAdjudicaciones window = new InterfazAdjudicaciones();
					window.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfazAdjudicaciones() {
		Auxiliares.adaptarVentanaAlUsuario();
		controlador = new Controlador(this);
		initialize();
		notificar(controlador.resolverAdjudicacion(metodoElegido));
	}

	
	private void initialize() {
		abrirVentanaSeleccionMetodo();
		inicializarVentana();
		generarTablaAdjudicacion();
	    generarLblMontoTotal();
	}

	private void inicializarVentana() {
		ventana = new JFrame();
		ventana.setTitle("Adjudicaciones");
		ventana.setSize(488,303);
		ventana.setBounds(100, 100, 611, 413);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		ventana.setVisible(true);
	}
	
	
	private void abrirVentanaSeleccionMetodo() {
		String[] opciones = {"Heuristica", "Optimo Polinomial"};
		
		int respuesta = JOptionPane.showOptionDialog(
				null, 
				"Seleccione un método de resolución", 
				"Método", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, 
				null, 
				opciones, 
				opciones[0]);
		
		if (respuesta == 0) {
			metodoElegido = "Heuristica";
		} else {
			metodoElegido = "Optimo Polinomial";
		}
	}
	
	private void generarTablaAdjudicacion() {
	    String[] columnas = {"Nombre", "Hora Inicio", "Hora Fin", "Monto", "Equipamiento"};
	    modelTablaAdjudicacion = new DefaultTableModel(columnas, 0);
	    tablaAdjudicacion = new JTable(modelTablaAdjudicacion);
	    
	    ajustarColumnasTablaAdjudicacion();
	    
	    scrollTablaAdjudicacion = new JScrollPane(tablaAdjudicacion);
	    scrollTablaAdjudicacion.setBounds(10, 24, 575, 282);
	    ventana.getContentPane().add(scrollTablaAdjudicacion);
	}
	
	private void ajustarColumnasTablaAdjudicacion() {
		tablaAdjudicacion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tablaAdjudicacion.getColumn("Nombre").setPreferredWidth(100);
		tablaAdjudicacion.getColumn("Hora Inicio").setPreferredWidth(65);
		tablaAdjudicacion.getColumn("Hora Fin").setPreferredWidth(65);
		tablaAdjudicacion.getColumn("Equipamiento").setPreferredWidth(450);
	}

	private void generarLblMontoTotal() {
		lblMontoTotal = new JLabel("Monto Total: ");
	    lblMontoTotal.setBounds(10, 331, 143, 32);
	}

	@Override
	public void notificar(List<Oferta> ofertasAdjudicadas) {
		llenarTablaAdjudicacion(ofertasAdjudicadas);
		agregarGananciaAlLblMontoTotal();
		
	}
	
	private void llenarTablaAdjudicacion(List<Oferta> ofertasAdjudicadas) {
        modelTablaAdjudicacion.setRowCount(0);
        for (Oferta ofertaAdjudicada : ofertasAdjudicadas) {
        	String equipamiento = AuxiliarOfertaFormatter.obtenerEquipamientoFormateado(ofertaAdjudicada);
            Object[] fila = {ofertaAdjudicada.getNombreOferente(), ofertaAdjudicada.getHoraInicio(), ofertaAdjudicada.getHoraFin(), ofertaAdjudicada.getMonto(), equipamiento};
            modelTablaAdjudicacion.addRow(fila);
        }
    }
	
	private void agregarGananciaAlLblMontoTotal() {
		lblMontoTotal.setText("Ganancia Total: " + controlador.calcularMontoTotal());
		ventana.getContentPane().add(lblMontoTotal);
	}
}
