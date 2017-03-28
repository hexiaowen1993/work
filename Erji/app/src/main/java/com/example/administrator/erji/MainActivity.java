package com.example.administrator.erji;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] group;
    private String[][] child;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExpandableListView elv= (ExpandableListView) findViewById(R.id.elv);
        group = new String[]{
                "a","b","c","d"
        };
        child = new String[][]{
                {"a","b"},{"c","d","e"},{"f"},{"g"}
        };
      MyAdapter adapter=new MyAdapter();
        elv.setAdapter(adapter);
    }
    public class MyAdapter extends BaseExpandableListAdapter{


        @Override
        public int getGroupCount() {
            return group.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return child[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return group[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return child[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return  childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
          ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.group,null);
                holder=new ViewHolder();
                holder.t1= (TextView) convertView.findViewById(R.id.group);
           convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.t1.setText(group[groupPosition].toString());
            return convertView;
        }
class ViewHolder{
    TextView t1;
}
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
          ViewHolder2 holder2;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.child,null);
                holder2=new ViewHolder2();
                holder2.t2 = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder2);
            }else{
                holder2= (ViewHolder2) convertView.getTag();
            }
            Log.d("ghghghghgh",child[groupPosition][childPosition]+"");
            holder2.t2.setText(child[groupPosition][childPosition]);
            return convertView;
        }
    class ViewHolder2{
    TextView t2;
}
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }
}
