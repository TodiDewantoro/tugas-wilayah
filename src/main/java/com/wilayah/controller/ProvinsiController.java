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

import com.wilayah.dto.ProvinsiDto;
import com.wilayah.dto.StatusMessageDto;
import com.wilayah.entity.ProvinsiEntity;
import com.wilayah.repository.ProvinsiRepository;
import com.wilayah.service.ProvinsiServiceImpl;

@RestController
@RequestMapping("/provinsi") //localhost:8800/provinsi
public class ProvinsiController {

	@Autowired
	private ProvinsiRepository provinsiRepository;
	
	@Autowired
	private ProvinsiServiceImpl service;
	
	//GET METHOD
	@GetMapping("/get-all")
	public ResponseEntity<?> getAllProvinsi(){
		List<ProvinsiEntity> provinsiEntities = provinsiRepository.findAll();
		return ResponseEntity.ok(provinsiEntities);
	}
	
	@GetMapping("/get-by-id/{idProvinsi}")
	public ResponseEntity<?> getProvById(@PathVariable Integer idProvinsi){
		List<ProvinsiEntity> provinsiEntities = provinsiRepository.findProvinsi(idProvinsi);
		return ResponseEntity.ok(provinsiEntities);
	}
	
	//POST METHOD SERVICE
	@PostMapping("/post")
	public ResponseEntity<?> postProvinsi(@RequestBody ProvinsiDto dto){
		if (dto.getKodeProvinsi() == null) {
			StatusMessageDto<ProvinsiEntity> result = new StatusMessageDto<>();
			result.setStatus(HttpStatus.BAD_REQUEST.value());
			result.setMessage("Kode provinsi tidak boleh kosong!");
			result.setData(null);
			return ResponseEntity.badRequest().body(result);
		} else {
			ProvinsiEntity provinsiEntity = service.post(dto);
			StatusMessageDto<ProvinsiEntity> result = new StatusMessageDto<>();
			result.setStatus(HttpStatus.OK.value());
			result.setMessage("Success");
			result.setData(provinsiEntity);
			return ResponseEntity.ok(result);
		}
	}
	
	//PUT METHOD SERVICE
	@PutMapping("/update/{idProvinsi}")
	public ResponseEntity<?> updateProvinsi(@PathVariable Integer idProvinsi, @RequestBody ProvinsiDto dto){
		ProvinsiEntity provinsiEntity = service.put(idProvinsi,dto);
		return ResponseEntity.ok(provinsiEntity);
	}
	
	//DELETE METHOD SERVICE
	@DeleteMapping("/delete/{idProvinsi}")
	public ResponseEntity<?> deletePendidikan(@PathVariable Integer idProvinsi) {
		ProvinsiEntity provinsiEntity = service.delete(idProvinsi);
		
		StatusMessageDto<ProvinsiEntity> result = new StatusMessageDto<>();
		result.setStatus(HttpStatus.OK.value());
		result.setMessage("Successfully deleted!");
		result.setData(provinsiEntity);
		return ResponseEntity.ok(result);
	}
}
