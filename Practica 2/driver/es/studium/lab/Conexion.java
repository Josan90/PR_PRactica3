package es.studium.lab;

import java.awt.Choice;
import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conexion
{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/laboratorio";
	String login = "userLab";
	String password = "Studium2023;";
	String sentencia = "";

	Connection connection = null;
	Statement statement = null;
	ResultSet rs = null;
	
	Conexion()
	{
		connection = this.conectar();
		if (connection == null)
		{
			System.out.println("Error al establecer la conexion");
		}
	}
	
	public Connection conectar()
	{
		try
		{
			Class.forName(driver);
			return(DriverManager.getConnection(url, login, password));
		}
		catch (ClassNotFoundException cnfe)
		{
			System.out.println("Error 1-"+cnfe.getMessage());
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 2-"+sqle.getMessage());
		}
		return null;
	}
	
	public int comprobarCredenciales(String u, String c)
	{
		String cadena = "SELECT * FROM usuarios WHERE nombreUsuario = '"+ u + "' AND claveUsuario = SHA2('" + c + "',256);";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(cadena);
			if(rs.next())
			{
				registrarMovimiento("["+u+"]"+"[Acceso al sistema]");
				return rs.getInt("tipoUsuario");
			}
			else
			{
				registrarMovimiento("["+u+"]"+"[Acceso fallido]");
				return -1;
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 3-"+sqle.getMessage());
		}
		return -1;
	}
	
	public int altaUsuario(String sentencia)
	{
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			registrarMovimiento("[Alta de usuario]"+"["+sentencia+"]");
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 4-"+sqle.getMessage());
			return 1;
		}
	}
	public void rellenarListadoUsuarios(TextArea txaListado)
	{
		String sentencia = "SELECT idEmpleado, nombreEmpleado, primerApellidoEmpleado, "
				+ "segundoApellidoEmpleado, telefonoEmpleado, correoElectronicoEmpleado, "
				+ "cargoEmpleado FROM empleados;";
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaListado.append(resultado.getString("idEmpleado")+" |  ");
				txaListado.append(resultado.getString("nombreEmpleado")+" |  ");
				txaListado.append(resultado.getString("primerApellidoEmpleado")+" |  ");
				txaListado.append(resultado.getString("segundoApellidoEmpleado")+" |  ");
				txaListado.append(resultado.getString("telefonoEmpleado")+" |  ");
				txaListado.append(resultado.getString("correoElectronicoEmpleado")+" |  ");
				txaListado.append(resultado.getString("cargoEmpleado")+"\n");
			}
			//registrarMovimiento("[ConsultaUsuarios]"+"["+txaListado+"]");

		}
		catch (SQLException sqle)
		{
			System.out.println("Error 5-"+sqle.getMessage());
		}
	}

	public void rellenarChoiceUsuarios(Choice choUsuarios)
	{
		String sentencia = "SELECT idEmpleado, nombreEmpleado FROM empleados ORDER BY 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choUsuarios.add("Elegir empleado...");
			while(resultado.next())
			{
				choUsuarios.add(resultado.getString("idEmpleado")+"-"+resultado.getString("nombreEmpleado"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 6-"+sqle.getMessage());
		}
	}
	public int eliminarUsuario(String idEmpleado)
	{
		String sentencia = "DELETE FROM empleados WHERE idEmpleado = " + idEmpleado;
		registrarMovimiento("[Eliminar Usuario]"+"["+sentencia+"]");
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Ejecutar la sentencia SQL
			statement.executeUpdate(sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 7-"+sqle.getMessage());
			return 1;
		}
	}
	public String getDatosEdicion(String idEmpleado)
	{
		String resultado = "";
		String sentencia = "SELECT * FROM empleados WHERE idEmpleado = " + idEmpleado;
		try
		{
			// Crear una sentencia
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Crear un objeto ResultSet para guardar lo obtenido
			// y ejecutar la sentencia SQL
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idEmpleado")+"-"+resultSet.getString("nombreEmpleado")+"-"+resultSet.getString("primerApellidoEmpleado")
			+"-"+resultSet.getString("segundoApellidoEmpleado")+"-"+resultSet.getString("telefonoEmpleado")
			+"-"+resultSet.getString("cargoEmpleado")+"-"+resultSet.getString("correoElectronicoEmpleado")+"-"+resultSet.getString("cargoEmpleado"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 8-"+sqle.getMessage());
		}
		return resultado;
	}

	public int modificarUsuario(String sentencia)
	{
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			registrarMovimiento("[Modificar Usuarios]"+"["+sentencia+"]");
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 9-"+sqle.getMessage());
			return 1;
		}
	}
	
	//DEPARTAMENTOS
	public int altaDep(String sentencia)
	{
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			registrarMovimiento("[Crear Cepartamento]"+"["+sentencia+"]");
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 10-"+sqle.getMessage());
			return 1;
		}
	}
	
	public void rellenarListaDep(TextArea txaLista)
	{
		String sentencia = "SELECT idDepartamento, nombreDepartamento, "
				+ "descripcionDepartamento FROM departamentos;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaLista.append(resultado.getString("idDepartamento")+" |  ");
				txaLista.append(resultado.getString("nombreDepartamento")+" |  ");
				txaLista.append(resultado.getString("descripcionDepartamento")+"\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 11-"+sqle.getMessage());
		}
	}
	public int eliminarDep(String idDepartamento)
	{
		String sentencia = "DELETE FROM departamentos WHERE idDepartamento = " + idDepartamento;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 12-"+sqle.getMessage());
			return 1;
		}
	}
	public String getEditarDep(String idDepartamento)
	{
		String resultado = "";
		String sentencia = "SELECT * FROM departamentos WHERE idDepartamento = " + idDepartamento;
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(sentencia);
			resultSet.next();
			resultado =(resultSet.getString("idDepartamento")+"-"+resultSet.getString("nombreDepartamento")+"-"+resultSet.getString("descripcionDepartamento"));
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 13-"+sqle.getMessage());
		}
		return resultado;
	}

	public int modificarDep(String sentencia)
	{
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.executeUpdate(sentencia);
			return 0;
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 14-"+sqle.getMessage());
			return 1;
		}
	}
	
	public void rellenarChoiceDep(Choice choDep)
	{
		String sentencia = "SELECT idDepartamento, nombreDepartamento FROM departamentos ORDER BY 1;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			choDep.add("Elegir departamento...");
			while(resultado.next())
			{
				choDep.add(resultado.getString("idDepartamento")+"-"+resultado.getString("nombreDepartamento"));
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 15-"+sqle.getMessage());
		}
	}

	// ** Nuevos metodos **
	public int altaTrabajan(String sentencia)
	{
		try 
		{
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            statement.executeUpdate(sentencia);
            return 0;
        } 
		catch (SQLException sqle) {
            System.out.println("Error 16-" + sqle.getMessage());
            return 1;
        }
	}
	
	public void rellenarListaTrabajan(TextArea txaListaTr)
	{
		String sentencia = "SELECT idTrabajan, nombreEmpleado, nombreDepartamento, cantidadPersonas FROM trabajan"
				+ " JOIN empleados ON trabajan.idEmpleadoFK1 = empleados.idEmpleado JOIN departamentos ON trabajan.idDepartamentoFK2 = departamentos.idDepartamento;";
		try
		{
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet resultado = statement.executeQuery(sentencia);
			while(resultado.next())
			{
				txaListaTr.append(resultado.getString("nombreEmpleado")+" |  ");
				txaListaTr.append(resultado.getString("nombreDepartamento")+" |  ");
				txaListaTr.append(resultado.getString("cantidadPersonas")+"\n");
			}
		}
		catch (SQLException sqle)
		{
			System.out.println("Error 17-"+sqle.getMessage());
		}
	}
	
	public void registrarMovimiento(String mensaje) 
	{
	    try 
	    {
	        FileWriter fw = new FileWriter("movimientos.log", true); // true para agregar al final del archivo
	        BufferedWriter bw = new BufferedWriter(fw);
	        PrintWriter salida = new PrintWriter(bw);
	        
	        //Formatear y mostrar fecha 
	        Date fechaActual = new Date(); //fecha actual
	        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //formateador

	        //Mensaje formateador en formato de fecha actual + el mensaje del log
	        salida.println("["+formateador.format(fechaActual)+"]"+mensaje);

	        salida.close();
	        bw.close();
	        fw.close();
	    } 
	    catch (IOException e) {
	        System.out.println("Error al escribir en el archivo de movimientos");
	    }
	}
	
}
