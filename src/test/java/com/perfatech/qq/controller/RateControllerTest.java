package com.perfatech.qq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfatech.qq.domain.Rate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RateControllerTest
{
    private static final String QQ_V1_RATE = "/qq/v1/rate";
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    @Test
    public void findAll() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + QQ_V1_RATE + "/2").toString(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        Rate[] rates = mapper.readValue(response.getBody(), Rate[].class);

        assertEquals("2 rates expected", 2, rates.length);

        List<Rate> list = Arrays.asList(rates);
        Assert.assertTrue(list.stream().anyMatch(s -> "rate-2.1".equals(s.getName())));
        Assert.assertTrue(list.stream().anyMatch(s -> "rate-2.2".equals(s.getName())));
    }

    @Test
    public void findById() throws Exception  {
        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + QQ_V1_RATE + "/2/2019-05-01").toString(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        Rate rate = mapper.readValue(response.getBody(), Rate.class);
        assertNotNull(rate);
        assertEquals("rate-2.2", rate.getName());
        assertEquals(Integer.valueOf(5), rate.getMinimumStay());
        BigDecimal price = new BigDecimal(250.00);
        BigDecimal priceDelta = new BigDecimal(0.001);
        assertThat(rate.getPrice(), is(closeTo(price, priceDelta)));
    }
}