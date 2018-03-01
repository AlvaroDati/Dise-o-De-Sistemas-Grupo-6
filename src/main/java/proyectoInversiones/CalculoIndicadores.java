package proyectoInversiones;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import proyectoInversiones.Empresa;
import proyectoInversiones.Indicador;
import proyectoInversiones.Periodo;
import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.indicadores.IndVisitor;
import proyectoInversiones.repositorio.RepositorioServicio;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;

public class CalculoIndicadores {
	static String usuarioActivo;
	static String rutaArchivo = "target/classes/public/IndicadoresDelUsuario";
	static List<Indicador> repoIndicadores = new ArrayList<Indicador>();
	static int valor;
	public CalculoIndicadores(String usuario){
		usuarioActivo = usuario;
	}
	
	public List<Indicador> setearListaIndicadores(List<Periodo> listaPeriodos, Empresa empresa) throws IOException {
		
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

	public List<Indicador> setearListaIndicadoresUsuario(List<Periodo> listaPeriodos,Empresa empresa,RepositorioServicio repositorio)throws IOException{
		List<Indicador> indicadoresADevolver = new ArrayList<Indicador>();
		IndVisitor indVisitor = new IndVisitor();
		String usuario = usuarioActivo;
		List<Indicador> indicadores = new ArrayList<Indicador>();
		indicadores = repositorio.buscarIndicadorPorUsuario2(usuario);
		System.out.println("Indicadores traidos de la base de datos: "+indicadores.size());
		Indicador indicador = new Indicador();
		for(int i = 0;i<indicadores.size();i++){
			System.out.println("Indicador: "+indicadores.get(i).getNombre() + " Expresion: "+indicadores.get(i).getExpresion());
		}
		for (Periodo periodo:listaPeriodos) {
			for (int i = 0; i < indicadores.size(); i++) {
				indicador.setPeriodo(periodo.getAnio());
				System.out.println("Periodo de indicador: "+indicador.getPeriodo());
					indicador = indVisitor.obtenerResultadoIndicadorSegunEmpresa(indicadores.get(i).getExpresion(), empresa,
							periodo.getAnio());
				indicadoresADevolver.add(indicador);
				
			}
		}
		
		for(int i = 0;i<indicadores.size();i++){
			System.out.println("Indicador: "+indicadores.get(i).getNombre() + " Expresion: "+indicadores.get(i).getExpresion());
		}
		return indicadoresADevolver;
	}
	
//	public List<Indicador> setearListaIndicadoresUsuario(List<Periodo> listaPeriodos,Empresa empresa)
//			throws IOException {
//		List<Indicador> indicadores = new ArrayList<Indicador>();
//		IndVisitor indVisitor = new IndVisitor();
//		String usuario = usuarioActivo;
//		LeerUsuarios archivoUsuarios = new LeerUsuarios();
//		Usuario usuarioCreador = archivoUsuarios.obtenerUsuario(usuario);
//		String archivoUsuario = rutaArchivo.concat(usuario);
//		System.out.println("Archivo del usuario: " + archivoUsuario);
//		File file = new File(archivoUsuario);
//		if (!file.exists()) {
//			file.createNewFile();
//		}
//		
//		repoIndicadores.addAll(indVisitor.obtenerResultadosIndicadoresUsuarioSegunEmpresa(archivoUsuario, empresa, listaPeriodos.get(1).getAnio()));
//		
//		
//
//		for(int i = 0;i<listaPeriodos.size();i++){
//			indicadores.addAll(indVisitor.obtenerResultadosIndicadoresUsuarioSegunEmpresa(archivoUsuario, empresa, listaPeriodos.get(i).getAnio()));
//			
//			
//		}
//			for(int j = 0;j<indicadores.size();j++){
//					indicadores.get(j).setUsuario(usuarioCreador);
//		}
//					
//		return indicadores;
//	}
//	
}