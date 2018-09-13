package com.example.yaratech.yaratube.ui.login.loginoption;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.source.Constant;
import com.example.yaratech.yaratube.ui.login.MainLoginContract;
import com.example.yaratech.yaratube.ui.login.MainLoginDialogFragment;
import com.example.yaratech.yaratube.util.TransferToFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;


public class LoginOptionFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, LoginOptionContract.View {
    String TAG = LoginOptionFragment.class.getName();
    private MainLoginContract.onChildButtonClickListener mListener;
    private TransferToFragment transferToFragment;
    private LoginOptionContract.Presenter mPresenter;
    private Button loginViaPhoneNumBut, loginViaGoogleBut;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;
    private ProgressBar progressBar;

    public LoginOptionFragment() {
        // Required empty public constructor
        Log.d(TAG, "LoginOptionFragment() called");
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach() called with: context = [" + context + "]");
        super.onAttach(context);

        if (context instanceof MainActivity) {
            transferToFragment = (TransferToFragment) context;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        mListener = (MainLoginContract.onChildButtonClickListener) getParentFragment();
        mPresenter = new LoginOptionPresenter(this);

        //return user basic info like userId, user photo + user email
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
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
        Log.d(TAG, "onCreateView() called with: inflater ");
        View view = inflater.inflate(R.layout.fragment_login_option, container, false);

        progressBar = view.findViewById(R.id.loginProgressbar);

        loginViaPhoneNumBut = view.findViewById(R.id.loginViaPhoneNumber);
        loginViaPhoneNumBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginViaGoogleBut.setEnabled(false);
                loginViaPhoneNumBut.setEnabled(false);
                mListener.goToMobileLoginFragment();
            }
        });

        loginViaGoogleBut = view.findViewById(R.id.loginViaGoogle);
        loginViaGoogleBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loginViaGoogleBut.setEnabled(false);
                loginViaPhoneNumBut.setEnabled(false);
                Toast.makeText(getContext(), "لطفا چند لحظه صبر کنید...", Toast.LENGTH_LONG).show();
                loginViaGoogle();
            }

        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getContext(), "ارتباط ناموفق، دوباره تلاش کنید.", Toast.LENGTH_LONG).show();
    }

    private void loginViaGoogle() {

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    private void handleResult(GoogleSignInResult result) {

        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();
            String googleToken = account.getIdToken();
            updateUserEntity(account);

            final String Device_id = Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            mPresenter.sendGoogleToken(googleToken, Device_id, Constant.DEVICE_OS, Constant.DEVICE_MODEL);

            Log.d("TAG", "handleResult() called with: result = [" + account.getDisplayName()  + " , " + account.getEmail() + ", "+ account.getPhotoUrl().toString()+ "]");

        } else {
            Log.d("TAG", "result is not successful");
        }
    }

    private void updateUserEntity(GoogleSignInAccount account){
        User user = new User();
        user.setName(account.getDisplayName());
        user.setEmail(account.getEmail());
        user.setImage(account.getPhotoUrl().toString());
        yaraDatabase.insertDao().saveUserInfo(user);
    }

    @Override
    public void googleLoginIsSuccessful() {
        ((DialogFragment) getParentFragment()).dismiss();
        transferToFragment.goToProfileFragment();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
