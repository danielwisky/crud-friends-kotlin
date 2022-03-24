package br.com.danielwisky.friends.configs.springdoc

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfiguration {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("Friends")
                .version("v1.0.0")
                .license(
                    License()
                        .name("Apache License Version 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0")
                )
        )
    }
}
