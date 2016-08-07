package com.crescentcitydevelopment.contactmanager;

import com.crescentcitydevelopment.contactmanager.model.Contact;
import com.crescentcitydevelopment.contactmanager.model.Contact.ContactBuilder;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Redando on 8/7/2016.
 */
public class Application
{
    //Hold reuseable reference to a SessionFactory (since we need only one)
   // private static final SessionFactory sessionFactory = buildSessionFactory();

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
                .withPhone(6059942042L)
                .build();
        System.out.println(contact);


    }
}
