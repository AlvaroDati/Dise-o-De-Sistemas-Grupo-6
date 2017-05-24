package proyectoInversiones;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
		texto.setText("<html><body> Ingrese una empresa y seleccione 'Leer Empresa' <br> para visualizar aqui su empresa: <body><html>");
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
		String datos;
		NuevoLeerArchivo archivo = new NuevoLeerArchivo();
		//datos = archivo.leerArchivo();
		

	
	}
		
	
}

