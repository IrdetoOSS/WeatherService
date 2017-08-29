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

  @GetMapping(value = "/regionInfo/{countryCode}/{language}/{offset}", produces = APPLICATION_JSON_UTF8_VALUE)
  public String regionInfo(final @PathVariable(value = "countryCode") String countryCode,
                           final @PathVariable(value = "language") String language,
                           final @PathVariable(value = "offset") int offset) {
    return accuWeatherFeignClient.regionInfo(countryCode, language, offset, apiKey);
  }

  @GetMapping(value = "/searchPostCode/{countryCode}/{postCode}/{language}", produces = APPLICATION_JSON_UTF8_VALUE)
  public String searchPostCode(final @PathVariable(value = "countryCode") String countryCode,
                               final @PathVariable(value = "postCode") String postCode,
                               final @PathVariable(value = "language") String language) {
    return accuWeatherFeignClient.searchPostCode(countryCode, postCode, language, apiKey);
  }

  @GetMapping(value = "/forecast/{locationKey}/{language}", produces = APPLICATION_JSON_UTF8_VALUE)
  public String forecast(final @PathVariable(value = "locationKey") String locationKey,
                               final @PathVariable(value = "language") String language) {
    return accuWeatherFeignClient.forecast(locationKey, language, apiKey);
  }
}
