package proyectoInversiones;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import proyectoInversiones.repositorio.Repositorio;
import proyectoInversiones.usuarios.Usuario;

public class CargaBatchCuentas extends TimerTask {

	String rutaAnterior = "";
	Long ultimaMod = null;
	private static final String PERSISTENCE_UNIT_NAME = "db";
	private EntityManagerFactory emFactory;
	@PersistenceContext(unitName = "db", type = PersistenceContextType.EXTENDED)
	private static EntityManager emanager;
	private Repositorio repositorio;

	public CargaBatchCuentas() {
		super();
	}

	@Override
	public void run() {
		try {
			this.persistirConJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void persistirConJson() {

		emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		System.out.println("asfssd");
		emanager = emFactory.createEntityManager();
		repositorio = new Repositorio(emanager);
		NuevoLeerArchivo archivo = new NuevoLeerArchivo();

		ArrayList<Empresa> empresas = archivo.leerArchivo();
		String rutaArchivo = archivo.getRuta();

		System.out.printf("\nRuta del archivo: %s\n", rutaArchivo);

		File file = new File(rutaArchivo);
		Long modReal = file.lastModified();

		System.out.printf("\nmodReal: %d", modReal);
		System.out.printf("\nultimaMod: %d\n", ultimaMod);

		if (rutaArchivo == rutaAnterior && ultimaMod.equals(modReal)) {

			System.out.printf("\nEl archivo ya fue cargado\n");
			repositorio.cerrar();
			emFactory.close();

		} else {

			System.out.printf("\nPersistiendo archivo\n");
			System.out.printf("///////////////////////\n");
			for (int i = 0; i < empresas.size(); i++) {
				for (int j = 0; j < empresas.get(i).getPeriodos().size(); j++) {
					empresas.get(i).getPeriodos().get(j).setEmpresa(empresas.get(i));
					empresas.get(i).getPeriodos().get(j).getCuentas()
							.setPeriodoVinculado(empresas.get(i).getPeriodos().get(j));
					repositorio.empresasRepo().persistir(empresas.get(i));
				}
			}
			rutaAnterior = rutaArchivo;
			ultimaMod = file.lastModified();
			repositorio.cerrar();
			emFactory.close();

		}
	}

	public List<Usuario> obtenerUsuariosSegunNombre(String nombre) {
		List<Usuario> usuarioALoguearse = new ArrayList<Usuario>();
		usuarioALoguearse = emanager.createNamedQuery("buscarUsuarioPorNombre")
				.setParameter("filtro", "%" + nombre + "%").getResultList();

		// System.out.println("Usuario: " +
		// usuarioALoguearse.get(0).getPassword());
		// Usuario usuarioALoguearse2 = null;
		// for (int i = 0; i < usuarioALoguearse.size(); i++) {
		// if (usuarioALoguearse.get(i).getUserTag().equals(nombre)) {
		// usuarioALoguearse2 = usuarioALoguearse.get(i);
		// }
		// }

		return usuarioALoguearse;

	}

}
