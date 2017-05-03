package baidumaplocation.bawei.com.recyclemoreitem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/5/3.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<infoBean.ItemsBean> items;
    private int T1 = 0;
    private int T2 = 1;

    private View inflate;
    private String url;

    public MyAdapter(Context context, List<infoBean.ItemsBean> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case 0:
                inflate = View.inflate(context, R.layout.item, null);
                viewHolder = new V1(inflate);
                break;
            case 1:
                View view = View.inflate(context, R.layout.item1, null);
                viewHolder = new V2(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                V1 v1 = (V1) holder;
                ImageLoader.getInstance().displayImage("http://img.dxycdn.com/avatars/120/" + items.get(position).getInfoAvatar(), v1.item1_pic);
                v1.item1_name.setText(items.get(position).getNickname());
                v1.item1_context.setText(items.get(position).getContent());
                break;

            case 1:
                V2 v2 = (V2)holder;
                ImageLoader.getInstance().displayImage("http://img.dxycdn.com/avatars/120/" + items.get(position).getInfoAvatar(), v2.item2_pic);
                v2.item2_name.setText(items.get(position).getNickname());
                v2.item2_context.setText(items.get(position).getContent());
                ImageLoader.getInstance().displayImage("http://res.dxycdn.com/upload/" +url,v2.item2_image );

                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        String content = items.get(position).getContent();
        Gson gson = new Gson();
        ContextBean contextBean = gson.fromJson(content, ContextBean.class);
        url = contextBean.getUrl();
        if (url != null) {
            return T2;
        } else {
            return T1;
        }
    }

    class V1 extends RecyclerView.ViewHolder {

        private final ImageView item1_pic;
        private final TextView item1_name;
        private final TextView item1_context;

        public V1(View itemView) {
            super(itemView);
            item1_pic = (ImageView) itemView.findViewById(R.id.item1_pic);
            item1_name = (TextView) itemView.findViewById(R.id.item1_name);
            item1_context = (TextView) itemView.findViewById(R.id.item1_context);

        }
    }

    class V2 extends RecyclerView.ViewHolder {

        private final ImageView item2_pic;
        private final ImageView item2_image;
        private final TextView item2_name;
        private final TextView item2_context;

        public V2(View itemView) {
            super(itemView);
            item2_pic = (ImageView) itemView.findViewById(R.id.item2_pic);
            item2_image = (ImageView) itemView.findViewById(R.id.item2_image);
            item2_name = (TextView) itemView.findViewById(R.id.item2_name);
            item2_context = (TextView) itemView.findViewById(R.id.item2_context);
        }
    }
}
