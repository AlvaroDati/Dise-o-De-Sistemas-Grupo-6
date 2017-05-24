package proyectoInversiones;


public class Cuenta{
	private int periodo;
	private int ebitda;
	private int fds;
	private int fCashFlow;
	private int ingNetoOpCont;
	private int ingNetoOpDiscont;
	private int deuda;
	
	public int getDeuda() {
		return deuda;
	}
	public void setDeuda(int deuda) {
		this.deuda = deuda;
	}
	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public int getEbitda() {
		return ebitda;
	}
	public void setEbitda(int ebitda) {
		this.ebitda = ebitda;
	}
	public int getFds() {
		return fds;
	}
	public void setFds(int fds) {
		this.fds = fds;
	}
	public int getfCashFlow() {
		return fCashFlow;
	}
	public void setfCashFlow(int fCashFlow) {
		this.fCashFlow = fCashFlow;
	}
	public int getIngNetoOpCont() {
		return ingNetoOpCont;
	}
	public void setIngNetoOpCont(int ingNetoOpCont) {
		this.ingNetoOpCont = ingNetoOpCont;
	}
	public int getIngNetoOpDiscont() {
		return ingNetoOpDiscont;
	}
	public void setIngNetoOpDiscont(int ingNetoOpDiscont) {
		this.ingNetoOpDiscont = ingNetoOpDiscont;
	}
	
	public String toString(){
		return getPeriodo() + "";
	}
	
	public String mostrarTodo (){
		
		String datosCuentas;
		
		datosCuentas = "<html><body>" + "Periodo: " + Integer.toString(this.getPeriodo()) + "<br>" + "Ebitda: " + Integer.toString (this.getEbitda())
		+  "<br>" + Integer.toString(this.getFds()) +  "<br>" + Integer.toString(this.getfCashFlow()) + "<br>" + Integer.toString(this.getIngNetoOpCont()) + "<br>" + this.getIngNetoOpDiscont() 
		+  "<br>" + Integer.toString(this.getDeuda()) 
 		+  "<br>" + "---------------------------------------";
		
		/*System.out.print("Periodo: ");
		System.out.println(this.getPeriodo());
		System.out.print("Ebitda: ");
		System.out.println(this.getEbitda());
		System.out.print("FDS: ");
		System.out.println(this.getFds());
		System.out.print("FCashFlow: ");
		System.out.println(this.getfCashFlow());
		System.out.print("ingNetoOpCont: ");
		System.out.println(this.getIngNetoOpCont());
		System.out.print("ingNetoOpDiscont: ");
		System.out.println(this.getIngNetoOpDiscont());
		System.out.print("deuda: ");
		System.out.println(this.getDeuda());

		System.out.println("---------------------------------------");*/
	
		return datosCuentas;

	}
	public static void main(String args[]){
		Cuenta cuenta = new Cuenta();
		System.out.println(cuenta.mostrarTodo());
	}
	
 }