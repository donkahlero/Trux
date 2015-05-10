package se.gu.tux.trux.gui.community;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import se.gu.tux.trux.application.DataHandler;
import se.gu.tux.trux.datastructure.Friend;
import se.gu.tux.trux.datastructure.Picture;
import se.gu.tux.trux.gui.base.BaseAppActivity;
import se.gu.tux.trux.technical_services.NotLoggedInException;
import tux.gu.se.trux.R;

public class FriendsWindow extends BaseAppActivity implements View.OnClickListener {

    private ListView friendsList;
    private FriendAdapter friendAdapter;
    private EditText searchField;
    private TextView noFriends;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_window);

        friendsList = (ListView) findViewById(R.id.friendsList);

        friendAdapter = new FriendAdapter(this, new Friend[0], new Picture[0]);
        friendsList.setAdapter(friendAdapter);
        friendsList.setEmptyView(findViewById(R.id.noFriends));
        searchField = (EditText) findViewById(R.id.searchField);
        searchButton =(Button) findViewById(R.id.searchButton);
        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}

            @Override
            public void afterTextChanged(Editable editable) {
                if (searchField.getText().toString().equals("")) {
                    showFriends();
                }
            }
        });
        searchButton.setOnClickListener(this);
        noFriends = (TextView) findViewById(R.id.noFriends);
        showFriends();
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.searchButton)) {
            // Show loading animation and proceed to load friends or search results
            findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
            if (searchField.getText().toString().equals("")){
                showFriends();
            } else {
                showSearchResults(searchField.getText().toString());
            }
        }
    }


    private void showFriends() {
        noFriends.setText("You have no friends :(");
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                // Load friend list
                Friend[] friends = null;
                Picture[] pictures = null;
                try {
                    System.out.println("Fetching friends...");
                    friends = DataHandler.getInstance().getFriends();
                    pictures = new Picture[friends.length];/*
                        for (int i = 0; i < pictures.length; i++) {
                            System.out.println("Fetching image " + friends[i].getProfilePic() + " for friend " +
                                friends[i].getFirstname());
                            pictures[i] = DataHandler.getInstance().getPicture(friends[i].getProfilePic());
                        }*/
                    System.out.println("Done.");

                } catch (NotLoggedInException e) {
                    System.out.println("Trying to fetch friends while not logged in!");
                    cancel(true);
                }

                final Friend[] finalFriends = friends;
                final Picture[] finalPictures = pictures;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Showing friends in list...");
                        friendAdapter.setFriends(finalFriends, finalPictures);
                        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    }
                });

                return null;
            }
        }.execute();
    }

    /**
     * Returns an array with all elements that contain the needle
     * (in username, firstname or lastname)
     */
    public Friend[] matchFriendSearch(Friend[] haystack, String needle) {
        if (haystack == null || haystack.length == 0) {
            return new Friend[0];
        }
        List<Friend> matches = new ArrayList<Friend>();
        for (Friend f : haystack) {
          if (f.getUsername().indexOf(needle) != -1 ||
                  f.getFirstname().indexOf(needle) != -1 ||
                  f.getLastname().indexOf(needle) != -1) {
              // Friend matched needle
              matches.add(f);
          }
        }

        // Cannot cast Object[] directly to Friend[]
        Friend[] matchesArray = new Friend[matches.size()];
        for (int i = 0; i < matchesArray.length; i++){
            matchesArray[i] = matches.get(i);
        }
        return  matchesArray;
    }

    /**
     * Shows friends that match needle followed by other people who also match needle.
     * @param needle
     */
    private void showSearchResults(final String needle) {
        noFriends.setText("No people found.");
        new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] objects) {
                // Load friend list
                Friend[] friends = null;
                Friend[] people = null;
                Picture[] pictures = null;
                try {
                    System.out.println("Fetching friends...");
                    friends = matchFriendSearch(DataHandler.getInstance().getFriends(), needle);
                    //people =
                    pictures = new Picture[friends.length];/*
                        for (int i = 0; i < pictures.length; i++) {
                            System.out.println("Fetching image " + friends[i].getProfilePic() + " for friend " +
                                friends[i].getFirstname());
                            pictures[i] = DataHandler.getInstance().getPicture(friends[i].getProfilePic());
                        }*/

                    System.out.println("Done.");

                } catch (NotLoggedInException e) {
                    System.out.println("Trying to fetch friends while not logged in!");
                    cancel(true);
                }

                final Friend[] finalFriends = friends;
                final Picture[] finalPictures = pictures;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Showing friends in list...");
                        friendAdapter.setFriends(finalFriends, finalPictures);
                        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                    }
                });

                return null;
            }
        }.execute();
    }
}

class FriendAdapter extends BaseAdapter {

    Context context;

    // The reason for not wrapping these together is that sometimes we want to be able to
    // send just friend info without the overhead of sending the picture. Could be handled
    // differentlyt though for example with a request boolean.
    Friend[] friends;
    Picture[] pictures;

    private static LayoutInflater inflater = null;

    public FriendAdapter(Context context, Friend[] friends, Picture[] pictures) {
        this.context = context;
        this.friends = friends;
        this.pictures = pictures;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (friends != null) {
            return friends.length;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return friends[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setFriends(Friend[] friends, Picture[] pictures) {
        this.friends = friends;
        this.pictures = pictures;
        notifyDataSetChanged();
    }

    public void setPictureFor(int position, Picture pic) {
        if (pictures == null) {
            pictures = new Picture[friends.length];
        }
        if (position >= 0 && position < pictures.length) {
            pictures[position] = pic;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.friend_row, null);
        TextView text = (TextView) view.findViewById(R.id.friendName);
        ImageView image = (ImageView) view.findViewById(R.id.friendPicture);

        text.setText(friends[position].getFirstname() + " " + friends[position].getLastname());
        if (pictures != null && pictures[position] != null) {
            Bitmap bmp;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            bmp = BitmapFactory.decodeByteArray(pictures[position].getImg(), 0,
                    pictures[position].getImg().length, options);
            image.setImageBitmap(bmp);
        }
        return view;
    }
}
