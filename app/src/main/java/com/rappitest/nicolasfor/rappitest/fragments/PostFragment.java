package com.rappitest.nicolasfor.rappitest.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.rappitest.nicolasfor.rappitest.R;
import com.rappitest.nicolasfor.rappitest.helper.DataCommunication;
import com.rappitest.nicolasfor.rappitest.model.Post;

import java.util.List;

/**
 * Created by nicolasfor on 15/12/2016.
 */
public class PostFragment extends Fragment {
    private View rootView;
    private TextView title;
    private TextView description;
    private TextView submit;
    private TextView id;
    private TextView over18;
    private TextView subs;
    private TextView created;
    private ImageView banner;
    private ImageView headerImage;
    private DataCommunication mCallback;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_item, container, false);
        title= (TextView)rootView.findViewById(R.id.title);
        description= (TextView)rootView.findViewById(R.id.description);
        submit= (TextView)rootView.findViewById(R.id.submit);
        id=(TextView)rootView.findViewById(R.id.idPost);
        banner=(ImageView)rootView.findViewById(R.id.banner);
        headerImage=(ImageView)rootView.findViewById(R.id.header_image);
        over18=(TextView)rootView.findViewById(R.id.over_18);
        subs=(TextView)rootView.findViewById(R.id.subscribers);
        created=(TextView)rootView.findViewById(R.id.created);
        List<Post> posts=mCallback.getPosts();

        String keyColor=posts.get(mCallback.getPosition()).getKeyColor();
        title.setText(posts.get(mCallback.getPosition()).getTitle());
        if(keyColor!=null && !keyColor.equals("") && !keyColor.equals("null"))
            title.setTextColor(Color.parseColor(keyColor));
        id.setText(posts.get(mCallback.getPosition()).getId());
        Spanned over18Text=Html.fromHtml("<strong>PG 18+: </strong>"+(posts.get(mCallback.getPosition()).isOver18()?" YES":" NO"));
        over18.setText(over18Text);
        Spanned createdText=Html.fromHtml("<strong>CREATED: </strong>"+posts.get(mCallback.getPosition()).getCreated());
        created.setText(createdText);
        Spanned subsText=Html.fromHtml("<strong>SUBSCRIBERS: </strong>"+posts.get(mCallback.getPosition()).getSubscribers());
        subs.setText(subsText);
        mCallback.setActionBar(posts.get(mCallback.getPosition()).getHeaderTitle());
        Spanned descriptionText=Html.fromHtml("<strong>DESCRIPTION: </strong>"+Html.fromHtml(posts.get(mCallback.getPosition()).getDescriptionHtml()).toString());
        description.setText((descriptionText!=null && !descriptionText.toString().isEmpty())?descriptionText:"There is no description yet!");
        Spanned submitText=Html.fromHtml("<strong>SUBMIT: </strong>"+Html.fromHtml(posts.get(mCallback.getPosition()).getSubmitTextHtml()).toString());
        submit.setText((submitText!=null && !submitText.toString().isEmpty())?submitText:"There is no submit yet!");
        banner.setImageBitmap(posts.get(mCallback.getPosition()).getBanner_img());
        headerImage.setImageBitmap(posts.get(mCallback.getPosition()).getHeaderImage());

        return rootView;
    }

}
