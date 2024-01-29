package com.example.telemetryanalyzer;

import com.example.telemetryanalyzer.repo.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TelemetryController {

	private final SensorsRepository sensorsRepository;

	@Autowired
	public TelemetryController(SensorsRepository sensorsRepository) {
		this.sensorsRepository = sensorsRepository;
	}

	@MessageMapping("/connect")
	@SendTo("/topic/telemetry")
	public List<Sensors> telemetry(TelemetryInput cicle) throws Exception {

		System.out.println(cicle.getCicle());

		//Query to get the telemetry data
		//The timestamp get manually incremented with the cicle value to simulate the passage of time
		//2017-03-03 00:00:00
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedDate = dateFormat.parse("2017-03-03 00:00:00");
		Timestamp startingTimestamp = new Timestamp(parsedDate.getTime());
		Timestamp timeToSearch = new Timestamp(startingTimestamp.getTime() + (cicle.getCicle() * 60*1000));

		//Query to get the telemetry data
		List<Sensors> results = sensorsRepository.findByTimestampAndPressGreaterThanAndOmegaGreaterThan(timeToSearch, 0, 0);


		/*Manual TEST
		List<Sensors> results = new ArrayList<>();
		results.add(new Sensors(new Timestamp(System.currentTimeMillis()), 1.11, "Front Right", 20.5, cicle.getCicle().doubleValue(), 5.5, "Volvo V40"));
		results.add(new Sensors(new Timestamp(System.currentTimeMillis()), 3.11, "Rear Right", 25.5, cicle.getCicle().doubleValue(), 55.5, "Volvo V40"));
		*/
		return results;
	}

}
