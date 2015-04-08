package se.gu.tux.trux.gui.detailedStats;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import se.gu.tux.trux.gui.detailedStats.DistanceTraveledWindow;
import se.gu.tux.trux.gui.detailedStats.FuelWindow;
import se.gu.tux.trux.gui.detailedStats.OverallStats;
import se.gu.tux.trux.gui.detailedStats.SpeedWindow;
import tux.gu.se.trux.R;


public class Stats extends ActionBarActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToSpeed(final View view)
    {
        final Intent intent = new Intent(this, SpeedWindow.class);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                view.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        startActivity(intent);
                    }
                });
            }

        }).start();

    }

    public void goToFuel(View view) {
        Intent intent = new Intent(this, FuelWindow.class);
        startActivity(intent);
    }

    public void goToDistanceTraveled(View view){
        Intent intent = new Intent(this, DistanceTraveledWindow.class);
        startActivity(intent);
    }

    public void goToOverallStats(View view){
        Intent intent = new Intent(this, OverallStats.class);
        startActivity(intent);
    }
}