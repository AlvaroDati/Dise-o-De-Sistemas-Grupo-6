package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.Query;

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

	
	public  ModelAndView setearEmpresa(Request req, Response res) {
		String nombreEmpresa = req.queryParams("Empresa");
		Empresa empresa = new Empresa(nombreEmpresa);
		try {

			RepositorioServicio repo = RepositorioServicio.getInstance();
			RepositorioGeneral repoGeneral = new RepositorioGeneral();
			
			List<Periodo> periodosEmpresa = repo.obtenerTodosLosPeriodos(empresa);
			CalculoIndicadores operadorIndicadores = new CalculoIndicadores(usuarioActivo);
			repoGeneral.setIndicadores(operadorIndicadores.setearListaIndicadores(periodosEmpresa, empresa));
			repoGeneral.setIndicadoresUsuario(operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa, empresa,repo));
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
		System.out.println("Cantidad de indicadores: "+indicadoresUsuario.size());

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

		if (nombreIndicador != null && expresionIndicador != null) {
			RepositorioServicio repo = RepositorioServicio.getInstance();
			RepositorioGeneral repositorio = new RepositorioGeneral();
			repositorio.setIndicadoresUsuario(repo.buscarIndicadorPorUsuario2(usuarioActivo));
			repo.modificarExpresionIndicador(nombreIndicador + "=" + expresionIndicador,
					repo.buscarUsuarioPorNombre(usuarioActivo).getId(), nombreIndicador);

//			repo.cambiarExpresionDeIndicador(nombreIndicador + "=" + expresionIndicador,
//					repo.buscarUsuarioPorNombre(usuarioActivo).getId(), nombreIndicador);
			res.redirect("/indicadores");
		
	}else{
		res.redirect("/indicadores/modificar");
	}

		return null;

	}

}



