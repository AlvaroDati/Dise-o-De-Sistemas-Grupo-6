package proyectoInversiones;


public class Cuenta{
	private int periodo;
	private float ebitda;
	private float fds;
	private float fCashFlow;
	private float ingNetoOpCont;
	private float ingNetoOpDiscont;
	private float deuda;
	private float capitalTotal;
	
	public float getDeuda() {
		return deuda;
	}
	public void setDeuda(float deuda) {
		this.deuda = deuda;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public float getEbitda() {
		return ebitda;
	}
	public void setEbitda(float ebitda) {
		this.ebitda = ebitda;
	}
	public float getFds() {
		return fds;
	}
	public void setFds(float fds) {
		this.fds = fds;
	}
	public float getfCashFlow() {
		return fCashFlow;
	}
	public void setfCashFlow(float fCashFlow) {
		this.fCashFlow = fCashFlow;
	}
	public float getIngNetoOpCont() {
		return ingNetoOpCont;
	}
	public void setIngNetoOpCont(float ingNetoOpCont) {
		this.ingNetoOpCont = ingNetoOpCont;
	}
	public float getIngNetoOpDiscont() {
		return ingNetoOpDiscont;
	}
	public void setIngNetoOpDiscont(float ingNetoOpDiscont) {
		this.ingNetoOpDiscont = ingNetoOpDiscont;
	}
	
	public float capitalTotal(){
		capitalTotal = this.getEbitda() + this.getfCashFlow() + this.getFds() + this.getIngNetoOpCont() + this.getIngNetoOpDiscont();
		return capitalTotal;
	}
	
	public static void main(String args[]){
	}
 }