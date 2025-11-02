package entidades;

import java.util.ArrayList;
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
		
		if (valor < 0) {
	        throw new IllegalArgumentException("El valor no puede ser negativo");
	    }
		
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

		if (!categoria.equals("INICIAL") && !categoria.equals("INTERMEDIO") && !categoria.equals("EXPERTO")) {
	        throw new IllegalArgumentException("Categoría inválida. Debe ser: INICIAL, INTERMEDIO o EXPERTO");
	    }
	    
	    if (valor < 0) {
	        throw new IllegalArgumentException("El valor no puede ser negativo");
	    }
		
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
		
		if (fin.compareTo(inicio) < 0) {
	        throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la de inicio");
	    }
		
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
	
	
	private void cambiarEstadoDeEmpleadoAsignadoEnLibre(Empleado empleado) {
		
		empleado.setEstado(false);
	}
	
	private void actualizarCostoProyecto(Integer numero) { 
		costoProyecto( numero);}
	
	
	
	
	private void asignarResponsableEnTarea(Tarea tarea) {
		
		Empleado empleadoAAsignar= colaEmpleadosLibres.desagregarEmpleado();
		
		cambiarEstadoDeEmpleadoLibreEnAsignado(empleadoAAsignar);
		
		tarea.setResponsable(empleadoAAsignar);
		
		
	} 
	
	
	
	
	private boolean todasLasTareasAsignadas(Proyecto proyecto) {
	    for (Tarea tarea : proyecto.retornarListaDeTareas().values()) {
	        if (tarea.getResponsable() == null) {
	            return false;
	        }
	    }
	    return true;
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
			actualizarCostoProyecto(numero);
			
			if (todasLasTareasAsignadas(proyecto)) {
                proyecto.setEstado(Estado.activo);
            }
		}}
		
		System.out.println("El proyecto ha quedado asi: "+ proyecto);
		System.out.println("Las tareas han quedado asi: " + proyecto.retornarListaDeTareas() );
		
		
		
	}

	private void restaurarColaEmpeladosLibres(ColaEmpleadosLibres original, ColaEmpleadosLibres backUp) {
		
		while (!backUp.estaVacia()) {
			Empleado actual =backUp.desagregarEmpleado();
			original.agregarEmpleado(actual);
			
		}
		
		System.out.println("Luego de restaurarColaEmpleadosLibres debe quedar con un empleado menos del que tenia , ahora tiene : " + original.informarCantidadDeEmpleadosEnCola());
		
	}
	
	private void generarCorrespondenciaEnColaEmpleados(Empleado empleado) {
		
		ColaEmpleadosLibres colaTemporal = new ColaEmpleadosLibres();
		boolean encontrado = false;
	    
	    while (!colaEmpleadosLibres.estaVacia()) {
	        Empleado actual = colaEmpleadosLibres.desagregarEmpleado();
	        if (actual.equals(empleado) && !encontrado) {
	            encontrado = true;  
	             
	        } else {
	         
	            colaTemporal.agregarEmpleado(actual);
	        }
	    }
	    
	    System.out.println("Antes de restaurar la colaEmpleados Libre queda vacia " + colaEmpleadosLibres.estaVacia());
	    
	restaurarColaEmpeladosLibres(colaEmpleadosLibres, colaTemporal);
		
	}
	
	
	
	private void asignarResponsableMenosRetraso(Tarea tarea) {
		
		
		int cantidadDeRetrasos =100;
		 
		 Empleado empleadoAAsignar = null;
		
		for(Empleado empleado: listaEmpleados) {
			 
			
			if ((empleado.getDemorasInformadas() < cantidadDeRetrasos) && (!empleado.isEstado())) {
				cantidadDeRetrasos =empleado.getDemorasInformadas();
				empleadoAAsignar = empleado;
					 
				}
			}
		if (empleadoAAsignar != null)  {
			cambiarEstadoDeEmpleadoLibreEnAsignado(empleadoAAsignar);
	        tarea.setResponsable(empleadoAAsignar);
	        }
		
		generarCorrespondenciaEnColaEmpleados(empleadoAAsignar);
		
		
		}
		
		 
	
	
	@Override
	public void asignarResponsableMenosRetraso(Integer numero, String titulo) throws Exception {
		
		//Recibo el Id del Proyecto y el titulo de la tarea
		System.out.println("Estoy en asignarResponsableEnTarea. Los parametros que recibo son " + numero + " " + titulo);
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		System.out.println("El proyecto que recibo es " + proyecto);
		
		Map<Integer, Tarea>  tareas= obtenerElMapDeTareasDeUnProyecto(proyecto);
		
		System.out.println("lareas que recibo son " + tareas);
		
		for (Tarea tarea: tareas.values()) {
			
			System.out.println("Recorro el foreach de tareas");
			
		if (tarea.getTitulo().compareTo(titulo)== 0) {
			
			if (tarea.getResponsable()!=null ){
				recolocarEnColaEmpleadoQueSeraSuplantado(tarea);
			};
			
			asignarResponsableMenosRetraso(tarea);
			System.out.println("El empleado ha sido asignado  " + tarea);
			actualizarCostoProyecto(numero);
		}}
		
	}


	@Override
	public void registrarRetrasoEnTarea(Integer numero, String titulo, double cantidadDias)
			throws IllegalArgumentException {
		
		System.out.println("Estoy en registrarRetrasoEnTarea que tiene las variable numero id de proyecto  " + numero +" y titulo " +  titulo + "cantidad de dia " +cantidadDias);
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		Map<Integer, Tarea>  tareas= obtenerElMapDeTareasDeUnProyecto( proyecto);
		
		for (Tarea tarea: tareas.values()) {
			
			System.out.println("Recorro el foreach de tareas");
			
		if (tarea.getTitulo().compareTo(titulo)== 0) {
			
			System.out.println("La duracion de la tarea es " + tarea.getDuracion());
			tarea.setDuracion(tarea.getDuracion()+ cantidadDias);
			tarea.setRegistraRetraso(true);
			System.out.println("La tarea " + tarea + "ha sido actualizada con la candidad de dias " + tarea.getDuracion());
			
			
			Empleado empleado = tarea.getResponsable();
			
			
			if (empleado instanceof EmpleadoPlanta) {
                ((EmpleadoPlanta) empleado).setRegistraRetrasoEnProyectoActual(true);
            }
                empleado.informarDemora();
                System.out.println("Ahora la cantidad de demoras del Empleado es: " + empleado);
            
			    }
			}
	
		actualizarCostoProyecto(numero);
	}


	@Override
	public void agregarTareaEnProyecto(Integer numero, String titulo, String descripcion, double dias)
			throws IllegalArgumentException {
		 
		Proyecto proyecto = obtenerProyectoPorId(numero);
		proyecto.agregarTarea(titulo, descripcion, dias);
		
	}


	@Override
	public void finalizarTarea(Integer numero, String titulo) throws Exception {
		

		System.out.println("Estoy en finalizar tarea que tiene las variable numero id de proyecto  " + numero +" y titulo " +  titulo);
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		Map<Integer, Tarea>  tareas= obtenerElMapDeTareasDeUnProyecto( proyecto);
		
		for (Tarea tarea: tareas.values()) {
			
			System.out.println("Recorro el foreach de tareas");
			
		if (tarea.getTitulo().compareTo(titulo)== 0) {
			tarea.setEstado(Estado.finalizado);
			System.out.println("La tarea " + tarea + "ha sido actualizada como " + tarea.isEstado());
		}}
		
	}


	@Override
	public void finalizarProyecto(Integer numero, String fin) throws IllegalArgumentException {
		
		 System.out.println(" Estoy en finalizarProyecto y recibo los parametros numero " + numero + " y fin : " + fin);
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		
		
		if (fin.compareTo(proyecto.getFechaInicio()) < 0) {
	        throw new IllegalArgumentException("La fecha de finalización no puede ser anterior a la fecha de inicio");
	    }
		
		 String fechaFinOriginal = proyecto.getFechaFin();
		    boolean proyectoTerminoTarde = fin.compareTo(fechaFinOriginal) > 0;
		
		proyecto.setFechaFin(fin);
		proyecto.setEstado(Estado.finalizado);
		
		  if (proyectoTerminoTarde) {
		        proyecto.setDemorado(true);
		    }

		
		System.out.println(" ANTES de llamar a costoProyecto()");
		costoProyecto(numero);
		 System.out.println(" DESPUÉS de llamar a costoProyecto()");
		 
		//vuelve a agregar los empleados a la colaEmpleadosLibres
		Map<Integer, Tarea> tareas = proyecto.retornarListaDeTareas();
	    for (Tarea tarea : tareas.values()) {
	        Empleado empleado = tarea.getResponsable();
	        if (empleado != null) {
	        	if (empleado instanceof EmpleadoPlanta) {
	                ((EmpleadoPlanta) empleado).setRegistraRetrasoEnProyectoActual(false);
	            }
	        	
	            cambiarEstadoDeEmpleadoAsignadoEnLibre(empleado);
	            colaEmpleadosLibres.agregarEmpleado(empleado);
	        }
	    }
	    System.out.println(" FIN de finalizarProyecto()");
	}


	private Empleado encontrarEmpleadoPorLegajo(Integer legajo) {
		
		for(Empleado empleado: listaEmpleados) {
			 
			
			if (empleado.getN_legajo() == legajo && empleado.isEstado()==false) {
				
				return empleado;
					 
				}
			}
		return null;
	} 
	
	
	
	
	private void recolocarEnColaEmpleadoQueSeraSuplantado(Tarea tarea) {
		
		 
		
		Empleado empleadoQueSeSuplantara = tarea.getResponsable();
		cambiarEstadoDeEmpleadoAsignadoEnLibre(empleadoQueSeSuplantara);
		
		
		colaEmpleadosLibres.agregarEmpleado(empleadoQueSeSuplantara);
	}
	
	@Override
	public void reasignarEmpleadoEnProyecto(Integer numero, Integer legajo, String titulo) throws Exception {
			
		System.out.println("Estoy en reasignarEmpleadoEnproyecto los parametros que recibo son: "+ numero +" " +   legajo + " "  +titulo );
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		Empleado empleadoAAsignar = encontrarEmpleadoPorLegajo(legajo);
		
		 Map<Integer, Tarea> tareas = proyecto.retornarListaDeTareas();
		
		 for (Tarea tarea: tareas.values()) {
				
				System.out.println("Recorro el foreach de tareas");
				
			if (tarea.getTitulo().compareTo(titulo)== 0) { 
					
					System.out.println("Se encontro al empleado");
					
					
					//restarCostoDelEmpleadoSuplantado(numero, tarea); 
					
					recolocarEnColaEmpleadoQueSeraSuplantado(tarea);
					
					
					tarea.setResponsable(empleadoAAsignar);
					actualizarCostoProyecto(numero);
					System.out.println("Se ha asignado al empleado");}
					
			}
		 
		 cambiarEstadoDeEmpleadoLibreEnAsignado(empleadoAAsignar);
		 
		generarCorrespondenciaEnColaEmpleados(empleadoAAsignar);
		
		
		
	}


	@Override
	public void reasignarEmpleadoConMenosRetraso(Integer numero, String titulo) throws Exception {
		
	 
		
		
		asignarResponsableMenosRetraso(numero, titulo);
	}

	
	
	private double costoSegunSalarioEmpleado(Empleado empleado, int cantidadHoras) {
		
		double costo;
		costo= cantidadHoras * empleado.getValor();
		
		return costo;
		
		
	}
	
	private boolean laTareaTieneRetrasos(Tarea tarea) {
		
		return tarea.isRegistraRetraso();
		
	}

	@Override
	public double costoProyecto(Integer numero) {
		
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		System.out.println("El costo actual del proyecto antes de recalcular es " + proyecto.getCostoProyecto());
		
		 double costo = 0;
		 boolean proyectoConRetraso = false;
		
		
		
		Map<Integer, Tarea> tareas= proyecto.retornarListaDeTareas();
		
		for (Tarea tarea: tareas.values()) {
			proyectoConRetraso= laTareaTieneRetrasos(tarea) || proyectoConRetraso;
			
			Empleado empleado = tarea.getResponsable();
			
			 if (empleado != null) {
			
			int cantidadHoras=0;
			if (empleado instanceof EmpleadoPlanta)
				{
			int cantidadDias =tarea.cantidadDiasTareaPlanta();
			costo+= costoSegunSalarioEmpleado(empleado, cantidadDias);}
			else if (empleado instanceof EmpleadoContratado) {
				cantidadHoras =tarea.cantidadHorasTareaContratado();
				costo += costoSegunSalarioEmpleado(empleado,cantidadHoras);
			}
				
		}}
		
		 if (proyectoConRetraso || proyecto.isDemorado()) {
		        costo = (costo * 1.25);
		    } else {
		        costo = (costo * 1.35);
		    }
		
		proyecto.setCostoProyecto(costo);
		
		System.out.println("El costo actual del proyecto despues de recalcular es " + proyecto.getCostoProyecto());
		return costo;
	}

	
	
	/*private void restarCostoDelEmpleadoSuplantado (Integer numero, Tarea tarea) {
		//cada vez que se reasigna un nuevo empleado hay que recalcular el costo del proyecto
		
		double costoEmpleadoAsuplantar;
		int cantidadHoras=0;
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		Empleado empleado = tarea.getResponsable();
		if (empleado instanceof EmpleadoPlanta)
			{
		cantidadHoras =tarea.cantidadDiasTareaPlanta();}
		else if (empleado instanceof EmpleadoContratado) {
			cantidadHoras =tarea.cantidadHorasTareaContratado();
		}
		
		costoEmpleadoAsuplantar= costoSegunSalarioEmpleado(empleado,cantidadHoras);
		
		proyecto.setCostoProyecto(proyecto.getCostoProyecto() - costoEmpleadoAsuplantar);
		
		
	}*/
		
		
	
	
	
	
	
	
	
	
	@Override
	public List<Tupla<Integer, String>> proyectosFinalizados() {
		
		LinkedList<Tupla<Integer, String>> proyectosFinalizados = new LinkedList<>();
		
		for (Proyecto proyecto : proyectos.values()) {
			if(proyecto.isEstado() == Estado.finalizado) {
				
				Tupla<Integer, String> p = new Tupla<>(proyecto.getId(), proyecto.toString());
				proyectosFinalizados.add(p);
				
				System.out.println(proyecto);
			}
			
		}
		return proyectosFinalizados;
		
	}


	@Override
	public List<Tupla<Integer, String>> proyectosPendientes() {

		LinkedList<Tupla<Integer, String>> proyectosPendientes = new LinkedList<>();
		
		for (Proyecto proyecto : proyectos.values()) {
			if(proyecto.isEstado() == Estado.pendiente) {
				
				Tupla<Integer, String> p = new Tupla<>(proyecto.getId(), proyecto.toString());
				proyectosPendientes.add(p);
				
				System.out.println(proyecto);
			}
			
		}
		return proyectosPendientes;
	}


	@Override
	public List<Tupla<Integer, String>> proyectosActivos() {
		
		LinkedList<Tupla<Integer, String>> proyectosActivos = new LinkedList<>();
		
		for (Proyecto proyecto : proyectos.values()) {
			if(proyecto.isEstado() == Estado.activo) {
				
				Tupla<Integer, String> p = new Tupla<>(proyecto.getId(), proyecto.toString());
				proyectosActivos.add(p);
				
				System.out.println(proyecto);
			}
			
		}
		return proyectosActivos;
	}


	@Override
	public Object[] empleadosNoAsignados() {
	    ArrayList<String> legajos = new ArrayList<>();
	    
	    for (Empleado empleado : listaEmpleados) {
	        if (!empleado.isEstado()) {
	            // Agregar solo el NÚMERO como String
	            legajos.add(String.valueOf(empleado.getN_legajo()));
	        }
	    }
	    
	    // Convertir ArrayList a array
	    String[] resultado = new String[legajos.size()];
	    for (int i = 0; i < legajos.size(); i++) {
	        resultado[i] = legajos.get(i);
	    }
	    
	    return resultado;
	}


	@Override
	public boolean estaFinalizado(Integer numero) {
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		if(proyecto.isEstado() == Estado.finalizado) {
		
		return true ;}
		
		return false;
	}


	@Override
	public int consultarCantidadRetrasosEmpleado(Integer legajo) {
		
		 for (Empleado empleado: listaEmpleados) {
			 if(empleado.getN_legajo()==legajo) {
				 return empleado.getDemorasInformadas();
			 }
		 }

		return 0;
	}


	@Override
	public List<Tupla<Integer, String>> empleadosAsignadosAProyecto(Integer numero) {
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		Map<Integer, Tarea> tareas = proyecto.retornarListaDeTareas();
		
		List<Tupla<Integer, String>> empleadosAsignados = new ArrayList<>();
		

		
		for (Tarea tarea: tareas.values()) {
			
			
			Empleado empleado= tarea.getResponsable();
			if (empleado !=null) {
			Tupla<Integer, String> tuplaEmpleado = new Tupla<>( empleado.getN_legajo(),  empleado.getNombre() );
			empleadosAsignados.add(tuplaEmpleado);}
		}
		
		
		return empleadosAsignados;
	}


	@Override
	public Object[] tareasProyectoNoAsignadas(Integer numero) {
		  Proyecto proyecto = obtenerProyectoPorId(numero);
		    
		  if (proyecto == null) {
		        throw new IllegalArgumentException("Proyecto no existe");
		    }
		  
		  if (proyecto.isEstado().equals(Estado.finalizado)) {
		        throw new IllegalArgumentException("No se puede consultar tareas de un proyecto finalizado");
		    }
		    
		    Map<Integer, Tarea> tareas = proyecto.retornarListaDeTareas();
		    List<String> tareasNoAsignadas = new ArrayList<>();
		    
		    for (Tarea tarea : tareas.values()) {
		        if (tarea.getResponsable() == null) {
		            tareasNoAsignadas.add(tarea.getTitulo());
		        }
		    }
		    
		    return tareasNoAsignadas.toArray(new String[0]);
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
		
		Proyecto proyecto = obtenerProyectoPorId(numero);
		
		String domicilio = proyecto.getDireccion();
		
		
		return domicilio;
	}


	@Override
	public boolean tieneRestrasos(Integer legajo) {
		
		for (Empleado empleado : listaEmpleados) {
			if (empleado.getN_legajo() == legajo) {
				return (empleado.getDemorasInformadas()>0);
				
			}
			
		}
		
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
