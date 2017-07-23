package com.example.iu.myapplication.net;

import com.example.iu.myapplication.App;
import com.example.iu.myapplication.config.ACache;
import com.example.iu.myapplication.net.callback.MyNetWorkCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2017/7/12.
 */

public class OkhttpUtils implements IHttp{

    private OkHttpClient client ;
    private static OkhttpUtils utils;

    private OkhttpUtils(){
        client =  new OkHttpClient();
    }

    public static OkhttpUtils getInstance(){

        if(utils==null){
            synchronized (OkhttpUtils.class){
                if(utils==null){
                    utils = new OkhttpUtils();
                }

            }
        }

        return utils;
    }


    @Override
    public <T> void get(String url, Map<String, String> params, final MyNetWorkCallBack<T> callBack) {

        StringBuffer sb = new StringBuffer(url);

        if(params!=null&&params.size()>0){

            sb.append("?");

            Set<String> keySet = params.keySet();

            for (String key :keySet){

                String value = params.get(key);

                sb.append(key).append("=").append(value).append("&");

            }
            url = sb.deleteCharAt(sb.length() - 1).toString();

        }

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        callBack.onError(e.getMessage().toString());

                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        App.context.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onSuccess(getGerenic(string,callBack));
                            }
                        });
                    }
                });

            }
        });

    }

    @Override
    public <T> void post(String url, Map<String, String> params, final MyNetWorkCallBack<T> callBack) {


        FormBody.Builder builder = new FormBody.Builder();

        if(params!=null&&params.size()>0){

            Set<String> keySet = params.keySet();

            for (String key : keySet) {

                String value = params.get(key);

                builder.add(key,value);
            }
        }

        Request request = new Request.Builder().post(builder.build()).url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onError(e.getMessage().toString());
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String string = response.body().string();

                App.context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(getGerenic(string,callBack));
                    }
                });

            }
        });


    }

    @Override
    public void upLoad() {

    }

    @Override
    public void downLoad() {

    }

    @Override
    public void imageLoad() {

    }

    private <T> T getGerenic(String jsonDate , MyNetWorkCallBack<T> callBack){

        Gson gson = new Gson();

        Type[] types = callBack.getClass().getGenericInterfaces();

        ParameterizedType type = (ParameterizedType) types[0];

        Type[] typeArguments = type.getActualTypeArguments();

        Type typeArgument = typeArguments[0];

        T t = gson.fromJson(jsonDate, typeArgument);

        ACache aCache = ACache.get(App.context);
        aCache.put(t.getClass().getSimpleName(), (Serializable) t);
        return t;
    }
}
