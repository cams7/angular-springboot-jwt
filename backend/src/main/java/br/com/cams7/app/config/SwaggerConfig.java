/**
 * 
 */
package br.com.cams7.app.config;

import static br.com.cams7.app.security.SecurityConstants.HEADER_STRING;
import static br.com.cams7.app.security.SecurityConstants.TOKEN_PREFIX;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ceanm
 *
 */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	public Docket greetingApi() {
		Parameter parameter = new ParameterBuilder().name(HEADER_STRING).description(TOKEN_PREFIX + "token")
				.modelRef(new ModelRef("string")).parameterType("header").required(true).build();

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.cams7.app.endpoint")).paths(PathSelectors.any())
				.build().apiInfo(metaData()).globalOperationParameters(Collections.singletonList(parameter));
	}

	private static ApiInfo metaData() {
		return new ApiInfoBuilder().title("Angular Spring Boot JWT Authentication example")
				.description("Angular + Spring Security + HSQLDB").version("0.0.1-SNAPSHOT")
				.contact(new Contact("César A. Magalhães", "https://www.linkedin.com/in/cams7", "ceanma@gmail.com"))
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.build();
	}
}
