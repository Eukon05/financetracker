package com.eukon05.financetracker.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Finance Tracker API",
                version = "V1.2",
                description = "A simple API for managing expenses and incomes",
                license = @License(name = "MIT License", url = "https://github.com/Eukon05/financetracker/blob/master/LICENSE.md")
        ),
        security = @SecurityRequirement(name = "security_auth")
)
@SecurityScheme(name = "security_auth", type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
                        tokenUrl = "${springdoc.oAuthFlow.tokenUrl}"
                )
        )
)
class OpenAPIConfiguration implements OpenApiCustomizer {
    @Override
    public void customise(OpenAPI openApi) {
        ApiResponse response = new ApiResponse().description("Unauthorized");
        openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> operation.getResponses().addApiResponse("401", response)));
    }
}
