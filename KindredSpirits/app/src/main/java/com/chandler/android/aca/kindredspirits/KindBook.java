package com.chandler.android.aca.kindredspirits;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KindBook {

    //todo populate these arrays with more data

    public String[] mKindness = {
            "You matter.",
            "You are enough.",
            "Your smile lights up the room.",
            "I'm so glad you exist.",
            "What you're going through is temporary.",
            "You can survive this.",
    };

    public String[] mKnope = {
            "You beautiful tropical fish.",
            "You’re smart as a whip and you’re cool under pressure.",
            "You are such a good friend.",
            "You’re a beautiful, talented, brilliant, powerful musk-ox",
            "You cunning, pliable, chestnut-haired sunfish",
            "You’re a genius! Your brain is almost as perfect as your face",
            "You beautiful, naïve, sophisticated newborn baby.",
            "You poetic, noble land-mermaid.",
            "You beautiful, rule-breaking moth.",
            "You beautiful, devious bastard.",
            "You beautiful spinster. I will find you love.",
            "You wild, majestic unicorn.",
            "You’re so sweet and innocent and pretty.",
            "I always forget because you're so pretty, you're not used to rejection.",
            "You are so brilliant and kind and stupid hot, you’re definitely " +
                    "going to find a wonderful person who loves you and respects you " +
                    "and fills your home with beautiful genius babies.",
            "You’re so beautiful. I mean, you’re always beautiful, " +
                    "but right now you’re the most beautiful, glowing, sun goddess ever.",
            "You spectacular, golden-tongued sunflower.",
            "You are the greatest human being ever invented.",
            "There is nothing harder in the entire world than saying no to your beautiful face.",
            "You tricky minx.",
            "Now I have two best friends: you and you. Each one more beautiful than the other.",
            "You're beautiful and you're organized!",
            "No one can fill your shoes, with your perfect little doll feet.",
            "You beautiful, coy bastard.",
            "You are the smartest, most accomplished person I know. And check out that bod!",
            "You barrell of monkeys...and kindness.",
            "You beautiful, confused defender of justice.",
            "You magnificent tree of life.",
            "You delicious stack of waffles.",
            "You equine, L'il Sebastian-spirited ballerina.",
            "You sly industrial park.",
            "You impish, beautiful liger.",
            "You spectacular cloud of brilliance.",
            "You compassionate ranger of parks.",
            "You unstoppable, glamorous freight train.",
            "You gorgeous, simple house cat.",
            "You fluorescent light bulb of truth.",
            "You handsome, genius child.",
            "You clever, wonderful flying squirrel.",
            "You wild and crazy flower pot.",
            "You elegant, tiny bus driver.",
            "You beautiful, misunderstood sports car.",
            "You vivacious rainbow of joy.",
            "You adorable, miniature horse.",
            "You gorgeous, purebred poodle.",
            "You transcendent, little seahorse",
            "You sneaky, persistent ice sculptor.",
            "You elusive, runaway hot air balloon.",
            "You humble, intelligent golden retriever.",
            "You phenomenal well of ideas.",
            "You magnificent middle school marching band.",
            "You priceless, expensive gem.",
            "You flawless, apple pie."
    };

    public String[] mReality = {
            "Be the change you wish to see in the world. -Mahatma Gandhi"
    };

    public String[] mAffirmations = {
            "I am kind.",
            "I am smart.",
            "I am important.",
    };

    Object[] mArrays = {
        mReality,
        mKnope,
        mKindness,
        mAffirmations
    };

    public String getKindness(){
        String kindness;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mKindness.length);
        kindness = mKindness[randomNumber];

        return kindness;
    }


    public String getKnope(){
        String knope;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mKnope.length);
        knope = mKnope[randomNumber];

        return knope;
    }

    public String getRealityCheck(){
        String realityCheck;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mAffirmations.length);
        realityCheck = mAffirmations[randomNumber];

        return realityCheck;
    }

    public String getReality(){
        String quote;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mReality.length);
        quote = mReality[randomNumber];

        return quote;
    }

    public String[] getArrays(){
        String[] chosen;
        Random randomGenerator = new Random();
        int randomNumber = randomGenerator.nextInt(mArrayList.size());
        chosen = mArrayList.get(randomNumber);

        return chosen;
    }

    public List<String[]> mArrayList = new ArrayList<>();

   /* mReality,
    mKnope,
    mKindness,
    mAffirmations*/

}
