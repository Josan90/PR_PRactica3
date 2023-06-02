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

public class EliminarDep implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Eliminar departamento");
	Dialog dlgSeguro = new Dialog(ventana, "¿Segur@?", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);
	
	Label lblElegir = new Label("Elija el departamento que desea eliminar");
	Label lblSeguro = new Label("¿Esta seguro de eliminarlo? ");
	Label lblMensaje = new Label("Departamento eliminado");
	
	Choice choDep = new Choice();
	
	Button btnEliminar = new Button("Eliminar");
	Button btnSi = new Button("Si");
	Button btnNo = new Button("No");
	
	Conexion conexion = new Conexion();
	
	EliminarDep()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(350,180);
		ventana.addWindowListener(this);
		
		ventana.add(lblElegir);
		conexion.rellenarChoiceDep(choDep);
		ventana.add(choDep);
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
			if(choDep.getSelectedIndex()!=0)
			{
				dlgSeguro.setLayout(new FlowLayout());
				dlgSeguro.setSize(350,100); //tamaño del mensaje seguro
				dlgSeguro.addWindowListener(this);
				
				lblSeguro.setText("Esta apunto de eliminar el departamento: "+choDep.getSelectedItem());
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
			String tabla[] = choDep.getSelectedItem().split("-");
			int respuesta = conexion.eliminarDep(tabla[0]);
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(190,120); //tamaño del mensaje "eliminado"
			dlgMensaje.addWindowListener(this);
			if(respuesta==0)
			{
				lblMensaje.setText("Departamento Eliminado");
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
