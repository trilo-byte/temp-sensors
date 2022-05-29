package com.trilobyte.temp_sensors.services.impl;

import com.trilobyte.temp_sensors.dto.ComputeReqDto;
import com.trilobyte.temp_sensors.dto.ComputeResDto;
import com.trilobyte.temp_sensors.model.Measure;
import com.trilobyte.temp_sensors.services.MeasurementsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MeasurementsServiceImpl implements MeasurementsService {

    @Override
    public ComputeResDto compute(ComputeReqDto dto) {

        List<Measure> measures = new ArrayList<>();
        for (int i = 0; i < dto.getReadings().size(); i++) {
            Measure measure = new Measure(dto.getReadings().get(i), Integer.valueOf(i));
            measures.add(measure);
        }
        Collections.sort(measures);

        List<Integer> partialIncrements = new ArrayList<>();
        int result = 0;
        partialIncrements.add(result);
        for ( int i = 0; i < measures.size() - 1; i++) {
            for ( int j = 1; j < measures.size(); j ++) {
                if (measures.get(i).getIndex() < measures.get(j).getIndex()){
                    result = measures.get(j).getValue() - measures.get(i).getValue();
                }
            }
            partialIncrements.add(result);
            result = 0;
        }

        Collections.sort(partialIncrements);
        Collections.reverse(partialIncrements);
        ComputeResDto resDto = new ComputeResDto();
        resDto.setIncrement(partialIncrements.get(0));
        return resDto;
    }
}
