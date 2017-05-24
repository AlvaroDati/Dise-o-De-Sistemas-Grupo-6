package proyectoInversiones;

import java.util.ArrayList;
import java.util.List;

public class Empresa {

	private String nombre;
	private int inicioActividad;
	private List<Cuenta> Cuentas;

	public Empresa() {
		Cuentas = new ArrayList<Cuenta>();
	}

	public String getNombre() {
		return nombre;
	}

	public String setNombre(String arg) {
		return nombre = arg;
	}

	public int getInicioActividad() {
		return inicioActividad;
	}

	public void setInicioActividad(int inicoActividad) {
		inicioActividad = inicoActividad;
	}

	public List<Cuenta> getCuentas() {
		return Cuentas;
	}

	public void setCuentas(List<Cuenta> Cuentas) {
		this.Cuentas = Cuentas;
	}

	public void addCuenta(Cuenta b) {
		Cuentas.add(b);
	}
	
	public String toString(){
		return getNombre();
	}
	
	public String mostrarTodo() {
		
		String datos;
					
		datos = "<html><body>" 
		+ "Empresa: " 
		+ this.getNombre() + "<br>"
		+ "Inicio de Actividad: " 
		+ Integer.toString(this.getInicioActividad()) + "<br>" 
		+ "<<<<<<<<<Cuentas>>>>>>>>>" + "<br>" 
		//+ this.getCuentas().forEach(cuenta -> cuenta.mostrarTodo()) + "<br>" 
		+ "---------------------------------------" + "";
	
		return datos;

	}
	public static void main(String args[]){
		Empresa empresa = new Empresa();
		System.out.println(empresa.mostrarTodo());
	}

}


/*	public void actionPerformed(ActionEvent evento) { 

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
}*/