package entidades;

public class EmpleadoPlanta extends Empleado{
	
	

	private double valorDia;
	private String categoria;
	private boolean registraRetrasoEnProyectoActual;

	public EmpleadoPlanta(String nombre, double valor, String categoria) {
		super(nombre);
	     this.valorDia= valor;
	     this.categoria = categoria;
	     this.registraRetrasoEnProyectoActual = false;
	}


	@Override
	public void informarBajaDeTarea(int n_legajo_empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informarFinalizacionTarea(int n_legajo_empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int consultarIdUltimoEmpleado() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void asignarNroDeLegajoAEmpleado(int nroLegajo) {
		
		this.n_legajo= nroLegajo;
	}
	
	

	public int getN_legajo() {
		return n_legajo;
	}
	
	
	@Override
	public String toString() {
		return "EmpleadoPlanta [" + super.toString() + " valorDia=" + valorDia + ", categoria=" + categoria
				+ ", registraRetrasoEnProyectoActual=" + registraRetrasoEnProyectoActual + "]";
	}

	
}
