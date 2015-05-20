package se.gu.tux.trux.gui.community;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import se.gu.tux.trux.gui.messaging.FriendListFragment;
import tux.gu.se.trux.R;


public class MapCommunityWindow extends Fragment {

    LinearLayout menu, layout;
    ImageButton messageButton, infoButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_map_community_window, container, false);

        layout = (LinearLayout) view.findViewById(R.id.backLayout);
        menu = (LinearLayout) view.findViewById(R.id.menu);
        messageButton = (ImageButton) view.findViewById(R.id.messageButton);
        infoButton = (ImageButton) view.findViewById(R.id.infoButton);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMenu();
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMenu();
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoWindow();
            }
        });



        return view;
    }

    public void removeMenu() {

        Fragment mcw = this;

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.remove(mcw);
        fragmentTransaction.commit();
    }

    public void showInfoWindow() {

        InfoFragment ifragment = new InfoFragment();

        //Passing the arguments from MapFrag to InfoFragment
        ifragment.setArguments(this.getArguments());

        menu.setVisibility(View.GONE);

        //Transaction to the InfoFragment
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.addToBackStack(null);
        System.out.println("Count on the popStack in MCW: " + getFragmentManager().getBackStackEntryCount());
        fragmentTransaction.replace(R.id.contentContainer, ifragment);
        fragmentTransaction.commit();
    }


}
