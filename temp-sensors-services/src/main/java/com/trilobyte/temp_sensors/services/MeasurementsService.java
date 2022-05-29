package com.trilobyte.temp_sensors.services;

import com.trilobyte.temp_sensors.dto.ComputeReqDto;
import com.trilobyte.temp_sensors.dto.ComputeResDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface MeasurementsService {

    ComputeResDto compute(@NotNull @Valid ComputeReqDto dto);
}
