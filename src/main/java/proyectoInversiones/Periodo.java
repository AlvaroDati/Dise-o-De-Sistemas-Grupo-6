package proyectoInversiones;

import java.util.*;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import proyectoInversiones.Empresa;

@Entity
@Table(name = "Periodos")
public class Periodo implements Serializable{
	
    @Id @GeneratedValue(strategy=GenerationType.AUTO)      
	protected Long id;
    @Column(name = "Anio")
	protected int anio;  
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id", referencedColumnName = "id")
	private Empresa empresa;
	//@OneToMany
	private ArrayList<Cuenta> cuentas;
	
	public Periodo() {
		;
	}
	
	
	@Column(name = "id")
	public Long getId() {
	return id;
	}
	
    public void setId(Long id) {
		this.id = id;
	}
	
   public Periodo(int unAnio) {  
		anio = unAnio;
	}
	
   public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public ArrayList<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(ArrayList<Cuenta> unasCuentas) {
		cuentas = unasCuentas;
	}

	
	
	
}
