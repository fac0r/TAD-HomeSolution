package entidades;

public class EmpleadoContratado extends Empleado {
	


	
	private double  valorHora;
	

	public EmpleadoContratado(String nombre, double valor) {
		super(nombre);
		this.valorHora= valor;
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
	
	
	public double getValorHora() {
		return valorHora;
	}

	public double getValor( ) {
		return this.getValorHora();
	}
	
	@Override
	public String toString() {
		return "EmpleadoContratado [" + super.toString() + "  valorHora=" + valorHora + "]";
	}



}
