package person;

import com.github.javafaker.Faker;
import user.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.ZoneId;
import java.util.Locale;

public class Main {
    private static Faker faker = new Faker(new Locale("en"));

    public static Person randomPerson() {
        Person person = Person.builder()
                .name(faker.name().name())
                .dob(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .gender(faker.options().option(Person.Gender.class))
                .address(Address.builder()
                        .country(faker.address().country())
                        .state(faker.address().state())
                        .city(faker.address().cityName())
                        .streetAddress(faker.address().streetAddress())
                        .zip(faker.address().zipCode())
                        .build())
                .email(faker.internet().emailAddress())
                .profession(faker.company().profession())
                .build();
        return person;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++)
            em.persist(randomPerson());
        em.getTransaction().commit();

        System.out.println(em.find(Person.class, Long.valueOf(543)));

        em.close();
        emf.close();
    }
}
