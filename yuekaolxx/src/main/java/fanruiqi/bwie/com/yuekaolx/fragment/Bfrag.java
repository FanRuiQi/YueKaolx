package fanruiqi.bwie.com.yuekaolx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import fanruiqi.bwie.com.yuekaolx.R;
import fanruiqi.bwie.com.yuekaolx.adapter.LeftMyAdapter;
import fanruiqi.bwie.com.yuekaolx.adapter.RightAdapter;
import fanruiqi.bwie.com.yuekaolx.bean.LeftBean;
import fanruiqi.bwie.com.yuekaolx.bean.RightBean;
import fanruiqi.bwie.com.yuekaolx.precenter.IPrecenterImpl;
import fanruiqi.bwie.com.yuekaolx.view.IView;

public class Bfrag extends Fragment implements IView{  //分类页面
    private RecyclerView mRecyclerView;
    private ExpandableListView mExpandableListView;
    private IPrecenterImpl iPrecenter;
    private int page=1;
    private String LeftUrl="http://www.zhaoapi.cn/product/getCatagory";
    private String RightUrl="http://www.zhaoapi.cn/product/getProductCatagory";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.bfrag, null);

         mRecyclerView = view.findViewById(R.id.bf_rv);
         mExpandableListView = view.findViewById(R.id.bf_elv);

         iPrecenter = new IPrecenterImpl(this);
        initLeftData();
        initRightData();
        return view;
    }

    private void initLeftData() {

        HashMap<String, Integer> map = new HashMap<>();
        iPrecenter.startRequestData(LeftUrl, LeftBean.class,map);

    }

    private void initRightData() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("cid",page);
        iPrecenter.startRequestData(RightUrl, RightBean.class,map);
    }
    @Override
    public void showData(Object data) {

        if (data instanceof LeftBean){
            LeftBean leftBean= (LeftBean) data;

            List<LeftBean.DataBean> data1 = leftBean.getData();

            LeftMyAdapter leftMyAdapter = new LeftMyAdapter(data1);
            mRecyclerView.setAdapter(leftMyAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

            leftMyAdapter.setOnItemClickListener(new LeftMyAdapter.onItemClickListener() {
                @Override
                public void onItemClick(int position1) {
                   // Toast.makeText(getActivity(),position1+"",Toast.LENGTH_SHORT).show();
                    page=position1;
                   initRightData();
                }
            });
        }else if (data instanceof RightBean){

            RightBean rightBean= (RightBean) data;

            List<RightBean.DataBean> data1 = rightBean.getData();
            RightAdapter rightAdapter = new RightAdapter(getActivity(), data1);
            mExpandableListView.setAdapter(rightAdapter);

            for (int k=0;k<data1.size();k++){
                mExpandableListView.expandGroup(k);
            }
        }

    }
}
