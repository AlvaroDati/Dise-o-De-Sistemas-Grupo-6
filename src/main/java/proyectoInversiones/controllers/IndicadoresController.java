package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import proyectoInversiones.Cuenta;
import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.indicadores.*;
//import proyectoInversiones.Empresa;
//import proyectoInversiones.repos.RepoCuentas;
/*import dominio.usuarios.RepositorioUsuarios;
import dominio.usuarios.Usuario;*/

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController implements WithGlobalEntityManager, TransactionalOps{
	
	public ModelAndView listar(Request req, Response res) throws IOException {

		Map<String, List<Indicador>> model = new HashMap<>();
		
		Empresa			 empresaInicial = new Empresa("America Movil");
		NuevoLeerArchivo arch = new NuevoLeerArchivo();
		List<Periodo>	 periodos =  arch.getPeriodos(empresaInicial);
		List<Indicador>  indicadores = setearListaIndicadores (periodos, empresaInicial);
		List<Indicador> indicadoresUsuario = setearListaIndicadoresUsuario(periodos, empresaInicial);
		model.put("indicadores", indicadores);
		model.put("indicadoresU", indicadoresUsuario);
		return new ModelAndView(model, "Indicadores2.html");
	}
	
	
	public static ModelAndView setearEmpresa(Request req, Response res) {
		
		String nombreEmpresa = req.queryParams("Empresa");
		
		Empresa empresa = new Empresa(nombreEmpresa);
		
		try{
			Map<String, List<Indicador>> model = new HashMap<>();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo>periodosEmpresa =  arch.getPeriodos(empresa);
			List<Indicador> indicadoresDeEmpresa = setearListaIndicadores(periodosEmpresa,empresa);
			List<Indicador> indicadoresUsuario = setearListaIndicadoresUsuario(periodosEmpresa, empresa);
			model.put("indicadores", indicadoresDeEmpresa);
			model.put("indicadoresU", indicadoresUsuario);
			return new ModelAndView(model, "Indicadores2.html");
			
		}catch ( Exception e ){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/cuentas");
		}
		
		return null;
		
	}

	
	public static List<Indicador> setearListaIndicadores(List<Periodo> listaPeriodos, Empresa empresa){
		
		List<Indicador>  indicadores = new ArrayList<Indicador>();
		ArmadorIndicador calculadorIndicadores = new ArmadorIndicador();
		
		for (int i = 0; i<listaPeriodos.size();i++){
			Indicador indicadorAux = new Indicador();
			indicadorAux.setPeriodo(listaPeriodos.get(i).getAnio());
			indicadorAux.setRoe(calculadorIndicadores.obtenerRoeSegunPeriodo(empresa, listaPeriodos.get(i).getAnio()));
			indicadorAux.setIngresoNeto(calculadorIndicadores.obtenerIngresoNetoSegunPeriodo(empresa, listaPeriodos.get(i).getAnio()));
			indicadores.add(indicadorAux);
		}
		return indicadores;
	}
	
	public static List<Indicador> setearListaIndicadoresUsuario(List<Periodo> listaPeriodos, Empresa empresa) throws IOException{
		List<Indicador> indicadores = new ArrayList<Indicador>();
		ArmadorIndicador calcularIndicadores = new ArmadorIndicador();
		indicadores = calcularIndicadores.getIndicadoresUsuario("output.txt",empresa);
		return indicadores;
	}
	
	public ModelAndView nuevoInd(Request req, Response res){
		
		String nombreIndicador = req.queryParams("nombreIndicador");
		String expresionIndicador = req.queryParams("valorIndicador");
		//Acá estaría bueno obtener el ID del usuario
		
		try {

			File file = new File("output.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(empresaSeleccionada.toString()+"("+nombreIndicador+")" + "=");
			bw.write(cuentaIndicador + "\n"); //numero + empresa(cuenta/indicador(periodo))
			bw.close();

			System.out.println("Datos guardados en output");
			textoIndicador.setText("");
			textoNombreIndicador.setText("");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return new ModelAndView(null, "IndicadoresNuevo.html");
	}
	
	
	
	
	
	
	
}

