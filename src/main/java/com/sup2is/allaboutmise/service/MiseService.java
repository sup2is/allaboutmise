package com.sup2is.allaboutmise.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sup2is.allaboutmise.model.Mise;

@Service
public interface MiseService {

	List<Mise> getCachedRealTimeMiseListByCityName(String cityName) throws Exception;
	
}
