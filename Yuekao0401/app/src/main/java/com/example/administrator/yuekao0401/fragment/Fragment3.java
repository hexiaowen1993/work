package com.example.administrator.yuekao0401.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.amiao.managlibary.dao.MangDao;
import com.example.administrator.yuekao0401.Databases.CreateDatabases;
import com.example.administrator.yuekao0401.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/1.
 */
/*
 *类的用途：频道管理
 *@name:贺晓雯
 *@date:{2017/4/2}
 */

public class Fragment3 extends Fragment {

    private GridView gv1;
    private GridView gv2;
    private SQLiteDatabase db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pindaoguanli, null);
        gv1 = (GridView) v.findViewById(R.id.gv1);
        gv2 = (GridView) v.findViewById(R.id.gv2);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MangDao dao=new MangDao(getContext());
       String[] mytitle={"热点","新闻","体育","动态"};
        final String[] other={"美女","房车","喜欢","个性"};
        final ArrayList<String> otherlist=new ArrayList<String>();
        for (int i = 0; i <other.length ; i++) {
            dao.addMore(other[i]);
            otherlist.add(other[i]);
        }
        final ArrayList<String> mylist=new ArrayList<String>();
        for (int i = 0; i <mytitle.length ; i++) {
            dao.addMy(mytitle[i]);
          mylist.add(mytitle[i]);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mylist);
        gv1.setAdapter(adapter);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, otherlist);
        gv2.setAdapter(adapter1);
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  dao.addMore(mylist.get(position));
               //dao.deleteMy(mylist.get(position));
               dao.getMyTable(otherlist,mylist,mylist.get(position));
                adapter.notifyDataSetChanged();
                adapter1.notifyDataSetChanged();

            }
        });
        gv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // dao.addMy(otherlist.get(position));
                //dao.deleteMore(otherlist.get(position));
                dao.getMoreTable(otherlist,mylist,otherlist.get(position));
                adapter1.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        });


    }



}
