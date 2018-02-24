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


import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.indicadores.IndVisitor;
import proyectoInversiones.repositorio.RepositorioGeneral;
import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.CalculoIndicadores;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
	static String usuarioActivo;
	static String rutaArchivo = "target/classes/public/IndicadoresDelUsuario";
	static List<Indicador> repoIndicadores = new ArrayList<Indicador>();
		

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ModelAndView listar(Request req, Response res) throws IOException {
		RepositorioGeneral repoGeneral = new RepositorioGeneral();
		NuevoLeerArchivo arch = new NuevoLeerArchivo();
		repoGeneral.setEmpresas(arch.leerArchivo());
		String usuario = req.cookie("usuario");
		usuarioActivo = usuario;
		return new ModelAndView(repoGeneral, "Indicadores2.html");
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	public static ModelAndView setearEmpresa(Request req, Response res) {
//		String nombreEmpresa = req.queryParams("Empresa");
//		
//		Empresa empresa = new Empresa(nombreEmpresa);
//		try {
//			
//			Map<String, List<Indicador>> model = new HashMap<>();
//			NuevoLeerArchivo arch = new NuevoLeerArchivo();
//			List<Periodo> periodosEmpresa = arch.getPeriodos(empresa);
//			CalculoIndicadores operadorIndicadores = new CalculoIndicadores(usuarioActivo);
//			List<Indicador> indicadoresDeEmpresa = operadorIndicadores.setearListaIndicadores(periodosEmpresa, empresa);
//			List<Indicador> indicadoresUsuario = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa,empresa);
//			repoIndicadores = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa, empresa);
//			Indicador indicadorDeEmpresa = indicadoresDeEmpresa.get(0);
//			List<Indicador> indicadorUnico = new ArrayList<Indicador>();
//			indicadorUnico.add(indicadorDeEmpresa);
//			
//			model.put("indicadorUnico", indicadorUnico);
//			model.put("indicadores", indicadoresDeEmpresa);
//			model.put("indicadoresU", indicadoresUsuario);
//			return new ModelAndView(model, "Indicadores2.html");
//		} catch (Exception e) {
//			res.cookie("mensajeError", e.getMessage());
//			res.redirect("/cuentas");
//		}
//
//		return null;
//
//	}
	
	
	
	public static ModelAndView setearEmpresa(Request req, Response res) {
		String nombreEmpresa = req.queryParams("Empresa");
		Empresa empresa = new Empresa(nombreEmpresa);
		try {
			
			RepositorioGeneral repoGeneral = new RepositorioGeneral();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo> periodosEmpresa = arch.getPeriodos(empresa);
			CalculoIndicadores operadorIndicadores = new CalculoIndicadores(usuarioActivo);
			repoGeneral.setIndicadores(operadorIndicadores.setearListaIndicadores(periodosEmpresa, empresa));
			repoGeneral.setIndicadoresUsuario(operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa,empresa));
			repoGeneral.setEmpresas(arch.leerArchivo());
			repoIndicadores = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa, empresa);
			repoGeneral.setEmpresaAsociada(nombreEmpresa);
			return new ModelAndView(repoGeneral, "Indicadores2.html");
		} catch (Exception e) {
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/cuentas");
		}

		return null;

	}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ModelAndView nuevoFormulario(Request req, Response res) {
		return new ModelAndView(null, "IndicadoresNuevo.html");
	}

	public static ModelAndView nuevoInd(Request req, Response res) {
		String usuario = req.cookie("usuario");
		String nombreIndicador = req.queryParams("nombreIndicador");
		String empresaSeleccionada = req.queryParams("Empresa");
		String expresionIndicador = req.queryParams("valorIndicador");
		
		
		Stream<Indicador> filtro = repoIndicadores.stream().filter(ind -> ind.getNombre().equals(nombreIndicador));
		System.out.println("cantidad de indicadores"+repoIndicadores.size());
//		System.out.println("-----------------------------");
//		System.out.println("Cantidad de indicadores de igual nombre");
		Long contador = filtro.count();
//		System.out.println(contador);
		
		
		if (contador == 0) {
			if (nombreIndicador != null  && expresionIndicador != null) {
				try {
					System.out.println("cantidad de indicadores 2 "+repoIndicadores.size());
					String archivoUsuario = rutaArchivo + usuario;
					System.out.println("archivo del usuario " + archivoUsuario);
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
