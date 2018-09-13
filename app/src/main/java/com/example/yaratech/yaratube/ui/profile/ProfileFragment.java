package com.example.yaratech.yaratube.ui.profile;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;

import static com.example.yaratech.yaratube.MainActivity.yaraDatabase;
import static com.example.yaratech.yaratube.util.StringGenerator.stringGenerator;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    public static String PROFILE_FRAGMENT = ProfileFragment.class.getName();
    private EditText nicknameET, birthDateET;
    private Button cancelBut, saveBut;
    private RadioGroup radioGroup;
    private RadioButton selectedRadioBut, man, woman;
    private ImageView datePicker;
    private PersianDatePickerDialog picker;
    private ProfileContract.Presenter mPresenter;
    private String nickname, gender, birthDate;
    private User user;

    public ProfileFragment() {
        // Required empty public constructor
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
        user = yaraDatabase.selectDao().getUserRecord();
        gender = user.getGender();
        datePicker = view.findViewById(R.id.editProfileBirthButton);


        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_forward_black_24dp);
            actionBar.setTitle("پروفایل");
        }

        //set profile fields
        readUserInfoAndSetProfileFields();

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

        saveBut.setOnClickListener(new MyOnClickListener());

        return view;
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void readUserInfoAndSetProfileFields() {

        user = yaraDatabase.selectDao().getUserRecord();
        if (user.getName() != null) {
            nicknameET.setText(user.getName());
        }

        if (gender != null && gender.equals("زن"))
            woman.setChecked(true);
        else if (gender != null && gender.equals("مرد"))
            man.setChecked(true);

        if (user.getBirthDate() != null) {
            birthDateET.setText(user.getBirthDate());
        }
    }

    private class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            nickname = nicknameET.getText().toString();
            birthDate = birthDateET.getText().toString();
            final String token = yaraDatabase.selectDao().selectToken();

            Log.d("PROFILE_FRAGMENT", "gender  = [" + gender + "], user.getGender() = [" + user.getGender() + "]\n " +
                    "nickname =  [" + nickname + "], user.getName() = [" + user.getName() + "]\n");

            if (isAnyFieldChanged(nickname, gender, birthDate)) {
                if (nickname.equals(""))
                    nickname = stringGenerator();

                if (!birthDate.equals("") && !dateValidate(birthDate))
                    showMessage("تاریخ وارد شده معتبر نیست");

                else
                    mPresenter.sendProfileField(nickname, birthDate, gender, "Token " + token);
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

        user = yaraDatabase.selectDao().getUserRecord();
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
