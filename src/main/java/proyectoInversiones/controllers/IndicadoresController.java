package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.indicadores.IndVisitor;
import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.indicadores.CalculoIndicadores;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController implements WithGlobalEntityManager, TransactionalOps {
	static String usuarioActivo;
	static String rutaArchivo = "IndicadoresDelUsuario";
	static List<Indicador> repoIndicadores = new ArrayList<Indicador>();
		

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ModelAndView listar(Request req, Response res) throws IOException {

		String usuario = req.cookie("userTag");
		usuarioActivo = usuario;
		Map<String, List<Indicador>> model = new HashMap<>();
		return new ModelAndView(model, "Indicadores2.html");
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ModelAndView setearEmpresa(Request req, Response res) {
		String nombreEmpresa = req.queryParams("Empresa");
		Empresa empresa = new Empresa(nombreEmpresa);
		try {
			Map<String, List<Indicador>> model = new HashMap<>();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo> periodosEmpresa = arch.getPeriodos(empresa);
			CalculoIndicadores operadorIndicadores = new CalculoIndicadores(usuarioActivo);
			List<Indicador> indicadoresDeEmpresa = operadorIndicadores.setearListaIndicadores(periodosEmpresa, empresa);
			List<Indicador> indicadoresUsuario = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa,empresa);
			Indicador indicadorDeEmpresa = indicadoresDeEmpresa.get(0);
			List<Indicador> indicadorUnico = new ArrayList<Indicador>();
			indicadorUnico.add(indicadorDeEmpresa);
			
			model.put("indicadorUnico", indicadorUnico);
			model.put("indicadores", indicadoresDeEmpresa);
			model.put("indicadoresU", indicadoresUsuario);
			return new ModelAndView(model, "Indicadores2.html");
		} catch (Exception e) {
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/cuentas");
		}

		return null;

	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static List<Indicador> setearListaIndicadores(List<Periodo> listaPeriodos, Empresa empresa) {

		List<Indicador> indicadores = new ArrayList<Indicador>();
		ArmadorIndicador calculadorIndicadores = new ArmadorIndicador();

		for (int i = 0; i < listaPeriodos.size(); i++) {
			Indicador indicadorAux = new Indicador();
			indicadorAux.setEmpresa(empresa);
			indicadorAux.setPeriodo(listaPeriodos.get(i).getAnio());
			indicadorAux.setRoe(calculadorIndicadores.obtenerRoeSegunPeriodo(empresa, listaPeriodos.get(i).getAnio()));
			indicadorAux.setIngresoNeto(calculadorIndicadores.obtenerIngresoNetoSegunPeriodo(empresa, listaPeriodos.get(i).getAnio()));
			indicadores.add(indicadorAux);
		}
		return indicadores;
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static List<Indicador> setearListaIndicadoresUsuario(List<Periodo> listaPeriodos,Empresa empresa)
			throws IOException {
		List<Indicador> indicadores = new ArrayList<Indicador>();
		//ArmadorIndicador calcularIndicadores = new ArmadorIndicador();
		IndVisitor indVisitor = new IndVisitor();
		String usuario = usuarioActivo;
		LeerUsuarios archivoUsuarios = new LeerUsuarios();
		Usuario usuarioCreador = archivoUsuarios.obtenerUsuario(usuario);
		String archivoUsuario = rutaArchivo.concat(usuario); //IndicadoresUsuarioUserTag;
		File file = new File(archivoUsuario);
		if (!file.exists()) {
			file.createNewFile();
		}
		
		//Esta lista hay que instanciarla antes! así como está ahora, si te mandas a hacer un nuevo ind sin setear una empresa, esto 
		//queda vacio!!!
		repoIndicadores.addAll(indVisitor.obtenerResultadosIndicadoresUsuarioSegunEmpresa(archivoUsuario, empresa, listaPeriodos.get(1).getAnio()));
		
		
//		System.out.println("\n-----------------------------");
//		System.out.println("Instanciacion repoIndicadores");
//		for (Indicador ind:repoIndicadores){
//			System.out.println(ind.getNombre());
//		}
		
		for(int i = 0;i<listaPeriodos.size();i++){
//			System.out.printf("En el periodo n°: %d\n", i);
			indicadores.addAll(indVisitor.obtenerResultadosIndicadoresUsuarioSegunEmpresa(archivoUsuario, empresa, listaPeriodos.get(i).getAnio()));
			
		}
			for(int j = 0;j<indicadores.size();j++){
					indicadores.get(j).setUsuario(usuarioCreador);
		}
					
		return indicadores;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ModelAndView nuevoFormulario(Request req, Response res) {
		return new ModelAndView(null, "IndicadoresNuevo.html");
	}

	public static ModelAndView nuevoInd(Request req, Response res) {
		String usuario = req.cookie("userTag");
		String nombreIndicador = req.queryParams("nombreIndicador");
		String empresaSeleccionada = req.queryParams("Empresa");
		String expresionIndicador = req.queryParams("valorIndicador");
		
//		System.out.println("-----------------------------");
//		System.out.println("RepoIndicadores en NuevoInd");
//		for (Indicador ind:repoIndicadores){
//			System.out.println(ind.getNombre());
//		}
		
//		System.out.println("-----------------------------");
//		System.out.printf("Nombre del indicador ingresado: %s\n", nombreIndicador);
		
		Stream<Indicador> filtro = repoIndicadores.stream().filter(ind -> ind.getNombre().equals(nombreIndicador));
		
//		System.out.println("-----------------------------");
//		System.out.println("Cantidad de indicadores de igual nombre");
		Long contador = filtro.count();
//		System.out.println(contador);
		
		
		if (contador == 0) {
			if (nombreIndicador != null && empresaSeleccionada != null && expresionIndicador != null) {
				try {

					String archivoUsuario = rutaArchivo.concat(usuario);
					File file = new File(archivoUsuario);

					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file, true);
					BufferedWriter bw = new BufferedWriter(fw);
					//bw.write(empresaSeleccionada.toString() + "(" + nombreIndicador + ")" + "=");
					bw.write(nombreIndicador  + "=");
					bw.write(expresionIndicador + "\n"); // numero +
					bw.close();
					res.redirect("/indicadores");

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// Tirar excepcion en spark
			}
		}else{
			return new ModelAndView(null,"IndicadoresError.html");
		}
		return null;
	}
}
