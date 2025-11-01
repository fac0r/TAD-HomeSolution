package entidades;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	    this.clientes = new TreeMap<>();     
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
	
	
	private void informarStoreDeColaDeEmpleados() {
		
		System.out.println("Voy a comprobar la cola de Empleados");
		
		System.out.println("La cantidad de empleados que hay actualmente es  " + colaEmpleadosLibres.informarCantidadDeEmpleadosEnCola());
		System.out.println("El  empleado a retornar sería : " + colaEmpleadosLibres.consultarProximo());
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
		
		colaEmpleadosLibres.agregarEmpleado(empleado);
		
		informarStoreDeColaDeEmpleados();
		
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
		
		colaEmpleadosLibres.agregarEmpleado(empleado);
		
		informarStoreDeColaDeEmpleados();
		
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
	
	private int retornarUltimoIdCliente() {
		
		if (clientes.isEmpty()) {
			System.out.println("Debido a que es el primer cliente se retornara la id 1" ); 
			return 1;
			
		}
		else {
		Integer ultimoKey = (((TreeMap<Integer, Cliente>) clientes).lastKey()+1);
		System.out.println("La funcion retornarUltimoIdCLiente retornara la id " + ultimoKey); 
		
		return ultimoKey;
		}}
	
	
	
	@Override
	public void registrarProyecto(String[] titulos, String[] descripcion, double[] dias, String domicilio,
			String[] cliente, String inicio, String fin) throws IllegalArgumentException {
		
		System.out.println("Ingreso al metodo RegistrarProyecto");
		
		Proyecto proyecto = new Proyecto(cliente,domicilio,titulos,descripcion, dias,inicio,fin);
		
		for(String c: cliente) {
		System.out.print(c + " ");}
		System.out.println(" ");
		
		int ultimaKeyCliente= retornarUltimoIdCliente(); 
		
		System.out.println("La id que se le asigna a cliente es " + ultimaKeyCliente);
	
		
		Cliente c = new Cliente (ultimaKeyCliente, cliente[0], cliente[1], cliente[2]); 
		
		clientes.put(ultimaKeyCliente, c);
		
		
		int ultimaKey= retornarUltimoIdProyecto();
		proyecto.asignarNroIdProyecto(ultimaKey);
		proyectos.put( ultimaKey, proyecto);
		
		for(Cliente listaClientes: clientes.values()) {
			
			System.out.println(listaClientes);
		}
		
		System.out.println("Finaliza el  metodo RegistrarProyecto");
	}

	
	private Proyecto obtenerProyectoPorId(int id) {
		
		Proyecto proyecto = proyectos.get(id);
		
		return proyecto;
	}
	
	private Map<Integer, Tarea> obtenerElMapDeTareasDeUnProyecto(Proyecto proyecto) {
		
		Map<Integer, Tarea>  tareas= proyecto.getTareas();
		
		return tareas;
	}
	
	
	private void cambiarEstadoDeEmpleadoLibreEnAsignado(Empleado empleado) {
		
		empleado.setEstado(true);
	}
	
	private void asignarResponsableEnTarea(Tarea tarea) {
		
		Empleado empleadoAAsignar= colaEmpleadosLibres.desagregarEmpleado();
		
		cambiarEstadoDeEmpleadoLibreEnAsignado(empleadoAAsignar);
		
		tarea.setResponsable(empleadoAAsignar);
	} 
	
	@Override
	public void asignarResponsableEnTarea(Integer numero, String titulo) throws Exception {
		
		//Recibo el Id del Proyecto y el titulo de la tarea
		System.out.println("Estoy en asignarResponsableEnTarea. Los parametros que recibo son " + numero + " " + titulo);
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		System.out.println("El proyecto que recibo es " + proyecto);
		
		Map<Integer, Tarea>  tareas= obtenerElMapDeTareasDeUnProyecto(proyecto);
		
		System.out.println("lareas que recibo son " + tareas);
		
		for (Tarea tarea: tareas.values()) {
			
			System.out.println("Recorro el foreach de tareas");
			
		if (tarea.getTitulo().compareTo(titulo)== 0) {
			asignarResponsableEnTarea(tarea);
			System.out.println("El empleado ha sido asignado  " + tarea);
		}}
		
		
		
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
		
		System.out.print("La lista Titulos es [ " );	
		for (String tarea: listaTitulos) {
			System.out.print(tarea + " ");
		} 
		System.out.println(" ]");
		System.out.println(" ");
		
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
