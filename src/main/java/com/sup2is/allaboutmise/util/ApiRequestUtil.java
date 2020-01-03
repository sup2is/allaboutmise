package com.sup2is.allaboutmise.util;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:/allaboutmise.properties")
public class ApiRequestUtil {

    final RestTemplate restTemplate;
    final Environment environment;

    ApiRequestDto apiRequestDto;

    @PostConstruct
    public void createApiRequestDto() {
        apiRequestDto = ApiRequestDto.builder()
                .baseUrl(environment.getProperty("app.base.url"))
                .serviceKey(environment.getProperty("app.service.key"))
                .numOfRows(environment.getProperty("app.num.of.row"))
                .pageNo(environment.getProperty("app.page.no"))
                .ver(environment.getProperty("app.ver"))
                .build();
    }

    public ResponseEntity<String> requestAirPollutionData(String cityName) throws URISyntaxException {
        return restTemplate.getForEntity(apiRequestDto.createBaseApiRequestUrl(cityName), String.class);
    }
    
    @Builder
    @Setter
    @Getter
    static class ApiRequestDto {

        private String baseUrl;
        private String serviceKey;
        private String numOfRows;
        private String pageNo;
        private String sidoName;
        private String ver;

        public URI createBaseApiRequestUrl(String sidoName) throws URISyntaxException {
            URI uri = new URI(baseUrl);
            return new URIBuilder(uri)
                    .setParameter("serviceKey", serviceKey)
                    .addParameter("numOfRows", numOfRows)
                    .addParameter("pageNo", pageNo)
                    .addParameter("sidoName", sidoName)
                    .addParameter("ver", ver)
                    .build();
        }
    }
}
