package com.rappitest.nicolasfor.rappitest.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.rappitest.nicolasfor.rappitest.fragments.SplashFragment;
import com.rappitest.nicolasfor.rappitest.helper.DataCommunication;
import com.rappitest.nicolasfor.rappitest.model.Post;
import com.rappitest.nicolasfor.rappitest.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCommunication{


    private List<Post> posts;
    private int position;
    private File fileCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts=new ArrayList<>();
        position=0;
        Fragment fragment = new SplashFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
        setActionBar("Reddit");

    }

    @Override
    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public void setPosts(List<Post> posts){
        this.posts=posts;
    }

    @Override
    public void setActionBar(String name){
        getSupportActionBar().setTitle(name);
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int pos) {
        this.position=pos;
    }

    @Override
    public File getFile(){
        return fileCache;
    }

    @Override
    public void setFile(File newFile){
        this.fileCache=newFile;
    }
}
