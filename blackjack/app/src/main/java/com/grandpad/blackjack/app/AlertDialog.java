package com.grandpad.blackjack.app;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


//import com.example.testproj.R;

import com.grandpad.blackjack.app.DialogType;

public class AlertDialog extends CustomDialog
{
	/**
	 * 
	 * 
	 * @param imgRes
	 *            The image you'd like to show up in the corner of the dialog
	 * @param finish
	 *            Whether you want the calling activity to finish when OK is hit
	 */
	public AlertDialog(String messageText, String titleText, int imgRes, final boolean finish, final Context context)
	{
		super(messageText, titleText, imgRes, DialogType.ALERT, context);

		Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog_ok);
		dialogButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{

				if (finish) ((Activity) context).finish();

				dialog.dismiss();
			}
		});

	}


}
