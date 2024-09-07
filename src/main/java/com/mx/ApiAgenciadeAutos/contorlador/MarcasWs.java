package com.mx.ApiAgenciadeAutos.contorlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiAgenciadeAutos.dominio.Marcas;
import com.mx.ApiAgenciadeAutos.servicio.MarcasImp;

@RestController
@RequestMapping(path = "MarcasWs")
@CrossOrigin

public class MarcasWs {
	
	///Iny de Dependencia
	@Autowired
	MarcasImp imp;
	
	
	///http://localhost:9000/MarcasWS/listar
	@GetMapping(path = "listar")
	public List<Marcas> listarRegistros(){
		return imp.listar();
	}
	
	
	///http://localhost:9000/MarcasWS/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca){
		String respuesta = imp.guardar(marca);
		
		if(respuesta.equals("Existe Id"))
			return new ResponseEntity<String>("Esa clave unica ya existe", HttpStatus.OK);
		else if(respuesta.equals("Existe Nombre"))
			return new ResponseEntity<String>("El nombre ya existe", HttpStatus.OK);
		else {
			return new ResponseEntity<>(marca, HttpStatus.CREATED);
		}
	}
	
	///http://localhost:9000/MarcasWS/buscar
	@PostMapping(path = "buscar")
	public Marcas buscar(@RequestBody Marcas marca) {
		return imp.buscar(marca.getId());
		
	}
	

	///http://localhost:9000/MarcasWS/editar
	@PostMapping(path = "editar")
	public ResponseEntity<?> editar (@RequestBody Marcas marca){
		
		boolean respuesta = imp.editar(marca);
		if(respuesta == false)
			return new ResponseEntity<String>("Ese Registro no existe", HttpStatus.OK);
		else {
			return new ResponseEntity<>(marca, HttpStatus.CREATED);
		}
		
	}
	
	
	///http://localhost:9000/MarcasWS/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Marcas marca){
		
		boolean respuesta = imp.eliminar(marca.getId());
		if(respuesta == false)
			return new ResponseEntity<String>("Ese Registro no existe", HttpStatus.OK);
		else {
			return new ResponseEntity<String>("Eliminado", HttpStatus.OK);
		}
		
	}
	
	
	
	
}
