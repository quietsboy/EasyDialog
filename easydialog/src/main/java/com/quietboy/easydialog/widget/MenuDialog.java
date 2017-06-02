package com.quietboy.easydialog.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.quietboy.easydialog.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RZKJ on 2017/6/2.
 */

public class MenuDialog {

    public static class Builder {

        private Context mContext;
        private Dialog mDialog;
        private TextView txt_title;
        private TextView txt_cancel;
        private LinearLayout lLayout_content;
        private ScrollView sLayout_content;
        private boolean showTitle = false;
        private List<SheetItem> sheetItemList;
        private DisplayMetrics mMetrics;
        private OnItemClickListener mOnItemClickListener;

        public Builder(Context context) {
            mContext = context;
            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            mMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(mMetrics);

            View view = LayoutInflater.from(context).inflate(
                    R.layout.view_actionsheet, null);

            view.setMinimumWidth(mMetrics.widthPixels);

            sLayout_content = (ScrollView) view.findViewById(R.id.sLayout_content);
            lLayout_content = (LinearLayout) view
                    .findViewById(R.id.lLayout_content);
            txt_title = (TextView) view.findViewById(R.id.txt_title);
            txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
            txt_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });

            mDialog = new Dialog(context, R.style.ActionSheetDialogStyle);
            mDialog.setContentView(view);
            Window dialogWindow = mDialog.getWindow();
            dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.x = 0;
            lp.y = 0;
            dialogWindow.setAttributes(lp);

        }

        public Builder setTitle(String title) {
            showTitle = true;
            txt_title.setVisibility(View.VISIBLE);
            txt_title.setText(title);
            return this;
        }

        public Builder setCancelable(boolean cancel) {
            mDialog.setCancelable(cancel);
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean cancel) {
            mDialog.setCanceledOnTouchOutside(cancel);
            return this;
        }

        public Builder addSheetItem(String strItem, @NonNull @ColorRes int color) {
            if (sheetItemList == null) {
                sheetItemList = new ArrayList<SheetItem>();
            }
            sheetItemList.add(new SheetItem(strItem, color));
            return this;
        }

        public Builder addSheetItem(String strItem) {
            if (sheetItemList == null) {
                sheetItemList = new ArrayList<SheetItem>();
            }
            sheetItemList.add(new SheetItem(strItem, R.color.dialogTextColor));
            return this;
        }

        public Builder addSheetItem(List<String> list) {
            if (sheetItemList == null) {
                sheetItemList = new ArrayList<SheetItem>();
            }
            for (int index = 0; index < list.size(); index++)
                sheetItemList.add(new SheetItem(list.get(index), R.color.dialogTextColor));
            return this;
        }

        public Builder addSheetItem(String[] list) {
            if (sheetItemList == null) {
                sheetItemList = new ArrayList<SheetItem>();
            }
            for (int index = 0; index < list.length; index++)
                sheetItemList.add(new SheetItem(list[index], R.color.dialogTextColor));
            return this;
        }

        public Builder addSheetItem(List<String> list, @NonNull @ColorRes int color) {
            if (sheetItemList == null) {
                sheetItemList = new ArrayList<SheetItem>();
            }
            for (int index = 0; index < list.size(); index++)
                sheetItemList.add(new SheetItem(list.get(index), color));
            return this;
        }

        public Builder addSheetItem(String[] list, @NonNull @ColorRes int color) {
            if (sheetItemList == null) {
                sheetItemList = new ArrayList<SheetItem>();
            }
            for (int index = 0; index < list.length; index++)
                sheetItemList.add(new SheetItem(list[index], color));
            return this;
        }

        private void setSheetItems() {
            if (sheetItemList == null || sheetItemList.size() <= 0) {
                return;
            }

            int size = sheetItemList.size();

            if (size >= 7) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) sLayout_content
                        .getLayoutParams();
                params.height = mMetrics.heightPixels / 2;
                sLayout_content.setLayoutParams(params);
            }

            for (int i = 1; i <= size; i++) {
                final int index = i;
                SheetItem sheetItem = sheetItemList.get(i - 1);
                String strItem = sheetItem.name;
                int color = sheetItem.color;

                TextView textView = new TextView(mContext);
                textView.setText(strItem);
                textView.setTextSize(18);
                textView.setGravity(Gravity.CENTER);

                if (size == 1) {
                    if (showTitle) {
                        textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.actionsheet_single_selector);
                    }
                } else {
                    if (showTitle) {
                        if (i >= 1 && i < size) {
                            textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                        } else {
                            textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                        }
                    } else {
                        if (i == 1) {
                            textView.setBackgroundResource(R.drawable.actionsheet_top_selector);
                        } else if (i < size) {
                            textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                        } else {
                            textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                        }
                    }
                }

                textView.setTextColor(ContextCompat.getColor(mContext, color));

                float scale = mContext.getResources().getDisplayMetrics().density;
                int height = (int) (45 * scale + 0.5f);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, height));
                lLayout_content.addView(textView);
            }
            for (int index = 0; index < lLayout_content.getChildCount(); index++) {
                final int position = index;
                lLayout_content.getChildAt(index).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick(position, sheetItemList.get(position).name);
                        }
                    }
                });
            }

        }

        public Dialog create() {
            setSheetItems();
            return mDialog;
        }

        public void show() {
            setSheetItems();
            mDialog.show();
        }

        public Builder setOnItemClickListener(OnItemClickListener onItemClickListener) {
            mOnItemClickListener = onItemClickListener;
            return this;
        }


    }

    public interface OnItemClickListener {
        void onItemClick(int position, String content);
    }

    public static class SheetItem {
        String name;
        @ColorRes
        int color;

        public SheetItem(String name, @ColorRes int color) {
            this.name = name;
            this.color = color;
        }

        public SheetItem(String name) {
            this.name = name;
        }
    }

}
