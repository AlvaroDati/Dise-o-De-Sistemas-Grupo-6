package db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;



import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.Cuenta;
import proyectoInversiones.Indicador;
import proyectoInversiones.usuarios.LeerUsuarios;
import proyectoInversiones.usuarios.Usuario;
import proyectoInversiones.repositorio.*;
import proyectoInversiones.indicadores.ArmadorIndicador;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPersistencia {
	private static final String PERSISTENCE_UNIT_NAME = "dds";
	private EntityManagerFactory emFactory;
	private Repositorio repositorio;
	
	
	@Before
	public void setUp() throws Exception {
		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		repositorio = new Repositorio(emFactory.createEntityManager());
	}
	
	
	@Test
	public void  persistir1UsuariosYverificarUno() {
		LeerUsuarios archivoUsuarios = new LeerUsuarios();
		ArrayList<Usuario> usuarios = archivoUsuarios.leerArchivo();
		
		for (Usuario head:usuarios){
			if(repositorio.usuariosRepo().buscarPorId(head.getId()).getUserTag().equals(head.getUserTag()))
				Assert.assertEquals(repositorio.usuariosRepo().buscarPorId((long)1).getUserTag(), "ivan");
			else
				repositorio.usuariosRepo().persistir(head);
		}
		
		Assert.assertEquals(repositorio.usuariosRepo().buscarPorId((long)2).getUserTag(), "alvitovito");
	}


	
	
	@Test
	public void persistir2ConJson() {
		NuevoLeerArchivo archivo = new NuevoLeerArchivo();
		ArrayList<Empresa> empresas = archivo.leerArchivo();
		ArmadorIndicador armarIndicador = new ArmadorIndicador();
//		ArrayList<Float> ingresoNeto = new ArrayList<Float>();

		for (int i = 0; i < empresas.size(); i++) {
	
			for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
				
				Indicador indicador = new Indicador();

				indicador.setNombre("Ingreso Neto");
				indicador.setEmpresaAsoc(empresas.get(i).getNombre());
				indicador.setPeriodo(empresas.get(i).getPeriodos().get(j).getAnio());
				indicador.setValorIndicador(armarIndicador.calcularIngresoNeto(empresas.get(i)).get(j));
								
//				empresas.get(i).addIndicador(indicador);
				empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
				empresas.get(i).getPeriodos().get(j).getCuentas().setPeriodoVinculado(empresas.get(i).getPeriodos().get(j));
				repositorio.indicadoresRepo().persistir(indicador);
				repositorio.empresasRepo().persistir(empresas.get(i)); 
			}
			
		}
		
		for (int i = 0; i < empresas.size(); i++) {
			
			for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
				
				Indicador indicador = new Indicador();

				indicador.setNombre("ROE");
				indicador.setEmpresaAsoc(empresas.get(i).getNombre());
				indicador.setPeriodo(empresas.get(i).getPeriodos().get(j).getAnio());
				indicador.setValorIndicador(armarIndicador.calcularRoe(empresas.get(i)).get(j));
								
			//	empresas.get(i).addIndicador(indicador);
				empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
				
				repositorio.indicadoresRepo().persistir(indicador);
				repositorio.empresasRepo().persistir(empresas.get(i)); 
			}
			
		}
	}
	
	@Test
	public void persistir3IndicadoresDefinidosPorUsuario(){
		Empresa empresa = new Empresa("America Movil");
		Usuario usuario = new Usuario("ivan","ivan");
		ArmadorIndicador armadorIndicador = new ArmadorIndicador();
		List<Indicador> indicadores = new ArrayList<Indicador>();
		String archivo = "IndicadoresDelUsuarioivan";
		try{
			indicadores = armadorIndicador.getIndicadoresUsuario(archivo, empresa);
		}catch(Exception e){
			
		}
		
		usuario.setIndicadoresUsuario(indicadores);
		for(Indicador head:indicadores){
			
			System.out.println(head.getEmpresaAsoc());
			System.out.println(head.getNombre());
			repositorio.indicadoresRepo().persistir(head);
		}
	}
	
	/*En vez de perisistir solo empresas, se podria persistir indicadores y metodologias, 
	Ya que al no estar en json, siempre van a tiran el mismo error 

Caused by: org.hibernate.MappingException: Could not determine type for: proyectoInversiones.NuevoLeerArchivo, at table: Indicador, for columns: [org.hibernate.mapping.Column(archivoEmpresas)]
	Esto lo tira, porque en el json, no hay un campo Indicador/Metodologia 
	Capaz si hacemos un persistir(Indicador/Metodologia) indicando la empresa a la que esta relacionada
	Va a ser mas llevadero esto, y se pueden hacer mejores tests
	 	*/
	
	
	@Test
	public void persistir4BuscarEmpresaPorNombre(){
		
		Empresa empresa = new Empresa("EmpresaPrueba1");
		Empresa empresa2 = new Empresa("EmpresaPrueba2");
		Empresa otraEmpresa = new Empresa("asd");
		repositorio.empresasRepo().persistir(empresa);
		repositorio.empresasRepo().persistir(empresa2);
		repositorio.empresasRepo().persistir(otraEmpresa);
		
		
		List<Empresa> empresasFiltradas = repositorio.empresasRepo().buscarEmpresaPorNombre("Emp");
		for(Empresa unaEmpresa : empresasFiltradas){
			System.out.println(unaEmpresa.getNombre());
		}
		
		Assert.assertFalse(empresasFiltradas.contains(otraEmpresa));
		Assert.assertTrue(empresasFiltradas.contains(empresa) && empresasFiltradas.contains(empresa2));
	}
	
/*	
	@Test
	public void  persistirEmpresaConPeriodos() {
		Periodo unPeriodo = new Periodo(2014);
		Periodo otroPeriodo = new Periodo(2010);
		Periodo p3 = new Periodo(2000);
		Empresa unaEmpresa = new Empresa();
		unaEmpresa.setNombre("IBM");
	//	unPeriodo.setEmpresa(unaEmpresa);
	//	otroPeriodo.setEmpresa(unaEmpresa);
	//	p3.setEmpresa(unaEmpresa);
		unaEmpresa.addPeriodo(unPeriodo);
		unaEmpresa.addPeriodo(otroPeriodo);
		unaEmpresa.addPeriodo(p3);
	    repositorio.empresasRepo().persistir(unaEmpresa);
		unaEmpresa.getPeriodos().forEach(periodo -> System.out.println(periodo.getAnio()));
	}*/
	
	


	
	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
		emFactory.close();
	}
}
	


