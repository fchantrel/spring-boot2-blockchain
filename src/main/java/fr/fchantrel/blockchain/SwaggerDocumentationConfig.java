package fr.fchantrel.blockchain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author fchantrel
 *
 */
@Configuration
public class SwaggerDocumentationConfig {

	@Value("${management.endpoints.web.base-path}")
	private String swaggerRootPath;

	@Value("${webservice.root.path}")
	private String webserviceRootPath;

	@Bean
	public Docket customImplementation() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(paths()).build().apiInfo(apiInfo());
	}

	private Predicate<String> paths() {
		return url -> url.startsWith(swaggerRootPath) || url.startsWith(webserviceRootPath);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Blockchain API")
				.description("API demo Blockchain, cf https://www.youtube.com/watch?v=519UIMIs5eI").version("1.0")
				.build();
	}

}
