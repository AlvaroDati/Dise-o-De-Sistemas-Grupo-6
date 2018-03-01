package db;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.DescargaDrive;
import proyectoInversiones.Indicador;
import proyectoInversiones.Metodologia;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import proyectoInversiones.repositorio.*;
import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.IndicadorAuxiliar;
import proyectoInversiones.metodologias.Antiguedad;
import proyectoInversiones.metodologias.CondicionPrioritaria;
import proyectoInversiones.metodologias.CondicionTaxativa;
import proyectoInversiones.metodologias.OperacionAgregacion;
import proyectoInversiones.metodologias.OperacionRelacional;
import proyectoInversiones.metodologias.OperandoCondicion;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPersistencia {
//	private static final String PERSISTENCE_UNIT_NAME = "db";
//	private EntityManagerFactory emFactory;
//	private EntityManager emanager;
//	private Repositorio repositorio;
//	private NuevoLeerArchivo archivo = new NuevoLeerArchivo();
//	private ArrayList<Empresa> empresasDelJson = new ArrayList<Empresa>();
//	
//	@Before
//	public void setUp() throws Exception {
//		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//		emanager = emFactory.createEntityManager();
//		repositorio = new Repositorio(emanager);
//		//empresasDelJson = archivo.leerArchivo();
//	}
//	@Test
//	public void cambiarExpresionIndicador(){
//			
//			Query query = emanager.createQuery("UPDATE Indicador i SET i.expresion='dds=20+20' WHERE i.usuario.id=1");
//			emanager.getTransaction().begin();
//			query.executeUpdate();
//			
//	}
//	@After
//	public void tearDown() throws Exception {
//		repositorio.cerrar();
//		emFactory.close();
//	}
//	
//	@Test
//	public void buscarIndicadorPorUsuario2(){
//		List<Indicador> indicador = new ArrayList<Indicador>();
//		//Usuario us = this.buscarUsuarioPorNombre(nombre);
//
//		indicador = emanager.createQuery("FROM Indicador WHERE usuario_id=" + 1L, Indicador.class).getResultList();
//		for(int i = 0;i<indicador.size();i++){
//			
//			System.out.println(indicador.get(i).getNombre() + " " + indicador.get(i).getExpresion());
//		}
//
//	@Test 
//	public void traerTodasLasEmpresas(){
//		List<Empresa> empresasEnLaDB = new ArrayList<Empresa>();
//		empresasEnLaDB = emanager.createQuery("FROM Empresa",Empresa.class).getResultList();
//
//	}
//	@Test
//	public void asdf(){
//		List<Empresa> empresasEnLaDB = new ArrayList<Empresa>();
//		empresasEnLaDB= emanager.createNamedQuery("buscarEmpresaPorNombre").setParameter("filtro", "Apple").getResultList();
//		
//		for(Empresa empresa:empresasEnLaDB){
//			for(int i = 0;i<empresasEnLaDB.size();i++){
//				
//				System.out.println("Empresa: "+empresa.getNombre()+ " Periodo: " + empresa.getPeriodos().get(i).getAnio());
//				System.out.println("Cuentas: "+ empresa.getPeriodos().get(i).getCuentas().getEbitda());
//				System.out.println("Cuentas: "+ empresa.getPeriodos().get(i).getCuentas().getfCashFlow());
//				System.out.println("Cuentas: "+ empresa.getPeriodos().get(i).getCuentas().getFds());
//				System.out.println("Cuentas: "+ empresa.getPeriodos().get(i).getCuentas().getIngNetoOpCont());
//				System.out.println("Cuentas: "+ empresa.getPeriodos().get(i).getCuentas().getIngNetoOpDiscont());
//				System.out.println("Cuentas: "+ empresa.getPeriodos().get(i).getCuentas().getDeuda());
//			}
//		}
//	}
//	
//	
//	@Test
//	public void verificarEmpresasPersistidas() throws IOException {
//		System.out.println("Vericando empresas a persistir");
//		ArrayList<Empresa> empresas = new DescargaDrive().obtenerEmpresas();
//		ArrayList<Empresa> empresasAPersistir = new ArrayList<Empresa>();
//		
//		for(int i=0;i<empresas.size();i++){
//			System.out.println("Empresas: " +empresas.get(i).getNombre());
//			List<Empresa> empresasEnLaDB = new ArrayList<Empresa>();
//			empresasEnLaDB= emanager.createNamedQuery("buscarEmpresaPorNombre").setParameter("filtro", empresas.get(i).getNombre()).getResultList();
//			if(empresasEnLaDB.size() == 0){
//				System.out.println("No se encontró la empresa:" + empresas.get(i).getNombre()+"en la base de datos");
//				empresasAPersistir.add(empresas.get(i));
//			}
//		}
//		System.out.println("Cantidad de empresas a persistir: " + empresasAPersistir.size());
//		
//	}
//	
//	
//	
//	@Test
//	public void conectateAPostgres(){
//		LeerUsuarios archivoUsuarios = new LeerUsuarios();
//		UsuariosRepo repo = new UsuariosRepo(emanager);
//		Usuario usuarioLoggeado = repo.buscarUsuarioPorNombre("ivan");
//		Long idUsuario =  usuarioLoggeado.getId();
//	}
//	
//	
//	
//	@Test
//	public void  persistir1UsuariosYverificarUno() {
//		LeerUsuarios archivoUsuarios = new LeerUsuarios();
//		ArrayList<Usuario> usuarios = archivoUsuarios.leerArchivo();
//		
//		
//		for (Usuario head:usuarios){
//			
//			if(repositorio.usuariosRepo().buscarPorId(head.getId()) == null){
//				repositorio.usuariosRepo().persistir(head);			
//			}else{
//				System.out.println("asdfsd");
//				if(repositorio.usuariosRepo().buscarPorId(head.getId()).getUserTag().equals(head.getUserTag()))
//					Assert.assertEquals(repositorio.usuariosRepo().buscarPorId((long)1).getUserTag(), "ivan");
//				else
//					repositorio.usuariosRepo().persistir(head);
//			}
//		}
//		
//		Assert.assertEquals(repositorio.usuariosRepo().buscarPorId((long)2).getUserTag(), "alvitovito");
//	}
//
//
//	
//	
//	
//	
//	
//	@Test
//	public void test() throws IOException{
//		ArrayList<Empresa> asf = new DescargaDrive().obtenerEmpresas();
////		ArrayList<Empresa> asf = new NuevoLeerArchivo().leerArchivo();
//		List<Periodo> periodos = new ArrayList<Periodo>();
//		for(int i = 0;i<asf.size();i++){
//			periodos.addAll(asf.get(i).getPeriodos());
//		}
//		
//		for(int i = 0;i<asf.size();i++){
//			
//			for(int j = 0;j<asf.get(i).getPeriodos().size();j++){
//				asf.get(i).getPeriodos().get(j).setEmpresa(asf.get(i));
//				System.out.println(asf.get(i).getPeriodos().get(j).getAnio());
//				System.out.println(asf.get(i).getPeriodos().get(j).getCuentas().getPeriodovinculado());
//				asf.get(i).getPeriodos().get(j).getCuentas().setPeriodoVinculado(asf.get(i).getPeriodos().get(j));
//			}
//		}
//	
//		for(int i = 0;i<asf.size();i++){
//			System.out.println(asf.get(i).getPeriodos());
//			repositorio.empresasRepo().persistir(asf.get(i));
//		}
//	}
//	
//	
//	
	
//	
//	@Test
//	public void persistir2ConJson() throws IOException {
//		NuevoLeerArchivo archivo = new NuevoLeerArchivo();
//		ArrayList<Empresa> empresas = new DescargaDrive().obtenerEmpresas();
//		ArmadorIndicador armarIndicador = new ArmadorIndicador();
////		ArrayList<Float> ingresoNeto = new ArrayList<Float>();
//
//		for (int i = 0; i < empresas.size(); i++) {
//	
//			for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
//				
//				Indicador indicador = new Indicador();
//
//				indicador.setNombre("Ingreso Neto");
//				indicador.setEmpresaAsoc(empresas.get(i).getNombre());
//				indicador.setPeriodo(empresas.get(i).getPeriodos().get(j).getAnio());
//				indicador.setValorIndicador(armarIndicador.calcularIngresoNeto(empresas.get(i)).get(j));
//								
////				empresas.get(i).addIndicador(indicador);
//				empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
//				empresas.get(i).getPeriodos().get(j).getCuentas().setPeriodoVinculado(empresas.get(i).getPeriodos().get(j));
//				repositorio.indicadoresRepo().persistir(indicador);
//				repositorio.empresasRepo().persistir(empresas.get(i)); 
//			}
//			
//		}
//		
//		for (int i = 0; i < empresas.size(); i++) {
//			
//			for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
//				
//				Indicador indicador = new Indicador();
//
//				indicador.setNombre("ROE");
//				indicador.setEmpresaAsoc(empresas.get(i).getNombre());
//				indicador.setPeriodo(empresas.get(i).getPeriodos().get(j).getAnio());
//				indicador.setValorIndicador(armarIndicador.calcularRoe(empresas.get(i)).get(j));
//								
//			//	empresas.get(i).addIndicador(indicador);
//				empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
//				
//				repositorio.indicadoresRepo().persistir(indicador);
//				repositorio.empresasRepo().persistir(empresas.get(i)); 
//			}
//			
//		}
//	}
//	
//	@Test
//	public void persistir3IndicadoresDefinidosPorUsuario(){
//		Empresa empresa = new Empresa("America Movil");
//		Usuario usuario = new Usuario("ivan","ivan");
//		ArmadorIndicador armadorIndicador = new ArmadorIndicador();
//		List<Indicador> indicadores = new ArrayList<Indicador>();
//		String archivo = "IndicadoresDelUsuarioivan";
//		try{
//			/*indicadores = armadorIndicador.getIndicadoresUsuario(archivo);*/
//		}catch(Exception e){
//			
//		}
//		
//		usuario.setIndicadoresUsuario(indicadores);
//		for(Indicador head:indicadores){
//			repositorio.indicadoresRepo().persistir(head);
//		}
//	}
//	
//	
//	
//	
//	
//
//	@Test
//	public void persistirMetodologia(){
//		Metodologia unaMetodologia = new Metodologia("Una Metodologia");
//		List<Empresa> empresasParaComparacionConMetodologias = new ArrayList<Empresa>();
//		Empresa ivanCompany = new Empresa("IvanCompany");
//		Empresa gulloCompany = new Empresa("GulloCompany");
//		ivanCompany.setInicioActividad(2000);
//		gulloCompany.setInicioActividad(1980);
//		IndicadorAuxiliar indicadorLoco = new IndicadorAuxiliar();
////		indicadorLoco.setEmpresaAsoc(ivanCompany.getNombre());
//		indicadorLoco.setExpresion("hola=DEUDA+500");
//		indicadorLoco.setNombre("indicadorLoco");
//		
//		CondicionTaxativa unaCondicionTaxativa = new CondicionTaxativa(
//												 new OperandoCondicion(OperacionAgregacion.Promedio,
//																					indicadorLoco,
//																					2), 
//																	  OperacionRelacional.Mayor, 
//																	  100);
//		if(unaMetodologia.evaluarPara(empresasParaComparacionConMetodologias).size() == 0){
//			unaMetodologia.agregarCondicionTaxativa(unaCondicionTaxativa);
//			System.out.println("2");
//		}
//		Antiguedad antiguedad = new Antiguedad();
////		antiguedad.setEmpresaAsoc("Soy un antiguedad");
////		antiguedad.setPeriodos("2999");
//		OperandoCondicion opCond = new OperandoCondicion(OperacionAgregacion.Ultimo, antiguedad, 1);
//		CondicionPrioritaria unaCondicionPrioritaria = new CondicionPrioritaria(opCond, OperacionRelacional.Mayor);		
//		if(unaCondicionPrioritaria.esMejorQue(gulloCompany, ivanCompany)){
//			unaMetodologia.agregarCondicionPrioritaria(unaCondicionPrioritaria);
//			System.out.println("3");
//		}
//		repositorio.metodologiasRepo().persistir(unaMetodologia);
//		
//		
//		
//	}
//	
//
//
//
//	
//	@After
//	public void tearDown() throws Exception {
//		repositorio.cerrar();
//		emFactory.close();
//	}
}
	


