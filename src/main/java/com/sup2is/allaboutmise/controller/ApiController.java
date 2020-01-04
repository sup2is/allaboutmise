package com.sup2is.allaboutmise.controller;

import com.sup2is.allaboutmise.model.*;
import com.sup2is.allaboutmise.service.MiseService;
import com.sup2is.allaboutmise.util.GlobalTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {
	
	@Autowired
	private MiseService miseService;
	
	@Autowired
	private GlobalTime globalTime;
	
	@GetMapping("/cities")
	public ResponseEntity<JsonResult> getCities() {
		try {
			log.debug("### : cities call");
			List<CityDto> cities = CityDto.createCityDtoList(City.getCities());
			Map<String, Object> param = new HashMap<>();
			param.put("cities", cities);
			return new ResponseEntity<>(new JsonResult(param),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(new JsonResult(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/modes")
	public ResponseEntity<JsonResult> getModes() {
		try {
			log.debug("### : Modes call");
			List<ModeDto> modes = ModeDto.createModeDtoList(Mode.getModes());
			Map<String, Object> param = new HashMap<>();
			param.put("modes", modes);
			return new ResponseEntity<>(new JsonResult(param),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(new JsonResult(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@PostMapping("/realtime-mise")
	public ResponseEntity<JsonResult> getRealtimeMise(@RequestBody Map<String,String> data) {
		try {
			log.debug("### : realtime-mise call");
			String city = data.get("city");
			log.debug("### : city = " + city);
			List<Mise> miseList = miseService.getCachedRealTimeMiseListByCityName(city);
			long reloadTime = globalTime.getReloadTimeToSeconds(city);
			Map<String, Object> param = new HashMap<>();
			param.put("miseList", miseList);
			param.put("reloadTime", reloadTime);
			return new ResponseEntity<JsonResult>(new JsonResult(param),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<JsonResult>(new JsonResult(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
