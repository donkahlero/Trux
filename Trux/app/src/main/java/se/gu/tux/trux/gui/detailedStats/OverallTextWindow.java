package se.gu.tux.trux.gui.detailedStats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tux.gu.se.trux.R;

public class OverallTextWindow extends Fragment {

    View myFragmentView;

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

        return myFragmentView;
    }

}