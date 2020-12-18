package com.wilayah.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wilayah.entity.KabupatenEntity;

@Repository
public interface KabupatenRepository extends JpaRepository<KabupatenEntity, Integer>{
	
	KabupatenEntity findByKodeKabupaten(String kodeKabupaten);
	
	@Query(value = "select * from kabupaten_entity where id = ?", nativeQuery = true)
	List<KabupatenEntity> findKabupaten(Integer id);
}
