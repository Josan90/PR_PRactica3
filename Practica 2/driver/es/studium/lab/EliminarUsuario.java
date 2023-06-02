package es.studium.lab;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EliminarUsuario implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Baja Empleado");
	Dialog dlgSeguro = new Dialog(ventana, "¿Segur@?", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	
	Label lblElegir = new Label("Elegir el empleado a dar de baja");
	Label lblSeguro = new Label("¿Seguro que desea dar de baja? ");
	Label lblMensaje = new Label("Empleado eliminado");
	
	Choice choUsuarios = new Choice();
	
	Button btnEliminar = new Button("Eliminar");
	Button btnSi = new Button("Si");
	Button btnNo = new Button("No");
	
	Conexion conexion = new Conexion();
	
	EliminarUsuario()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(220,220);
		ventana.addWindowListener(this);
		dlgSeguro.addWindowListener(this);
		
		ventana.add(lblElegir);
		conexion.rellenarChoiceUsuarios(choUsuarios);
		ventana.add(choUsuarios);
		btnEliminar.addActionListener(this);
		ventana.add(btnEliminar);
		
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnEliminar))
		{
			if(choUsuarios.getSelectedIndex()!=0)
			{
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.setSize(280,100); //tamaño del mensaje seguro
				
				lblSeguro.setText("Seguro que quiere dar de baja a "+choUsuarios.getSelectedItem());
				dlgSeguro.add(lblSeguro);
				btnSi.addActionListener(this);
				dlgSeguro.add(btnSi);
				btnNo.addActionListener(this);
				dlgSeguro.add(btnNo);
				dlgSeguro.setResizable(false);
				dlgSeguro.setLocationRelativeTo(null);
				dlgSeguro.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnNo))
		{
			dlgSeguro.setVisible(false);
		}
		else if(e.getSource().equals(btnSi))
		{
			String tabla[] = choUsuarios.getSelectedItem().split("-");
			int respuesta = conexion.eliminarUsuario(tabla[0]);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(190,120); //tamaño del mensaje "eliminado"
			dlgMensaje.addWindowListener(this);
			if(respuesta==0)
			{
				lblMensaje.setText("Empleado Eliminado");
			}
			else
			{
				lblMensaje.setText("Error al eliminar");
			}
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlgSeguro.isActive())
		{
			dlgSeguro.setVisible(false);
		}
		else if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
			dlgSeguro.setVisible(false);
			ventana.setVisible(false);
		}
		else
		{
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
