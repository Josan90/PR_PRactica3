package es.studium.lab;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NuevoTrabajan implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Agregar empleados");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblTitulo = new Label("---Número de Empleados---");
	Label lblNombre = new Label("Empleado");
	Label lblDep = new Label("Departamento");
	Label lblMensaje = new Label("Número añadido");

	Choice choUsuarios = new Choice();
	Choice choDep = new Choice();
	
	Label lblNumero = new Label("Insertar número:");
    TextField txtNumero = new TextField(15);
	
	Button btnAceptar = new Button("Añadir");
	Button btnCancelar = new Button("Cancelar");

	Conexion conexion = new Conexion();

	NuevoTrabajan()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(190, 280);
		ventana.addWindowListener(this);
		dlgMensaje.addWindowListener(this);

		ventana.add(lblTitulo);
		ventana.add(lblNombre);
		conexion.rellenarChoiceUsuarios(choUsuarios);
		ventana.add(choUsuarios);
		ventana.add(lblDep);
		conexion.rellenarChoiceDep(choDep);
		ventana.add(choDep);
		ventana.add(lblNumero);
        ventana.add(txtNumero);

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
			
			String empleado = choUsuarios.getSelectedItem().split("-")[0];
			String departamento = choDep.getSelectedItem().split("-")[0];
			
			if(choUsuarios.getSelectedIndex()== -1 || choUsuarios.getSelectedItem().equals("Elegir empleado...") 
					|| choDep.getSelectedIndex()== -1 || choDep.getSelectedItem().equals("Elegir departamento...") || txtNumero.getText().length() == 0)
			{
				lblMensaje.setText("Seleccione los campos correctamente");
			}
			else
			{
				String sentencia = "INSERT INTO trabajan VALUES (null, '"+txtNumero.getText()+
						"', '"+empleado+"', '"+departamento+"')";
				int respuesta = conexion.altaTrabajan(sentencia);
				lblMensaje.setText("Alta correcta");
				if(respuesta!=0)
				{
					lblMensaje.setText("Error al Añadir");
				}
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if (e.getSource().equals(btnCancelar))
		{
			txtNumero.setText("");
			ventana.dispose();
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		if (dlgMensaje.isActive()) {
			dlgMensaje.setVisible(false);
		} else {
			ventana.setVisible(false);
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}
