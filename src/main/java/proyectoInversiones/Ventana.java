package proyectoInversiones;

import javax.swing.*;
import java.awt.*;
/*import java.awt.Color;
import java.awt.Dimension;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame implements ActionListener {

	JPanel panelPrincipal;
	JPanel panelInterfaz;
	JPanel panelEmpresas;
	JPanel panelIndicadoresYMetodologias;
	JPanel panelIndPredefinidos;
	JPanel panelIndUsuario;
	JPanel panelCuentas;
	JList listaEmpresas;	
	DefaultListModel modeloEmpresas;
	JList listaCuentas;
	JScrollPane scrollListaCuentas;
	DefaultListModel modeloCuentas;
	JList listaIndPredefinidos;
	DefaultListModel modeloIndPredefinidos;
	JScrollPane scrollListaIndPredefinidos;
	JList listaIndUsuario;
	DefaultListModel modeloIndUsuario;
	JScrollPane scrollListaIndUsuario;
	JLabel descripcionCuentas;
	JButton botonVerInformacion;
	JButton botonAgregarIndicador;
	JButton botonBorrarIndicador;
	JTextField textoIndicador;
	JTextField textoNombreIndicador;
	
	
	//Tomamos el tamanio de la pantalla
	
	Toolkit instanciarToolKit = Toolkit.getDefaultToolkit();
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int ancho = screenSize.width;
	int alto = screenSize.height;
	
	
	//Constructor vacio
	
	public Ventana(){
	       super(); 
	      }
	
	//Constructor con titulo	
	
	public Ventana (String titulo){
		super(titulo);
	}
	
	
	//Carga los elementos en la ventana
	
	public void InicializarVentana(){
		
		///////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////LISTAS/////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////
			
		
		NuevoLeerArchivo archivoEmpresas = new NuevoLeerArchivo();
		ArrayList<Empresa> empresasDescargadas = new ArrayList<Empresa>();
		empresasDescargadas = archivoEmpresas.leerArchivo();
		listaEmpresas = new JList();
		modeloEmpresas = new DefaultListModel();
		listaEmpresas.setModel(modeloEmpresas);
		empresasDescargadas.forEach(empresa ->  modeloEmpresas.addElement(empresa));
		listaEmpresas.setPreferredSize(new Dimension(ancho/3-10,150));
		
				
		///////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////BOTONES//////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////
		
		botonVerInformacion = new JButton();
		botonVerInformacion.setText("Ver informacion de la empresa");
		this.botonVerInformacion.addActionListener(this);
		
		botonAgregarIndicador = new JButton();
		botonAgregarIndicador.setText("Agregar Indicador");
		this.botonAgregarIndicador.addActionListener(this);
		
		botonBorrarIndicador = new JButton();
		botonBorrarIndicador.setText("Borrar Indicador");
		this.botonBorrarIndicador.addActionListener(this);
		
		///////////////////////////////////////////////////////////////////////////////
		/////////////////////////////CUADROS DE TEXTO//////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////
		
		textoNombreIndicador = new JTextField("Introduzca el nombre del indicador que desea crear",50);
		textoIndicador = new JTextField("Introduzca el calculo del indicador",50);
		
		///////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////PANELES//////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////
		
		panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.setVisible(true);
		
				
		panelEmpresas = new JPanel();
		panelEmpresas.setVisible(true);
		panelEmpresas.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Lista Empresas"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		panelEmpresas.setPreferredSize(new Dimension(ancho/3,alto/4));
		
		
		panelCuentas = new JPanel();
		panelCuentas.setVisible(true);
		panelCuentas.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Cuentas:"),
                BorderFactory.createEmptyBorder(5,5,5,5)));
		panelCuentas.setPreferredSize(new Dimension(ancho/3,alto/4));


		panelIndicadoresYMetodologias = new JPanel();
		panelIndicadoresYMetodologias.setVisible(true);
		panelIndicadoresYMetodologias.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Indicadores y metodologías"),
                BorderFactory.createEmptyBorder(15,15,15,15)));
		panelIndicadoresYMetodologias.setPreferredSize(new Dimension(ancho/3,alto/4));
		
		panelIndPredefinidos = new JPanel();
		panelIndPredefinidos.setVisible(true);
		panelIndPredefinidos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Indicadores Predefinidos"),
                BorderFactory.createEmptyBorder(15,15,15,15)));
		panelIndPredefinidos.setPreferredSize(new Dimension(ancho/3,alto/4));
		
		panelIndUsuario = new JPanel();
		panelIndUsuario.setVisible(true);
		panelIndUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Indicadores del Usuario"),
                BorderFactory.createEmptyBorder(15,15,15,15)));
		panelIndUsuario.setPreferredSize(new Dimension(ancho/3,alto/4));
		
		this.add(panelPrincipal);
		panelPrincipal.add(panelEmpresas,BorderLayout.NORTH);	// el tamanio de los paneles es automatico, dependen de lo que contienen
		panelPrincipal.add(panelIndicadoresYMetodologias,BorderLayout.SOUTH); // los objetos "j" pueden instanciarse solo una vez
		panelPrincipal.add(panelCuentas, BorderLayout.WEST);
		panelPrincipal.add(panelIndPredefinidos, BorderLayout.CENTER);
		panelPrincipal.add(panelIndUsuario, BorderLayout.EAST);

		panelEmpresas.add(listaEmpresas);
		panelEmpresas.add(botonVerInformacion);
		panelIndicadoresYMetodologias.add(textoNombreIndicador);
		panelIndicadoresYMetodologias.add(textoIndicador);
		panelIndicadoresYMetodologias.add(botonAgregarIndicador);
		panelIndicadoresYMetodologias.add(botonBorrarIndicador);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

		///////////////////////////////////////////////////////////////////////////////
		/////////////////////////////ACCIONES DE LOS BOTONES///////////////////////////
		///////////////////////////////////////////////////////////////////////////////	
	
public void actionPerformed(ActionEvent evento) { 
	
	if (evento.getSource()==botonVerInformacion){
		
		panelCuentas.removeAll(); 	//Borramos lo que haya quedado en el panel cuentas
		panelIndPredefinidos.removeAll();
		panelIndUsuario.removeAll();
	
		Object empresaSeleccionada = new Empresa();
		empresaSeleccionada = listaEmpresas.getSelectedValue();
		ArrayList<Cuenta> cuentasRequeridas = new ArrayList<Cuenta>();
		cuentasRequeridas = ((Empresa) empresaSeleccionada).getCuentas();
		
		//generamos la lista de cuentas
		
		modeloCuentas = new DefaultListModel();
		listaCuentas = new JList();
		listaCuentas.setModel(modeloCuentas);
		listaCuentas.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaCuentas.setVisibleRowCount(modeloCuentas.size());
		
		//generamos la lista de indicadores predefinidos
		
		modeloIndPredefinidos = new DefaultListModel();
		listaIndPredefinidos = new JList();
		listaIndPredefinidos.setModel(modeloIndPredefinidos);
		listaIndPredefinidos.setLayoutOrientation(JList.VERTICAL_WRAP);
		//listaIndPredefinidos.setVisibleRowCount(modeloIndPredefinidos.size());

		//generamos la lista de indicadores de usuario

		modeloIndUsuario = new DefaultListModel();
		listaIndUsuario = new JList();
		listaIndUsuario.setModel(modeloIndUsuario);
		listaIndUsuario.setLayoutOrientation(JList.VERTICAL_WRAP);
		//listaIndPredefinidos.setVisibleRowCount(modeloIndUsuario.size());
		
		//Generamos el scrollbar para la lista de cuentas
		
		scrollListaCuentas = new JScrollPane(listaCuentas);
		scrollListaCuentas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollListaCuentas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollListaCuentas.setViewportView(listaCuentas);
		scrollListaCuentas.setPreferredSize(new Dimension(ancho/3-150,150));
		
		//Generamos el scrollbar para la lista de indicadores predefinidos
		
		scrollListaIndPredefinidos = new JScrollPane(listaIndPredefinidos);
		scrollListaIndPredefinidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollListaIndPredefinidos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollListaIndPredefinidos.setViewportView(listaIndPredefinidos);
		scrollListaIndPredefinidos.setPreferredSize(new Dimension(ancho/3-150,150));
		
		//Generamos el scrollbar para la lista de indicadores de usuario
		
		scrollListaIndUsuario = new JScrollPane(listaIndUsuario);
		scrollListaIndUsuario.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollListaIndUsuario.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollListaIndUsuario.setViewportView(listaIndUsuario);
		scrollListaIndUsuario.setPreferredSize(new Dimension(ancho/3-150,150));
		
		
		//Aniadimos los encabezados de las filas de la lista de cuentas
		
		modeloCuentas.addElement("Periodo:");
		modeloCuentas.addElement("Ebitda:");
		modeloCuentas.addElement("FDS:");
		modeloCuentas.addElement("fCashFlow:");
		modeloCuentas.addElement("IngNetoOpCont:");
		modeloCuentas.addElement("IngNetoOpDiscont:");
		modeloCuentas.addElement("Deuda:");
		
		
		//Rellenamos la lista con los datos de las cuentas

		for(Cuenta cuenta: cuentasRequeridas){

			modeloCuentas.addElement(cuenta.getPeriodo());
			modeloCuentas.addElement(cuenta.getEbitda());
			modeloCuentas.addElement(cuenta.getFds());
			modeloCuentas.addElement(cuenta.getfCashFlow());
			modeloCuentas.addElement(cuenta.getIngNetoOpCont());
			modeloCuentas.addElement(cuenta.getIngNetoOpDiscont());
			modeloCuentas.addElement(cuenta.getDeuda());

		}

		/*Aniadimos los encabezados de las filas de la lista de indicadores predefinidos

		modeloCuentas.addElement("Periodo:");
		modeloCuentas.addElement("Ebitda:");
		modeloCuentas.addElement("FDS:");
		modeloCuentas.addElement("fCashFlow:");
		modeloCuentas.addElement("IngNetoOpCont:");
		modeloCuentas.addElement("IngNetoOpDiscont:");
		modeloCuentas.addElement("Deuda:");*/


		/*Rellenamos la lista con los datos de los indicadores predefinidos

		for(Cuenta cuenta: cuentasRequeridas){

			modeloCuentas.addElement(cuenta.getPeriodo());
			modeloCuentas.addElement(cuenta.getEbitda());
			modeloCuentas.addElement(cuenta.getFds());
			modeloCuentas.addElement(cuenta.getfCashFlow());
			modeloCuentas.addElement(cuenta.getIngNetoOpCont());
			modeloCuentas.addElement(cuenta.getIngNetoOpDiscont());
			modeloCuentas.addElement(cuenta.getDeuda());

		}*/
		
		/*Aniadimos los encabezados de las filas de la lista de indicadores de usuario

		modeloCuentas.addElement("Periodo:");
		modeloCuentas.addElement("Ebitda:");
		modeloCuentas.addElement("FDS:");
		modeloCuentas.addElement("fCashFlow:");
		modeloCuentas.addElement("IngNetoOpCont:");
		modeloCuentas.addElement("IngNetoOpDiscont:");
		modeloCuentas.addElement("Deuda:");*/


		/*Rellenamos la lista con los datos de los indicadores de usuario

		for(Cuenta cuenta: cuentasRequeridas){

			modeloCuentas.addElement(cuenta.getPeriodo());
			modeloCuentas.addElement(cuenta.getEbitda());
			modeloCuentas.addElement(cuenta.getFds());
			modeloCuentas.addElement(cuenta.getfCashFlow());
			modeloCuentas.addElement(cuenta.getIngNetoOpCont());
			modeloCuentas.addElement(cuenta.getIngNetoOpDiscont());
			modeloCuentas.addElement(cuenta.getDeuda());

		}*/

		//aniadimos los elementos a los paneles
		
		panelCuentas.add(scrollListaCuentas);
		panelIndPredefinidos.add(scrollListaIndPredefinidos);
		panelIndUsuario.add(scrollListaIndUsuario);
		panelCuentas.revalidate();
		panelIndPredefinidos.revalidate();
		panelIndUsuario.revalidate();
		panelCuentas.repaint();
		panelIndPredefinidos.repaint();
		panelIndUsuario.repaint();
		
	}
	
	
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
	ventana.setBounds(0,0,ventana.GetAncho(),ventana.GetAlto()-100);
					
	}		
}

