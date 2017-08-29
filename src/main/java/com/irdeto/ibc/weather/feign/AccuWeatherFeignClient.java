package com.irdeto.ibc.weather.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(serviceId = "accu-weather")
public interface AccuWeatherFeignClient {

  @RequestMapping(
      value = "locations/v1/adminareas/nl?apikey={apiKey}&language={language}&offset={offset}",
      method = GET,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  String regionInfo(final @PathVariable(value = "apiKey") String apiKey,
                    final @PathVariable(value = "language") String language,
                    final @PathVariable(value = "offset") int offset);

  @RequestMapping(
      value = "locations/v1/postalcodes/nl/search?apikey={apiKey}&q={postCode}&language={language}",
      method = GET,
      produces = APPLICATION_JSON_UTF8_VALUE
  )
  String searchPostCode(final @PathVariable(value = "apiKey") String apiKey,
                        final @PathVariable(value = "postCode") String postCode,
                        final @PathVariable(value = "language") String language);
}
