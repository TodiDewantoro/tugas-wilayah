package com.wilayah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilayah.dto.KabupatenDto;
import com.wilayah.entity.KabupatenEntity;
import com.wilayah.entity.ProvinsiEntity;
import com.wilayah.repository.KabupatenRepository;
import com.wilayah.repository.ProvinsiRepository;

@Service
@Transactional
public class KabupatenServiceImpl implements KabupatenService{

	@Autowired
	private KabupatenRepository kabupatenRepository;
	
	@Autowired
	private ProvinsiRepository provinsiRepository;

	@Override
	public KabupatenEntity post(KabupatenDto dto) {
		KabupatenEntity kabupatenEntity = new KabupatenEntity();
		ProvinsiEntity provinsiEntity = provinsiRepository.findByKodeProvinsi(dto.getKodeProvinsi());
		//set provinsiId
		kabupatenEntity.setProvinsiEntity(provinsiEntity);
		kabupatenEntity.setNamaKabupaten(dto.getNamaKabupaten());
		kabupatenEntity.setKodeKabupaten(dto.getKodeKabupaten());
		kabupatenRepository.save(kabupatenEntity);
		return kabupatenEntity;
	}
	
	@Override
	public KabupatenEntity postKabProv(KabupatenDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KabupatenEntity put(Integer idKabupaten, KabupatenDto dto) {
		KabupatenEntity kabupatenEntity = kabupatenRepository.findById(idKabupaten).get();
		kabupatenEntity.setNamaKabupaten(dto.getNamaKabupaten());
		kabupatenEntity.setKodeKabupaten(dto.getKodeKabupaten());
		kabupatenRepository.save(kabupatenEntity);
		return kabupatenEntity;
	}

	@Override
	public KabupatenEntity delete(Integer idKabupaten) {
		KabupatenEntity kabupatenEntity = kabupatenRepository.findById(idKabupaten).get();
		kabupatenRepository.delete(kabupatenEntity);
		return kabupatenEntity;
	}

	
}
