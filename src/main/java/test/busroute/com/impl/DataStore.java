package test.busroute.com.impl;

import test.busroute.com.objects.Station;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataStore {
    private static final String DELIMITER = " ";
    private final String path;
    private Map<Integer, Station> stations = new ConcurrentHashMap<>();

    public DataStore(String path) {
        this.path = path;
        initialize();
    }

    public Map<Integer, Station> getStations() {
        return stations;
    }

    /**
     * Read the input file.
     */
    private void initialize() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            int numRoutes = 0;

            if (line != null) {
                // not throwing a typed error response from here.
                numRoutes = Integer.parseInt(line);
            }

            for (int i = 0; i < numRoutes; i++) {
                validateAndProcessRouteDetail(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Validates that route lines are positive integers.
     * 1. RouteId entered is not duplicate.
     * 2. Route line has minimum of 3 entries.
     * 3. Max StationIds in a route line is 1000
     * 4. Same StationId is not used in a route line again.
     * <p>
     * The instructions don't provide any information on how to handle input validations.
     * That is why not throwing any exception on validation failures.
     */
    private void validateAndProcessRouteDetail(String routeDetail) {
        // Max items in a line are 1 + 1000
        String [] items = routeDetail.split(DELIMITER, 1001);
        Integer routeId = Integer.parseInt(items[0]);

        for (int i = 1; i < items.length - 1 ; i++) {
            Integer stationId = Integer.parseInt(items[i]);
            Station station = stations.computeIfAbsent(stationId, k -> newStation(stationId));

            // we build a reverse map of Station to Routes, this helps with faster searches.
            // all the lookups that we are performing are for the stations.
            station.getRoutes().add(routeId);
        }
    }

    /**
     * Create a new set with empty routes.
     */
    private Station newStation(Integer stationId) {
        return new Station(stationId, new HashSet<>());
    }
}

