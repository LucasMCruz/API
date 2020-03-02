package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFox implements WebMvcConfigurer{
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.example.demo.resources"))
					.paths(PathSelectors.any())
					//.paths(PathSelectors.ant("/livros/*"))
					.build()
				.apiInfo(apiInfo())
				.tags(new Tag("Livro", "Gerencia de Livros"));
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("APi de Livro e autores")
				.description("API Aberta para amantes de livros")
				.version("1")
				.contact(new Contact("Amarelao", "http://https://gamejolt.com/games/alice2091/419597", "Amarelao@Gmail.com"))
				.build();
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	
}
