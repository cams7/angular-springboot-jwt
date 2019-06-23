/**
 * 
 */
package br.com.cams7.app.error.handler;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cams7
 *
 */
@Slf4j
public class RestResponseExceptionHandler extends DefaultResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode() != HttpStatus.OK)
			log.error("Inside hasError");
		return super.hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		log.error("Doing something with status code {}", response.getStatusCode());
		log.error("Doing something with status body {}", IOUtils.toString(response.getBody(), "UTF-8"));
		super.handleError(response);
	}
}
