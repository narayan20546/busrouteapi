package test.busroute.com.objects;

import java.util.Set;

public class Route {
    Integer id;
    Set<Integer> stations;

    public Route(Integer id, Set<Integer> stations) {
        this.id = id;
        this.stations = stations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Integer> getStations() {
        return stations;
    }

    public void setStations(Set<Integer> stations) {
        this.stations = stations;
    }
}
