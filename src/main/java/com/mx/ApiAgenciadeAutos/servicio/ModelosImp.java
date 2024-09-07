package com.mx.ApiAgenciadeAutos.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiAgenciadeAutos.dao.MarcasDao;
import com.mx.ApiAgenciadeAutos.dao.ModelosDao;
import com.mx.ApiAgenciadeAutos.dominio.Marcas;
import com.mx.ApiAgenciadeAutos.dominio.Modelos;

@Service
public class ModelosImp implements ModelosServ{

	@Autowired
	ModelosDao modelodao;
	
	@Autowired
	MarcasDao marcadao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Modelos> listar() {
		// TODO Auto-generated method stub
		return (List<Modelos>) modelodao.findAll();
	}

	@Transactional
	@Override
	public String guardar(Modelos modelo) {
		// TODO Auto-generated method stub
		///Id y nombre que no se repitan
		///id marca existe
		String respuesta = "";
		boolean banderaMar = false;
		boolean banderaMod = false;
		
		///recorrer registros de la tabla marcas
		for(Marcas ma : marcadao.findAll()) {
			///iterar hasta que encuentre marca existe
			if(ma.getId().equals(modelo.getMarca().getId())) {
				banderaMar = true;
				
				//2do for Recorriendo en la tabla de modelos
				for(Modelos mo : modelodao.findAll()) {
					//Ese id existe
					if(mo.getId().equals(modelo.getId())) {
						respuesta = "Id Existe";
						banderaMod = true;
					} else if (mo.getNombre().equals(modelo.getNombre())) {
						respuesta = "existeNombre";
					}
				}
				break;
			}
		}
		
		if(banderaMar == false) {
			respuesta = "IdMarcaNoExiste";
			banderaMod = true;
		} else if (banderaMod == false) {
			modelodao.save(modelo);
		}
		return respuesta;
	}

	
	@Transactional
	@Override
	public Modelos buscar(Long id) {
		// TODO Auto-generated method stub
		return modelodao.findById(id).orElse(null);
	}

	
	
	@Transactional
	@Override
	public String editar(Modelos modelo) {
		// TODO Auto-generated method stub
		String respuesta = "";
		boolean banderaMar = false;
		boolean banderaMod = false;
		
		///for tabla marcas
		for (Marcas ma : marcadao.findAll()) {
			if(ma.getId().equals(modelo.getMarca().getId())) {
				banderaMar = true;
				
				//2do for Recorriendo en la tabla de modelos
				for(Modelos mo : modelodao.findAll()) {
					//id existe
					if(mo.getId().equals(modelo.getId())) {
						respuesta = "Id Existe";
						banderaMod = true;
					}
				}
				break;
			}
			
		}
		
		if(banderaMar == false) {
			respuesta = "IdMarcaNoExiste";
			banderaMod = true;
		} else if (banderaMod == false) {
			modelodao.save(modelo);
		}
		return respuesta;
	}

	
	@Transactional
	@Override
	public boolean eliminar(Long id) {
		// TODO Auto-generated method stub
		for(Modelos mo:modelodao.findAll()) {
			if(mo.getNombre().equals(id)) {
				//modelodao.deleteById(id);
				modelodao.delete(mo);
				return true;
			}
		}
		
		return false;
	}

	
	
}
