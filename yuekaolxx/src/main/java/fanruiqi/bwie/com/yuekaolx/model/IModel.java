package fanruiqi.bwie.com.yuekaolx.model;

import java.util.HashMap;
import java.util.Map;

import fanruiqi.bwie.com.yuekaolx.callback.MyCallBack;

public interface IModel {
    void requestData(String url, Class clazz, Map<String,Integer> map, MyCallBack myCallBack);

}
