package com.rushional.thoughtin;

import com.rushional.thoughtin.entities.User;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

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
        for ( User user : result ) {
            System.out.println( "User (" + user.getUsername() + ")");
        }
//        TODO: There's no actual asserts, we just made sure the connection is there
//        but hooray, Hibernate generated all the tables
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
