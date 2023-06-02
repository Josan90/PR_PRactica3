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

public class ModificarDep implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Editar Departamento");
	Dialog dlgEdicion = new Dialog(ventana, "Edicion", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblElegir = new Label("Elegir el departamento a editar");

	Choice choDep = new Choice();

	Button btnEditar = new Button("Editar");

	Conexion conexion = new Conexion();

	Label lblTitulo = new Label("Modificar Departamento");
	Label lblNombre = new Label("Nombre");
	Label lblDesc = new Label("Breve descripcion");
	Label lblDesc2 = new Label ("(100 caracteres maximo)");
	Label lblMensaje = new Label("Modificacion del Departamento Correcta");

	TextField txtNombre = new TextField(10);
	TextField txtDesc = new TextField(10);
	
	String [] cargos = {"Administrativo/a", "Conserje", "Investigador/a", "Limpiador/a",
			"Mozo/a de almacen", "Profesor/a", "Tecnico/a"};
	Choice choCargo = new Choice();

	Button btnModificar = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");

	String idDepartamento = "";
	
	ModificarDep()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(230,220);
		ventana.addWindowListener(this);

		ventana.add(lblElegir);
		// Rellenar el Choice
		conexion.rellenarChoiceDep(choDep);
		ventana.add(choDep);
		btnEditar.addActionListener(this);
		ventana.add(btnEditar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnEditar))
		{
			if(choDep.getSelectedIndex()!=0)
			{
				dlgEdicion.setLayout(new FlowLayout());
				dlgEdicion.setSize(170, 280);
				dlgEdicion.addWindowListener(this);

				dlgEdicion.add(lblTitulo);
				dlgEdicion.add(lblNombre);
				dlgEdicion.add(txtNombre);
				dlgEdicion.add(lblDesc);
				dlgEdicion.add(txtDesc);

				btnModificar.addActionListener(this);
				btnCancelar.addActionListener(this);

				dlgEdicion.add(btnModificar);
				dlgEdicion.add(btnCancelar);

				dlgEdicion.setResizable(false);
				dlgEdicion.setLocationRelativeTo(null);

				String tabla[] = choDep.getSelectedItem().split("-");
				String resultado = conexion.getEditarDep(tabla[0]);

				String datos[] = resultado.split("-");
				idDepartamento = datos[0];
				txtNombre.setText(datos[1]);
				txtDesc.setText(datos[2]);

				dlgEdicion.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnModificar))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(300,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtNombre.getText().length()==0||txtDesc.getText().length()==0)
			{
				lblMensaje.setText("Rellene todos los campos correctamente");
			}
			else
			{
				// Modificar
				String sentencia = "UPDATE departamentos SET nombreDepartamento='"+txtNombre.getText()+"', descripcionDepartamento = '"
				+txtDesc.getText()+"' WHERE idDepartamento="+idDepartamento+";";
				int respuesta = conexion.modificarUsuario(sentencia);
				if(respuesta!=0)
				{
					// Mostrar Mensaje Error
					lblMensaje.setText("Error en la Modificacion");
				}
			}

			dlgMensaje.add(lblMensaje);
			dlgMensaje.setResizable(false);
			dlgMensaje.setLocationRelativeTo(null);
			dlgMensaje.setVisible(true);
		}
		else if (e.getSource().equals(btnCancelar))
		{
			dlgEdicion.setVisible(false);
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
		if(dlgEdicion.isActive())
		{
			dlgEdicion.setVisible(false);
			ventana.setVisible(false);
		}
		else if (dlgMensaje.isActive())
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
