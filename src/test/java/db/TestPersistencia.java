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



import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.Empresa;
import proyectoInversiones.NuevoLeerArchivo;
import proyectoInversiones.Periodo;
import proyectoInversiones.Cuenta;


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
	
	
/*
	@Test
	public void  persistirEmpresaConPeriodos() {
		Periodo unPeriodo = new Periodo(2014);
		Periodo otroPeriodo = new Periodo(2010);
		Periodo p3 = new Periodo(2000);
		Empresa unaEmpresa = new Empresa();
		unaEmpresa.setNombre("IBM");
		unPeriodo.setEmpresa(unaEmpresa);
		otroPeriodo.setEmpresa(unaEmpresa);
		p3.setEmpresa(unaEmpresa);
		unaEmpresa.addPeriodo(unPeriodo);
		unaEmpresa.addPeriodo(otroPeriodo);
		unaEmpresa.addPeriodo(p3);
	    repositorio.empresasRepo().persistir(unaEmpresa);
		unaEmpresa.getPeriodos().forEach(periodo -> System.out.println(periodo.getAnio()));
	}*/
	
	
/*	@Test
	public void  buscarEmpresaPorNombre() {
		List<Empresa> unasEmpresas = repositorio.empresasRepo().buscarEmpresaPorNombre("IB");
		for(Empresa unaEmpresa : unasEmpresas){
		System.out.println(unaEmpresa.getNombre());	
		System.out.println(unaEmpresa.getPeriodos());
		
		}
	}*/
	
	
//	@Test
//	public void  persistirEmpresaConPeriodosYCuentas() {
//		Empresa unaEmpresa = new Empresa("America Movil");
//        Periodo unPeriodo = new Periodo(2006);
//        Cuenta unaCuenta = new Cuenta();
//        unaCuenta.setEbitda(200);
//        unPeriodo.setCuentas(unaCuenta);
//        unaCuenta.setPeriodoVinculado(unPeriodo);
//        unaEmpresa.addPeriodo(unPeriodo);
//        unPeriodo.setEmpresa(unaEmpresa);
//		//NuevoLeerArchivo archivo = new NuevoLeerArchivo();
//		
//		
//		//Set<Periodo> periodos = archivo.getPeriodos(unaEmpresa);
//       // periodos.forEach(unPeriodo -> unPeriodo.setEmpresa(unaEmpresa));
//       /* for(Periodo head:periodos){
//        	head.setEmpresa(unaEmpresa);
//        }*/
//		repositorio.empresasRepo().persistir(unaEmpresa);
//		unaEmpresa.getPeriodos().forEach(periodo -> System.out.println(periodo.getAnio()));
//	}
//	
	
	@Test
      public void persistirConJson(){
		NuevoLeerArchivo archivo = new NuevoLeerArchivo();
		ArrayList<Empresa> empresa = archivo.leerArchivo();
		for(int i = 0;i<empresa.size();i++){
			empresa.get(i).getPeriodos().get(i).setEmpresa(empresa.get(i));
			repositorio.empresasRepo().persistir(empresa.get(i));
		}
	}
	
	@After
	public void tearDown() throws Exception {
		repositorio.cerrar();
		emFactory.close();
	}
}
	


