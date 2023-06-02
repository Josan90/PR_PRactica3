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

public class ModificarUsuario implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Editar Empleado");
	Dialog dlgEdicion = new Dialog(ventana, "Edicion", true);
	Dialog dlgMensaje = new Dialog(ventana, "Mensaje", true);

	Label lblElegir = new Label("Elegir el empleado a editar");

	Choice choUsuarios = new Choice();

	Button btnEditar = new Button("Editar");

	Conexion conexion = new Conexion();

	Label lblTitulo = new Label("Modificar Empleado");
	Label lblNombre = new Label("Nombre");
	Label lblApe1 = new Label("Primer apellido");
	Label lblApe2 = new Label("Segundo apellido");
	Label lblCorreo = new Label("Correo");
	Label lblTel = new Label("Telefono");
	Label lblCargo = new Label("Cargo");
	Label lblMensaje = new Label("Modificacion de Usuario Correcta");

	TextField txtNombre = new TextField(10);
	TextField txtApe1 = new TextField(10);
	TextField txtApe2 = new TextField(10);
	TextField txtCorreo = new TextField(10);
	TextField txtTel = new TextField(10);
	
	String [] cargos = {"Administrativo/a", "Conserje", "Investigador/a", "Limpiador/a",
			"Mozo/a de almacen", "Profesor/a", "Tecnico/a"};
	Choice choCargo = new Choice();

	Button btnModificar = new Button("Modificar");
	Button btnCancelar = new Button("Cancelar");

	String idEmpleado = "";

	ModificarUsuario()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(230,220);
		ventana.addWindowListener(this);

		ventana.add(lblElegir);
		// Rellenar el Choice
		conexion.rellenarChoiceUsuarios(choUsuarios);
		ventana.add(choUsuarios);
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
			if(choUsuarios.getSelectedIndex()!=0)
			{
				dlgEdicion.setLayout(new FlowLayout());
				dlgEdicion.setSize(167,450);
				dlgEdicion.addWindowListener(this);

				dlgEdicion.add(lblTitulo);
				dlgEdicion.add(lblNombre);
				dlgEdicion.add(txtNombre);
				dlgEdicion.add(lblApe1);
				dlgEdicion.add(txtApe1);
				dlgEdicion.add(lblApe2);
				dlgEdicion.add(txtApe2);
				dlgEdicion.add(lblTel);
				dlgEdicion.add(txtTel);
				dlgEdicion.add(lblCorreo);
				dlgEdicion.add(txtCorreo);
				dlgEdicion.add(lblCargo);
				for(String cargo: cargos)
				{
					choCargo.add(cargo);
				}
				dlgEdicion.add(choCargo);

				btnModificar.addActionListener(this);
				btnCancelar.addActionListener(this);

				dlgEdicion.add(btnModificar);
				dlgEdicion.add(btnCancelar);

				dlgEdicion.setResizable(false);
				dlgEdicion.setLocationRelativeTo(null);

				String tabla[] = choUsuarios.getSelectedItem().split("-");
				String resultado = conexion.getDatosEdicion(tabla[0]);

				String datos[] = resultado.split("-");
				idEmpleado = datos[0];
				txtNombre.setText(datos[1]);
				txtApe1.setText(datos[2]);
				txtApe2.setText(datos[3]);
				txtTel.setText(datos[4]);
				choCargo.select(datos[7]);
				txtCorreo.setText(datos[6]);

				dlgEdicion.setVisible(true);
			}
		}
		else if(e.getSource().equals(btnModificar))
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.setSize(300,100);
			dlgMensaje.addWindowListener(this);
			
			if(txtNombre.getText().length()==0||txtApe1.getText().length()==0||txtApe2.getText().length()==0||
					!(txtTel.getText().length()==9)||txtCorreo.getText().length()==0)
			{
				lblMensaje.setText("Rellene todos los campos correctamente");
			}
			else
			{
				// Modificar
				String sentencia = "UPDATE empleados SET nombreEmpleado='"+txtNombre.getText()+"', primerApellidoEmpleado = '"+txtApe1.getText()
				+"', segundoApellidoEmpleado = '"+txtApe2.getText()+
						"', telefonoEmpleado = '"+txtTel.getText()+"', correoElectronicoEmpleado = '"+txtCorreo.getText()+"', cargoEmpleado = '"
				+choCargo.getSelectedItem()+"' WHERE idEmpleado="+idEmpleado+";";
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
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
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
