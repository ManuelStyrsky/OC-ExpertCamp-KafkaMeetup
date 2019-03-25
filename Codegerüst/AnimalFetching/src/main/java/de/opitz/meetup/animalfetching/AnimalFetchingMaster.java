package de.opitz.meetup.animalfetching;

public class AnimalFetchingMaster {

    private static final int numbPartitions = 6;


    public static void main(String[] args) {
        AnimalFetchingThread[] animalFetchingThreads = new AnimalFetchingThread[numbPartitions];

        for (int i = 0; i < animalFetchingThreads.length; i++) {
            animalFetchingThreads[i] = new AnimalFetchingThread(i);
            animalFetchingThreads[i].start();
        }
    }
}