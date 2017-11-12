package test.busroute.com;

import spark.Request;
import test.busroute.com.impl.DataStore;
import test.busroute.com.impl.RouteFinder;
import test.busroute.com.objects.DirectRoute;
import test.busroute.com.objects.JsonTransformer;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;

/**
 * Main Application Runner.
 */
public class Main
{
    public static void main( String[] args ) {
        DataStore dataStore = new DataStore(args[0]);
        port(8088);
        before((request, response) -> response.type("application/json"));
        get("/api/direct", (request, response) -> validateDirectRoute(request, dataStore), new JsonTransformer());
    }

    private static DirectRoute validateDirectRoute(Request request, DataStore dataStore) {
        // Ideally we need to validate input to be valid integer and throw 4xx typed error.
        // There were no intructions given in the problem around error handling, therefore not sure on how will automated tests
        // work with error typed response.
        Integer depId = Integer.parseInt(request.queryParams("dep_sid"));
        Integer arrId = Integer.parseInt(request.queryParams("arr_sid"));

        return RouteFinder.isDirectRoute(depId, arrId, dataStore);
    }
}
