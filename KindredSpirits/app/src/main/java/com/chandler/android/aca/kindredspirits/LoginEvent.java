package com.chandler.android.aca.kindredspirits;

public class LoginEvent {

    public final String mUserEmailBus;
    public final String mUserNameBus;
    public final String mFirstNameBus;

    public LoginEvent(String userEmail, String userName, String firstName){
        this.mUserEmailBus = userEmail;
        this.mUserNameBus = userName;
        this.mFirstNameBus = firstName;
    }
}
