package test.busroute.com.objects;

import java.util.Set;

public class Station {
    Integer id;
    Set<Integer> routes;

    public Station(Integer id, Set<Integer> routes) {
        this.id = id;
        this.routes = routes;
    }

    public Integer getId() {
        return id;
    }

    public Set<Integer> getRoutes() {
        return routes;
    }
}
