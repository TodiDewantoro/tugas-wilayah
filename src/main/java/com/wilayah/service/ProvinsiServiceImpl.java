package com.wilayah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilayah.dto.ProvinsiDto;
import com.wilayah.entity.ProvinsiEntity;
import com.wilayah.repository.ProvinsiRepository;

@Service
@Transactional
public class ProvinsiServiceImpl implements ProvinsiService {
	
	@Autowired
	private ProvinsiRepository provinsiRepository;

	@Override
	public ProvinsiEntity post(ProvinsiDto dto) {
		ProvinsiEntity provinsiEntity = new ProvinsiEntity();
		provinsiEntity.setNamaProvinsi(dto.getNamaProvinsi());
		provinsiEntity.setKodeProvinsi(dto.getKodeProvinsi());
		provinsiRepository.save(provinsiEntity);
		return provinsiEntity;
	}

	@Override
	public ProvinsiEntity put( Integer idProvinsi, ProvinsiDto dto) {
		ProvinsiEntity provinsiEntity = provinsiRepository.findById(idProvinsi).get();
		provinsiEntity.setNamaProvinsi(dto.getNamaProvinsi());
		provinsiEntity.setKodeProvinsi(dto.getKodeProvinsi());
		provinsiRepository.save(provinsiEntity);
		return provinsiEntity;
	}

	@Override
	public ProvinsiEntity delete(Integer idProvinsi) {
		ProvinsiEntity provinsiEntity = provinsiRepository.findById(idProvinsi).get();
		provinsiRepository.delete(provinsiEntity);
		return provinsiEntity;
	}
}
