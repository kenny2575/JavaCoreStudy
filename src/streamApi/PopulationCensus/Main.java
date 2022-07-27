package streamApi.PopulationCensus;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }


        long count;

        count = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Number of minors: " + count);

        List<String> familiesList = persons.stream()
                .filter(x -> x.getSex() == Sex.MALE)
                .filter(x -> x.getAge() <= 27 && x.getAge() >= 18)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Number of conscripts: " + familiesList.size());

        List<Person> workersList = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER && x.getAge() >= 18)
                .filter(x -> (x.getSex() == Sex.MALE && x.getAge() <= 65) || (x.getSex() == Sex.FEMALE && x.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Number of workers: " + workersList.size());
    }
}
