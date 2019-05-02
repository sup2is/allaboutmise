package com.sup2is.allaboutmise.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sup2is.allaboutmise.model.JsonResult;
import com.sup2is.allaboutmise.model.Mise;
import com.sup2is.allaboutmise.service.MiseService;
import com.sup2is.allaboutmise.util.GlobalTime;

@RestController
@RequestMapping("/api")
@EnableCaching
public class ApiController {
	
	@Autowired
	private MiseService miseService;
	
	@GetMapping("/reloadTime")
	public ResponseEntity<JsonResult> getReloadTime() {
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("reloadTime", GlobalTime.getReloadTimeToSeconds());
			return new ResponseEntity<JsonResult>(new JsonResult(param),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<JsonResult>(new JsonResult(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@CrossOrigin
	@PostMapping("/realtime-mise")
	public ResponseEntity<JsonResult> getRealtimeMise(@RequestBody Map<String,String> data) {
		try {
			String city = data.get("city");
			List<Mise> miseList = miseService.getCachedRealTimeMiseListByCityName(city);
			Map<String, Object> param = new HashMap<>();
			param.put("miseList", miseList);
			return new ResponseEntity<JsonResult>(new JsonResult(param),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<JsonResult>(new JsonResult(e),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
