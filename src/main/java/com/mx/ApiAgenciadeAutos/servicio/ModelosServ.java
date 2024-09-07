package com.mx.ApiAgenciadeAutos.servicio;

import java.util.List;

import com.mx.ApiAgenciadeAutos.dominio.Modelos;

public interface ModelosServ {
	
	public List<Modelos> listar();
	
	public String guardar(Modelos modelo);  ///validar que el id y nombre no se repitan -idmarcaexiste
	
	public Modelos buscar(Long id);
	
	public String editar (Modelos modelo);  //validar que el idmodelo existe y id marca
	
	public boolean eliminar (Long id); //validar que el id existe

}
