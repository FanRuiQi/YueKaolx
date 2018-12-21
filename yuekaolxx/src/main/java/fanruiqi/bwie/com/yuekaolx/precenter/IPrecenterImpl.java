package fanruiqi.bwie.com.yuekaolx.precenter;

import java.util.HashMap;
import java.util.Map;

import fanruiqi.bwie.com.yuekaolx.callback.MyCallBack;
import fanruiqi.bwie.com.yuekaolx.model.IModelImpl;
import fanruiqi.bwie.com.yuekaolx.view.IView;

public class IPrecenterImpl implements IPrecenter {

    IView mIView;
    IModelImpl iModel;

    public IPrecenterImpl(IView IView) {
        mIView = IView;

         iModel = new IModelImpl();
    }

    @Override
    public void startRequestData(String url, Class clazz, Map<String,Integer> map) {
        iModel.requestData(url, clazz,map, new MyCallBack() {
            @Override
            public void setData(Object data) {

                mIView.showData(data);
            }
        });
    }


    public void close(){
        if (mIView!=null){
            mIView=null;
        }
        if (iModel!=null){
            iModel=null;
        }
    }
}
