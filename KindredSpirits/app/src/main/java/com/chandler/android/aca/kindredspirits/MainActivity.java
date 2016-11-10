package com.chandler.android.aca.kindredspirits;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    String firebaseUser;

    private DrawerLayout mDrawerLayout;

    @BindView(R.id.imageBackground) ImageView mBackground;

    @BindView(R.id.imageViewBreathing)
    ImageButton mBreathingImage;
    @BindView(R.id.seekBar)
    SeekBar mSeekBar;
    @BindView(R.id.textViewSeeker)
    TextView mSeekerText;
    @BindView(R.id.buttonPause)
    Button mPauseButton;

    Animation mAnimZoomInOut;
    // Animation mAnimZoomOut;
    int mSeekSpeedProgress = 3000;
    int repeat;

    private EventBus mBus = EventBus.getDefault();
    String mUsername;
    String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);


        mFirebaseAuth = FirebaseAuth.getInstance();
        String email = mFirebaseAuth.getCurrentUser().getEmail();

        if (mFirebaseAuth.getCurrentUser() == null){
            startActivity(new Intent(this, LoginActivity.class));
        } else {firebaseUser = mFirebaseAuth.getCurrentUser().toString();}

        new DrawerBuilder().withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .build();



        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.v("FirebaseAuth", "onAuthStateChanged:signed_in:" + user.getUid());
                    firebaseUser = user.getUid();
                } else {
                    // User is signed out
                    Log.v("FirebaseAuth", "onAuthStateChanged:signed_out");
                    firebaseUser = "No one is logged in";
                }

            }
        };

        //Begin MaterialDrawer setup
        //get user login data
        ProfileSettingDrawerItem profileSettings = new ProfileSettingDrawerItem()
                .withIdentifier(100)
                .withName("Logout")
                .withIcon(GoogleMaterial.Icon.gmd_exit_to_app)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        mFirebaseAuth.signOut();
                        Toast.makeText(getApplicationContext(), "Signing out...", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });

        //user account header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
               // .withHeaderBackground()
                .withSelectionListEnabled(true)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("") //todo event bus
                                .withEmail(email)
                                //.withIcon(mFirebaseAuth.getCurrentUser().getPhotoUrl())
                                .withIcon(R.mipmap.ic_launcher),
                        profileSettings
                )
                .withDividerBelowHeader(true)
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //add items to drawer
        PrimaryDrawerItem item1 = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName("Deep Breaths")
                .withIcon(GoogleMaterial.Icon.gmd_account_balance)
                .withTextColorRes(R.color.md_white_1000);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("Kind Words")
                .withIcon(GoogleMaterial.Icon.gmd_perm_identity)
                .withTextColorRes(R.color.md_white_1000);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName("Journal")
                .withIcon(GoogleMaterial.Icon.gmd_brush)
                .withTextColorRes(R.color.md_white_1000);
        //can add others later
        SecondaryDrawerItem item4 = new SecondaryDrawerItem()
                .withIdentifier(4)
                .withName("Hotlines")
                .withIcon(GoogleMaterial.Icon.gmd_favorite)
                .withTextColorRes(R.color.md_white_1000);
        SecondaryDrawerItem item5 = new SecondaryDrawerItem()
                .withIdentifier(5)
                .withName("Safe Circle")
                .withIcon(GoogleMaterial.Icon.gmd_help)
                .withTextColorRes(R.color.md_white_1000);
        SecondaryDrawerItem item6 = new SecondaryDrawerItem()
                .withIdentifier(6)
                .withName("About")
                .withIcon(GoogleMaterial.Icon.gmd_help)
                .withIconColorRes(R.color.md_white_1000);

        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withSliderBackgroundColor(getResources().getColor(R.color.colorPrimaryDark))
                .withSelectedItem(-1)
                .addDrawerItems(
                        item1,
                        item2,
                        item3,
                        new DividerDrawerItem(),
                        item4,
                        item5,
                        item6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        goToActivity(position);
                        return false;
                    }
                })
                .build();
        //end MaterialDrawer


        Toast.makeText(this, "Tap the circle to begin", Toast.LENGTH_LONG).show();


        mBreathingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAnimZoomInOut.setDuration(mSeekSpeedProgress);
                mBreathingImage.startAnimation(mAnimZoomInOut);

            }
        });

        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mBreathingImage.setAnimation(null);

            }
        });


        mSeekerText.setText("Change the pace using the seekbar");
        loadAnimations();
        repeat = 30;

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                mSeekSpeedProgress = value + 3000;
                mSeekerText.setText("" + (mSeekSpeedProgress/1000) + " seconds");
                mAnimZoomInOut.setDuration(mSeekSpeedProgress);
                Toast.makeText(MainActivity.this, "Tap the circle to set new pace", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void loadAnimations() {

        mAnimZoomInOut = AnimationUtils.loadAnimation(this, R.anim.zoom_in_out);

    }

    public void goToActivity(int position){
        Activity activity = null;
        Fragment fragment = null;
        Intent intent;

        switch (position){
            case 1:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, KindWordsActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, NoteActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, HotlineActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, SafeCircle.class));
                break;
            case 7:
                //todo about fragment
        }
    }
}
/*    //this sends the bus to all subscribers
    mBus.postSticky(new LoginEvent(mUserName.getText().toString()));

    //Recieving event when it happens
    @Subscribe
    public void onLoginEvent(LoginEvent event){
        mUsername = event.mUserNameBus;
        mEmail = event.mUserEmailBus;
    }


    Receiving login event when it happens
    Using sticky - true telling the activity to go and get the last LoginEvent

    Subscribe    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onLoginEvent(LoginEvent event) {
        mUserStatus.setText("User Status: Logged In, userName: " + event.mUserName);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBus.register(this); //registering the event bus
    }

    @Override
    public void onStop() {
        mBus.unregister(this); //unregister the event bus
        super.onStop(); //nothing will be completed after this line is executed
    }

    @Override
    protected void onDestroy() {
        mBus.unregister(this);
        super.onDestroy();
    }*/

