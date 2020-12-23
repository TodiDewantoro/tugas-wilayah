package com.wilayah.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wilayah.entity.ProvinsiEntity;

@Repository
public interface ProvinsiRepository extends JpaRepository<ProvinsiEntity, Integer> {
	
	ProvinsiEntity findByKodeProvinsi(String kodeProvinsi);
	
	@Query(value = "select * from provinsi_entity where id = ?", nativeQuery = true)
	List<ProvinsiEntity> findProvinsi(Integer id);
}
