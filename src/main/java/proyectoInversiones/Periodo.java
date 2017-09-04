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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import proyectoInversiones.Empresa;
import proyectoInversiones.Cuenta;

@Entity
@Table(name = "periodos")
public class Periodo implements Serializable{
	
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)      
	protected Long id;
    @Column(name = "anio")
	protected int anio;  
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    private Cuenta cuentas;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "empresa_id", referencedColumnName = "id")
	private Empresa empresa;
	
	
	

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
	public Periodo() {
		cuentas = new Cuenta();
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

	public Cuenta getCuentas() {
		return cuentas;
	}
	public void setCuentas(Cuenta unasCuentas) {
		cuentas = unasCuentas;
	}

	
	
	
}
