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
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadoresController implements WithGlobalEntityManager, TransactionalOps {
	static String usuarioActivo;
	static String rutaArchivo = "IndicadoresDelUsuario";
	static List<Indicador> repoIndicadores = new ArrayList<Indicador>();
	
	public ModelAndView listar(Request req, Response res) throws IOException {

		String usuario = req.cookie("userTag");
		System.out.printf("Tag del usuario: %s '\n",usuario);
		usuarioActivo = usuario;
		Map<String, List<Indicador>> model = new HashMap<>();
		Empresa empresaInicial = new Empresa("America Movil");
		NuevoLeerArchivo arch = new NuevoLeerArchivo();
		List<Periodo> periodos = arch.getPeriodos(empresaInicial);
		List<Indicador> indicadores = setearListaIndicadores(periodos, empresaInicial);
		List<Indicador> indicadoresUsuario = setearListaIndicadoresUsuario(periodos, empresaInicial);
		model.put("indicadores", indicadores);
		model.put("indicadoresU", indicadoresUsuario);
		return new ModelAndView(model, "Indicadores2.html");
	}

	public static ModelAndView setearEmpresa(Request req, Response res) {
		String nombreEmpresa = req.queryParams("Empresa");
		Empresa empresa = new Empresa(nombreEmpresa);
		try {
			Map<String, List<Indicador>> model = new HashMap<>();
			NuevoLeerArchivo arch = new NuevoLeerArchivo();
			List<Periodo> periodosEmpresa = arch.getPeriodos(empresa);
			List<Indicador> indicadoresDeEmpresa = setearListaIndicadores(periodosEmpresa, empresa);
			List<Indicador> indicadoresUsuario = setearListaIndicadoresUsuario(periodosEmpresa, empresa);
			model.put("indicadores", indicadoresDeEmpresa);
			model.put("indicadoresU", indicadoresUsuario);
			return new ModelAndView(model, "Indicadores2.html");
		} catch (Exception e) {
			res.cookie("mensajeError", e.getMessage());
			res.redirect("/cuentas");
		}

		return null;

	}

	public static List<Indicador> setearListaIndicadores(List<Periodo> listaPeriodos, Empresa empresa) {

		List<Indicador> indicadores = new ArrayList<Indicador>();
		ArmadorIndicador calculadorIndicadores = new ArmadorIndicador();

		for (int i = 0; i < listaPeriodos.size(); i++) {
			Indicador indicadorAux = new Indicador();
			indicadorAux.setPeriodo(listaPeriodos.get(i).getAnio());
			indicadorAux.setRoe(calculadorIndicadores.obtenerRoeSegunPeriodo(empresa, listaPeriodos.get(i).getAnio()));
			indicadorAux.setIngresoNeto(calculadorIndicadores.obtenerIngresoNetoSegunPeriodo(empresa, listaPeriodos.get(i).getAnio()));
			indicadores.add(indicadorAux);
		}
		return indicadores;
	}

	public static List<Indicador> setearListaIndicadoresUsuario(List<Periodo> listaPeriodos, Empresa empresa)
			throws IOException {
		List<Indicador> indicadores = new ArrayList<Indicador>();
		ArmadorIndicador calcularIndicadores = new ArmadorIndicador();
		String usuario = usuarioActivo;
		LeerUsuarios archivoUsuarios = new LeerUsuarios();
		Usuario usuarioCreador = archivoUsuarios.obtenerUsuario(usuario);
		String archivoUsuario = rutaArchivo.concat(usuario); //IndicadoresUsuarioUserTag;
		File file = new File(archivoUsuario);
		if (!file.exists()) {
			file.createNewFile();
		}
		
		indicadores = calcularIndicadores.getIndicadoresUsuario(archivoUsuario, empresa);
		for(int i = 0;i<indicadores.size();i++){
			indicadores.get(i).setUsuario(usuarioCreador);
		}
		return indicadores;
	}

	public static ModelAndView nuevoFormulario(Request req, Response res) {
		return new ModelAndView(null, "IndicadoresNuevo.html");
	}

	public static ModelAndView nuevoInd(Request req, Response res) {
		String usuario = req.cookie("userTag");
		String nombreIndicador = req.queryParams("nombreIndicador");
		String empresaSeleccionada = req.queryParams("Empresa");
		String expresionIndicador = req.queryParams("valorIndicador");
		if (repoIndicadores.stream().filter(x -> x.getNombre().equals(nombreIndicador)) == null) {
			if (nombreIndicador != null && empresaSeleccionada != null && expresionIndicador != null) {
				try {

					String archivoUsuario = rutaArchivo.concat(usuario);
					File file = new File(archivoUsuario);

					if (!file.exists()) {
						file.createNewFile();
					}

					FileWriter fw = new FileWriter(file, true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(empresaSeleccionada.toString() + "(" + nombreIndicador + ")" + "=");
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
