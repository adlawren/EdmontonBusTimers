package firsttimesthecharm.edmontonbustimers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by robin on 28/01/17.
 */

public class Result {

    private String busStop;
    private String busNum;
    private String routeName = "";
    private Date time;

    public Result(String _busStop, String _busNum, String _time) {
        busStop = _busStop;
        busNum = _busNum;
        try {
            time = new SimpleDateFormat("hh:mm:ss").parse(_time);
        } catch(Exception e) {

        }
    }

    public Result(String _busStop, String _busNum, String _time, String _routeName) {
        this(_busStop, _busNum, _time);
        routeName = _routeName;
    }

    private String formatOutput() {
        if(routeName!=null) {
            return (busNum + " " + routeName + ": " + busStop + " " + "This will be time.");
        } else {
            return (busNum + ": " + busStop + " " + "This will be time.");
        }
    }

    public String get_formattedResult() {
        return formatOutput();
    }

    public String get_BusNumRouteName() {
        return (busNum + " " + routeName);
    }

    public String get_BusNum() {
        return busNum;
    }

    public String get_BusStop() {
        return (busStop);
    }

    public Date get_time() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;

        if(o == null || o.getClass() != getClass()) {
            result = false;
        } else {
            Result r = (Result) o;

            if (this.get_BusNum().equals(r.get_BusNum()) &&
                    this.get_BusStop().equals(r.get_BusStop())) {
                result = true;
            }
        }
        return result;
    }
}
