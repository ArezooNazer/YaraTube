package com.example.daryacomputer.yaratube.ui.login.activationcodelogin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSListener extends BroadcastReceiver {

    String TAG = SMSListener.class.getName();
    private static ActivationContract.OTPListener otpListener;


    @Override
    public void onReceive(Context context, Intent intent) {
        // this function is trigged when each time a new SMS is received on device.

        Bundle data = intent.getExtras();

        //PDU = Protocol Data Unit
        Object[] pdus = new Object[0];
        if (data != null) {
            pdus = (Object[]) data.get("pdus"); // the pdus key will contain the newly received SMS
        }

        if (pdus != null) {
            for (Object pdu : pdus) { // loop through and pick up the SMS of interest

                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                String message = smsMessage.getDisplayMessageBody();

                //find numerical code from sms body using regex(regular expression)
                Pattern p = Pattern.compile("\\d+");
                Matcher m = p.matcher(message);
                int sms;

                if (m.find()) {
                    do {
                        sms = Integer.parseInt(m.group());
                        Log.d("TAG", String.valueOf(sms));
                    } while (m.find());
                } else
                    sms = 0;


                if (otpListener != null)
                    otpListener.onOTPReceived(String.valueOf(sms));
                break;
            }
        }

    }

    public static void bindListener(ActivationContract.OTPListener listener) {
        otpListener = listener;
    }

    public static void unbindListener() {
        otpListener = null;
    }
}
