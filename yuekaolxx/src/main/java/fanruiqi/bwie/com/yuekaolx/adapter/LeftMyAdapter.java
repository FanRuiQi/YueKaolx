package fanruiqi.bwie.com.yuekaolx.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fanruiqi.bwie.com.yuekaolx.R;
import fanruiqi.bwie.com.yuekaolx.bean.LeftBean;

public class LeftMyAdapter extends RecyclerView.Adapter<LeftMyAdapter.ViewHolder>{

    List<LeftBean.DataBean> list;

    public LeftMyAdapter(List<LeftBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public LeftMyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_left, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public interface onItemClickListener{
        void onItemClick(int position1);
    }

    onItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull final LeftMyAdapter.ViewHolder holder, int position) {

        holder.mTextView.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position1 = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(position1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);

            mTextView= itemView.findViewById(R.id.item_left_text);
        }
    }
}
