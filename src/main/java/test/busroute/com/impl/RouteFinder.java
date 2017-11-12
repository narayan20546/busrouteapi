package test.busroute.com.impl;

import test.busroute.com.objects.DirectRoute;
import test.busroute.com.objects.Station;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class RouteFinder {
    private RouteFinder() {}

    /**
     * Finds if there is a direct route between two stations.
     */
    public static DirectRoute isDirectRoute(int dep, int arr, DataStore dataStore) {
        boolean outcome = false;
        Map<Integer, Station> stations = dataStore.getStations();

        Station departure = stations.get(dep);
        Station arrival = stations.get(arr);

        if (departure != null && arrival != null) {
            Set<Integer> departureStationRoutes = departure.getRoutes();
            Set<Integer> arrivalStationRoutes = arrival.getRoutes();

            Set<Integer> commonRoutes = departureStationRoutes.stream()
                    .filter(e -> arrivalStationRoutes.contains(e))
                    .collect(Collectors.toSet());

            if (commonRoutes != null && commonRoutes.size() > 0) {
                outcome = true;
            }
        }

        return new DirectRoute(dep, arr, outcome);
    }
}
