package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	public ModelAndView listar(Request req, Response res) {

		Map<String, List<Indicador>> model = new HashMap<>();
		
		Empresa			 empresaInicial = new Empresa("America Movil");
		NuevoLeerArchivo arch = new NuevoLeerArchivo();
		List<Periodo>	 periodos =  arch.getPeriodos(empresaInicial);
		List<Indicador>  indicadores = setearListaIndicadores (periodos, empresaInicial);
		model.put("indicadores", indicadores);
		return new ModelAndView(model, "Indicadores2.html");
	}
	
	
	public static ModelAndView setearEmpresa(Request req, Response res) {
		
		String nombreEmpresa = req.queryParams("Empresa");
		System.out.println(nombreEmpresa);
		
		Empresa empresa = new Empresa(nombreEmpresa);
		
		try{
			Map<String, List<Indicador>> model = new HashMap<>();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo>periodosEmpresa =  arch.getPeriodos(empresa);
			List<Indicador> indicadoresDeEmpresa = setearListaIndicadores(periodosEmpresa,empresa);
			model.put("indicadores", indicadoresDeEmpresa);
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
	
	
	
}

