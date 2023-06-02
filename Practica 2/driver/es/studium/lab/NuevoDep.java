package es.studium.lab;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoDep implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Nuevo Departamento");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblTitulo = new Label("---Nuevo Departamento---");
	Label lblNombre = new Label("Nombre");
	Label lblDesc = new Label("Breve descripcion");
	Label lblDesc2 = new Label ("(100 caracteres maximo)");
	Label lblMensaje = new Label("Departamento añadido correctamente");

	TextField txtNombre = new TextField(10);
	TextField txtDesc = new TextField(12);

	Button btnAceptar = new Button("Crear");
	Button btnCancelar = new Button("Cancelar");

	Conexion conexion = new Conexion();

	NuevoDep()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(170, 280);
		ventana.addWindowListener(this);
		dlgMensaje.addWindowListener(this);

		ventana.add(lblTitulo);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblDesc);
		ventana.add(txtDesc);
		ventana.add(lblDesc2);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		ventana.add(btnAceptar);
		ventana.add(btnCancelar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource().equals(btnAceptar))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(280, 100);
			if (txtNombre.getText().length() == 0 || txtDesc.getText().length() == 0 || txtDesc.getText().length() == 100) 
			{
				lblMensaje.setText("Rellene todos los campos correctamente");
			}
			else
			{
				String sentencia = "INSERT INTO departamentos VALUES " + "(null, '" + txtNombre.getText() + "', '"
						+ txtDesc.getText() + "')";
				int respuesta = conexion.altaDep(sentencia);
				if (respuesta != 0) {
					lblMensaje.setText("Error al añadir");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if (e.getSource().equals(btnCancelar))

		{
			txtNombre.setText("");
			txtDesc.setText("");
			txtNombre.requestFocus();
			ventana.dispose(); // cerrar la ventana
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (dlgMensaje.isActive()) {
			dlgMensaje.setVisible(false);
		} else {
			ventana.setVisible(false);
		}

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}
}
