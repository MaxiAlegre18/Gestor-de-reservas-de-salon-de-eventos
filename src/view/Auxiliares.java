package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Auxiliares {
	
	static void adaptarVentanaAlUsuario() {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	
	static void cerrarVentanaActual(JFrame ventana) {
		 ventana.dispose();
	 }

	public static void mostrarMensaje(String mensaje, JFrame ventana) {
        JOptionPane.showMessageDialog(ventana, mensaje, "Mensaje: ", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void mostrarError(String mensaje, JFrame ventana) {
        JOptionPane.showMessageDialog(ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
