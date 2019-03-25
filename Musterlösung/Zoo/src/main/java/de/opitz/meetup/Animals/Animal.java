package de.opitz.meetup.Animals;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public abstract class Animal {
    private final UUID uuid;
    private String species;
    private String name;
    private int age;
    private String color;
    private boolean hungry;
    private String foodPreferences;

    public Animal() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHungry() {
        return hungry;
    }

    public void setHungry(boolean hungry) {
        this.hungry = hungry;
    }

    public String getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }

    /**
     * @return a json String, containing the data of the animal
     */
    public String toString() {
        return toJSON().toString();
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("uuid", uuid);
            jsonObject.put("species", species);
            jsonObject.put("name", name);
            jsonObject.put("age", age);
            jsonObject.put("color", color);
            jsonObject.put("hungry", hungry);
            jsonObject.put("foodPreferences", foodPreferences);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
