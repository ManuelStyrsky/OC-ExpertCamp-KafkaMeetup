package de.opitz.meetup.Animals;

import java.util.Random;

import static de.opitz.meetup.Animals.Slug.getRandomSlug;

public class AnimalFactory {

    public Animal createRandomAnimal(){
        Random random = new Random();
        if (random.nextBoolean()){
            return Snake.getRandomSnake();
        }else{
            return getRandomSlug();
        }
    }
}
