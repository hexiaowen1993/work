package baidumaplocation.bawei.com.recyclemoreitem;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by Administrator on 2017/5/3.
 */

public class Image  {
    public static DisplayImageOptions options(int id){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(id).showImageForEmptyUri(id).cacheInMemory(true).cacheOnDisk(true).build();
        return options;
    }

}
