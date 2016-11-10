package com.chandler.android.aca.kindredspirits;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

    @BindView(R.id.buttonNoteActivity) Button mNoteActivity;
    @BindView(R.id.buttonKindnessActivity) Button mKindnessActivity;
    @BindView(R.id.buttonHotlineActivity) Button mHotlineActivity;
    @BindView(R.id.buttonLoginActivity) Button mLoginActivity;
    @BindView(R.id.buttonRegistrationActivity) Button mRegistrationActivity;
    @BindView(R.id.buttonBreathingActivity) Button mBreathingActivity;
    @BindView(R.id.buttonSafe) Button mSafeActivity;
    @BindView(R.id.button3) Button mFirebase;
    @BindView(R.id.button5) Button mLogout;
    @BindView(R.id.userTextView) TextView mUser;
    @BindView(R.id.imageBackground) ImageView mBackground;

    FirebaseAuth mFirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    String firebaseUser;

    private DrawerLayout mDrawerLayout;

    private EventBus mBus = EventBus.getDefault();
    String mUsername;
    String mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new DrawerBuilder().withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .build();

        ButterKnife.bind(this);
        Picasso.with(this).load(R.drawable.wallpaper).fit().into(mBackground);

        mAuthListener = new FirebaseAuth.AuthStateListener(){
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
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        return false;
                    }
                });

        //user account header
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                //.withHeaderBackground()
                .withSelectionListEnabled(true)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Name") //todo event bus
                                .withEmail("Email")
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
                .withName("Home")
                .withIcon(GoogleMaterial.Icon.gmd_account_balance)
                .withTextColorRes(R.color.md_white_1000);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("Artist")
                .withIcon(GoogleMaterial.Icon.gmd_perm_identity)
                .withTextColorRes(R.color.md_white_1000);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName("Artwork")
                .withIcon(GoogleMaterial.Icon.gmd_brush)
                .withTextColorRes(R.color.md_white_1000);
        //can add others later
        SecondaryDrawerItem item7 = new SecondaryDrawerItem()
                .withIdentifier(7)
                .withName("Favorites")
                .withIcon(GoogleMaterial.Icon.gmd_favorite)
                .withTextColorRes(R.color.md_white_1000);
        SecondaryDrawerItem item8 = new SecondaryDrawerItem()
                .withIdentifier(8)
                .withName("About")
                .withIcon(GoogleMaterial.Icon.gmd_help)
                .withTextColorRes(R.color.md_white_1000);

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
                        item7,
                        item8
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //Intent here
                        return false;
                    }
                })
                .build();
        //end MaterialDrawer

        mUser.setText(firebaseUser);

        mRegistrationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });

        mNoteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoteActivity.class));
            }
        });

        mKindnessActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, KindWordsActivity.class));
            }
        });

        mHotlineActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HotlineActivity.class));
            }
        });

        mLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        mBreathingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BreathingActivity.class));
            }
        });

        mSafeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SafeCircle.class));
            }
        });

        mFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FirebaseTest.class));
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(firebaseUser == null) {

                    Toast.makeText(getApplicationContext(), "No users logged in", Toast.LENGTH_SHORT).show();
                }
                else {

                    firebaseUser = mFirebaseAuth.getCurrentUser().toString();
                    Log.e("User: ", "" + firebaseUser + " signing out...");
                    mFirebaseAuth.signOut();
                    Toast.makeText(getApplicationContext(), "Logging out...", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}
