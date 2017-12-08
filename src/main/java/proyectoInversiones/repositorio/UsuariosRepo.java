package proyectoInversiones.repositorio;

import java.util.List;
import javax.persistence.EntityManager;

import proyectoInversiones.Empresa;
import proyectoInversiones.Periodo;
import proyectoInversiones.indicadores.ArmadorIndicador;
import proyectoInversiones.usuarios.Usuario;

public class UsuariosRepo extends Repositorio {
	UsuariosRepo(EntityManager emanager) {
		super(emanager);
	}

	public Usuario buscarPorId(Long id) {
		return emanager.find(Usuario.class, id);
	}
	
	public void persistir(Usuario unUsuario) {
		emanager.getTransaction().begin();
		emanager.persist(unUsuario);
		emanager.getTransaction().commit();
	}
	
	public void persistirIndicador(ArmadorIndicador indicador){
		emanager.getTransaction().begin();
		emanager.persist(indicador);
		emanager.getTransaction().commit();
	}

	
//	public void persistirMetodologia(){
//		emanager.getTransaction().begin();
//		emanager.persist();
//		emanager.getTransaction().commit();
//	}
	
}


