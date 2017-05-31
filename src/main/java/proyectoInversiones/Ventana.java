package proyectoInversiones;

import javax.swing.*;
import java.awt.*;
/*import java.awt.Color;
import java.awt.Dimension;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;


public class Ventana extends JFrame implements ActionListener {

//	private static final long serialVersionUID = 1L;
	JPanel panelPrincipal;
	JPanel panelInterfaz;
	JPanel panelEmpresas;
	JPanel panelIndicadores;
	JLabel texto;
	JButton botonLeer;
	JButton botonIndicadores;
	JButton botonVerEmpresas;
	JTextField cuadroDeTexto;
	
	Toolkit instanciarToolKit = Toolkit.getDefaultToolkit();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int ancho = screenSize.width;
	int alto = screenSize.height;
	
	
	//Constructor vacio
	Ventana(){
	       super(); 
	      }
	
	//Constructor con titulo		
	public Ventana (String titulo){
		super(titulo);
	}
	
	
	//Carga los elementos en la ventana
	public void InicializarVentana(){
		
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setVisible(true);
		
		
		panelInterfaz = new JPanel();
		panelInterfaz.setVisible(true);
		panelInterfaz.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Menu principal"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		
		panelIndicadores = new JPanel();
		panelIndicadores.setVisible(true);
		panelIndicadores.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Lista Indicadores"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		panelIndicadores.setSize(500, 500);
		
		
		
		panelEmpresas = new JPanel();
		panelEmpresas.setVisible(true);
		panelEmpresas.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Lista Empresas"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		panelEmpresas.setSize(500, 500);

		
		
		texto = new JLabel();
		//texto.setText("<html><body> Ingrese una empresa y seleccione 'Leer Empresa' <br> para visualizar aqui su empresa: <body><html>");
		texto.setText("Ya veremos que mostrar aca");
		texto.setPreferredSize(new Dimension (350,100));
	
		botonLeer = new JButton();
		botonLeer.setText("Ver Cuentas");
		this.botonLeer.addActionListener(this);
		
		botonIndicadores = new JButton();
		botonIndicadores.setText("Agregar Indicador");
		this.botonIndicadores.addActionListener(this);
		
		botonVerEmpresas = new JButton();
		botonVerEmpresas.setText("Borrar Indicador");
		this.botonVerEmpresas.addActionListener(this);

		
		cuadroDeTexto = new JTextField();
		cuadroDeTexto.setPreferredSize(new Dimension(250,25));
		
				
		this.add(panelPrincipal);
		panelPrincipal.add(panelInterfaz,BorderLayout.NORTH);
		panelInterfaz.add(cuadroDeTexto);
		panelInterfaz.add(botonLeer);
		panelInterfaz.add(botonIndicadores);
		panelPrincipal.add(panelEmpresas,BorderLayout.WEST);	// el tamanio de los paneles es automatico, dependen de lo que contienen
		panelPrincipal.add(panelIndicadores,BorderLayout.EAST); // los objetos pueden instanciarse solo una vez
		panelEmpresas.add(botonVerEmpresas);
		panelIndicadores.add(texto);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
		
public void actionPerformed(ActionEvent evento) { 
	NuevoLeerArchivo archivo = new NuevoLeerArchivo();
	archivo.leerArchivo();
		
}

public int GetAncho(){
	return ancho;
}

public int GetAlto(){
	return alto;
}
	
	
public static void main(String[] args){
		
	Ventana ventana = new Ventana("Donde Invierto?");
	
	ventana.InicializarVentana();
	ventana.pack();
	ventana.setVisible(true);
	ventana.setResizable(true);	
	ventana.setBounds(0,0,ventana.GetAncho(),ventana.GetAlto());
					
	}		
}

