package com.crescentcitydevelopment.contactmanager;

import com.crescentcitydevelopment.contactmanager.model.Contact;
import com.crescentcitydevelopment.contactmanager.model.Contact.ContactBuilder;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by Redando on 8/7/2016.
 */
public class Application
{
    //Hold reuseable reference to a SessionFactory (since we need only one)
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        //Create a StandardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();

    }

    public static void main(String[] args)
    {
        Contact contact = new ContactBuilder("Reaux","Ford")
                .withEmail("Redandof@gmail.com")
                .withPhone(5049942042L)
                .build();
               int id = save(contact);

        //Display Contact list before the update
        System.out.println("Before Update... \n\n ");
        for(Contact c: fetchAllContacts())
        {
            System.out.println(c);
        }

        //Obtain the persisted contact
        Contact c = findContactById(id);

        //Update the contact
        c.setFirstName("Redando");

        //Persist the changes
        System.out.println("Updating... \n\n ");
        update(c);
        System.out.println("Update Complete... \n\n ");

        for(Contact d: fetchAllContacts())
        {
            System.out.println(d);
        }

        //Display a list of contacts after the update


    }

    private static Contact findContactById(int id)
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Retrieve the persistent object (null if not found)
        Contact contact = session.get(Contact.class, id);

        //Close Session
        session.close();

        //Return the Object
        return contact;
    }

    private static void update(Contact contact)
    {
        //Open Session
        Session session = sessionFactory.openSession();

        //Begin transaction
        session.beginTransaction();

        //Use the session to update the contact
        session.update(contact);

        //Commit the transaction
        session.getTransaction().commit();

        //Close session
        session.close();

    }
    @SuppressWarnings("unchecked")
    private static List<Contact> fetchAllContacts()
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Create Criteria
        Criteria criteria = session.createCriteria(Contact.class);

        // Get a lis of Contact objects according to the Criteria
        List<Contact> contacts = criteria.list();

        //Close the session
        session.close();

        return contacts ;
    }

    private static int save(Contact contact)
    {
        //Open a session
        Session session = sessionFactory.openSession();

        //Begin a transaction
        session.beginTransaction();
        //Use the session to save the contact
        int id = (Integer) session.save(contact);
        //commit the transaction
        session.getTransaction().commit();
        //Close the session
        session.close();

        return id;

    }
}
