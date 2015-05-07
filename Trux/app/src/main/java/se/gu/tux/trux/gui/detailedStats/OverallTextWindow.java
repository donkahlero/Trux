package se.gu.tux.trux.gui.detailedStats;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.gu.tux.trux.application.DataHandler;
import se.gu.tux.trux.application.DetailedStatsBundle;
import se.gu.tux.trux.datastructure.Distance;
import se.gu.tux.trux.datastructure.Fuel;
import se.gu.tux.trux.datastructure.Speed;
import tux.gu.se.trux.R;

public class OverallTextWindow extends Fragment {

    View myFragmentView;

    TextView speedTextViewTotal, fuelTextViewTotal, distanceTextViewTotal;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        myFragmentView = inflater.inflate(R.layout.fragment_overall_text_window, container, false);

        speedTextViewTotal = (TextView) myFragmentView.findViewById(R.id.avg_speed_value);
        fuelTextViewTotal = (TextView) myFragmentView.findViewById(R.id.avg_fuel_value);
        distanceTextViewTotal = (TextView) myFragmentView.findViewById(R.id.avg_distance_value);
        myFragmentView.findViewById(R.id.loadingPanel).bringToFront();
        return myFragmentView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myFragmentView = getView();

        // Make sure values are set once they are loaded
        AsyncTask myTask = new AsyncTask<Void, Void, Boolean>()
        {
            Speed s = new Speed(0);
            Fuel f = new Fuel(0);
            Distance d = new Distance(0);

            @Override
            protected Boolean doInBackground(Void... voids)
            {
                while (!(DataHandler.getInstance().detailedStatsReady(s)
                        && DataHandler.getInstance().detailedStatsReady(f)
                        && DataHandler.getInstance().detailedStatsReady(d))) {
                    try {
                        Thread.sleep(100);
                        // Stop waiting if fragment was cancelled
                        if (!isVisible()) {
                            cancel(true);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Wait interrupted.");
                    }
                }
                return null;
            }

            @Override
            public void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Boolean b)
            {
                super.onPostExecute(b);
                setValues(DataHandler.getInstance().getDetailedStats(s),
                        DataHandler.getInstance().getDetailedStats(f),
                        DataHandler.getInstance().getDetailedStats(d));
            }
        }.execute();
    }


    public void setValues(DetailedStatsBundle speedBundle, DetailedStatsBundle fuelBundle,
                          DetailedStatsBundle distBundle) {
        if (speedBundle != null && speedTextViewTotal != null) {
            speedTextViewTotal.setText(
                    new Long(Math.round((Double) speedBundle.getTotal().getValue())).toString() + " km/h");
        }
        if (fuelBundle != null && fuelTextViewTotal != null) {
            fuelTextViewTotal.setText(
                    new Long(Math.round((Double) fuelBundle.getTotal().getValue())).toString() + " L/h");
        }
        if (distBundle != null && distanceTextViewTotal != null) {
            Long distTotal = (Long) distBundle.getTotal().getValue() / 1000;
            //System.out.println(distBundle.getTotal().getValue().toString());
            distanceTextViewTotal.setText(distTotal.toString() + " km");
        }
        if (myFragmentView != null) {
            myFragmentView.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
        }
    }
}
