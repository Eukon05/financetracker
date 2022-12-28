package com.eukon05.financetracker.configuration;

import com.eukon05.financetracker.handler.ApiErrorDTO;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Finance Tracker API",
                version = "V1.2",
                description = "A simple API for managing expenses and incomes",
                license = @License(name = "MIT License", url = "https://github.com/Eukon05/financetracker/blob/master/LICENSE.md")
        ),
        security = @SecurityRequirement(name = "Keycloak Access Token Authentication")
)
@SecurityScheme(
        name = "Keycloak Access Token Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
class OpenAPIConfiguration {
    @Bean
    OpenAPI openAPICustomizer() {
        OpenAPI api = new OpenAPI();
        Schema<?> apiErrorSchema = ModelConverters.getInstance().resolveAsResolvedSchema(new AnnotatedType(ApiErrorDTO.class)).schema;
        Content apiErrorContent = new Content().addMediaType(APPLICATION_JSON_VALUE, new MediaType().schema(apiErrorSchema));

        api.components(new Components()
                .addResponses("unauthorized",
                        new ApiResponse()
                                .description("Unauthorized")
                                .content(new Content()))
                .addResponses("validation",
                        new ApiResponse()
                                .description("Validation failed for given input data")
                                .content(apiErrorContent))
                .addResponses("conflict",
                        new ApiResponse()
                                .description("Input data in conflict with existing data")
                                .content(apiErrorContent))
                .addResponses("notfound",
                        new ApiResponse()
                                .description("Entity not found or does not belong to your account")
                                .content(apiErrorContent)));
        return api;
    }

}
