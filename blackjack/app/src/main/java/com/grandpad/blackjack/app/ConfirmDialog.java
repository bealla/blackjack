package com.grandpad.blackjack.app;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

//import com.example.testproj.R;

import com.grandpad.blackjack.app.DialogType;

public class ConfirmDialog extends CustomDialog
{

	/**
	 * 
	 * 
	 * @param imgRes
	 *            The image you'd like to show up in the corner of the dialog
	 */
	public ConfirmDialog(String messageText, String titleText, int imgRes, Context context)
	{
		super(messageText, titleText, imgRes, DialogType.CONFIRM, context);

		// by default
		setNegativeButton(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				dialog.dismiss();
			}
		});
	}

	public void setPositiveButton(OnClickListener listener)
	{
		Button yesButton = (Button) dialog.findViewById(R.id.btn_dialog_yes);
		yesButton.setOnClickListener(listener);
	}

	public void setNegativeButton(OnClickListener listener)
	{
		Button noButton = (Button) dialog.findViewById(R.id.btn_dialog_no);
		noButton.setOnClickListener(listener);
	}
}
