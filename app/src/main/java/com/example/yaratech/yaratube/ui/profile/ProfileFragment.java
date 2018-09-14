package com.example.yaratech.yaratube.ui.profile;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yaratech.yaratube.MainActivity;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.ProfileGetResponse;
import com.example.yaratech.yaratube.util.TransferToFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import static com.example.yaratech.yaratube.data.source.Constant.BASE_URL;
import static com.example.yaratech.yaratube.data.source.Constant.TOKEN;
import static com.example.yaratech.yaratube.util.StringGenerator.stringGenerator;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    public static String PROFILE_FRAGMENT = ProfileFragment.class.getName();
    private EditText nicknameET, birthDateET;
    private Button cancelBut, saveBut;
    private RadioGroup radioGroup;
    private RadioButton selectedRadioBut, man, woman;
    private ImageView datePicker, profileAvatar;
    private PersianDatePickerDialog picker;
    private ProfileContract.Presenter mPresenter;
    private String nickname, gender, birthDate;
    private ProgressBar progressBar;
    private User user;
    private TransferToFragment transferToFragment;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainActivity)
            transferToFragment = (TransferToFragment) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProfilePresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        nicknameET = view.findViewById(R.id.nickname);
        birthDateET = view.findViewById(R.id.birthDate);
        cancelBut = view.findViewById(R.id.cancelBut);
        saveBut = view.findViewById(R.id.saveProfileBut);
        radioGroup = view.findViewById(R.id.radioGroup);
        man = view.findViewById(R.id.radioMan);
        woman = view.findViewById(R.id.radioWoman);
        user = mPresenter.getUserInfo();
        gender = user.getGender();
        datePicker = view.findViewById(R.id.editProfileBirthButton);
        profileAvatar = view.findViewById(R.id.profileAvatar);
        progressBar = view.findViewById(R.id.profileProgress);

        //set profile fields
//        mPresenter.getProfileFieldFromServer("Token " + TOKEN);
        mPresenter.getProfileFiledFromDb();

        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_forward_black_24dp);
            actionBar.setTitle("پروفایل");
        }

        cancelBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar(view);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int selectedId) {
                selectedRadioBut = view.findViewById(selectedId);
                gender = selectedRadioBut.getText().toString();
                Log.d("PROFILE_FRAGMENT", "selectedRadioBut.getText() = [" + gender + "]");
            }
        });

        profileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferToFragment.goToAvatarOptionDialogFragment();
            }
        });

        saveBut.setOnClickListener(new MyOnClickListener());

        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {

        progressBar.setVisibility(View.GONE);
    }


    //TODO: check best practice for filling profile fields : server or db?

    @Override
    public void showProfileField(ProfileGetResponse profileGetResponse) {

        String avatarUrl = profileGetResponse.getAvatar();
        String name = profileGetResponse.getNickname();
        Object gender = profileGetResponse.getGender();
        Object birthDate = profileGetResponse.getDateOfBirth();

        if (avatarUrl != null)
            Glide.with(getContext()).load(BASE_URL + avatarUrl).into(profileAvatar);

        if (name != null) {
            nicknameET.setText(name);
        }

        //TODO: gender and date of birth are null

        Log.d("PROFILE_FRAGMENT", "showProfileField() avatarUrl " + avatarUrl + "name" + name);

    }

    @Override
    public void showProfileFieldFromDb(User user) {
        String avatarUrl = user.getImage();
        String name = user.getNickname();
        String gender = user.getGender();
        String birthDate = user.getBirthDate();

        Log.d("PROFILE_FRAGMENT", "showProfileField() avatarUrl " + avatarUrl + " name" + name);

        if (avatarUrl != null && avatarUrl.startsWith(BASE_URL))
            Glide.with(getContext()).load(BASE_URL + avatarUrl).into(profileAvatar);

        if (name != null) {
            nicknameET.setText(name);
        }

        if (gender != null && gender.equals("زن"))
            woman.setChecked(true);
        else if (gender != null && gender.equals("مرد"))
            man.setChecked(true);

        if (birthDate != null) {
            birthDateET.setText(user.getBirthDate());
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            nickname = nicknameET.getText().toString();
            birthDate = birthDateET.getText().toString();

            if (isAnyFieldChanged(nickname, gender, birthDate)) {
                if (nickname.equals(""))
                    nickname = stringGenerator();

                if (!birthDate.equals("") && !dateValidate(birthDate))
                    showMessage("تاریخ وارد شده معتبر نیست");

                else
                    mPresenter.sendProfileField(nickname, birthDate, gender, "Token " + TOKEN);
            } else {
                showMessage("تغییری ایجاد نشده است");
            }
        }
    }

    public void onBackPressed() {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }

    private boolean dateValidate(String date) {

        String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(date);
        System.out.println(date + " : " + matcher.matches());

        return matcher.matches();
    }

    private boolean isAnyFieldChanged(String currentNickname, String currentGender, String currentBirthDate) {

        user = mPresenter.getUserInfo();
        if (currentNickname.equals(user.getName()) && (currentGender == null || currentGender.equals(user.getGender())) && currentBirthDate.equals(user.getBirthDate()))
            return false;
        else
            return true;
    }

    public void showCalendar(View v) {

        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(1370, 3, 13);

        picker = new PersianDatePickerDialog(getContext())
                .setPositiveButtonString("باشه")
                .setNegativeButton("بیخیال")
                .setTodayButton("امروز")
                .setTodayButtonVisible(true)
                .setMinYear(1300)
                .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
                .setInitDate(initDate)
                .setActionTextColor(Color.GRAY)
                .setListener(new Listener() {
                    @Override
                    public void onDateSelected(PersianCalendar persianCalendar) {
                        birthDateET.setText(persianCalendar.getPersianDay() + "/" + persianCalendar.getPersianMonth() + "/" + persianCalendar.getPersianYear());
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
    }

}
