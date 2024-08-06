package com.zetes.trustservices.json.schema.demo;

import com.github.erosb.jsonsKema.FormatValidationPolicy;
import com.github.erosb.jsonsKema.JsonParser;
import com.github.erosb.jsonsKema.JsonValue;
import com.github.erosb.jsonsKema.Schema;
import com.github.erosb.jsonsKema.SchemaLoader;
import com.github.erosb.jsonsKema.ValidationFailure;
import com.github.erosb.jsonsKema.Validator;
import com.github.erosb.jsonsKema.ValidatorConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
@Slf4j
public class JsonSchemaDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(JsonSchemaDemoApplication.class, args);
        validateSchema();
    }

    private static void validateSchema() {
        log.info("Validating JSON...");

        JsonValue schemaJson = new JsonParser(JsonResources.issuanceLocationsJsonSchema).parse();
        Schema schema = new SchemaLoader(schemaJson).load();
        Validator validator = Validator.create(schema, new ValidatorConfig(FormatValidationPolicy.ALWAYS));

        JsonValue json = new JsonParser(JsonResources.issuanceLocationsJson).parse();
        ValidationFailure failure = validator.validate(json);

        log.info(Optional.ofNullable(failure).map(ValidationFailure::toString).orElse("You JSON is valid."));
    }
}
