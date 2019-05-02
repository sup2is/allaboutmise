package com.sup2is.allaboutmise.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sup2is.allaboutmise.model.JsonResult;
import com.sup2is.allaboutmise.util.GlobalTime;

import net.sf.ehcache.CacheManager;

@RestController
@RequestMapping("/api")
@EnableCaching
public class ApiController {
	
	@Autowired
	private CacheManager cacheManager;
	
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
	

}