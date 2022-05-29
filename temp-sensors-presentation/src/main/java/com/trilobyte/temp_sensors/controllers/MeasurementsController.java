package com.trilobyte.temp_sensors.controllers;

import com.trilobyte.temp_sensors.dto.ComputeReqDto;
import com.trilobyte.temp_sensors.dto.ComputeResDto;
import com.trilobyte.temp_sensors.services.MeasurementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MeasurementsController implements MeasurementsApiDelegate {

    private final NativeWebRequest request;

    private final MeasurementsService service;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ComputeResDto> compute(ComputeReqDto computeReqDto) {
        return ResponseEntity.ok(service.compute(computeReqDto));
    }
}
