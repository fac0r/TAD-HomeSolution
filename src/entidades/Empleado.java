package entidades;

public abstract class Empleado {
	
	private String nombre;
	protected int n_legajo;
	private boolean estado;
	private int demorasInformadas;
	
	 public Empleado(String nombre) {
	        this.nombre = nombre;
	        this.n_legajo= 0;
	        this.setEstado(false);
	        this.setDemorasInformadas(0);
	    }
	
	
	 
	 
	
	 public abstract void informarDemora(int n_legajo_empleado);
	 public abstract void informarBajaDeTarea(int n_legajo_empleado);
	 public abstract void informarFinalizacionTarea(int n_legajo_empleado);
	 public abstract   int consultarIdUltimoEmpleado ();
	 public abstract void asignarNroDeLegajoAEmpleado(int nroLegajo);





	public String getNombre() {
		return nombre;
	}





	public boolean isEstado() {
		return estado;
	}





	public void setEstado(boolean estado) {
		this.estado = estado;
	}





	public int getDemorasInformadas() {
		return demorasInformadas;
	}





	public void setDemorasInformadas(int demorasInformadas) {
		this.demorasInformadas = demorasInformadas;
	}
	 


}
