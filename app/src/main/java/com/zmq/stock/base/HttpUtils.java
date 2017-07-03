package com.zmq.stock.base;

import android.app.ProgressDialog;
import android.content.Context;

import com.okhttplib.HttpInfo;
import com.okhttplib.OkHttpUtil;
import com.okhttplib.callback.Callback;
import com.zmq.stock.utils.DialogUtil;
import com.zmq.stock.utils.LogUtils;

import java.io.IOException;
import java.util.Map;


/**
 * Created by Administrator on 2017/5/24.
 */

public class HttpUtils {
    private Context context;
    private RequestResult requestResult;
    private static String mRequestCode;
    private ProgressDialog dialog;
    private static final String url = "";

    public HttpUtils(Context context, RequestResult requestResult, String msg, boolean isShow) {
        this.dialog = DialogUtil.getProgressDialog(context, msg);
        if (isShow){
            if (dialog != null){
                dialog.show();
            }
        }
        this.context = context;
        this.requestResult = requestResult;
    }

    /**
     * 异步请求：回调方法可以直接操作UI
     */
    public void async(String requestCode, Map<String,String> data) {
        this.mRequestCode = requestCode;
        LogUtils.d(""+mRequestCode);
        OkHttpUtil.getDefault(context).doGetAsync(
                HttpInfo.Builder().setUrl(url).addParams(data).build(),
                new Callback() {
                    @Override
                    public void onFailure(HttpInfo info) throws IOException {
                        String result = info.getRetDetail();
                        if (dialog != null){
                            dialog.dismiss();
                        }
                        if (requestResult != null) {
                            requestResult.onFailure(result, mRequestCode);
                        }
                    }

                    @Override
                    public void onSuccess(HttpInfo info) throws IOException {
                        String result = info.getRetDetail();
                        if (dialog != null){
                            dialog.dismiss();
                        }
                        if (requestResult != null) {
                            requestResult.onSuccess(result, mRequestCode);
                        }
                    }
                });
    }
}
