package org.analytics.covidtracker.service;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.analytics.covidtracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoronaVirusDataService {

	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${corona.api.url}")
    private String VIRUS_DATA_URL;
   
    private List<LocationStats> allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    //  @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
    	List<LocationStats> newStats = new ArrayList<>();

    	String body = restTemplate.getForObject(VIRUS_DATA_URL, String.class);

    	StringReader csvBodyReader = new StringReader(body);
    	Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
    	for (CSVRecord record : records) {
    		LocationStats locationStat = new LocationStats();
    		locationStat.setState(record.get("Province/State"));
    		locationStat.setCountry(record.get("Country/Region"));
    		int latestCases = Integer.parseInt(record.get(record.size() - 1));
    		int prevDayCases = Integer.parseInt(record.get(record.size() - 2));
    		locationStat.setLatestTotalCases(latestCases);
    		locationStat.setDiffFromPrevDay(latestCases - prevDayCases);
    		newStats.add(locationStat);
    	}
    	this.allStats = newStats;
    }

}
