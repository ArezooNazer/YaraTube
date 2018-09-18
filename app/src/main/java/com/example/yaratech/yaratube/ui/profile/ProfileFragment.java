package com.example.yaratech.yaratube.ui.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.yaratech.yaratube.R;
import com.example.yaratech.yaratube.data.entity.User;
import com.example.yaratech.yaratube.data.model.ProfileGetResponse;

import java.io.File;

import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.example.yaratech.yaratube.data.source.Constant.BASE_URL;
import static com.example.yaratech.yaratube.data.source.Constant.TOKEN;
import static com.example.yaratech.yaratube.ui.profile.PickAvatarDialogFragment.AVATAR_OPTION_DIALOG;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    public static String PROFILE_FRAGMENT = ProfileFragment.class.getName();
    private EditText nicknameET;
    private TextView birthDateET;
    private Button cancelBut, saveBut;
    private RadioGroup radioGroup;
    private RadioButton selectedRadioBut, man, woman;
    private ImageView datePicker, profileAvatar;
    private PersianDatePickerDialog picker;
    private ProfileContract.Presenter mPresenter;
    private String nickname, gender, birthDate, avatarUrl, avatarPath;
    private ProgressBar progressBar;
    private User user;
    private String token;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProfilePresenter(this);
        gender = mPresenter.getUserInfo().getGender();
        token = mPresenter.getUserInfo().getToken();

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
        datePicker = view.findViewById(R.id.editProfileBirthButton);
        profileAvatar = view.findViewById(R.id.profileAvatar);
        progressBar = view.findViewById(R.id.profileProgress);


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
                String genderET = selectedRadioBut.getText().toString();
                if (genderET.equals("مرد"))
                    gender = "Male";
                else
                    gender = "Female";
            }
        });

        profileAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final PickAvatarDialogFragment pickAvatarDialogFragment = PickAvatarDialogFragment.newInstance();
                pickAvatarDialogFragment.setPhotoSelectedListener(new ProfileContract.Listener() {
                    @Override
                    public void photoSelectedListener(String imagePath) {
                        pickAvatarDialogFragment.dismiss();

                        File file = new File(imagePath);
                        long imageSize = file.length() / 1024;

                        if (imageSize > 1024)
                            //TODO: reduce image size
                            showMessage("حجم عکس بیشتر از 1mb است");
                        else {

                            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                            MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);
                            mPresenter.sendUserAvatarToServer(body, "Token " + TOKEN);
                        }
                    }
                });

                pickAvatarDialogFragment.show(getChildFragmentManager(), AVATAR_OPTION_DIALOG);

            }
        });

        saveBut.setOnClickListener(new MyOnClickListener());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //set profile fields
        mPresenter.getProfileFiledFromDb();
        mPresenter.getProfileFieldFromServer("Token " + token);
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


    @Override
    public void showProfileFieldFromServer(ProfileGetResponse profileGetResponse) {

        String avatarUrl = profileGetResponse.getAvatar();
        String name = profileGetResponse.getNickname();
        String gender = (String) profileGetResponse.getGender();
        String birthDate = (String) profileGetResponse.getDateOfBirth();

        if (avatarUrl != null && avatarUrl.startsWith("16/static_files/users"))
            Glide.with(getContext()).load(BASE_URL + avatarUrl).into(profileAvatar);

        if (name != null) {
            nicknameET.setText(name);
        }

        if (gender != null && gender.equals("Female"))
            woman.setChecked(true);
        else if (gender != null && gender.equals("Male"))
            man.setChecked(true);

        if (birthDate != null) {
            birthDateET.setText(birthDate);
        }

        Log.d("showProfileFromServer", "showProfileFieldFromServer() avatarUrl " + avatarUrl + "gender" + gender);

    }

    @Override
    public void showProfileFieldFromDb(User user) {
        String avatarUrl = user.getImage();
        String name = user.getNickname();
        String gender = user.getGender();
        String birthDate = user.getBirthDate();

        Log.d("showProfileFromDb", "showProfileFieldFromDb() avatarUrl " + avatarUrl + " gender " + gender);

        if (avatarUrl != null && avatarUrl.startsWith("16/static_files/users"))
            Glide.with(getContext())
                    .load(BASE_URL + avatarUrl)
                    .into(profileAvatar);

        if (name != null) {
            nicknameET.setText(name);
        }

        if (gender != null && gender.equals("Female"))
            woman.setChecked(true);
        else if (gender != null && gender.equals("Male"))
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
//                if (nickname.equals(""))
//                    nickname = stringGenerator();
//                else
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

    private String dateValidate(int day, int month) {

        String validDay, validMonth, validDate;
        validDay = String.valueOf(day);
        validMonth = String.valueOf(month);

        if (validDay.length() == 1)
            validDay = "0" + String.valueOf(day);
        if (validMonth.length() == 1)
            validMonth = "0" + String.valueOf(month);

        validDate = validMonth + "-" + validDay;

        return validDate;
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
        initDate.setPersianDate(1370, 03, 13);

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
                        String monthAndDate = dateValidate(persianCalendar.getPersianDay(), persianCalendar.getPersianMonth());
                        birthDateET.setText(persianCalendar.getPersianYear() + "-" + monthAndDate);
                    }

                    @Override
                    public void onDismissed() {

                    }
                });

        picker.show();
    }


}