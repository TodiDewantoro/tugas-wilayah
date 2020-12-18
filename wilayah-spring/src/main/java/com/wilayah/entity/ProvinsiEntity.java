package com.wilayah.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "provinsi_entity")
public class ProvinsiEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nama_provinsi",length = 50, nullable = false)
	private String namaProvinsi;
	
	@Column(name = "kode_provinsi",length = 50, nullable = false, unique = true)
	private String kodeProvinsi;
	
}
