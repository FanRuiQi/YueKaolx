package fanruiqi.bwie.com.yuekaolx.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import fanruiqi.bwie.com.yuekaolx.R;
import fanruiqi.bwie.com.yuekaolx.adapter.CarAdapter;
import fanruiqi.bwie.com.yuekaolx.bean.CarBean;
import fanruiqi.bwie.com.yuekaolx.precenter.IPrecenterImpl;
import fanruiqi.bwie.com.yuekaolx.view.IView;

public class Cfrag extends Fragment implements IView,View.OnClickListener{
    private ExpandableListView mExpandableListView;
    private CheckBox mCheckBox;
    private TextView mTextView;
    private Button mButton;
    IPrecenterImpl iPrecenter;
    CarAdapter carAdapter;
    private String url="http://www.zhaoapi.cn/product/getCarts";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.cfrag, null);

        mExpandableListView = view.findViewById(R.id.cf_elv);
         mCheckBox = view.findViewById(R.id.cf_check);
         mTextView = view.findViewById(R.id.cf_price);
         mButton = view.findViewById(R.id.cf_btn);

         mCheckBox.setOnClickListener(this);
         iPrecenter = new IPrecenterImpl(this);
        initData();
        return view;
    }

    private void initData() {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("uid",71);
        iPrecenter.startRequestData(url, CarBean.class,map);
    }

    @Override
    public void showData(Object data) {

        CarBean carBean= (CarBean) data;
        List<CarBean.DataBean> data1 = carBean.getData();
        carAdapter = new CarAdapter(getActivity(), data1);
        mExpandableListView.setAdapter(carAdapter);

        for (int p=0;p<data1.size();p++){
            mExpandableListView.expandGroup(p);
        }

        carAdapter.setOnListClickListener(new CarAdapter.OnListClickListener() {
            @Override
            public void onSjChangeListener(int i) {

                boolean sjAllSpSelected = carAdapter.getSjAllSpSelected(i);
                carAdapter.sjChangeSp(i,!sjAllSpSelected);
                carAdapter.notifyDataSetChanged();
                refresh();
            }

            @Override
            public void onSpChangeListener(int i, int i1) {

                carAdapter.changeSp(i,i1);
                carAdapter.notifyDataSetChanged();
                refresh();
            }

            @Override
            public void onNumChangeListener(int i, int i1, int number) {

                carAdapter.changeNumber(i,i1,number);
                carAdapter.notifyDataSetChanged();
                refresh();
            }
        });
    }


    public void refresh(){

        boolean allSpSelected = carAdapter.getAllSpSelected();
        mCheckBox.setChecked(allSpSelected);

        float price = carAdapter.getTotalPrice();
        mTextView.setText("总价￥:"+price);

        int num = carAdapter.getTotalNum();
        mButton.setText("结算("+num+")");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cf_check:

                boolean allSpSelected = carAdapter.getAllSpSelected();
                carAdapter.qxChangeAllSp(!allSpSelected);
                carAdapter.notifyDataSetChanged();
                refresh();
                break;
        }
    }
}
