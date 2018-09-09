package com.example.yaratech.yaratube.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


public class LoginOptionFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {
    String TAG = LoginOptionFragment.class.getName();
    private MainLoginContract.onChildButtonClickListener mListener;
    private TransferToFragment transferToFragment;
    private Button loginViaPhoneNumBut, loginViaGoogleBut;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;


    public LoginOptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity) {
            transferToFragment = (TransferToFragment) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListener = (MainLoginContract.onChildButtonClickListener) getParentFragment();

        //return user basic info like userId, user photo + user email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_option, container, false);

        loginViaPhoneNumBut = view.findViewById(R.id.loginViaPhoneNumber);
        loginViaPhoneNumBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToMobileLoginFragment();
            }
        });

        loginViaGoogleBut = view.findViewById(R.id.loginViaGoogle);
        loginViaGoogleBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),"لطفا چند لحظه صبر کنید...", Toast.LENGTH_LONG).show();
                loginViaGoogle();
            }

        });

        return view;
    }


    private void loginViaGoogle(){

        Log.d(TAG, "loginViaGoogle() called");

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    private void handleResult(GoogleSignInResult result){



        if (result.isSuccess()){


            GoogleSignInAccount account = result.getSignInAccount();
//            String name = account.getDisplayName();
//            String email= account.getEmail();
//            String image = account.getPhotoUrl().toString();
//            String token = account.getIdToken();

//            User user = yaraDatabase.selectDao().getUserRecord();
//            user.setNickname(account.getDisplayName());
//            user.setToken(account.getIdToken());
//            yaraDatabase.insertDao().updateUserInfo(user);

            Log.d("TAG", "handleResult() called with: result = [" + result + " , " + account + "]");
            ((DialogFragment) getParentFragment()).dismiss();
            transferToFragment.goToProfileFragment();

        }else {
            Log.d("TAG", "result is not successful");
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed() called with: connectionResult = [" + connectionResult + "]");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE){

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }
}
