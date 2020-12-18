package com.wilayah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilayah.dto.KecamatanDto;
import com.wilayah.dto.StatusMessageDto;
import com.wilayah.entity.KabupatenEntity;
import com.wilayah.entity.KecamatanEntity;
import com.wilayah.entity.ProvinsiEntity;
import com.wilayah.repository.KabupatenRepository;
import com.wilayah.repository.KecamatanRepository;
import com.wilayah.repository.ProvinsiRepository;

@RestController
@RequestMapping("kecamatan")
public class KecamatanController {
	
	@Autowired
	private KecamatanRepository kecamatanRepository;

	@Autowired
	private KabupatenRepository kabupatenRepository;
	
	@Autowired
	private ProvinsiRepository provinsiRepository;
	
	//post kec berdasarkan id kab yg sudah ada
		@PostMapping("/post")
		public ResponseEntity<?> postKec(@RequestBody KecamatanDto dto){
			if (dto.getKodeKecamatan() == null) {
				StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				result.setMessage("Kode kecamatan tidak boleh kosong!");
				result.setData(null);
				return ResponseEntity.badRequest().body(result);
			} else {
				KecamatanEntity kecamatanEntity = new KecamatanEntity();
				KabupatenEntity kabupatenEntity = kabupatenRepository.findByKodeKabupaten(dto.getKodeKabupaten());
				//set provinsiId
				kecamatanEntity.setKabupatenEntity(kabupatenEntity);
				kecamatanEntity.setNamaKecamatan(dto.getNamaKecamatan());
				kecamatanEntity.setKodeKecamatan(dto.getKodeKecamatan());
				kecamatanRepository.save(kecamatanEntity);
				
				StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
				result.setStatus(HttpStatus.OK.value());
				result.setMessage("Success");
				result.setData(kabupatenEntity);
				return ResponseEntity.ok(result);
			}
		}
		
		//post kec, kab dan prov yg belum ada tiga-tiganya
		@PostMapping("/post-kec-kab-prov")
		public ResponseEntity<?> postKecKabProv(@RequestBody KecamatanDto dto){
			
			if (dto.getKodeKecamatan() == "") {
				StatusMessageDto<KecamatanEntity> result = new StatusMessageDto<>();
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				result.setMessage("Kode kecamatan tidak boleh kosong!");
				result.setData(null);
				return ResponseEntity.badRequest().body(result);
			} 
			else if(dto.getKodeKabupaten() == ""){
				StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				result.setMessage("Kode kabupaten tidak boleh kosong!");
				result.setData(null);
				return ResponseEntity.badRequest().body(result);
			} 
			else if(dto.getKodeProvinsi() == ""){
				StatusMessageDto<ProvinsiEntity> result = new StatusMessageDto<>();
				result.setStatus(HttpStatus.BAD_REQUEST.value());
				result.setMessage("Kode provinsi tidak boleh kosong!");
				result.setData(null);
				return ResponseEntity.badRequest().body(result);
			} 
			else {
				ProvinsiEntity provinsiEntity = new ProvinsiEntity();
				provinsiEntity.setNamaProvinsi(dto.getNamaProvinsi());
				provinsiEntity.setKodeProvinsi(dto.getKodeProvinsi());
				provinsiRepository.save(provinsiEntity);
				
				KabupatenEntity kabupatenEntity = new KabupatenEntity();
				kabupatenEntity.setNamaKabupaten(dto.getNamaKabupaten());
				kabupatenEntity.setKodeKabupaten(dto.getKodeKabupaten());
				kabupatenEntity.setProvinsiEntity(provinsiEntity);
				kabupatenRepository.save(kabupatenEntity);
				
				KecamatanEntity kecamatanEntity = new KecamatanEntity();
				kecamatanEntity.setNamaKecamatan(dto.getNamaKecamatan());
				kecamatanEntity.setKodeKecamatan(dto.getKodeKecamatan());
				kecamatanEntity.setKabupatenEntity(kabupatenEntity);
				kecamatanEntity.setKabupatenEntity(kabupatenEntity);
				kecamatanRepository.save(kecamatanEntity);
				
				StatusMessageDto<KecamatanEntity> result = new StatusMessageDto<>();
				result.setStatus(HttpStatus.OK.value());
				result.setMessage("Success");
				result.setData(kecamatanEntity);
				return ResponseEntity.ok(result);
			}
		}
}
