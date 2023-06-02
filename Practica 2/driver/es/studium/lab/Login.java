package es.studium.lab;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.KeyListener;

public class Login implements WindowListener, ActionListener, KeyListener
{
	Frame ventanaLogin = new Frame ("Inicio de Sesion");
	Dialog dlgMensaje = new Dialog (ventanaLogin, "Error", true);

	Label lblUsuario = new Label ("Usuario");
	Label lblClave = new Label ("Contraseña");
	Label lblMensaje = new Label ("Error al iniciar sesion");
	
	TextField txtUsuario = new TextField(10);
	TextField txtClave = new TextField(10);
	
	Button btnAcceder = new Button("Acceder");
	
	Conexion conexion = new Conexion();
	
	int tipoUsuario;
	
	Login()
	{
		ventanaLogin.setLayout(new FlowLayout());
		ventanaLogin.addWindowListener(this);
		ventanaLogin.setBackground(Color.cyan);
		
		ventanaLogin.add(lblUsuario);
		ventanaLogin.add(txtUsuario);
		ventanaLogin.add(lblClave);
		txtClave.setEchoChar('*');
		ventanaLogin.add(txtClave);
		btnAcceder.addActionListener(this);
		ventanaLogin.add(btnAcceder);
		
		ventanaLogin.setSize(260,150);
		ventanaLogin.setResizable(false);
		ventanaLogin.setLocationRelativeTo(null);
		
		txtUsuario.addKeyListener(this);
		txtClave.addKeyListener(this);
		ventanaLogin.setVisible(true);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnAcceder))
		{
			comprobarCredenciales();
		}
	}

	private void comprobarCredenciales()
	{
		String usuario = txtUsuario.getText();
		String clave = txtClave.getText();

		tipoUsuario = conexion.comprobarCredenciales(usuario, clave);
		if(tipoUsuario!=-1)
		{
			new MenuPrincipal(tipoUsuario);
			ventanaLogin.setVisible(false);
		}
		// Credenciales incorrectas
		else
		{
			dlgMensaje.setLayout(new FlowLayout());
			dlgMensaje.addWindowListener(this);
			
			dlgMensaje.add(lblMensaje);
			dlgMensaje.setSize(210,80);
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
		if(dlgMensaje.isActive())
		{
			dlgMensaje.setVisible(false);
		}
		else
		{
			System.exit(0);
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
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			comprobarCredenciales();
	    }
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		new Login();
	}

}
