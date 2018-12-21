package fanruiqi.bwie.com.yuekaolx.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import fanruiqi.bwie.com.yuekaolx.R;
import fanruiqi.bwie.com.yuekaolx.bean.RightBean;

public class RightAdapter extends BaseExpandableListAdapter{

    private Context mContext;
    private List<RightBean.DataBean> list;

    public RightAdapter(Context context, List<RightBean.DataBean> list) {
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
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder;
        if (view==null){

            groupViewHolder = new GroupViewHolder();
            view = View.inflate(viewGroup.getContext(), R.layout.item_right_group, null);
            groupViewHolder.mTextView = view.findViewById(R.id.item_right_text);
            view.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder) view.getTag();
        }

        groupViewHolder.mTextView.setText(list.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        ChildViewHolder childViewHolder;
        if (view==null){

            childViewHolder = new ChildViewHolder();
            view = View.inflate(viewGroup.getContext(), R.layout.item_right_child, null);
            childViewHolder.mTextView = view.findViewById(R.id.item_right_child_text);
            childViewHolder.mImageView = view.findViewById(R.id.item_right_child_img);
            view.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }

        RightBean.DataBean dataBean = list.get(i);
        List<RightBean.DataBean.ListBean> list1 = dataBean.getList();

        childViewHolder.mTextView.setText(list1.get(i1).getName());
        Glide.with(mContext).load(list1.get(i1).getIcon()).into(childViewHolder.mImageView);
        return view;
    }

    class GroupViewHolder{
        TextView mTextView;

    }

    class ChildViewHolder{
        TextView mTextView;

        ImageView mImageView;
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
