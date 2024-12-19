package com.example.franchise.core.core.config;

import com.example.franchise.core.common.constants.ErrorMessages;
import com.example.franchise.core.common.responses.ApiResponse;
import com.example.franchise.core.core.components.I18NComponent;
import com.example.franchise.core.core.exceptions.ApiException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import com.google.common.io.CharStreams;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final I18NComponent i18NComponent;

    public FeignErrorDecoder(I18NComponent i18NComponent) {
        this.i18NComponent = i18NComponent;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            try {
                Reader reader = response.body().asReader(Charset.defaultCharset());
                String result = CharStreams.toString(reader);
                ObjectMapper mapper = new ObjectMapper();
                mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                ApiResponse apiError = mapper.readValue(result, ApiResponse.class);
                return new ApiException(apiError.getMessage());
            } catch (IOException e) {
                return new ApiException(i18NComponent.getMessage(ErrorMessages.HANDLER_UNKNOWN_ERROR));
            }
        }
        return new ApiException(i18NComponent.getMessage(ErrorMessages.HANDLER_UNKNOWN_ERROR));
    }
}
