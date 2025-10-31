package entidades;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HomeSolution implements IHomeSolution {
	
	
	private String nombreEmpresa;
	private Map <Integer, Cliente> clientes;
	private Map <Integer, Proyecto> proyectos;
	private List<Empleado> listaEmpleados;
	private List <Tupla <Integer, String>> idEmpleados;
	private ColaEmpleadosLibres colaEmpleadosLibres;
	

	public HomeSolution() {
		this.nombreEmpresa = "HomeSolution";
	    this.clientes = new HashMap<>();     
	    this.proyectos = new TreeMap<>();    
	    this.listaEmpleados = new LinkedList<>();
	    this.idEmpleados= new LinkedList<>();
	    this.colaEmpleadosLibres= new ColaEmpleadosLibres();
	}


	
	private int retornarUltimoIdLegajoEmpleados() {
		
		if (idEmpleados.isEmpty()) {
			
			System.out.println("no hay empleados");
			
			return 0;
		}
		
		else {Tupla<Integer, String> ultimoEmpleado= idEmpleados.get(idEmpleados.size() - 1);
		Integer ultimaClave = ultimoEmpleado.getValor1();
	
		return ultimaClave;}
		
		
		}
	
	@Override
	public void registrarEmpleado(String nombre, double valor) throws IllegalArgumentException {
		
		System.out.println("Metodo registrarEmpleadoContratado iniciado");
		
		Empleado empleado = new EmpleadoContratado(nombre,valor );
		int ultimoIdEmpleado= retornarUltimoIdLegajoEmpleados() +1;

		empleado.asignarNroDeLegajoAEmpleado(ultimoIdEmpleado );
		listaEmpleados.add(empleado);
		Tupla <Integer, String> nuevoEmpleado = new Tupla<>((Integer) ultimoIdEmpleado, empleado.getNombre());
		idEmpleados.add(nuevoEmpleado);
		
		System.out.println("Metodo registrarEmpleadoContratado Finalizado");
		
	}


	@Override
	public void registrarEmpleado(String nombre, double valor, String categoria) throws IllegalArgumentException {

		System.out.println("Metodo registrarEmpleadoCPlanta iniciado");
		
		Empleado empleado = new EmpleadoPlanta(nombre,valor, categoria);
		int ultimoIdEmpleado= retornarUltimoIdLegajoEmpleados() +1;

		empleado.asignarNroDeLegajoAEmpleado(ultimoIdEmpleado );
		listaEmpleados.add(empleado);
		Tupla <Integer, String> nuevoEmpleado = new Tupla<>((Integer) ultimoIdEmpleado, empleado.getNombre());
		idEmpleados.add(nuevoEmpleado);
		
		System.out.println("Metodo registrarEmpleadoPlanta Finalizado");
		
	}

	
	private int retornarUltimoIdProyecto () {
		
		if (proyectos.isEmpty()) {
			return 1;
		}
		else {
			
			Integer ultimoKey = (((TreeMap<Integer, Proyecto>) proyectos).lastKey()+1);
			return ultimoKey ;
		}
		
	}
	
	@Override
	public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio,
			String[] cliente, String inicio, String fin) throws IllegalArgumentException {
		
		System.out.println("Ingreso al metodo RegistrarProyecto");
		
		Proyecto proyecto = new Proyecto(cliente,domicilio,titulos,descripcion, dias,inicio,fin);
		int ultimaKey= retornarUltimoIdProyecto();
		
		proyecto.asignarNroIdProyecto(ultimaKey);
		proyectos.put( ultimaKey, proyecto);
		
		System.out.println("Finaliza el  metodo RegistrarProyecto");
	}


	@Override
	public void asignarResponsableEnTarea(Integer numero, String titulo) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void asignarResponsableMenosRetraso(Integer numero, String titulo) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void registrarRetrasoEnTarea(Integer numero, String titulo, double cantidadDias)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void agregarTareaEnProyecto(Integer numero, String titulo, String descripcion, double dias)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void finalizarTarea(Integer numero, String titulo) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public double costoProyecto(Integer numero) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Tupla<Integer, String>> proyectosFinalizados() {
		
		return null;
	}


	@Override
	public List<Tupla<Integer, String>> proyectosPendientes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Tupla<Integer, String>> proyectosActivos() {
		
		LinkedList<Tupla<Integer, String>> proyectosActivos = new LinkedList<>();
		
		for (Proyecto proyecto : proyectos.values()) {
			if(proyecto.isEstado() == false) {
				
				Tupla<Integer, String> p = new Tupla<>(proyecto.getId(), proyecto.toString());
				proyectosActivos.add(p);
				
				System.out.println(proyecto);
			}
			
		}
		return proyectosActivos;
	}


	@Override
	public Object[] empleadosNoAsignados() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean estaFinalizado(Integer numero) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int consultarCantidadRetrasosEmpleado(Integer legajo) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] tareasProyectoNoAsignadas(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	private String [] listaDeTareas(Map<Integer, Tarea> tareas ) {
		
		
		
		
		List<String> tareasDelProyecto = new LinkedList<>();
		
		for (Tarea tarea: tareas.values()) {
			tareasDelProyecto.add(tarea.getTitulo());
			
		}
		
		String [] arrayDeTareasDeRetorno= new String [tareasDelProyecto.size()];
		
		int i=0;
		for (String titulo: tareasDelProyecto) {
			
			arrayDeTareasDeRetorno[i]= titulo;
			i +=1;
			
		}
		
		return arrayDeTareasDeRetorno;
		
	}
	
	@Override
	public Object[] tareasDeUnProyecto(Integer numero) {
		
		 System.out.println("Inicio metodo tareasDeUnProyecto  ");
		 
		 Proyecto proyecto = proyectos.get(numero);
		 
		 if (proyecto == null) {
			 System.out.println("No existe ese ID");
			 return null;
		 }
		 
		 Map<Integer, Tarea> tareas = proyecto.retornarListaDeTareas();
		 
		 if (  tareas.isEmpty() ) {
			 System.out.println("El proyecto no tiene tareas");
			 return null;
		 }
		
			String [] listaTitulos= listaDeTareas(tareas);
		
		System.out.println("La listaTituylos es" + listaTitulos);	
			
		return listaTitulos;
	
	}


	@Override
	public String consultarDomicilioProyecto(Integer numero) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean tieneRestrasos(Integer legajo) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Tupla<Integer, String>> empleados() {
		// TODO Auto-generated method stub
		return idEmpleados;
	}


	@Override
	public String consultarProyecto(Integer numero) {
		
		Proyecto proyecto = proyectos.get(numero);
		
		System.out.println(proyecto);
		
		return null;
	}
	
	

}
