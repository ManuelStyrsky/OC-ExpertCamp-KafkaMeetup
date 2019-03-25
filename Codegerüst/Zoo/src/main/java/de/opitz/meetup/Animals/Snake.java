package de.opitz.meetup.Animals;

import java.util.ArrayList;
import java.util.Random;

public class Snake extends Animal {
    private String[] food = new String[]{
            "snake",
            "mouse",
            "salad",
            "tiger"
    };


    public static Snake getRandomSnake(){
        Random random = new Random();

        Snake snake = new Snake();

        snake.setSpecies("snake");
        snake.setAge(random.nextInt(40));
        snake.setColor("green");
        snake.setHungry(random.nextBoolean());
        snake.setRandomFoodPreferences();

        return snake;
    }

    private void setRandomFoodPreferences(){
        Random random = new Random();

        String preferences = "";

        for (int i = 0; i< food.length; i++){
            if (random.nextBoolean())
                preferences += food[i] + ", ";
        }

        super.setFoodPreferences(preferences);
    }
}
