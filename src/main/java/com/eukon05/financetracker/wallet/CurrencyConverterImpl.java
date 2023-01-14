package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.wallet.exception.CurrencyConversionException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class CurrencyConverterImpl implements CurrencyConverter {

    private static final String CONVERTER_API_URL = "https://api.exchangerate.host/convert?from=%s&to=%s&amount=%f&date=%s";
    private final ObjectMapper objectMapper;
    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public BigDecimal convert(String from, String to, BigDecimal amount, Instant date) {
        try {
            LocalDate parsedDate = LocalDate.ofInstant(date, ZoneId.of("UTC"));
            JsonNode node = getRequest(String.format(CONVERTER_API_URL, from, to, amount.abs(), parsedDate));
            BigDecimal result = new BigDecimal(node.get("result").asText());

            return amount.signum() < 0 ? result.negate() : result;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CurrencyConversionException();
        }
    }

    private JsonNode getRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .GET()
                .build();
        String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        return objectMapper.readValue(body, JsonNode.class);
    }

}
