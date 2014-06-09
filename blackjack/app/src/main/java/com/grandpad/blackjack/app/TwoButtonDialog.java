package com.grandpad.blackjack.app;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.Button;

//import com.example.testproj.R;

import com.grandpad.blackjack.app.DialogType;

public class TwoButtonDialog extends CustomDialog
{

	/**
	 * 
	 * 
	 * @param imgRes
	 *            The image you'd like to show up in the corner of the dialog
	 */
	public TwoButtonDialog(String messageText, String titleText, int imgRes, Context context)
	{
		super(messageText, titleText, imgRes, DialogType.CONFIRM, context);

		Button btn = (Button) dialog.findViewById(R.id.btn_dialog_no);
		btn.setText("");

		Button btn2 = (Button) dialog.findViewById(R.id.btn_dialog_yes);
		btn2.setText("");
	}

	/**
	 * 
	 * 
	 * @param firstButton
	 *            Whether the button is the first (left to right) or not
	 */
	public void setButton(boolean firstButton, String text, OnClickListener listener)
	{
		Button btn = (Button) dialog.findViewById(firstButton ? R.id.btn_dialog_no : R.id.btn_dialog_yes);
		btn.setText(text);
		btn.setOnClickListener(listener);
	}

}
