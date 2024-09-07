package com.mx.ApiAgenciadeAutos.contorlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiAgenciadeAutos.dao.MarcasDao;
import com.mx.ApiAgenciadeAutos.dominio.Marcas;
import com.mx.ApiAgenciadeAutos.dominio.Modelos;
import com.mx.ApiAgenciadeAutos.servicio.ModelosImp;

@RestController
@RequestMapping(path="ModelosWS")
@CrossOrigin


public class ModelosWS {
	
	@Autowired
	ModelosImp imp;	
	
	
	//http://localhost:9000/ModelosWS/listar
	@GetMapping("listar")
	public List<Modelos> listar(){
		return imp.listar();
	}
	
	
	//http://localhost:9000/ModelosWS/guardar
	@PostMapping("guardar")
	public ResponseEntity<String> guardar(@RequestBody Modelos modelo) {
		
		String responde = imp.guardar(modelo);
		
		if(responde.equals("IdExiste"))
			return new ResponseEntity<String>("No se puede, IdModelo en existencia", HttpStatus.OK);
		else if(responde.equals("NombreExiste"))
			return new ResponseEntity<String>("No se puede, nombre Existe", HttpStatus.OK);
		else if(responde.equals("IdMarcaExiste"))
			return new ResponseEntity<String>("No se puede, IdMarca NO Existe", HttpStatus.OK);
		else {
			return new ResponseEntity<String>("Aceptado", HttpStatus.OK);
		}
	}
	
	
	//http://localhost:9000/ModelosWS/buscar
	@PostMapping("buscar")
	public Modelos buscar(@RequestBody Modelos modelo) {
		return imp.buscar(modelo.getId());
		
	}
	
	
	//http://localhost:9000/ModelosWS/editar
	@PostMapping("editar")
	public ResponseEntity<String> editar (@RequestBody Modelos modelo) {
		
		String responde = imp.editar(modelo);
		if(responde.equals("IdExiste"))
			return new ResponseEntity<String>("Ese Registro no existe", HttpStatus.OK);
		else {
			return new ResponseEntity<String>("Perfecto", HttpStatus.CREATED);
		}
		
	}
	
	
	//http://localhost:9000/ModelosWS/eliminar
	@PostMapping("eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Modelos modelo){
		
		boolean respuesta = imp.eliminar(modelo.getId());
		if(respuesta == false)
			return new ResponseEntity<String>("Ese Registro no existe", HttpStatus.OK);
		else {
			return new ResponseEntity<String>("Eliminado", HttpStatus.OK);
		}
		
	}
	
	
	

}
