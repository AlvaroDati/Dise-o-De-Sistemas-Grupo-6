package proyectoInversiones.repositorio;

import javax.persistence.EntityManager;

import proyectoInversiones.Empresa;
import proyectoInversiones.Periodo;

public class PeriodosRepo extends Repositorio {
	PeriodosRepo(EntityManager emanager) {
		super(emanager);
	}

	public Periodo buscarPorId(Long id) {
		return emanager.find(Periodo.class, id);
	}
	public void persistir(Periodo unaEmpresa) {
		emanager.getTransaction().begin();
		emanager.persist(unaEmpresa);
		emanager.getTransaction().commit();
	}
	
/*
	public List<Periodo> buscarPeriodoPorNombre(String nombre) {
		List<Periodo> periodos = null;
		periodos = emanager.createNamedQuery("buscarPeriodoPorNombre").setParameter("pnombre", "%" + nombre + "%").getResultList();
		return periodos;
	}*/
}