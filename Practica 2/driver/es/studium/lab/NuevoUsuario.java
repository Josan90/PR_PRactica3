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


public class NuevoUsuario implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Nuevo Empleado");
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblTitulo = new Label("---Alta de Empleado---");
	Label lblNombre = new Label("Nombre");
	Label lblApe1 = new Label("Primer apellido");
	Label lblApe2 = new Label("Segundo apellido");
	Label lblCorreo = new Label("Correo");
	Label lblTel = new Label("Telefono");
	Label lblCargo = new Label("Cargo");
	Label lblMensaje = new Label("Alta de Empleado Correcta");
	
	String [] cargos = {"Administrativo/a", "Conserje", "Investigador/a", "Limpiador/a",
			"Mozo/a de almacen", "Profesor/a", "Tecnico/a"};
	Choice choCargo = new Choice();

	TextField txtNombre = new TextField(10);
	TextField txtApe1 = new TextField(10);
	TextField txtApe2 = new TextField(10);
	TextField txtCorreo = new TextField(10);
	TextField txtTel = new TextField(10);

	Button btnAceptar = new Button("Aceptar");
	Button btnCancelar = new Button("Cancelar");

	Conexion conexion = new Conexion();
	
	NuevoUsuario()
	{	
		ventana.setLayout(new FlowLayout());
		ventana.setSize(167,500);
		ventana.addWindowListener(this);
		dlgMensaje.addWindowListener(this);

		ventana.add(lblTitulo);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblApe1);
		ventana.add(txtApe1);
		ventana.add(lblApe2);
		ventana.add(txtApe2);
		ventana.add(lblTel);
		ventana.add(txtTel);
		ventana.add(lblCorreo);
		ventana.add(txtCorreo);
		ventana.add(lblCargo);
		
		for(String cargo: cargos)
		{
			choCargo.add(cargo);
		}
		ventana.add(choCargo);

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
		if(e.getSource().equals(btnAceptar))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(280,100);
			if(txtNombre.getText().length()==0||txtApe1.getText().length()==0||txtApe2.getText().length()==0||
					txtTel.getText().length()==0||txtCorreo.getText().length()==0)
			{
				lblMensaje.setText("Rellene todos los campos correctamente");
			}
			else
			{
				if(txtTel.getText().length()!=9)
				{
					lblMensaje.setText("Escriba un telefono valido");
				}
				else
				{
					String sentencia = "INSERT INTO empleados VALUES (null, '"+txtNombre.getText()+"', '"+txtApe1.getText()+"', '"
							+txtApe2.getText()+"', '"+txtTel.getText()+"', '"+txtCorreo.getText()+"', '"+choCargo.getSelectedItem()+"')";
							int respuesta = conexion.altaUsuario(sentencia);
							lblMensaje.setText("Alta correcta");
							if(respuesta!=0)
							{
								lblMensaje.setText("Error en Alta");
							}
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if(e.getSource().equals(btnCancelar))
		{
			txtNombre.setText("");
			txtApe1.setText("");
			txtApe2.setText("");
			txtTel.setText("");
			txtCorreo.setText("");
			txtNombre.requestFocus();
			ventana.dispose(); //cerrar la ventana
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			ventana.setVisible(false);
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}

