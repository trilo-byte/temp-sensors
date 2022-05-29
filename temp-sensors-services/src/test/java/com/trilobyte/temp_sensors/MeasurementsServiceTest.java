package com.trilobyte.temp_sensors;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.trilobyte.temp_sensors.dto.ComputeReqDto;
import com.trilobyte.temp_sensors.dto.ComputeResDto;
import com.trilobyte.temp_sensors.services.MeasurementsService;
import com.trilobyte.temp_sensors.services.impl.MeasurementsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MeasurementsServiceTest.class})
public class MeasurementsServiceTest {

    @InjectMocks
    private MeasurementsService service = new MeasurementsServiceImpl();

    @Test
    void compute_returns0_whenListIsEmpty() {
        // GIVEN
        final int expectedResponse = 0;
        final List<Integer> readings = Arrays.asList();
        ComputeReqDto dto = new ComputeReqDto();
        dto.setReadings(readings);

        // WHEN
        final ComputeResDto response = this.service.compute(dto);

        // THEN
        assertThat(response).isNotNull();
        assertEquals(expectedResponse, response.getIncrement());
    }

    @Test
    void compute_returns0_whenListIsHasOneValue() {
        // GIVEN
        final int expectedResponse = 0;
        final List<Integer> readings = Arrays.asList(1);
        ComputeReqDto dto = new ComputeReqDto();
        dto.setReadings(readings);

        // WHEN
        final ComputeResDto response = this.service.compute(dto);

        // THEN
        assertThat(response).isNotNull();
        assertEquals(expectedResponse, response.getIncrement());
    }

    @Test
    void compute_returns0_whenListHasSameValues() {
        // GIVEN
        final int expectedResponse = 0;
        final List<Integer> readings = Arrays.asList(1, 1, 1);
        ComputeReqDto dto = new ComputeReqDto();
        dto.setReadings(readings);

        // WHEN
        final ComputeResDto response = this.service.compute(dto);

        // THEN
        assertThat(response).isNotNull();
        assertEquals(expectedResponse, response.getIncrement());
    }

    @Test
    void compute_returns2_whenListIs1_2_3() {
        // GIVEN
        final int expectedResponse = 2;
        final List<Integer> readings = Arrays.asList(1, 2, 3);
        ComputeReqDto dto = new ComputeReqDto();
        dto.setReadings(readings);

        // WHEN
        final ComputeResDto response = this.service.compute(dto);

        // THEN
        assertThat(response).isNotNull();
        assertEquals(expectedResponse, response.getIncrement());
    }

    @Test
    void compute_returns0_whenListIs3_2_1() {
        // GIVEN
        final int expectedResponse = 0;
        final List<Integer> readings = Arrays.asList(3, 2, 1);
        ComputeReqDto dto = new ComputeReqDto();
        dto.setReadings(readings);

        // WHEN
        final ComputeResDto response = this.service.compute(dto);

        // THEN
        assertThat(response).isNotNull();
        assertEquals(expectedResponse, response.getIncrement());
    }

    @Test
    void compute_returns3_whenListIs1_3_2_4() {
        // GIVEN
        final int expectedResponse = 3;
        final List<Integer> readings = Arrays.asList(1, 3, 2, 4);
        ComputeReqDto dto = new ComputeReqDto();
        dto.setReadings(readings);

        // WHEN
        final ComputeResDto response = this.service.compute(dto);

        // THEN
        assertThat(response).isNotNull();
        assertEquals(expectedResponse, response.getIncrement());
    }

    @Test
    void compute_returns8_whenListIs10_15_18_1_8() {
        // GIVEN
        final int expectedResponse = 8;
        final List<Integer> readings = Arrays.asList(10, 15, 18, 1, 8);
        ComputeReqDto dto = new ComputeReqDto();
        dto.setReadings(readings);

        // WHEN
        final ComputeResDto response = this.service.compute(dto);

        // THEN
        assertThat(response).isNotNull();
        assertEquals(expectedResponse, response.getIncrement());
    }

    @Test
    void compute_returns11_whenListIs10_15_18_1_10_12() {
        // GIVEN
        final int expectedResponse = 11;
        final List<Integer> readings = Arrays.asList(10, 15, 18, 1, 10, 12);
        ComputeReqDto dto = new ComputeReqDto();
        dto.setReadings(readings);

        // WHEN
        final ComputeResDto response = this.service.compute(dto);

        // THEN
        assertThat(response).isNotNull();
        assertEquals(expectedResponse, response.getIncrement());
    }
}
