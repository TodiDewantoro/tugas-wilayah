package com.wilayah.service;

import com.wilayah.dto.ProvinsiDto;
import com.wilayah.entity.ProvinsiEntity;

public interface ProvinsiService {

	ProvinsiEntity post(ProvinsiDto dto);
	
	ProvinsiEntity put( Integer idProvinsi, ProvinsiDto dto);
	
	ProvinsiEntity delete( Integer idProvinsi);
}
