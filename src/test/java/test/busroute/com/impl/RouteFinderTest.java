package test.busroute.com.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.busroute.com.objects.Station;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.doReturn;

public class RouteFinderTest {

    @Mock DataStore dataStore;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        doReturn(getTestStations()).when(dataStore).getStations();
    }

    @Test
    public void testIsDirectRoute() {
        assertTrue(RouteFinder.isDirectRoute(1,3, dataStore).isDirect_bus_route());
        assertTrue(RouteFinder.isDirectRoute(2,4, dataStore).isDirect_bus_route());
        assertFalse(RouteFinder.isDirectRoute(1,2, dataStore).isDirect_bus_route());
        assertFalse(RouteFinder.isDirectRoute(1,9, dataStore).isDirect_bus_route());

        assertFalse(RouteFinder.isDirectRoute(100,2, dataStore).isDirect_bus_route());
        assertFalse(RouteFinder.isDirectRoute(1000,4, dataStore).isDirect_bus_route());

    }

    private Map<Integer, Station> getTestStations() {
        Map<Integer, Station> stationMap = new HashMap<>();

        Set<Integer> routes1 = new HashSet<>(Arrays.asList(1, 3, 5, 7));
        Station station1 = new Station(1, routes1);
        stationMap.put(1, station1);

        Set<Integer> routes2 = new HashSet<>(Arrays.asList(2, 4, 6, 8));
        Station station2 = new Station(2, routes2);
        stationMap.put(2, station2);

        Set<Integer> routes3 = new HashSet<>(Arrays.asList(1, 3, 2, 4));
        Station station3 = new Station(3, routes3);
        stationMap.put(3, station3);

        Set<Integer> routes4 = new HashSet<>(Arrays.asList(6, 8, 5, 7));
        Station station4 = new Station(4, routes4);
        stationMap.put(4, station4);

        return stationMap;
    }
}
