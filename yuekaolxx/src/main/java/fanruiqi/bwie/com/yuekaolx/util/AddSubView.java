package fanruiqi.bwie.com.yuekaolx.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import fanruiqi.bwie.com.yuekaolx.R;

public class AddSubView extends LinearLayout implements View.OnClickListener{

    private int number=1;
    private TextView mTextView_jian;
    private TextView mTextView_num;
    private TextView mTextView_jia;

    public AddSubView(Context context) {
        this(context,null);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddSubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = inflate(context, R.layout.add_remove, this);
         mTextView_jian = view.findViewById(R.id.sub_jian);
         mTextView_num = view.findViewById(R.id.sub_num);
         mTextView_jia = view.findViewById(R.id.sub_jia);

         mTextView_jian.setOnClickListener(this);
         mTextView_jia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.sub_jian:

                if (number>1){

                    number--;

                    mTextView_num.setText(number+"");

                    if (mOnNumberChangeListener!=null){
                        mOnNumberChangeListener.onNumberChange(number);
                    }
                }else {
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.sub_jia:

                    number++;

                    mTextView_num.setText(number+"");

                    if (mOnNumberChangeListener!=null){
                        mOnNumberChangeListener.onNumberChange(number);
                    }

                break;
        }
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        mTextView_num.setText(number+"");
    }

    public interface OnNumberChangeListener{
        void onNumberChange(int number);
    }

    OnNumberChangeListener mOnNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener){
        mOnNumberChangeListener=onNumberChangeListener;
    }
}
