package com.sup2is.allaboutmise.config;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.xml.sax.SAXException;

@Configuration
public class WebConfig implements WebMvcConfigurer  {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/*").allowedOrigins("*").allowedMethods("*");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.cache.ehcache")
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		return factoryBean;
	}
	
	@Bean
	public SAXParser parser() throws ParserConfigurationException, SAXException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		return factory.newSAXParser();
	}
	
	
}
