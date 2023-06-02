package es.studium.lab;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class MenuPrincipal implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Menu Principal");

	MenuBar menuBar = new MenuBar();

	//Apartados
	Menu mnuUsuarios = new Menu("Empleados");
	Menu mnuDeps = new Menu("Departamentos");
	//UPDATE nuevos menus
	Menu mnuTrabajo = new Menu ("Trabajadores");
	Menu mnuAyuda = new Menu ("Ayuda");

	//Apartado Empleados
	MenuItem mniNuevoUsuario = new MenuItem("Alta");
	MenuItem mniBajaUsuario = new MenuItem("Baja");
	MenuItem mniModificarUsuario = new MenuItem("Modificar");
	MenuItem mniListadoUsuarios = new MenuItem("Consultar");
	
	//Apartado Departamento
	MenuItem mniNuevoDep = new MenuItem("Agregar");
	MenuItem mniEliminarDep = new MenuItem("Eliminar");
	MenuItem mniModificarDep = new MenuItem("Modificar");
	MenuItem mniConsultarDep = new MenuItem("Consultar");

	//*** Apartado Trabajadores ***
	MenuItem mniNuevoTrabajo = new MenuItem("Agregar");
	MenuItem mniConsultarTrabajo = new MenuItem("Consultar");
	
	MenuItem mniAyuda = new MenuItem("Mostrar");
		
	int tipoUsuario;
	
	MenuPrincipal(int t)
	{
		tipoUsuario = t;
		ventana.setLayout(new FlowLayout());
		ventana.setSize(360,300);
		ventana.addWindowListener(this);

		mniNuevoUsuario.addActionListener(this);
		mniBajaUsuario.addActionListener(this);
		mniModificarUsuario.addActionListener(this);
		mniListadoUsuarios.addActionListener(this);
		//A partir de aqui, el nuevo usuario lo puede ver cualquiera
		mnuUsuarios.add(mniNuevoUsuario);
		//Pero aqui, solo si eres admin
		if(tipoUsuario==0)
		{
			mnuUsuarios.add(mniBajaUsuario);
			mnuUsuarios.add(mniModificarUsuario);
			mnuUsuarios.add(mniListadoUsuarios);
		}

		mniNuevoDep.addActionListener(this);
		mniEliminarDep.addActionListener(this);
		mniModificarDep.addActionListener(this);
		mniConsultarDep.addActionListener(this);
		//Lo mismo, pero aqui tambien puedes consultar los departamentos
		mnuDeps.add(mniNuevoDep);
		//Y aqui el admin puede ver las otras 2 opciones
		if(tipoUsuario==0)
		{
			mnuDeps.add(mniEliminarDep);
			mnuDeps.add(mniModificarDep);
		}
		mnuDeps.add(mniConsultarDep);

		//UPDATE el nuevo apartado de Trabajadores y ayuda
		mniNuevoTrabajo.addActionListener(this);
		mniConsultarTrabajo.addActionListener(this);
		if(tipoUsuario==0)
		{
		mnuTrabajo.add(mniNuevoTrabajo);
		mnuTrabajo.add(mniConsultarTrabajo);
		}
		
		mniAyuda.addActionListener(this);
		mnuAyuda.add(mniAyuda);
		
		menuBar.add(mnuUsuarios);
		menuBar.add(mnuDeps);
		menuBar.add(mnuTrabajo);
		menuBar.add(mnuAyuda);

		ventana.setMenuBar(menuBar);

		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// Nuevo Usuario
				if(e.getSource().equals(mniNuevoUsuario))
				{
					new NuevoUsuario();
				}
				// Eliminar Usuario
				else if(e.getSource().equals(mniBajaUsuario))
				{
					new EliminarUsuario();
				}
				// Modificar Usuario
				else if(e.getSource().equals(mniModificarUsuario))
				{
					new ModificarUsuario();
				}
				// Listado Usuarios
				else if(e.getSource().equals(mniListadoUsuarios))
				{
					new ListadoUsuarios();
				}
				else if(e.getSource().equals(mniNuevoDep))
				{
					new NuevoDep();
				}
				else if(e.getSource().equals(mniEliminarDep))
				{
					new EliminarDep();
				}
				else if(e.getSource().equals(mniModificarDep))
				{
					new ModificarDep();
				}
				else if(e.getSource().equals(mniConsultarDep))
				{
					new ComprobarDep();
				}
				else if(e.getSource().equals(mniNuevoTrabajo))
				{
					new NuevoTrabajan();
				}
				else if(e.getSource().equals(mniConsultarTrabajo))
				{
					new ComprobarTrabajan();
				}
				//UPDATE menu de ayuda
				else if(e.getSource().equals(mniAyuda))
				{
					try
					{
					Runtime.getRuntime().exec("hh.exe Indice.chm"); //mantener el hh.exe
					}
					catch (IOException e2)
					{
					e2.printStackTrace();
					}
				}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);	
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
