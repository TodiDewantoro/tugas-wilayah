package com.wilayah.service;

import com.wilayah.dto.KabupatenDto;
import com.wilayah.entity.KabupatenEntity;

public interface KabupatenService {

	KabupatenEntity post(KabupatenDto dto);
	
	KabupatenEntity postKabProv(KabupatenDto dto);
	
	KabupatenEntity put( Integer idKabupaten, KabupatenDto dto);
	
	KabupatenEntity delete( Integer idKabupaten);
}
