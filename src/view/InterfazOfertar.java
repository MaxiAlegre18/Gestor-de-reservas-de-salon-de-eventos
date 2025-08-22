package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Observer;
import model.Oferta;
import controller.Controlador;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class InterfazOfertar implements Observer {

	private JFrame ventana;
	private Controlador controlador;
	private JButton btnRegistrarOferta;
	private JButton btnBorrarTodo;
	private JButton btnAdjudicacion;
	private JTextField campoNombre;
	private JTextField campoHoraInicio;
	private JTextField campoHoraFin;
	private JTextField campoMonto;
	private JPanel panelEquipamiento;
	private JScrollPane scrollPanelEquipamiento;
	private JCheckBox[] checkboxesEquipamiento;
	private JTable tablaOfertas;
	private JScrollPane scrollTablaOfertas;
	private DefaultTableModel modelTablaOfertas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazOfertar window = new InterfazOfertar();
					window.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazOfertar() {
		Auxiliares.adaptarVentanaAlUsuario();
		controlador = new Controlador(this);
		initialize();
		notificar(controlador.obtenerOfertas());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		inicializarVentana();
		generarJLabels();
		generarJTextFields();
		generarJButtons();
		generarJPanelEquipamiento();
		generarTablaOfertas();
	}

	private void inicializarVentana() {
		ventana = new JFrame();
		ventana.setTitle("Gestión de Reservas");
		ventana.setSize(500, 400);
		ventana.setBounds(100, 100, 611, 413);
		ventana.setLocationRelativeTo(null);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
	}
	
	// Generar Jlabel

	private void generarJLabels() {
		generarLblNombre();
		generarLblHoraInicio();
		generarLblHoraFin();
		generarLblMonto();
		generarLblEquipamiento();
	}

	private void generarLblNombre() {
		JLabel lblNombreOferente = new JLabel("Nombre oferente: ");
		lblNombreOferente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreOferente.setBounds(10, 25, 97, 14);
		ventana.getContentPane().add(lblNombreOferente);
	}

	private void generarLblHoraInicio() {
		JLabel horaInicioText = new JLabel("Hora inicio: ");
		horaInicioText.setHorizontalAlignment(SwingConstants.RIGHT);
		horaInicioText.setBounds(44, 55, 63, 13);
		ventana.getContentPane().add(horaInicioText);
	}

	private void generarLblHoraFin() {
		JLabel horaFinText = new JLabel("Hora fin: ");
		horaFinText.setHorizontalAlignment(SwingConstants.RIGHT);
		horaFinText.setBounds(44, 85, 63, 13);
		ventana.getContentPane().add(horaFinText);
	}

	private void generarLblMonto() {
		JLabel montoText = new JLabel("Monto: $");
		montoText.setHorizontalAlignment(SwingConstants.RIGHT);
		montoText.setBounds(44, 115, 63, 13);
		ventana.getContentPane().add(montoText);
	}
	
	private void generarLblEquipamiento() {
	    JLabel lblEquipamiento = new JLabel("Equipamiento: ");
	    lblEquipamiento.setHorizontalAlignment(SwingConstants.RIGHT);
	    lblEquipamiento.setBounds(10, 145, 97, 14);
	    ventana.getContentPane().add(lblEquipamiento);
	}

	
	//Generar JTextField
	
	private void generarJTextFields() {
		generarTextNombre();
		generarTextHoraInicio();
		generarTextHoraFin();
		generarTextMonto();
	}

	private void generarTextNombre() {
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		campoNombre.setBounds(113, 22, 158, 19);
		ventana.getContentPane().add(campoNombre);
	}

	private void generarTextHoraInicio() {
		campoHoraInicio = new JTextField();
		campoHoraInicio.setBounds(113, 52, 158, 19);
		ventana.getContentPane().add(campoHoraInicio);
		campoHoraInicio.setColumns(10);
	}

	private void generarTextHoraFin() {
		campoHoraFin = new JTextField();
		campoHoraFin.setBounds(113, 82, 158, 19);
		ventana.getContentPane().add(campoHoraFin);
		campoHoraFin.setColumns(10);
	}

	private void generarTextMonto() {
		campoMonto = new JTextField();
		campoMonto.setBounds(113, 112, 158, 19);
		ventana.getContentPane().add(campoMonto);
		campoMonto.setColumns(10);
	}

	// Generar Jbutton
	
	private void generarJButtons() {
		generarBtnRegistrarOferta();
		generarBtnBorrarTodo();
		generarBtnAdjudicacion();
	}
	
	private void generarBtnRegistrarOferta() {
		btnRegistrarOferta = new JButton("Agregar oferta");
		btnRegistrarOferta.setBounds(307, 96, 119, 23);
		ventana.getContentPane().add(btnRegistrarOferta);
		agregarOferta();
	}

	private void generarBtnBorrarTodo() {
		btnBorrarTodo = new JButton("Borrar Todo");
		btnBorrarTodo.setBounds(307, 127, 119, 23);
		ventana.getContentPane().add(btnBorrarTodo);
		borrarTodo();
	}

	private void generarBtnAdjudicacion() {
		btnAdjudicacion = new JButton("Resolver Adjudicación");
		btnAdjudicacion.setBounds(307, 161, 252, 32);
		ventana.getContentPane().add(btnAdjudicacion);
		resolverAdjudicacion();
	}
	
	
	// Generar JPanelEquipamiento
	
	private void generarJPanelEquipamiento() {
	    inicializarJPanelEquipamiento();
	    generarCheckBoxes();
	    generarScrollPanelEquipamiento();
	}
	
	private void inicializarJPanelEquipamiento() {
		panelEquipamiento = new JPanel();
	    panelEquipamiento.setBounds(113, 142, 158, 60);
	    panelEquipamiento.setLayout(null);
	}

	private void generarCheckBoxes() {
	    String[] equipamiento = controlador.equipamientoDisponible();
	    int posInicial = 15;
	    
	    inicializarArregloDeCheckBoxes(equipamiento.length);
	     
		for (int i = 0; i < equipamiento.length; i++) {
			checkboxesEquipamiento[i] = new JCheckBox(equipamiento[i]);
			checkboxesEquipamiento[i].setBounds(6, posInicial * i, 130, 16);
		    panelEquipamiento.add(checkboxesEquipamiento[i]);
	    }
		
		reAjustarPanelEquipamiento(158, posInicial, equipamiento.length);
	}

	private void inicializarArregloDeCheckBoxes(int largo) {
		checkboxesEquipamiento = new JCheckBox[largo];
	}

	private void reAjustarPanelEquipamiento(int ancho, int posicionBase, int cantidadCheckBoxes) {
		panelEquipamiento.setPreferredSize(new Dimension(ancho, posicionBase * cantidadCheckBoxes));
	}
	
	private void generarScrollPanelEquipamiento() {
		scrollPanelEquipamiento = new JScrollPane(panelEquipamiento);
		scrollPanelEquipamiento.setBounds(113, 142, 158, 63);
		scrollPanelEquipamiento.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPanelEquipamiento.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ventana.getContentPane().add(scrollPanelEquipamiento);
	}

	
	// Generar JTable
	
	private void generarTablaOfertas() {
	    String[] columnas = {"Nombre", "Hora Inicio", "Hora Fin", "Monto", "Equipamiento"};
	    modelTablaOfertas = new DefaultTableModel(columnas, 0);
	    tablaOfertas = new JTable(modelTablaOfertas);
	    
	    ajustarColumnasTablaOfertas();
	    
	    scrollTablaOfertas = new JScrollPane(tablaOfertas);
	    scrollTablaOfertas.setBounds(10, 217, 575, 146);
	    ventana.getContentPane().add(scrollTablaOfertas);
	}

	private void ajustarColumnasTablaOfertas() {
		tablaOfertas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    tablaOfertas.getColumn("Nombre").setPreferredWidth(100);
	    tablaOfertas.getColumn("Hora Inicio").setPreferredWidth(65);
	    tablaOfertas.getColumn("Hora Fin").setPreferredWidth(65);
	    tablaOfertas.getColumn("Equipamiento").setPreferredWidth(450);
	}
	

	// ActionListeners

	private void agregarOferta() {
		btnRegistrarOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					procesarOferta();
				} catch (IllegalArgumentException ex) {
					Auxiliares.mostrarError(ex.getMessage(), ventana);
	}}});}

	private void borrarTodo() {
		btnBorrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.borrarTodasLasOfertas();
				Auxiliares.mostrarMensaje("Todas las ofertas han sido eliminadas", ventana);
	}});}

	private void resolverAdjudicacion() {
		btnAdjudicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!hayAlgunaOferta()) {
			        return;
			    }
				pasarALaVentanaAdjudicaciones();
	}});}
	
	private void procesarOferta() {
		verificarCamposVacios();
		String nombreOferente = obtenerNombreOferente();
        int horaInicio = obtenerHoraInicio();
        int horaFin = obtenerHoraFin();
        int monto = obtenerMonto();
        ArrayList<String> equipamiento = obtenerEquipamiento();
		controlador.registrarOferta(nombreOferente, horaInicio, horaFin, monto, equipamiento);
		Auxiliares.mostrarMensaje("Oferta agregada exitosamente", ventana);
		limpiarCamposDeTexto();
		limpiarCheckBoxesEquipamiento();
	}
	
	private void pasarALaVentanaAdjudicaciones() {
		Auxiliares.cerrarVentanaActual(ventana);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new InterfazAdjudicaciones();
				} catch (Exception e) {
					e.printStackTrace();
	}}});}
	
	// Notificar

	@Override
		public void notificar(List<Oferta> ofertasActualizadas) {
			actualizarTablaOfertas(ofertasActualizadas);
	}
	
	
	private void actualizarTablaOfertas(List<Oferta>ofertasActualizadas) {
        modelTablaOfertas.setRowCount(0);
        for (Oferta oferta : ofertasActualizadas) {
        	String equipamiento = AuxiliarOfertaFormatter.obtenerEquipamientoFormateado(oferta);
            Object[] fila = {oferta.getNombreOferente(), oferta.getHoraInicio(), oferta.getHoraFin(), oferta.getMonto(), equipamiento};
            modelTablaOfertas.addRow(fila);
        }
    }

	// Otros métodos
	 
	private void verificarCamposVacios() {
		if (campoNombre.getText().isEmpty() || campoHoraInicio.getText().isEmpty() || campoHoraFin.getText().isEmpty() || campoMonto.getText().isEmpty()) {
			Auxiliares.mostrarError("Error, al menos uno de los campos está vacío", ventana);
			return;
			}
		}
	
	private void limpiarCamposDeTexto() {
		campoNombre.setText("");
		campoHoraInicio.setText("");
		campoHoraFin.setText("");
		campoMonto.setText("");
		}
	
	private boolean hayAlgunaOferta() {
	    if (modelTablaOfertas.getRowCount() < 1) { 
	        Auxiliares.mostrarError("No hay ofertas registradas para adjudicar", ventana);
	        return false; 
	    } return true; 
	}

    
    private String obtenerNombreOferente() {
    	String nombre = campoNombre.getText();
		if(!nombre.matches("[a-zA-Z]+")) {
			Auxiliares.mostrarError("Debe ingresar texto, no números", ventana);
			throw new IllegalArgumentException("Nombre inválido"); // Lanza una excepción para detener la ejecución
		}
		return nombre;
	}

    private int obtenerHoraInicio() {
        return convertirTextoANumero(campoHoraInicio.getText(), "Error en el formato de la hora de inicio");
    }

    private int obtenerHoraFin() {
        return convertirTextoANumero(campoHoraFin.getText(), "Error en el formato de la hora de fin");
    }

    private int obtenerMonto() {
        return convertirTextoANumero(campoMonto.getText(), "Error en el formato del monto");
    }
    
    private ArrayList<String> obtenerEquipamiento() {
    	ArrayList<String> ret = new ArrayList<>();
    	
    	for (JCheckBox checkbox : checkboxesEquipamiento) {
    		if (checkbox.isSelected()) {
    			ret.add(checkbox.getText());
    		}
    	}
    	
    	return ret;
    }
    
    private void limpiarCheckBoxesEquipamiento() {
    	for (JCheckBox checkbox : checkboxesEquipamiento) {
    		checkbox.setSelected(false);
    	}
    }

	private int convertirTextoANumero(String texto, String mensajeDeError) {
		try {
			return Integer.parseInt(texto);
		} catch (NumberFormatException e) {
			throw new NumberFormatException(mensajeDeError);
		}
	}
}
