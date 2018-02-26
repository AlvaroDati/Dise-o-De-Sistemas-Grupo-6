package proyectoInversiones.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

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
import proyectoInversiones.repositorio.RepositorioServicio;
import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.CalculoIndicadores;
import proyectoInversiones.DescargaDrive;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController {
	static String usuarioActivo;
	static String rutaArchivo = "target/classes/public/IndicadoresDelUsuario";
	static List<Indicador> repoIndicadores = new ArrayList<Indicador>();
		
    static DescargaDrive lectorDrive = new DescargaDrive();
	private static RepositorioServicio repositorioServicio;


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public ModelAndView listar(Request req, Response res) throws IOException {
		System.out.println(req.queryParams("userTag"));
		System.out.println("web" + req.cookie("usuario"));
		Map<String, List<Empresa>> model = new HashMap<>();
		lectorDrive.obtenerEmpresas();
		ArrayList<Empresa> listaEmpresas = lectorDrive.getTodasLasEmpresas();

		model.put("empresasAMostrar", listaEmpresas);

		String usuario = req.cookie("usuario");
		usuarioActivo = usuario;
		return new ModelAndView(model, "Indicadores2.html");
	}

	
	public  ModelAndView setearEmpresa(Request req, Response res) {
		String nombreEmpresa = req.queryParams("Empresa");
		Empresa empresa = new Empresa(nombreEmpresa);
		try {
			Map<String, List<Indicador>> model = new HashMap<>();
			
			List<Periodo>periodosEmpresa =  lectorDrive.getPeriodos(empresa);
			CalculoIndicadores operadorIndicadores = new CalculoIndicadores(usuarioActivo);
			List<Indicador> indicadoresDeEmpresa = operadorIndicadores.setearListaIndicadores(periodosEmpresa, empresa);
			List<Indicador> indicadoresUsuario = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa,empresa);
			repoIndicadores = operadorIndicadores.setearListaIndicadoresUsuario(periodosEmpresa, empresa);
			return new ModelAndView(model, "Indicadores2.html");

		} catch (Exception e) {
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/cuentas");
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
//			indicador.setPeriodo(listaPeriodos.get(j).getAnio());
			for (int i = 0; i < expresionIndicadores.size(); i++) {
				indicador.setPeriodo(periodo);
				System.out.println("Periodo de indicador: "+indicador.getPeriodo());
					indicador = indVisitor.obtenerResultadoIndicadorSegunEmpresa(expresionIndicadores.get(i), empresa,
							periodo);
				indicadores.add(indicador);
//				indicadores.get(i).setPeriodo(listaPeriodos.get(j).getAnio());

			}
		}

//			for (int i = 0; i < indicadores.size(); i++) {
//				for (int j = 0; j < listaPeriodos.size(); j++) {
//				indicadores.get(i).setPeriodo(listaPeriodos.get(j).getAnio());
//			}
//		}		
		
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
		String empresaSeleccionada = req.queryParams("Empresa");
		String expresionIndicador = req.queryParams("valorIndicador");
		
		
		Stream<Indicador> filtro = repoIndicadores.stream().filter(ind -> ind.getNombre().equals(nombreIndicador));
		System.out.println("cantidad de indicadores"+repoIndicadores.size());

		Long contador = filtro.count();

		
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
					
//					Usuario us = new Usuario(usuario,req.cookie("password"));
//					System.out.println("us: "+us.getUserTag()+"pw:"+us.getUserTag());
					Indicador indicador = new Indicador();
					RepositorioServicio repo = repositorioServicio.getInstance();
					Usuario u = repo.buscarUsuarioPorNombre(usuario);
					System.out.println("u: "+u.getUserTag()+"pw:"+u.getUserTag());
					indicador.setUsuario(u);
					indicador.setNombre(nombreIndicador);
					indicador.setExpresion(nombreIndicador+"="+expresionIndicador+"\n");
					
					repo.getEmanager().getTransaction().begin();
					 repo.getEmanager().persist(indicador);
					 repo.getEmanager().getTransaction().commit();
					
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
