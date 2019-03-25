package de.opitz.meetup.Animals;

import java.util.ArrayList;
import java.util.Random;

public class Slug extends Animal {
    private String[] vegetables = new String[]{
            "cauliflower",
            "brussels sprouts",
            "salad",
            "radish",
            "strawberries"
    };


    public static Slug getRandomSlug(){
        Random random = new Random();

        Slug slug = new Slug();

        slug.setSpecies("slug");
        slug.setAge(random.nextInt(10));
        slug.setColor("brown");
        slug.setHungry(random.nextBoolean());
        slug.setRandomFoodPreferences();

        return slug;
    }

    private void setRandomFoodPreferences(){
        Random random = new Random();

        String preferences = "";

        for (int i=0;i<vegetables.length;i++){
            if (random.nextBoolean())
                preferences += vegetables[i] + ", ";
        }

        super.setFoodPreferences(preferences);
    }


}
