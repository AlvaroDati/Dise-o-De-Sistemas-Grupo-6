package proyectoInversiones.repositorio;

import javax.persistence.EntityManager;
import proyectoInversiones.Empresa;


public class  Empresas extends Repositorio {
	Empresas(EntityManager emanager) {
		super(emanager);
	}

	public Empresa buscarPorId(Long id) {
		return emanager.find(Empresa.class, id);
	}

	public void persistir(Empresa empresa) {
		emanager.getTransaction().begin();
		emanager.persist(empresa);
		emanager.getTransaction().commit();
	}
}