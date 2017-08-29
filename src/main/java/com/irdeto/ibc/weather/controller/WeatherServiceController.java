package com.irdeto.ibc.weather.controller;

import com.irdeto.ibc.weather.feign.AccuWeatherFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
public class WeatherServiceController {

  @Value("${accu-weather.api-key}")
  private String apiKey;

  private final AccuWeatherFeignClient accuWeatherFeignClient;

  @Autowired
  public WeatherServiceController(final AccuWeatherFeignClient accuWeatherFeignClient) {
    this.accuWeatherFeignClient = accuWeatherFeignClient;
  }

  @GetMapping(value = "/regionInfo/{language}/{offset}", produces = APPLICATION_JSON_UTF8_VALUE)
  public String regionInfo(final @PathVariable(value = "language") String language,
                           final @PathVariable(value = "offset") int offset) {
    return accuWeatherFeignClient.regionInfo(apiKey, language, offset);
  }

  @GetMapping(value = "/searchPostCode/{postCode}/{language}", produces = APPLICATION_JSON_UTF8_VALUE)
  public String searchPostCode(final @PathVariable(value = "postCode") String postCode,
                               final @PathVariable(value = "language") String language) {
    return accuWeatherFeignClient.searchPostCode(apiKey, postCode, language);
  }
}
