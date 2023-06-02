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

public class ComprobarDep implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Lista de Departamentos");
	
	Label lblId = new Label("Id");
	Label lblNombre = new Label("Nombre");
	Label lblDep = new Label("Descripcion");

	TextArea txaListado = new TextArea(6, 80);
	Button btnPdf = new Button("PDF");

	Conexion conexion = new Conexion();
	
	ComprobarDep()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(600,220);
		ventana.addWindowListener(this);
		
		ventana.add(lblId);
		ventana.add(lblNombre);
		ventana.add(lblDep);
		
		// Rellenar el TextArea
		conexion.rellenarListaDep(txaListado);
		
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
			String dest = "departamentos.pdf";
			
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
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) 
	{
		ventana.setVisible(false);
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
