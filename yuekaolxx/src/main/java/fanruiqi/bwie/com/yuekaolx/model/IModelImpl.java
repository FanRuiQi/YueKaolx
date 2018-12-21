package fanruiqi.bwie.com.yuekaolx.model;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fanruiqi.bwie.com.yuekaolx.adapter.MyAdapter;
import fanruiqi.bwie.com.yuekaolx.bean.ResponseBean;
import fanruiqi.bwie.com.yuekaolx.callback.MyCallBack;
import fanruiqi.bwie.com.yuekaolx.util.OkUtils;

public class IModelImpl implements IModel {

    @Override
    public void requestData(String url, final Class clazz, Map<String,Integer> map, final MyCallBack myCallBack) {

        /*OkUtils.getInstance().doGet(url, new OkUtils.OnCallBack() {
            @Override
            public void onFail() {

            }

            @Override
            public void onResponse(String josn) {

                Object o = new Gson().fromJson(josn,clazz);
                myCallBack.setData(o);
            }
        });*/

        OkUtils.getInstance().doPost(url, map, new OkUtils.OnCallBack() {
            @Override
            public void onFail() {

            }

            @Override
            public void onResponse(String josn) {
                Object o = new Gson().fromJson(josn,clazz);
                myCallBack.setData(o);
            }
        });
    }


}
