package proyectoInversiones.repositorio;


import javax.persistence.EntityManager;

public class Repositorio {
	private Periodos periodos;
	//private Cuentas cuentas;  revisar si la estructura de cuentas se puede alterar para hacer OneToOne
	private Empresas empresas;
	protected EntityManager emanager;

	public Repositorio(EntityManager emanager) {
		this.emanager = emanager;
	}
	
	public Empresas empresas(){
		if (empresas == null) {
			empresas = new Empresas(emanager);
		}
		return empresas;
	}

	public Periodos periodos() {
		if (periodos == null) {
			periodos = new Periodos(emanager);
		}
		return periodos;
	}

	/*public Cuentas cuentas() {
		if (cuentas == null) {
			cuentas = new Cuentas(emanager);
		}
		return cuentas;
	}*/
	
	


	public void cerrar() {
		emanager.close();
	}
}