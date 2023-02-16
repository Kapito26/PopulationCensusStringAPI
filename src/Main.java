import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Найти количество несовершеннолетних (т.е. людей младше 18 лет)
        long juvenile = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println();
        System.out.println("Количество несовершеннолетних " + juvenile);

        //Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет)
        List<String> conscript = persons.stream()
                .filter(x -> x.getSex().equals(Sex.MAN))
                .filter(x -> (x.getAge() >= 18) & (x.getAge() <= 27))
                .map(x -> x.getFamily() + " " + x.getName() + " " + x.getAge())
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("Список призывников");
        System.out.println(conscript);

        //Список потенциально работоспособных людей с высшим образованием
        List<String> workable = persons.stream()
                .filter(x -> x.getEducation().equals(Education.HIGHER))
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 60 & x.getSex().equals(Sex.WOMAN) |
                        x.getAge() <= 65 & x.getSex().equals(Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::toString)
                .collect(Collectors.toList());
        System.out.println();
        System.out.println("Список потенциально работоспособных людей");
        System.out.println(workable);
    }
}