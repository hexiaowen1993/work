package com.example.administrator.volleylianxi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/10.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private JSONArray jsonArray;
    //存储条目id和状态
    private Map<String, Boolean> map = new HashMap<String, Boolean>();
    //声明接口
    public OnCheckLitener litener;

    public interface OnCheckLitener {
        void onCheck(Boolean check);
    }

    //保存条目id和状态
    public void setCheck(Boolean checkFlag) {
        map.clear();

        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                String id = jsonArray.getJSONObject(i).getString("ID");
                map.put(id, checkFlag);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    //跟新状太
    public void niticheck(boolean checkFlag) {
        setCheck(checkFlag);
        notifyDataSetChanged();
    }

    public MyAdapter(Context context, JSONArray jsonArray, OnCheckLitener litener) {
        this.context = context;
        this.jsonArray = jsonArray;
        this.litener=litener;
        setCheck(false);
    }

    public void setData(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        setCheck(false);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return jsonArray != null ? jsonArray.length() : 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.buju, null);
            holder = new ViewHolder();
            holder.box = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.pic = (ImageView) convertView.findViewById(R.id.pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            JSONObject object = jsonArray.getJSONObject(position);
            String title = object.getString("TITLE");
            String imageurl = object.getString("IMAGEURL");
            final String id = object.getString("ID");
            holder.title.setText(title);
            Picasso.with(context).load(imageurl).into(holder.pic);
            holder.box.setChecked(map.get(id));
            holder.box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean check=((CheckBox)v).isChecked();
                    map.put(id,check);
                    boolean ischecked=true;
                    for (String key :
                            map.keySet()) {
                        Boolean value=map.get(key);
                        if(!value){
                             ischecked=false;
                            litener.onCheck(ischecked);
                            return;
                        }
                    }if(ischecked){
                        litener.onCheck(ischecked);

                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }

    class ViewHolder {
        CheckBox box;
        TextView title;
        ImageView pic;
    }

}
