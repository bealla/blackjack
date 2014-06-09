package com.grandpad.blackjack.app;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.testproj.R;

import com.grandpad.blackjack.app.DialogType;

public class CustomDialog
{
	protected Dialog dialog;
	private final Context context;

	public CustomDialog(String messageText, String titleText, int imgRes, DialogType type, Context context)
	{
		this.context = context;

		dialog = new Dialog(context, R.style.ThemeDialogCustom);
		dialog.getWindow();

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialog.setContentView(type == DialogType.ALERT ? R.layout.alert_dialog : R.layout.confirm_dialog);

		dialog.setCancelable(false);
		TextView titleView = (TextView) dialog.findViewById(R.id.tv_dialog_title);
		titleView.setText(titleText);

		TextView messageView = (TextView) dialog.findViewById(R.id.tv_dialog_message);
		messageView.setText(messageText);

		ImageView image = (ImageView) dialog.findViewById(R.id.iv_dialog_image);
		image.setImageResource(imgRes);
	}

	public void show()
	{
		int dHeight = (7 * context.getResources().getDisplayMetrics().heightPixels) / 10;
		int dWidth = (8 * context.getResources().getDisplayMetrics().widthPixels) / 10;

		dialog.getWindow().setLayout(dWidth, dHeight);
		dialog.show();
	}

	public void dismiss()
	{
		dialog.dismiss();
	}
}
