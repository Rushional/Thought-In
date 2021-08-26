package com.rushional.thoughtin;

import com.rushional.thoughtin.entities.User;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This thing stopped working the moment I deleted persistence.xml, but!
// It looks like this test should work anyway because I'm not relying on Hibernate directly anymore, Spring FTW.
// At least, I've managed to run the app and Spring has actually generated the tables.
// So I'm guessing I'm doing it right and this test has to be remade from scratch
public class HibernateInitialTest {

    @Test
    public void testBasicUsage() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("everything");
        // create a couple of events...
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.detach(new User( 1, "CabbagePerson"));
        entityManager.detach(new User( 2, "CarrotPerson"));
        entityManager.getTransaction().commit();
        entityManager.close();

        // now let's pull events from the database and list them
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        I should probably learn to use Hibernate Query Language, but I also need to pace myself
//        idk why IDEA marks this query as unexpected, the query is correct
        List<User> result = entityManager.createQuery( "from User", User.class ).getResultList();
//        TODO: actually add stuff to database (then remove it)
//         or, better yet, only test the connection here, and check the other stuff some other way
//         so honestly, just learn to test databases and don't invent a bicycle
        assertEquals(2, result.size());
        assertEquals("CarrotPerson", result.get(1).getUsername());
        for ( User user : result ) {
            System.out.println( "User (" + user.getUsername() + ")");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
