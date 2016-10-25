package com.chandler.android.aca.kindredspirits;

import java.util.Random;

public class KindBook {

    //todo populate these arrays with more data

    private String[] mKindness = {
        "You matter.",
        "You are enough.",
        "Your smile lights up the room.",
        ""
    };

    private String[] mQuotes = {

    };

    private String[] mRealityCheck = {
        "What you're going through is temporary.",
            "You can survive this.",
            ""
    };

    public String getKindness(){
        String kindness;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mKindness.length);
        kindness = mKindness[randomNumber];

        return kindness;
    } //todo add to method to allow user to choose which arrays are pulled in
}
