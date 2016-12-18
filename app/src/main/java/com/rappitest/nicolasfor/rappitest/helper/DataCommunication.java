package com.rappitest.nicolasfor.rappitest.helper;

import com.rappitest.nicolasfor.rappitest.model.Post;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolasfor on 15/12/2016.
 */
public interface DataCommunication {

    public List<Post> getPosts();
    public void setPosts(List<Post> posts);

    public int getPosition();
    public void setPosition(int pos);

    public void setActionBar(String name);

    public File getFile();
    public void setFile(File newFile);

}
