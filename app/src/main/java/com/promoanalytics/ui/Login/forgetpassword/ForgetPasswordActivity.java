package com.promoanalytics.ui.Login.forgetpassword;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.promoanalytics.R;

public class ForgetPasswordActivity extends AppCompatActivity implements GetOtpFragment.OnFragmentInteractionListener, VerifyOtpFragment.OnFragmentInteractionListener, ChangePasswordFragment.OnFragmentInteractionListener {

    private GetOtpFragment getOtpFragment;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        if (savedInstanceState == null) {
            // withholding the previously created fragment from being created again
            // On orientation change, it will prevent fragment recreation
            // its necessary to reserving the fragment stack inside each tab
            initScreen();

        } else {
            // restoring the previously created fragment
            // and getting the reference
            getOtpFragment = (GetOtpFragment) getSupportFragmentManager().getFragments().get(0);
        }


    }


    private void initScreen() {
        // Creating the ViewPager container fragment once
        getOtpFragment = new GetOtpFragment();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, getOtpFragment)
                .commit();
    }

    /**
     * Only Activity has this special callback method
     * Fragment doesn't have any onBackPressed callback
     * <p/>
     * Logic:
     * Each time when the back button is pressed, this Activity will propagate the call to the
     * container Fragment and that Fragment will propagate the call to its each tab Fragment
     * those Fragments will propagate this method call to their child Fragments and
     * eventually all the propagated calls will get back to this initial method
     * <p/>
     * If the container Fragment or any of its Tab Fragments and/or Tab child Fragments couldn't
     * handle the onBackPressed propagated call then this Activity will handle the callback itself
     */
    @Override
    public void onBackPressed() {

        if (!getOtpFragment.onBackPressed()) {
            // container Fragment or its associates couldn't handle the back pressed task
            // delegating the task to super class

            if (doubleBackToExitPressedOnce) {
//                super.onBackPressed();
                super.onBackPressed();
//                finish();
                return;
            }

            this.doubleBackToExitPressedOnce = true;

            Toast.makeText(this, "Please BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {

                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        } else {
            // carousel handled the back pressed task
            // do not call super


        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
