package proyectoInversiones;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;


public class Ventana extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel texto;
	JButton botonLeer;
	JTextField cuadroDeTexto;
		
	public Ventana (){
		
		panel = new JPanel();
		
		texto = new JLabel();
		texto.setText("<html><body> Ingrese una empresa y seleccione 'Leer Empresa' <br> para visualizar aquí sus cuentas: <body><html>");
		texto.setPreferredSize(new Dimension (350,100));
		texto.setBackground(Color.yellow);
		
		botonLeer = new JButton();
		botonLeer.setText("Leer empresa");
		this.botonLeer.addActionListener(this);
		
		cuadroDeTexto = new JTextField();
		cuadroDeTexto.setPreferredSize(new Dimension(250,25));
		
		this.add(panel);
		panel.add(cuadroDeTexto);
		panel.add(botonLeer);
		panel.add(texto);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
	public void actionPerformed(ActionEvent evento) { 
		
		Collection<Double> cuentas;
	    String cadenaFinal = "";
		NuevoLeerArchivo objetoLector = new NuevoLeerArchivo();
		cuentas = objetoLector.leerArchivo(cuadroDeTexto.getText());
		int i = 0;
		
		for(Double cuenta : cuentas){
			i ++;
			switch(i){
				
				case 1:
			cadenaFinal += "<html><body>" + "Cuenta EBITDA: " + Double.toString(cuenta) + "<br>";
			break;
				case 2:
			cadenaFinal += "<html><body>" + "Cuenta FDS: " + Double.toString(cuenta) + "<br>";	
			break;
				case 3:
			cadenaFinal += "<html><body>" + "Cuenta FreeCashFlow: " + Double.toString(cuenta) + "<br>";	
			break;
				case 4:
			cadenaFinal += "<html><body>" + "Cuenta IngresoNetoCont: " + Double.toString(cuenta) + "<br>";	
			break;
				case 5:
			cadenaFinal += "<html><body>" + "Cuenta IngresoNetoDisc: " + Double.toString(cuenta) + "<br>";	
			break;
				default:
				System.out.println("Hubo conflictos con las cuentas");
			} 
		}
	texto.setText(cadenaFinal);		
	}
}


