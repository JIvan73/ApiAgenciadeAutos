package com.mx.ApiAgenciadeAutos.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiAgenciadeAutos.dao.MarcasDao;
import com.mx.ApiAgenciadeAutos.dominio.Marcas;

@Service
public class MarcasImp implements MarcasServ{

	
	@Autowired
	MarcasDao dao;
	
	@Transactional (readOnly = true)
	@Override
	public List<Marcas> listar() {
		// TODO Auto-generated method stub
		return (List<Marcas>) dao.findAll();
	}

	@Transactional
	@Override
	public String guardar(Marcas marca) {
		// TODO Auto-generated method stub
		String respuesta = "";
		boolean bandera = false;
		for (Marcas p: dao.findAll()) {
			
////////todos los tipos de datos primitivos y estan parseados  se compara con un equals(todas las cadenas)
			if(p.getId().equals(marca.getId())) {
				respuesta = "Existe Id";
				bandera = true;
				break;
			} else if (p.getNombre().equals(marca.getNombre())) {
				respuesta="Existe Nombre";
				bandera = true;
				break;
			}
		}
		if(bandera=false)
		dao.save(marca);
		return respuesta;
	}
		

	@Transactional
	@Override
	public Marcas buscar(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public boolean editar(Marcas marca) {
		// TODO Auto-generated method stub
		for(Marcas m:dao.findAll()) {
			if(m.getId().equals(marca.getId())) {
				dao.save(marca);
				return true;
			}
		}
		return false;
	}

	@Transactional
	@Override
	public boolean eliminar(Long id) {
		// TODO Auto-generated method stub
		for(Marcas m:dao.findAll()) {
			if(m.getNombre().equals(id)) {
				dao.deleteById(id);
				//dao.delete(m);
				return true;
			}
		}
		return false;
	}

}
