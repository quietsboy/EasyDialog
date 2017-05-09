package com.quietboy.easydialog.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quietboy.easydialog.R;

public class LoadingDailog {


    public static class Builder {

        private String message = "正在加载...";
        private Dialog dialog;

        private boolean cancelable = false;

        private LinearLayout layout;
        ImageView spaceshipImage;
        TextView tipTextView;
        Animation hyperspaceJumpAnimation;

        public Builder(Context context) {

            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.loading_dialog, null);// 得到加载view
            layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
            spaceshipImage = (ImageView) v.findViewById(R.id.img);
            tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
            hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                    context, R.anim.loading_animation);
            spaceshipImage.startAnimation(hyperspaceJumpAnimation);
            if (TextUtils.isEmpty(message)) {
                tipTextView.setVisibility(View.GONE);
            } else {
                tipTextView.setVisibility(View.VISIBLE);
                tipTextView.setText(message);// 设置加载信息
            }
            dialog = new Dialog(context, R.style.LoadingDialog);// 创建自定义样式dialog
            dialog.setCancelable(cancelable);// 不可以用“返回键”取消
            dialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局

        }

        public Builder setMessage(String message) {
            this.message = message;
            tipTextView.setVisibility(View.VISIBLE);
            tipTextView.setText(message);
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            dialog.setCancelable(cancelable);
            return this;
        }


        public Dialog create() {
            return dialog;
        }

        public void show() {
            if (dialog != null)
                dialog.show();
        }

        public void dismiss() {
            if (dialog != null) {
                dialog.dismiss();
            }
        }

    }
}
