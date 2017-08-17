package proyectoInversiones;

import javax.swing.*;
import java.awt.*;
/*import java.awt.Color;
import java.awt.Dimension;*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import proyectoInversiones.indicadores.IndVisitor;
import proyectoInversiones.indicadores.Indicador;

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
		NuevoLeerArchivo				archivoAux		     = new NuevoLeerArchivo();
		Object                      empresaSeleccionada      = listaEmpresas.getSelectedValue();
		
		ArrayList<Cuenta>           cuentasRequeridas        = archivoAux.obtenerCuentasSegunEmpresa((Empresa) empresaSeleccionada);//((Empresa) empresaSeleccionada).getCuentas();		 
		Indicador                   indicadorPredefinido     = new Indicador();
		IndVisitor                  indicadorVisitor         = new IndVisitor();
		Map<String,List<Indicador>> indicadorUsuario         = new HashMap<String,List<Indicador>>();
//		
//		try {
//			indicadorUsuario = indicadorVisitor.obtenerIndicadoresUsuario("output.txt");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
		
		
		modeloCuentas = new DefaultListModel();
		listaCuentas = new JList();
		listaCuentas.setModel(modeloCuentas);
		listaCuentas.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaCuentas.setVisibleRowCount(archivoAux.cantidadDeCuentas((Empresa)empresaSeleccionada) + 1);
		
		//generamos la lista de indicadores predefinidos
		
		modeloIndPredefinidos = new DefaultListModel();
		listaIndPredefinidos = new JList();
		listaIndPredefinidos.setModel(modeloIndPredefinidos);
		listaIndPredefinidos.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaIndPredefinidos.setVisibleRowCount(3); //TODO VER COMO DESHARCODEAR ESTA COSA
		/*
		 * listaIndPredefinidos.setVisibleRowCount(indicadorPredefinido.getCantidadDeIndicadoresPredefinidos());
		System.out.printf("Size de modeloIndPredefinidos %d", indicadorPredefinido.getCantidadDeIndicadoresPredefinidos());
		 */

		//generamos la lista de indicadores de usuario

		modeloIndUsuario = new DefaultListModel();
		listaIndUsuario = new JList();
		listaIndUsuario.setModel(modeloIndUsuario);
		listaIndUsuario.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaIndPredefinidos.setVisibleRowCount(3);
		
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

			modeloCuentas.addElement(archivoAux.obtenerPeriodosSegunEmpresa((Empresa)empresaSeleccionada));
			modeloCuentas.addElement(cuenta.getEbitda());
			modeloCuentas.addElement(cuenta.getFds());
			modeloCuentas.addElement(cuenta.getfCashFlow());
			modeloCuentas.addElement(cuenta.getIngNetoOpCont());
			modeloCuentas.addElement(cuenta.getIngNetoOpDiscont());
			modeloCuentas.addElement(cuenta.getDeuda());

		}
		
		modeloIndPredefinidos.addElement("Periodo:");
		modeloIndPredefinidos.addElement("Ingreso Neto:");
		modeloIndPredefinidos.addElement("ROE:");
		
//		for(int i = 0;i<cuentasRequeridas.size();i++){
//			modeloIndPredefinidos.addElement(indicadorPredefinido.periodos(((Empresa) empresaSeleccionada)).get(i));
//			modeloIndPredefinidos.addElement(indicadorPredefinido.ingresoNeto(((Empresa) empresaSeleccionada)).get(i));
//			modeloIndPredefinidos.addElement(indicadorPredefinido.roe(((Empresa) empresaSeleccionada)).get(i));
//			
//		}
//		
		 for(Entry<String, List<Indicador>> entry : indicadorUsuario.entrySet()){
			 if(entry.getKey().equals(empresaSeleccionada.toString())){
				 for(int i = 0;i<entry.getValue().size();i++){
					 modeloIndUsuario.addElement(entry.getValue().get(i).getNombre());
				 }
				 
			 }
		 }

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
		
		
		String texto = textoNombreIndicador.getText();
		String texto2 =textoIndicador.getText();
		
		try {

			File file = new File("output.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(empresaSeleccionada.toString()+"("+texto+")" + "=");
			bw.write(texto2 + "\n");
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * System.out.printf("INTRODUZCA EL NOMBRE DEL INDICADOR QUE DESEA CREAR %s\n", texto);
	System.out.printf("INTRODUZCA EL CALCULO DEL INDICADOR %s\n", texto2);
	 */
	//try( PrintStream out = new PrintStream( new File( "C:\\Users\\kimel\\Desktop\\text.txt" ) ) ) {
	/*try( PrintStream out = new PrintStream( new File( "output.txt" ) ) ) {
	    out.print( texto+"=" );
	    out.print( texto2 );
	    
	    out.write(12);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
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

