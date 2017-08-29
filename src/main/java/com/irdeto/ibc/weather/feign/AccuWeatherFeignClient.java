package com.irdeto.ibc.weather.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(serviceId = "accu-weather")
public interface AccuWeatherFeignClient {

  @RequestMapping(
      value = "locations/v1/adminareas/{countryCode}?apikey={apiKey}&language={language}&offset={offset}",
      method = GET,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  String regionInfo(final @PathVariable(value = "countryCode") String countryCode,
                    final @PathVariable(value = "language") String language,
                    final @PathVariable(value = "offset") int offset,
                    final @PathVariable(value = "apiKey") String apiKey);

  @RequestMapping(
      value = "locations/v1/postalcodes/{countryCode}/search?apikey={apiKey}&q={postCode}&language={language}",
      method = GET,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  String searchPostCode(final @PathVariable(value = "countryCode") String countryCode,
                        final @PathVariable(value = "postCode") String postCode,
                        final @PathVariable(value = "language") String language,
                        final @PathVariable(value = "apiKey") String apiKey);

  @RequestMapping(
      value = "forecasts/v1/daily/1day/{locationKey}?apikey={apiKey}&language={language}",
      method = GET,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  String forecast(final @PathVariable("locationKey") String locationKey,
                  final @PathVariable(value = "language") String language,
                  final @PathVariable("apiKey") String apiKey);
}
