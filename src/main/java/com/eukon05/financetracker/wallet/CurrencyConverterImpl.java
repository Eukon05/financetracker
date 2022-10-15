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
            LocalDate parsedDate = LocalDate.ofInstant(date, ZoneId.systemDefault());
            HttpResponse<String> response = getRequest(String.format(CONVERTER_API_URL, from, to, amount, parsedDate));

            JsonNode node = objectMapper.readValue(response.body(), JsonNode.class);
            return new BigDecimal(node.get("result").asText());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CurrencyConversionException();
        }
    }

    private HttpResponse<String> getRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
