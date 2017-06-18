package xh.life.box.callback;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.view.Window;

import com.lzy.okgo.request.BaseRequest;

import wai.gr.cla.callback.JsonCallback;

/**
 * ================================================
 * 作    者：廖子尧
 * 版    本：1.0
 * 创建日期：2016/1/14
 * 描    述：对于网络请求是否需要弹出进度对话框
 * 修订历史：
 * ================================================
 */
public abstract class DialogCallback<T> extends JsonCallback<T> {

    private ProgressDialog dialog;
    boolean isCloseEnd = true;

    /**
     * @param activity
     * @param closeEnd
     */
    private void initDialog(Activity activity, boolean closeEnd) {
        dialog = new ProgressDialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
        isCloseEnd = closeEnd;
    }

    public DialogCallback(Activity activity, boolean closeEnd) {
        super();
        initDialog(activity, closeEnd);
    }

    public DialogCallback(Activity activity) {
        super();
        initDialog(activity, true);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        //网络请求前显示对话框
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing() && isCloseEnd) {
            dialog.dismiss();
        }
    }
}
