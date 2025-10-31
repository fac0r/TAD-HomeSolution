package entidades;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Proyecto {
	
	  	private int id;
	  


		private String [] solicitante;
	    private String direccion;
	    private Map<Integer, Tarea> tareas;
	    private String fechaInicio;
	    private String fechaFin;
	    private double [] duracion;
	    private boolean estado;
	    private boolean demorado;
	    
	    public Proyecto(String []cliente, String direccion, String [] listaTareas, String [] descripciones,  double [] duracion, String fechaInicio, String fechaFin) {
	        this.id = 0;
	        this.solicitante = cliente;
	        this.direccion = direccion;
	        this.tareas = new HashMap<>();
	        this.fechaInicio = fechaInicio;
	        this.fechaFin = fechaFin;
	        this.duracion= duracion;
	        this.setEstado(false); // false = en progreso, true = completado
	        this.demorado = false;
	        
	       
	        
	        // Convertir LinkedList a Map usando el ID de cada tarea
	        if (listaTareas != null) {
	        	int cantidad=1;
	        	int correspondencia=0;
	            for (String tarea : listaTareas) {
	            	Tarea t = new Tarea (tarea,descripciones[correspondencia],duracion[correspondencia] );
	                this.tareas.put(cantidad, t);
	                cantidad+=1;
	                correspondencia+=1;
	            }
	        }
	    }
	    
	    
	    
	    public Map<Integer, Tarea> retornarListaDeTareas () {
	    	
	    	return tareas;
	    }
	    
	    public void asignarNroIdProyecto (int nroId) {
	    	
	    	this.id = nroId;
	    }


		public boolean isEstado() {
			return estado;
		}


		public void setEstado(boolean estado) {
			this.estado = estado;
		}


		public int getId() {
			return id;
		}


		@Override
		public String toString() {
			return "Proyecto [id=" + id + ", solicitante=" + Arrays.toString(solicitante) + ", direccion=" + direccion
					+ ", tareas=" + tareas + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", duracion="
					+ Arrays.toString(duracion) + ", estado=" + estado + ", demorado=" + demorado + "]";
		}
		
	   
}
