package firsttimesthecharm.edmontonbustimers;

/**
 * Created by robin on 28/01/17.
 */

public class Result {

    private String busStop;
    private String busNum;
    private String routeName = null;
    private String time;

    public Result(String _busStop, String _busNum, String _time) {
        busStop = _busStop;
        busNum = _busNum;
        time = _time;
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
}
