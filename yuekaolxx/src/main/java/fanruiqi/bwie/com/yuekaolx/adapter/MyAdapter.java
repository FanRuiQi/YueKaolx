package fanruiqi.bwie.com.yuekaolx.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import fanruiqi.bwie.com.yuekaolx.R;
import fanruiqi.bwie.com.yuekaolx.bean.ResponseBean;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<ResponseBean.DataBean> list;

    public MyAdapter(Context context, List<ResponseBean.DataBean> list) {
        mContext = context;
        this.list = list;
    }

    public static final int ONE=0;
    public static final int TWO=1;
    public static final int THREE=2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==ONE){

            View view = View.inflate(parent.getContext(), R.layout.item_af_one, null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }else if (viewType==TWO){
            View view = View.inflate(parent.getContext(), R.layout.item_af_two, null);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }else {
            View view = View.inflate(parent.getContext(), R.layout.item_af_three, null);
            ViewHolder3 viewHolder3 = new ViewHolder3(view);
            return viewHolder3;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemViewType = holder.getItemViewType();
        if (itemViewType==ONE){

           final ViewHolder viewHolder = (ViewHolder) holder;
           viewHolder.mTextView.setText(list.get(position).getTitle());
            Glide.with(mContext).load(list.get(position).getThumbnail_pic_s()).into(viewHolder.mImageView);
        }else if (itemViewType==TWO){

            final ViewHolder2 viewHolder2 = (ViewHolder2) holder;
            viewHolder2.mTextView.setText(list.get(position).getTitle());
            Glide.with(mContext).load(list.get(position).getThumbnail_pic_s()).into(viewHolder2.mImageView);
            Glide.with(mContext).load(list.get(position).getThumbnail_pic_s02()).into(viewHolder2.mImageView2);
        }else{
            final ViewHolder3 viewHolder3 = (ViewHolder3) holder;
            viewHolder3.mTextView.setText(list.get(position).getTitle());
            Glide.with(mContext).load(list.get(position).getThumbnail_pic_s()).into(viewHolder3.mImageView);
            Glide.with(mContext).load(list.get(position).getThumbnail_pic_s02()).into(viewHolder3.mImageView2);
            Glide.with(mContext).load(list.get(position).getThumbnail_pic_s03()).into(viewHolder3.mImageView3);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        String t2 = list.get(position).getThumbnail_pic_s02();
        String t3 = list.get(position).getThumbnail_pic_s03();

        if (t2==null&&t3==null){
            return ONE;
        }else if (t2!=null&&t3==null){
            return TWO;
        }else {
            return THREE;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.item_af_img);
            mTextView = itemView.findViewById(R.id.item_af_text);
        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView mImageView2;
        ImageView mImageView;
        TextView mTextView;
        public ViewHolder2(View itemView) {
            super(itemView);

            mImageView2 = itemView.findViewById(R.id.item_af_img2);
            mImageView = itemView.findViewById(R.id.item_af_img);
            mTextView = itemView.findViewById(R.id.item_af_text);
        }
    }

    public class ViewHolder3 extends RecyclerView.ViewHolder {
        ImageView mImageView3;
        ImageView mImageView2;
        ImageView mImageView;
        TextView mTextView;
        public ViewHolder3(View itemView) {
            super(itemView);
            mImageView3 = itemView.findViewById(R.id.item_af_img3);
            mImageView2 = itemView.findViewById(R.id.item_af_img2);
            mImageView = itemView.findViewById(R.id.item_af_img);
            mTextView = itemView.findViewById(R.id.item_af_text);
        }
    }
}
