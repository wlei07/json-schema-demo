package com.zetes.trustservices.json.schema.demo;

import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Getter
@Component
public class JsonResources implements InitializingBean {
    @Value("classpath:issuance-locations.json")
    private Resource issuanceLocationsJsonResource;

    @Value("classpath:issuance-locations-schema.json")
    private Resource issuanceLocationsJsonSchemaResource;

    public static String issuanceLocationsJson;
    public static String issuanceLocationsJsonSchema;

    @Override
    public void afterPropertiesSet() throws Exception {
        issuanceLocationsJson = asString(issuanceLocationsJsonResource);
        issuanceLocationsJsonSchema = asString(issuanceLocationsJsonSchemaResource);
    }

    public static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
