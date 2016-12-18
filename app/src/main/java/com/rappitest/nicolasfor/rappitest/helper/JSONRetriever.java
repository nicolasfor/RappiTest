package com.rappitest.nicolasfor.rappitest.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import com.rappitest.nicolasfor.rappitest.model.Post;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolasfor on 15/12/2016.
 */
public class JSONRetriever {

    private final String URL="url";
    private final String BANNER="banner_img";
    private final String HEADER_TITLE="display_name";
    private final String ID="id";
    private final String ICON="icon_img";
    private final String HEADER_IMAGE="header_img";
    private final String DESCRIPTION_TEXT="public_description";
    private final String DESCRIPTION_TEXT_HTML="description_html";
    private final String SUBMIT_TEXT="submit_text_label";
    private final String SUBMIT_HTML="submit_text_html";
    private final String SUBS="subscribers";
    private final String OVER_18="over18";
    private final String CREATED="created";
    private final String KEY_COLOR="key_color";
    private final String URL_TEMPLATE=
            "https://www.reddit.com/SUBREDDIT_NAME"
                    +".json";

    String subreddit;
    String url;
    public JSONRetriever(String sr){
        subreddit=sr;
        generateURL();
    }

    private void generateURL(){
        url=URL_TEMPLATE.replace("SUBREDDIT_NAME", subreddit);
    }

    public List<Post> fetchPosts(){
        List<Post> list=new ArrayList<Post>();
        try{
            String raw= new HTTP().execute(url).get();
            JSONObject data=new JSONObject(raw)
                    .getJSONObject("data");
            JSONArray children=data.getJSONArray("children");

            for(int i=0;i<children.length();i++){
                JSONObject cur=children.getJSONObject(i)
                        .getJSONObject("data");
                ArrayList<String> array=new ImageManager().execute( cur.optString(BANNER),cur.optString(ICON),cur.optString(HEADER_IMAGE)).get();
                Post p=new Post(cur.optString(ID), cur.optString(HEADER_TITLE),cur.optString(URL),cur.optInt("num_comments"),
                        cur.optInt(SUBS),cur.optString(HEADER_TITLE),cur.optString("subreddit"),cur.optString(DESCRIPTION_TEXT),cur.optString(DESCRIPTION_TEXT_HTML),cur.optString(SUBMIT_TEXT),cur.optString(SUBMIT_HTML), cur.getBoolean(OVER_18),cur.optInt(CREATED), cur.optString(KEY_COLOR),StringToBitMap(array.get(0)),StringToBitMap(array.get(1)),StringToBitMap(array.get(2)));
                if(p.getTitle()!=null)
                    list.add(p);
            }
        }catch(Exception e){
            Log.e("fetchPosts()",e.toString());
        }
        return list;
    }



    public List<Post> fetchPostsFromArray( JSONArray children){
        List<Post> list=new ArrayList<Post>();
        try{
            for(int i=0;i<children.length();i++){
                JSONObject cur=children.getJSONObject(i)
                        .getJSONObject("contact_group");
                Post p=new Post(cur.optString(ID), cur.optString(HEADER_TITLE),cur.optString(URL),cur.optInt("num_comments"),
                        cur.optInt("subscribers"),cur.optString(HEADER_TITLE),cur.optString("subreddit"),cur.optString(DESCRIPTION_TEXT),cur.optString(DESCRIPTION_TEXT_HTML),cur.optString(SUBMIT_TEXT),cur.optString(SUBMIT_HTML), cur.getBoolean(OVER_18),cur.optInt(CREATED), cur.optString(KEY_COLOR),StringToBitMap(cur.getString(BANNER)),StringToBitMap(cur.getString(ICON)),StringToBitMap(cur.getString(HEADER_IMAGE)));
                if(p.getTitle()!=null)
                    list.add(p);
            }
        }

        catch(Exception e){
        Log.e("fetchPosts()",e.toString());
         }
        return list;
    }

    public Bitmap StringToBitMap(String encodedString){
        if(encodedString!=null && !encodedString.isEmpty()) {
            try {
                byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                return bitmap;
            } catch (Exception e) {
                e.getMessage();
            }
        }
        return null;
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    public JSONObject createGroupInServer(List<Post> posts)
            throws JSONException {

        JSONObject jResult = new JSONObject();
        JSONArray jArray = new JSONArray();

        for (int i = 0; i < posts.size(); i++) {
            JSONObject jGroup = new JSONObject();
            jGroup.put(ID, posts.get(i).getId());
            jGroup.put(HEADER_TITLE, posts.get(i).getHeaderTitle());
            jGroup.put(URL, posts.get(i).getUrl());
            jGroup.put("num_comments", posts.get(i).getNumComments());
            jGroup.put(SUBS, posts.get(i).getSubscribers());
            jGroup.put(HEADER_TITLE, posts.get(i).getTitle());
            jGroup.put("subreddit", posts.get(i).getSubreddit());
            jGroup.put(DESCRIPTION_TEXT, posts.get(i).getDescription());
            jGroup.put(DESCRIPTION_TEXT_HTML, posts.get(i).getDescriptionHtml());
            jGroup.put(SUBMIT_TEXT, posts.get(i).getSubmitText());
            jGroup.put(SUBMIT_HTML, posts.get(i).getSubmitTextHtml());
            jGroup.put(OVER_18, posts.get(i).isOver18());
            jGroup.put(CREATED, posts.get(i).getCreated());
            jGroup.put(KEY_COLOR, posts.get(i).getKeyColor());
            jGroup.put(BANNER, posts.get(i).getBanner_img()!=null?BitMapToString(posts.get(i).getBanner_img()):"null");
            jGroup.put(ICON, posts.get(i).getIcon()!=null?BitMapToString(posts.get(i).getIcon()):"null");
            jGroup.put(HEADER_IMAGE, posts.get(i).getHeaderImage()!=null?BitMapToString(posts.get(i).getHeaderImage()):"null");

            JSONObject jOuter = new JSONObject();
            jOuter.put("contact_group", jGroup);

            jArray.put(jOuter);
        }

        jResult.put("recordset", jArray);
        return jResult;
    }
}
