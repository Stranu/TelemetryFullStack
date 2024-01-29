package com.example.telemetryanalyzer.repo;

import com.example.telemetryanalyzer.Sensors;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface SensorsRepository extends CrudRepository<Sensors, Long> {

    List<Sensors> findByTimestamp(Timestamp timestamp);

    List<Sensors> findByTimestampAndPressGreaterThanAndTempGreaterThanAndOmegaGreaterThanAndSpeedGreaterThanOrderByCar_idDesc(
            Timestamp startingTimestamp, double press, double temp, double omega, double speed);
}
