package com.wilayah.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wilayah.entity.KecamatanEntity;

@Repository
public interface KecamatanRepository extends JpaRepository<KecamatanEntity, Integer> {

	@Query(value = "select * from kecamatan_entity where id = ?", nativeQuery = true)
	List<KecamatanEntity> findKecamatan(Integer id);
}
