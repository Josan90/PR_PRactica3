package es.studium.lab;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ComprobarTrabajan implements WindowListener, ActionListener
{
	Frame ventana = new Frame("Lista de Trabajadores");

	TextArea txaListaTr = new TextArea(6, 80);
	Button btnPdf = new Button("PDF");

	Conexion conexion = new Conexion();
	
	ComprobarTrabajan()
	{
		ventana.setLayout(new FlowLayout());
		ventana.setSize(600,220);
		ventana.addWindowListener(this);
		
		// Rellenar el TextArea
		conexion.rellenarListaTrabajan(txaListaTr);
		
		ventana.add(txaListaTr);
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
			document.add(new Paragraph(txaListaTr.getText()));
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
	public void windowOpened(WindowEvent e){}
	@Override
	public void windowClosing(WindowEvent e){
		ventana.setVisible(false);
	}
	@Override
	public void windowClosed(WindowEvent e){}
	@Override
	public void windowIconified(WindowEvent e){}
	@Override
	public void windowDeiconified(WindowEvent e){}
	@Override
	public void windowActivated(WindowEvent e){}
	@Override
	public void windowDeactivated(WindowEvent e){}
}
