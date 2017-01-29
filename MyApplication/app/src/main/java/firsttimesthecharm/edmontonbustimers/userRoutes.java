package firsttimesthecharm.edmontonbustimers;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by robin on 28/01/17.
 */

public class userRoutes {

    public userRoutes(){}

    private ArrayList<Route> userRoutes = new ArrayList<Route>();

    public void addRoute(Route route) {
        for(Route r : userRoutes) {
            if (r.equals(route)) {
                return;
            }
        }
        userRoutes.add(route);
    }

    public void removeRoute(Route route) {
        if(userRoutes.contains(route)) {
            userRoutes.remove(route);
        }
    }

    public ArrayList<Route> get_routes() {
        return userRoutes;
    }

}
