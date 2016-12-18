package com.rappitest.nicolasfor.rappitest.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.rappitest.nicolasfor.rappitest.R;
import com.rappitest.nicolasfor.rappitest.helper.Adapter;
import com.rappitest.nicolasfor.rappitest.helper.DataCommunication;

import java.util.List;

/**
 * Created by nicolasfor on 15/12/2016.
 */
public class PostListFragment extends Fragment {

    private View rootView;
    private DataCommunication mCallback;
    ListView list;
    Adapter adapter;

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
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        list= (ListView)rootView.findViewById(R.id.list);
        adapter=new Adapter(getActivity(), mCallback.getPosts());
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                FragmentManager fm = getFragmentManager();
                mCallback.setPosition(position);
                fm.beginTransaction().addToBackStack("Post").replace(R.id.fragment, new PostFragment()).commit();
            }
        });

        return rootView;
    }
}
