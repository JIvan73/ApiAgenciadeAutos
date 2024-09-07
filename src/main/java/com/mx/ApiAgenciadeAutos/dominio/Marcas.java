package com.mx.ApiAgenciadeAutos.dominio;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARCAS")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Marcas {
	
	
	///MAPEO DE CAMPOS DE LA TABLA
	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NOMBRE")
	private String nombre;
	@Column(name = "ORIGEN")
	private String origen;
	@Column (name = "FECHA_CREACION")
	private Date fechaCreacion;
	
	
	///Mappedby  --  con esto realizamos la relacion de las entidades
    ////@OneToMany-----agregando cardinalidad de uno a muchos
	///Cardinalidad de 1 a muchos  Esterotipos o decoradores uno
	@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
	@JsonIgnore
	List<Modelos> lista = new ArrayList<>();
	
	
	
	
	

}
