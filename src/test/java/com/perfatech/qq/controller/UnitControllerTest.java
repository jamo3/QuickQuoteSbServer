package com.perfatech.qq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfatech.qq.domain.Unit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnitControllerTest
{
    private static final String QQ_V1_UNIT = "/qq/v1/unit";
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @LocalServerPort
    private int port;

    @Test
    public void findAll() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + QQ_V1_UNIT).toString(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        Unit[] units = mapper.readValue(response.getBody(), Unit[].class);

        assertEquals("2 units expected", 2, units.length);

        List<Unit> list = Arrays.asList(units);
        Assert.assertTrue(list.stream().anyMatch(s -> "unit-1".equals(s.getName())));
        Assert.assertTrue(list.stream().anyMatch(s -> "unit-2".equals(s.getName())));
    }

    @Test
    public void findById() throws Exception
    {
        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:" + port + QQ_V1_UNIT+ "/2").toString(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        Unit unit = mapper.readValue(response.getBody(), Unit.class);
        assertNotNull(unit);
        assertEquals("unit-2", unit.getName());
        assertEquals("9876", unit.getCalendarId());
    }
}