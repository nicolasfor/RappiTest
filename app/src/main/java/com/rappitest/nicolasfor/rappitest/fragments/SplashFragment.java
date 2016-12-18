package com.rappitest.nicolasfor.rappitest.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.rappitest.nicolasfor.rappitest.R;
import com.rappitest.nicolasfor.rappitest.helper.DataCommunication;
import com.rappitest.nicolasfor.rappitest.helper.JSONRetriever;
import com.rappitest.nicolasfor.rappitest.model.Post;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by nicolasfor on 16/12/2016.
 */
public class SplashFragment extends Fragment {

    private View rootView;
    private DataCommunication mCallback;
    static final String JSON = "reddits";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (DataCommunication) getActivity();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.progress_bar, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        new Thread(new Runnable() {
            public void run() {
                try {
                    ConnectivityManager conMgr = (ConnectivityManager) getActivity()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo i = conMgr.getActiveNetworkInfo();
                    JSONRetriever json = new JSONRetriever(JSON);
                    List<Post> posts = new ArrayList<Post>();
                        if (mCallback.getPosts().isEmpty()) {

                            SharedPreferences sharedPref = getActivity().getSharedPreferences( "appData", Context.MODE_WORLD_WRITEABLE );
                            if(sharedPref.contains("json")){
                                String s=sharedPref.getString("json", "DEFAULT");
                                Log.e("EXISTE",s);
                                JSONObject obj = new JSONObject(s);
                                Log.e("POST",obj.toString());
                                posts= json.fetchPostsFromArray(obj.getJSONArray("recordset"));
                                mCallback.setPosts(posts);
                            }
                            else {
                            if(i.isAvailable() && i.isConnected()) {
                                posts = json.fetchPosts();
                                mCallback.setPosts(posts);
                                SharedPreferences.Editor prefEditor = getActivity().getSharedPreferences("appData", Context.MODE_WORLD_WRITEABLE).edit();
                                JSONObject object = json.createGroupInServer(posts);
                                prefEditor.putString("json", object.toString());
                                Log.e("JSON-", object.toString());
                                prefEditor.commit();
                            }

                            }

                        }

                }

                catch (Exception e) {
                    e.printStackTrace();

                }
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.fragment, new PostListFragment()).commit();
            }
        }).start();

        return rootView;
    }



}