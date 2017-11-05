package com.sample.testapp.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.sample.testapp.R;

/**
 * Created by srikanth on 28/10/2017.
 */

public class ServiceErrorDialog extends BaseDialog {

    String mCancelTitle, mReportTitle, mErrorTitle;
    protected TextView mCancelButton, mReportButton, errorTitle;

    OnClickDialogButton cancelOnClick;
    OnClickDialogButton reportOnClick;

    public ServiceErrorDialog(Context context) {
        super(context);
        getWindow();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_action_view);
        mCancelButton = (TextView) findViewById(R.id.bt_cancel);
        mReportButton = (TextView) findViewById(R.id.bt_report);
        errorTitle = (TextView) findViewById(R.id.error_title);

        mReportButton.setVisibility(mReportTitle != null ? View.VISIBLE : View.GONE);

        if (cancelOnClick != null) {
            mCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancelOnClick.onClick(ServiceErrorDialog.this, v, null);
                }
            });
        } else {
            mCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
        }

        if (reportOnClick != null) {
            mReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reportOnClick.onClick(ServiceErrorDialog.this, v, null);
                }
            });
        } else {
            mReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancel();
                }
            });
        }
        setDoneButtonTitle(mCancelTitle).setOptionalButtonTitle(mReportTitle).setErrorTitle(mErrorTitle);
    }

    public ServiceErrorDialog setDoneButtonTitle(String title) {
        mCancelButton.setText(title);
        return this;
    }

    public ServiceErrorDialog setOptionalButtonTitle(String title) {
        mReportButton.setText(title);
        return this;
    }

    public ServiceErrorDialog setErrorTitle(String title){
        errorTitle.setText(title);
        return this;
    }

    public ServiceErrorDialog show(String cancelTitle, OnClickDialogButton onBtDoneClick) {
        mCancelTitle = cancelTitle;
        cancelOnClick = onBtDoneClick;
        show();
        return this;
    }

    public ServiceErrorDialog show(String cancelTitle, String reportTitle,
                                     OnClickDialogButton onCancelClick, OnClickDialogButton onReportClick) {
        mCancelTitle = cancelTitle;
        mReportTitle = reportTitle;

        cancelOnClick = onCancelClick;
        reportOnClick = onReportClick;
        show();
        return this;
    }

    public ServiceErrorDialog show(String dialogTitle, String cancelTitle, String reportTitle,
                                     OnClickDialogButton onCancelClick, OnClickDialogButton onReportClick) {
        mCancelTitle = cancelTitle;
        mReportTitle = reportTitle;
        mErrorTitle = dialogTitle;
        cancelOnClick = onCancelClick;
        reportOnClick = onReportClick;
        show();
        return this;
    }

    public ServiceErrorDialog show(OnClickDialogButton onBtDoneClick) {
        cancelOnClick = onBtDoneClick;
        show();
        return this;
    }

}
