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

        long NumberJuvenile = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних - " + NumberJuvenile);

        List<String> listRecruit = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .filter(person -> person.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список призывников (фамилии): ");
        listRecruit.forEach(System.out::println);

        List<Person> listWorkable = persons.stream()
                .filter(educ -> educ.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 65)
                .filter(person -> (person.getSex() == Sex.WOMAN && person.getAge() <= 60 || person.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Список работоспособных людей: ");
        listWorkable.forEach(System.out::println);

    }
}
