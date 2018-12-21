package fanruiqi.bwie.com.yuekaolx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import fanruiqi.bwie.com.yuekaolx.R;
import fanruiqi.bwie.com.yuekaolx.adapter.MyAdapter;
import fanruiqi.bwie.com.yuekaolx.bean.ResponseBean;
import fanruiqi.bwie.com.yuekaolx.precenter.IPrecenterImpl;
import fanruiqi.bwie.com.yuekaolx.util.OkUtils;
import fanruiqi.bwie.com.yuekaolx.view.IView;

public class Afrag extends Fragment implements IView{
    private RecyclerView mRecyclerView;
    IPrecenterImpl iPrecenter;
    String urlString="http://www.xieast.com/api/news/news.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.afrag, null);

        mRecyclerView = view.findViewById(R.id.af_rv);

        iPrecenter = new IPrecenterImpl(this);
        initData();
        return view;
    }

    private void initData() {
        HashMap<String, Integer> map = new HashMap<>();
        iPrecenter.startRequestData(urlString,ResponseBean.class,map);

        /*OkUtils.getInstance().doGet(urlString, new OkUtils.OnCallBack() {
            @Override
            public void onFail() {

            }

            @Override
            public void onResponse(String josn) {

                ResponseBean responseBean = new Gson().fromJson(josn, ResponseBean.class);
                List<ResponseBean.DataBean> data = responseBean.getData();

                MyAdapter myAdapter = new MyAdapter(getActivity(), data);
                mRecyclerView.setAdapter(myAdapter);

                mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

            }
        });*/
    }

    @Override
    public void showData(Object data) {
        ResponseBean responseBean= (ResponseBean) data;
        List<ResponseBean.DataBean> data1 = responseBean.getData();

        MyAdapter myAdapter = new MyAdapter(getActivity(), data1);
        mRecyclerView.setAdapter(myAdapter);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPrecenter.close();
    }
}
