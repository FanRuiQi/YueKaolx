package fanruiqi.bwie.com.yuekaolx.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fanruiqi.bwie.com.yuekaolx.R;
import fanruiqi.bwie.com.yuekaolx.bean.CarBean;
import fanruiqi.bwie.com.yuekaolx.bean.RightBean;
import fanruiqi.bwie.com.yuekaolx.util.AddSubView;

public class CarAdapter extends BaseExpandableListAdapter{

    private Context mContext;
    private List<CarBean.DataBean> list;

    public CarAdapter(Context context, List<CarBean.DataBean> list) {
        mContext = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList().size();
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (view==null){

            groupViewHolder = new GroupViewHolder();
            view = View.inflate(viewGroup.getContext(), R.layout.item_cf_group, null);
            groupViewHolder.mCheckBox = view.findViewById(R.id.cf_group_check);
            groupViewHolder.mTextView = view.findViewById(R.id.cf_group_text);
            view.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder) view.getTag();
        }

        groupViewHolder.mTextView.setText(list.get(i).getSellerName());

        boolean sjAllSpSelected = getSjAllSpSelected(i);
        groupViewHolder.mCheckBox.setChecked(sjAllSpSelected);
        groupViewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnListClickListener.onSjChangeListener(i);
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {

        ChildViewHolder childViewHolder;
        if (view==null){

            childViewHolder = new ChildViewHolder();
            view = View.inflate(viewGroup.getContext(), R.layout.item_cf_child, null);
            childViewHolder.mCheckBox = view.findViewById(R.id.cf_child_check);
            childViewHolder.mTextView = view.findViewById(R.id.cf_child_title);
            childViewHolder.mTextView2 = view.findViewById(R.id.cf_child_price);
            childViewHolder.mAddSubView = view.findViewById(R.id.cf_child_add);
            childViewHolder.mImageView = view.findViewById(R.id.cf_child_img);
            view.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }

        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list1 = dataBean.getList();

        childViewHolder.mCheckBox.setChecked(list1.get(i1).getSelected()==1);
        childViewHolder.mTextView.setText(list1.get(i1).getTitle());
        childViewHolder.mTextView2.setText(list1.get(i1).getPrice()+"");
        childViewHolder.mAddSubView.setNumber(list1.get(i1).getNum());
        //Glide.with(mContext).load(list1.get(i1).getIcon()).into(childViewHolder.mImageView);

        childViewHolder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnListClickListener.onSpChangeListener(i,i1);
            }
        });

        childViewHolder.mAddSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int number) {
                mOnListClickListener.onNumChangeListener(i,i1,number);
            }
        });
        return view;
    }

    public boolean getSjAllSpSelected(int i){
        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
        for (CarBean.DataBean.ListBean bean:list1){
            if (bean.getSelected()==0){
                return false;
            }
        }
        return true;
    }

    public boolean getAllSpSelected(){

        for (int i=0;i<list.size();i++){
            CarBean.DataBean dataBean = list.get(i);
            List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
            for (int j=0;j<list1.size();j++){
                if (list1.get(j).getSelected()==0){
                    return false;
                }
            }
        }
        return true;
    }

    public int getTotalNum(){
        int totalNumber=0;
        for (int i=0;i<list.size();i++){
            CarBean.DataBean dataBean = list.get(i);
            List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
            for (int j=0;j<list1.size();j++){
                if (list1.get(j).getSelected()==1){
                    int num = list1.get(j).getNum();
                    totalNumber+=num;
                }
            }
        }
        return totalNumber;
    }

    public float getTotalPrice(){
        int totalPrice=0;
        for (int i=0;i<list.size();i++){
            CarBean.DataBean dataBean = list.get(i);
            List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
            for (int j=0;j<list1.size();j++){
                if (list1.get(j).getSelected()==1){
                    int num = list1.get(j).getNum();
                    float price = (float) list1.get(j).getPrice();

                    totalPrice+=num*price;
                }
            }
        }
        return totalPrice;
    }

    public void sjChangeSp(int i,boolean isSelected){
        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
        for (int j=0;j<list1.size();j++){
            CarBean.DataBean.ListBean bean = list1.get(j);
            bean.setSelected(isSelected?1:0);
        }
    }

    public void changeSp(int i,int i1){
        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
        CarBean.DataBean.ListBean bean = list1.get(i1);
        bean.setSelected(bean.getSelected()==0?1:0);
    }

    public void qxChangeAllSp(boolean isSelected){

        for (int i=0;i<list.size();i++){
            CarBean.DataBean dataBean = list.get(i);
            List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
            for (int j=0;j<list1.size();j++){
                list1.get(j).setSelected(isSelected?1:0);
            }
        }

    }

    public void changeNumber(int i,int i1,int number){
        CarBean.DataBean dataBean = list.get(i);
        List<CarBean.DataBean.ListBean> list1 = dataBean.getList();
        CarBean.DataBean.ListBean bean = list1.get(i1);
        bean.setNum(number);
    }

    public interface OnListClickListener{
        void onSjChangeListener(int i);
        void onSpChangeListener(int i,int i1);
        void onNumChangeListener(int i,int i1,int number);
    }

    OnListClickListener mOnListClickListener;

    public void setOnListClickListener(OnListClickListener onListClickListener){
        mOnListClickListener=onListClickListener;
    }

    class GroupViewHolder{
        CheckBox mCheckBox;
        TextView mTextView;

    }

    class ChildViewHolder{
        CheckBox mCheckBox;
        ImageView mImageView;
        TextView mTextView;
        TextView mTextView2;
        AddSubView mAddSubView;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
