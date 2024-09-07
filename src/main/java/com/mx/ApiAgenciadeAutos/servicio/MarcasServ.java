package com.mx.ApiAgenciadeAutos.servicio;

import java.util.List;

import com.mx.ApiAgenciadeAutos.dominio.Marcas;

public interface MarcasServ {
	
	public List<Marcas> listar();
	
	public String guardar (Marcas marca);   //validar que el id y nombre no se repita
	
	public Marcas buscar(Long id);
	
	public boolean editar(Marcas marca);  /// validar que el id exista
	
	public boolean eliminar (Long id);  ///validar que el Id exista
	
	

}
