package com.fengmi.he.FragmentPakage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fengmi.he.R;

/**
 * Created by Administrator on 2017/4/11.
 */

public class Fragment_meila extends Fragment {

    private RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fr_meila,null);
        rv = (RecyclerView) v.findViewById(R.id.meile_recycle);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StaggeredGridLayoutManager stg = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
        rv.setLayoutManager(stg);

    }
}
