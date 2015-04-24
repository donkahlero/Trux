package se.gu.tux.trux.gui.detailedStats;

import android.app.Fragment;

import com.jjoe64.graphview.series.LineGraphSeries;

import se.gu.tux.trux.datastructure.MetricData;

/**
 * Created by dennis on 2015-04-24.
 */
public abstract class DetailedStatsFragment extends Fragment {
    public abstract void setValues(final MetricData today, final MetricData week, final MetricData month,
                          final MetricData total, final LineGraphSeries values);
}
