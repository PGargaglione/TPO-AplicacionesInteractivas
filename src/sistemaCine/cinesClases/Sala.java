package sistemaCine.cinesClases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistemaCine.utils.FilaColumna;

public class Sala {
	private String nombre;
	private List<Funcion> funciones;
	private Map<FilaColumna, AsinentoFisico> mapaDeAsientos;

	public Sala(String nombre) {
		super();
		this.nombre = nombre;
		this.funciones = new ArrayList<>();
		this.mapaDeAsientos = new HashMap<>();

	}

	public boolean sosSala(String nombre) {
		return this.nombre == nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Funcion> getFunciones() {
		return funciones;
	}

	public Map<FilaColumna, AsinentoFisico> getMapaDeAsientos() {
		return mapaDeAsientos;
	}

	public boolean addAsiento(AsinentoFisico asiento) {
		return null == this.mapaDeAsientos.put(new FilaColumna(asiento.getFila(), asiento.getColumna()), asiento);
	}

	public boolean setAsientoRoto(AsinentoFisico asiento, boolean roto) {
		asiento = this.mapaDeAsientos.get(new FilaColumna(asiento.getFila(), asiento.getColumna()));
		if (asiento != null) {
			asiento.setUsable(!roto);
			return true;
		}
		return roto;
	}

	public void addFuncion(Funcion funcion) {
		if (!funciones.isEmpty()) {
			if (funciones.get(funciones.size()-1).getFechaYHora().before(funcion.getFechaYHora())) {
				funciones.add(getPosicion(funcion, funciones.size() / 2, funciones.size()), funcion);
			}else {
				funciones.add(funcion);
			}
		}else {
			funciones.add(funcion);
		}
	}

	private int getPosicion(Funcion funcion, int pivot, int size) {
		if (funciones.get(pivot) == null || size < 1) {
			return pivot;
		} else {
			if (funcion.getFechaYHora().before(funciones.get(pivot).getFechaYHora())) {
				return getPosicion(funcion, pivot - size / 4, size / 2);
			} else {
				return getPosicion(funcion, pivot + size/ 4, size / 2);
			}
		}
	}

}
