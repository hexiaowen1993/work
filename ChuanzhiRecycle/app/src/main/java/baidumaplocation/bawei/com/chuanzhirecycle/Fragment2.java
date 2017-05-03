package baidumaplocation.bawei.com.chuanzhirecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class Fragment2 extends Fragment {

    private RecyclerView fra_recy2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=View.inflate(getContext(),R.layout.fragment2,null);
        fra_recy2 = (RecyclerView) v.findViewById(R.id.fra_recy2);
        return  v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fra_recy2.setLayoutManager(new GridLayoutManager(getContext(),4));
        Fragment1 fragmentleft = (Fragment1) getFragmentManager().findFragmentById(R.id.frag_left);
          fragmentleft.setOnTransValues(new Fragment1.OnTrans() {
              @Override
              public void trans(List<Bean.ResultBean.BrandsBean.ProductsBean> banlist) {
                  MyAdapter myAdapter=new MyAdapter(banlist);
                  fra_recy2.setAdapter(myAdapter);
              }
          });
    }
   public  class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder>{
            private List<Bean.ResultBean.BrandsBean.ProductsBean> list;

       public MyAdapter(List<Bean.ResultBean.BrandsBean.ProductsBean> list) {
           this.list = list;
       }

       @Override
       public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          View v=View.inflate(getActivity(),R.layout.frag_item2,null);
           MyViewHolder holder=new MyViewHolder(v);
           return holder;
       }

       @Override
       public void onBindViewHolder(MyViewHolder holder, int position) {
           holder.name.setText(list.get(position).getName());
           Glide.with(getActivity()).load(list.get(position).getPic()).into(holder.pic);

       }

       @Override
       public int getItemCount() {
           return list.size();
       }

       public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView pic;
            TextView name;

           public MyViewHolder(View itemView) {
               super(itemView);
               pic = (ImageView) itemView.findViewById(R.id.pic);
               name = (TextView) itemView.findViewById(R.id.name);
           }
       }
   }


}
