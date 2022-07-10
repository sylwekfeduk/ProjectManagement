package pl.fis.lbd.day2.ProjectManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class NBPApiService {


    private static final Logger LOG = LoggerFactory.getLogger(NBPApiService.class);
    private RestTemplate restTemplate = new RestTemplate();
    private static final String baseUrl = "http://api.nbp.pl/api/exchangerates";

    public void getTableWithExchangeRatesFromYesterday() {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/tables/A/" + LocalDate.now().minusDays(2) + "/", String.class);
        LOG.info(response.toString());
    }

    public void getUsdExchangeRatesForLast10Days() {
        String response = restTemplate.getForObject(baseUrl + "/rates/A/usd/" + LocalDate.now().minusDays(10) + "/" + LocalDate.now(), String.class);
        LOG.info(response);
    }
}
