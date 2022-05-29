package com.trilobyte.temp_sensors;

import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilobyte.temp_sensors.dto.ComputeReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TempSensorsApplication.class)
@AutoConfigureMockMvc(addFilters = false)
public class MeasurementsControllerTestIT {

    @Autowired
    private MockMvc mvc;

    @Test
    void givenValidValues_whenCompute_thenReturnsRightValue() throws Exception {

        final List<Integer> values = Arrays.asList(1, 3, 2, 4);
        final ComputeReqDto reqDto = new ComputeReqDto();
        reqDto.setReadings(values);
        final ObjectMapper mapper = new ObjectMapper();
        final String reqDtoJson = mapper.writeValueAsString(reqDto);

        mvc.perform(post("/v2/stats/compute").contentType(MediaType.APPLICATION_JSON).content(reqDtoJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.increment", is(3)));
    }

    @Test
    void givenNonValidValues_whenCompute_thenReturnsBadRequest() throws Exception {
        final List<Integer> values = Arrays.asList(1, 3, 2, 4);
        final ComputeReqDto reqDto = new ComputeReqDto();
        reqDto.setReadings(values);
        final ObjectMapper mapper = new ObjectMapper();
        final String reqDtoJson = mapper.writeValueAsString(reqDto).replace("4", "a");

        mvc.perform(post("/v2/stats/compute").contentType(MediaType.APPLICATION_JSON).content(reqDtoJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void givenWrongRequest_whenCompute_thenReturnsBadRequest() throws Exception {
        final List<Integer> values = Arrays.asList(1, 3, 2, 4);
        final ComputeReqDto reqDto = new ComputeReqDto();
        reqDto.setReadings(values);
        final ObjectMapper mapper = new ObjectMapper();
        final String reqDtoJson = mapper.writeValueAsString(reqDto).replace("readings", "writings");

        mvc.perform(post("/v2/stats/compute").contentType(MediaType.APPLICATION_JSON).content(reqDtoJson))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
