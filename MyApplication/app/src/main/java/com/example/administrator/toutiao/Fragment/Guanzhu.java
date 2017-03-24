package com.example.administrator.toutiao.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.toutiao.Bean.GuanzhuBean;
import com.example.administrator.toutiao.Chakan;
import com.example.administrator.toutiao.Database.ShouyeDatabase;
import com.example.administrator.toutiao.R;
import com.example.administrator.toutiao.Util.GuanzhuAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/14.
 */

public class Guanzhu extends Fragment {

    private ListView lv;
    private int id;
    private GuanzhuAdapter adapter;
    private View v;
    private SQLiteDatabase db;
    private ArrayList<GuanzhuBean> list;
    private ArrayList<GuanzhuBean> asa;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.shiping_listview, null);
        }
        lv = (ListView) v.findViewById(R.id.lv);
        return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ShouyeDatabase cdb = new ShouyeDatabase(getActivity());
        db = cdb.getWritableDatabase();
        //查询数据库，把值适配给listview
        getCursorDate();
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
               builder.setTitle("是否取消收藏？");
               builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                    String delete="delete from shouye where _id="+asa.get(position).getId();
                    db.execSQL(delete);
                       asa.remove(asa.get(position));
                       adapter.notifyDataSetChanged();
                       builder.create().dismiss();
                   }
               });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                             builder.create().dismiss();
                    }
                });

                builder.create().show();

               return false;
           }
       });
lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(getActivity(),Chakan.class);
        intent.putExtra("name",asa.get(position).getUrl());
        getActivity().startActivity(intent);
    }
});

    }

//当Fragment隐藏状态改变的时候调用
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    if(hidden!=true){
        getCursorDate();
        asa = new ArrayList<GuanzhuBean>();
        for (int i =list.size()-1; i>=0 ; i--) {
           asa.add(list.get(i));
        }
        adapter = new GuanzhuAdapter(getContext(), asa);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    }

    private void getCursorDate() {
        list = new ArrayList<GuanzhuBean>();
        String sql = "select * from shouye";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            id = cursor.getInt(0);
            String title = cursor.getString(1);
            String pic = cursor.getString(2);
            String url = cursor.getString(3);
            GuanzhuBean bean = new GuanzhuBean(id,title, pic, url);
            list.add(bean);


        }
    }
}
