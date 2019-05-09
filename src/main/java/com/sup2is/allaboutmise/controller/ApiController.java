package com.sup2is.allaboutmise.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sup2is.allaboutmise.model.JsonResult;
import com.sup2is.allaboutmise.model.Mise;
import com.sup2is.allaboutmise.service.MiseService;
import com.sup2is.allaboutmise.service.MiseServiceImpl;
import com.sup2is.allaboutmise.util.GlobalTime;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;

@RestController
@RequestMapping("/api")
@PropertySource("classpath:/allaboutmise.properties")
@Slf4j
public class ApiController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private MiseService miseService;
	
	@Autowired
	private GlobalTime globalTime;
	
	@GetMapping("/cities")
	public ResponseEntity<JsonResult> getCities() {
		try {
			log.debug("### : cities call");
			String[] cities = environment.getProperty("cities").toString().split(",");
			Map<String, Object> param = new HashMap<>();
			param.put("cities", cities);
			return new ResponseEntity<JsonResult>(new JsonResult(param),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<JsonResult>(new JsonResult(e),HttpStatus.INTERNAL_SERVER_ERROR);
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
