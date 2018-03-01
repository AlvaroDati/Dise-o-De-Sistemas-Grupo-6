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
import proyectoInversiones.Periodo;
import proyectoInversiones.DescargaDrive;
import proyectoInversiones.indicadores.IndVisitor;
import proyectoInversiones.repositorio.RepositorioGeneral;
import proyectoInversiones.repositorio.RepositorioServicio;
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
	static RepositorioGeneral repoGeneral;
    static DescargaDrive lectorDrive = new DescargaDrive();
	private static RepositorioServicio repositorioServicio;
	static List<Empresa> empresasEnLaDB = new ArrayList<Empresa>();
	static List<Periodo> periodos = new ArrayList<Periodo>();

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ModelAndView listar(Request req, Response res) throws IOException {
		RepositorioGeneral repoGeneral = new RepositorioGeneral();
		RepositorioServicio repo= RepositorioServicio.getInstance();
		empresasEnLaDB = repo.obtenerTodasLasEmpresas();
		usuarioActivo = req.cookie("usuario");
		repoGeneral.setEmpresas(empresasEnLaDB);
		return new ModelAndView(repoGeneral, "Indicadores2.html");
	}

	
//	public  ModelAndView setearEmpresa(Request req, Response res) {
//		String nombreEmpresa = req.queryParams("Empresa");
//		Empresa empresa = new Empresa(nombreEmpresa);
//		try {
//			Map<String, List<Indicador>> model = new HashMap<>();
//			
//			List<Periodo>periodosEmpresa =  lectorDrive.getPeriodos(empresa);
//			CalculoIndicadores operadorIndicadores = new CalculoIndicadores(usuarioActivo);
//			List<Indicador> indicadoresDeEmpresa = operadorIndicadores.setearListaIndicadores(periodosEmpresa, empresa);
//			System.out.println(indicadoresDeEmpresa);
//			List<Indicador> indicadoresUsuario = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa,empresa);
//			System.out.println(indicadoresUsuario);
//			repoIndicadores = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa, empresa);
//			return new ModelAndView(model, "Indicadores2.html");
//
//		} catch (Exception e) {
//			res.cookie("mensajeError", e.getMessage());
//			res.redirect("/cuentas");
//		}
//
//		return null;
//
//	}
	
	public  ModelAndView setearEmpresa(Request req, Response res) {
		String nombreEmpresa = req.queryParams("Empresa");
		Empresa empresa = new Empresa(nombreEmpresa);
		try {

			RepositorioGeneral repoGeneral = new RepositorioGeneral();
			RepositorioServicio repo = RepositorioServicio.getInstance();
			
			List<Periodo> periodosEmpresa = repo.obtenerTodosLosPeriodos(empresa);
			CalculoIndicadores operadorIndicadores = new CalculoIndicadores(usuarioActivo);
			repoGeneral.setIndicadores(operadorIndicadores.setearListaIndicadores(periodosEmpresa, empresa));
			repoGeneral.setIndicadoresUsuario(operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa, empresa));
			System.out.println("Indicadores a mostrar"+ repoGeneral.getIndicadoresUsuario().get(0).getNombre());
			repoGeneral.setEmpresas(empresasEnLaDB);
			repoIndicadores = repoGeneral.getIndicadores();
			repoGeneral.setEmpresaAsociada(nombreEmpresa);
			return new ModelAndView(repoGeneral, "Indicadores2.html");
		} catch (Exception e){
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/indicadores");

		}
		return null;
	}
	
	
	
	public List<Indicador> setearListaIndicadoresUsuario(List<Periodo> listaPeriodos,Empresa empresa)throws IOException{
		
		List<Indicador> indicadores = new ArrayList<Indicador>();
		IndVisitor indVisitor = new IndVisitor();
		String usuario = usuarioActivo;
		LeerUsuarios archivoUsuarios = new LeerUsuarios();
		RepositorioServicio repositorio = RepositorioServicio.getInstance();
		List<String> expresionIndicadores = new ArrayList<String>();
		expresionIndicadores = repositorio.buscarIndicadorPorUsuario(usuario);
		Usuario usuarioCreador = archivoUsuarios.obtenerUsuario(usuario);
		Indicador indicador = new Indicador();
		for (int j = 0; j < listaPeriodos.size(); j++) {
			int periodo = listaPeriodos.get(j).getAnio();
			for (int i = 0; i < expresionIndicadores.size(); i++) {
				indicador.setPeriodo(periodo);
				System.out.println("Periodo de indicador: "+indicador.getPeriodo());
					indicador = indVisitor.obtenerResultadoIndicadorSegunEmpresa(expresionIndicadores.get(i), empresa,
							periodo);
				indicadores.add(indicador);

			}
		}
		return indicadores;
	}
	
	
	
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static ModelAndView nuevoFormulario(Request req, Response res) {
		return new ModelAndView(null, "IndicadoresNuevo.html");
	}

	public static ModelAndView nuevoInd(Request req, Response res) {
		String usuario = req.cookie("usuario");
		String nombreIndicador = req.queryParams("nombreIndicador");
		String expresionIndicador = req.queryParams("valorIndicador");
		
		RepositorioServicio repo = repositorioServicio.getInstance();
		List<Indicador> indicadoresUsuario = repo.buscarIndicadorPorUsuario2(usuario);		
		Stream<Indicador> filtro = indicadoresUsuario.stream().filter(ind -> ind.getNombre().equals(nombreIndicador));
		System.out.println("cantidad de indicadores"+indicadoresUsuario.size());

		Long contador = filtro.count();

		
		if (contador == 0) {
			if (nombreIndicador != null  && expresionIndicador != null) {
				System.out.println("cantidad de indicadores 2 "+repoIndicadores.size());

				Indicador indicador = new Indicador();
				Usuario u = repo.buscarUsuarioPorNombre(usuario);
				System.out.println("u: "+u.getUserTag()+"pw:"+u.getUserTag());
				indicador.setUsuario(u);
				indicador.setNombre(nombreIndicador);
				indicador.setExpresion(nombreIndicador+"="+expresionIndicador+"\n");
				
				repo.getEmanager().getTransaction().begin();
				 repo.getEmanager().persist(indicador);
				 repo.getEmanager().getTransaction().commit();
				
				res.redirect("/indicadores");
			} else {
				// Tirar excepcion en spark
			}
		}else{
			return new ModelAndView(null,"IndicadoresError.html");
		}
		return null;
	}
	
	public static ModelAndView modificarInicio(Request req, Response res){
		RepositorioServicio repo = RepositorioServicio.getInstance();
		RepositorioGeneral repositorio= new RepositorioGeneral();
		repositorio.setIndicadoresUsuario(repo.buscarIndicadorPorUsuario2(usuarioActivo));
		
		return new ModelAndView(repositorio,"IndicadoresModificar.html");
	}
	
	
public static ModelAndView modificarIndicador(Request req, Response res){
	String nombreIndicador = req.queryParams("nombreIndicador");
	String expresionIndicador = req.queryParams("valorIndicador");
	Stream<Indicador> filtro = repoIndicadores.stream().filter(ind -> ind.getNombre().equals(nombreIndicador));
	System.out.println("Cantidad de indicadores: "+repoIndicadores.size());


	
	
		if (nombreIndicador != null  && expresionIndicador != null) {
			RepositorioServicio repo = RepositorioServicio.getInstance();
			RepositorioGeneral repositorio= new RepositorioGeneral();
			repositorio.setIndicadoresUsuario(repo.buscarIndicadorPorUsuario2(usuarioActivo));
			repo.cambiarExpresionDeIndicador(nombreIndicador+"="+expresionIndicador,repo.buscarUsuarioPorNombre(usuarioActivo).getId());
			res.redirect("/indicadores");
		
	}else{
		res.redirect("/indicadores/modificar");
	}

		return null;

	}

}



