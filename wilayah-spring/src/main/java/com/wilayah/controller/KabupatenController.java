package com.wilayah.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilayah.dto.KabupatenDto;
import com.wilayah.dto.StatusMessageDto;
import com.wilayah.entity.KabupatenEntity;
import com.wilayah.entity.ProvinsiEntity;
import com.wilayah.repository.KabupatenRepository;
import com.wilayah.repository.ProvinsiRepository;

@RestController
@RequestMapping("kabupaten")
public class KabupatenController {

	@Autowired
	private KabupatenRepository kabupatenRepository;
	
	@Autowired
	private ProvinsiRepository provinsiRepository;
	
	//post kab berdasarkan id prov yg sudah ada
	@PostMapping("/post")
	public ResponseEntity<?> postKab(@RequestBody KabupatenDto dto){
		if (dto.getKodeKabupaten() == null) {
			StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			result.setMessage("Kode kabupaten tidak boleh kosong!");
			result.setData(null);
			return ResponseEntity.badRequest().body(result);
		} else {
			KabupatenEntity kabupatenEntity = new KabupatenEntity();
			ProvinsiEntity provinsiEntity = provinsiRepository.findByKodeProvinsi(dto.getKodeProvinsi());
			//set provinsiId
			kabupatenEntity.setProvinsiEntity(provinsiEntity);
			kabupatenEntity.setNamaKabupaten(dto.getNamaKabupaten());
			kabupatenEntity.setKodeKabupaten(dto.getKodeKabupaten());
			kabupatenRepository.save(kabupatenEntity);
			
			StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
			result.setStatus(HttpStatus.OK.value());
			result.setMessage("Success");
			result.setData(kabupatenEntity);
			return ResponseEntity.ok(result);
		}
	}
	
	//post kab dan prov yg belum ada dua-duanya
	@PostMapping("/post-kab-prov")
	public ResponseEntity<?> postKabProv(@RequestBody KabupatenDto dto){
		
		if (dto.getKodeKabupaten() == "") {
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
			
			StatusMessageDto<KabupatenEntity> result = new StatusMessageDto<>();
			result.setStatus(HttpStatus.OK.value());
			result.setMessage("Success");
			result.setData(kabupatenEntity);
			return ResponseEntity.ok(result);
		}
	}
	
	
	
	//KENA SERIALIZABLE ERROR
	
	@GetMapping("/get-all")
	public ResponseEntity<?> getAllKabupaten(){
		List<KabupatenEntity> kabupatenEntities = kabupatenRepository.findAll();
		return ResponseEntity.ok(kabupatenEntities);
	}
	
	@GetMapping("/get-by-id/{idKabupaten}")
	public ResponseEntity<?> getKabById(@PathVariable Integer idKabupaten){
		List<KabupatenEntity> kabupatenEntities = kabupatenRepository.findKabupaten(idKabupaten);
		return ResponseEntity.ok(kabupatenEntities);
	}
	
	@PutMapping("/update/{idKabupaten}")
	public ResponseEntity<?> update(@PathVariable Integer idKabupaten, @RequestBody KabupatenDto dto){
		KabupatenEntity kabupatenEntity = kabupatenRepository.findById(idKabupaten).get();
		kabupatenEntity.setNamaKabupaten(dto.getNamaKabupaten());
		kabupatenEntity.setKodeKabupaten(dto.getKodeKabupaten());
		kabupatenRepository.save(kabupatenEntity);
		return ResponseEntity.ok(kabupatenEntity);
	}
	
	@DeleteMapping("/delete/{idKabupaten}")
	public ResponseEntity<?> delete(@PathVariable Integer idKabupaten) {
		KabupatenEntity kabupatenEntity = kabupatenRepository.findById(idKabupaten).get();
		kabupatenRepository.delete(kabupatenEntity);
		return ResponseEntity.ok(kabupatenEntity);
	}
}
