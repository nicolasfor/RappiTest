package com.rappitest.nicolasfor.rappitest.helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.rappitest.nicolasfor.rappitest.model.Post;
import com.rappitest.nicolasfor.rappitest.R;
import java.util.List;

/**
 * Created by nicolasfor on 15/12/2016.
 */
public class Adapter extends BaseAdapter {
    private Activity activity;
    private List<Post> data;
    private static LayoutInflater inflater=null;

    public Adapter(Activity a, List<Post> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.list_row, null);

        TextView title = (TextView)vi.findViewById(R.id.title); // title
        TextView id = (TextView)vi.findViewById(R.id.id); // artist name
        TextView suscribers = (TextView)vi.findViewById(R.id.subs); // duration
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); // thumb image


        title.setText(data.get(position).getTitle());
        id.setText(data.get(position).getId());
        suscribers.setText(data.get(position).getSubscribers()+"");
        thumb_image.setImageBitmap(data.get(position).getIcon());
        return vi;
    }
}
