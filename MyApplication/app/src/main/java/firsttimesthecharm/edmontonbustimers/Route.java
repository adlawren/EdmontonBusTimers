package firsttimesthecharm.edmontonbustimers;

/**
 * Created by robin on 28/01/17.
 */

public class Route {

    private int busStop, busNum;
    private String routeName = null;

    public Route(int _busStop, int _busNum) {
        busStop = _busStop;
        busNum = _busNum;
    }

    public Route(int _busStop, int _busNum, String _routeName) {
        this(_busStop, _busNum);
        routeName = _routeName;
    }

    public String get_busStop() {
        return String.valueOf(busStop);
    }

    public String get_busNum() {
        return String.valueOf(busNum);
    }

    public String get_routeName() {
        return routeName;
    }

    public boolean has_routeName() {
        if(routeName != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if(o == null || o.getClass() != getClass()) {
            result = false;
        } else {
            Route route = (Route) o;

            if(this.get_busStop().equals(route.get_busStop()) &&
                    this.get_busNum().equals(route.get_busNum())) {
                result = true;
            }

            if(this.has_routeName() && route.has_routeName()) {
                if(this.get_routeName().equals(route.get_routeName())) {
                    result = true;
                } else {
                    result = false;
                }
            } else {
                result = false;
            }

        }
        return result;
    }
}
