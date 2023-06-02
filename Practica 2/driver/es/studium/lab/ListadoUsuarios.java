package es.studium.lab;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import java.io.IOException;
import java.awt.Desktop;
import java.io.File;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ListadoUsuarios implements WindowListener, ActionListener
{
		Frame ventana = new Frame("Listado Empleados");
		
		Label lblId = new Label("Id");
		Label lblNombre = new Label("Nombre");
		Label lblApe1 = new Label("Primer apellido");
		Label lblApe2 = new Label("Segundo apellido");
		Label lblCorreo = new Label("Correo");
		Label lblTel = new Label("Telefono");
		Label lblCargo = new Label("Cargo");

		TextArea txaListado = new TextArea(6, 80);
		Button btnPdf = new Button("PDF");

		Conexion conexion = new Conexion();

		ListadoUsuarios()
		{
			ventana.setLayout(new FlowLayout());
			ventana.setSize(600,220);
			ventana.addWindowListener(this);
			
			ventana.add(lblId);
			ventana.add(lblNombre);
			ventana.add(lblApe1);
			ventana.add(lblApe2);
			ventana.add(lblTel);
			ventana.add(lblCorreo);
			ventana.add(lblCargo);
			
			// Rellenar el TextArea
			conexion.rellenarListadoUsuarios(txaListado);
			
			ventana.add(txaListado);
			btnPdf.addActionListener(this);
			ventana.add(btnPdf);

			ventana.setResizable(false);
			ventana.setLocationRelativeTo(null);
			ventana.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(btnPdf))
			{
				exportarPDF();
			}
			
		}
		
		private void exportarPDF() 
		{
			try
			{
				String dest = "empleados.pdf";
				
				// Initialize PDF writer
				PdfWriter writer = new PdfWriter(dest);
				// Initialize PDF document
				PdfDocument pdf = new PdfDocument(writer);
				// Initialize document
				Document document = new Document(pdf);
				// Add content to the document
				document.add(new Paragraph(txaListado.getText()));
				// Close document
				document.close();
				
				// Open the new PDF document
				Desktop.getDesktop().open(new File(dest));
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent e)
		{
			ventana.setVisible(false);
			
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
