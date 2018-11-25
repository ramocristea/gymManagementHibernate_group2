package org.example.gymmanagementhibernate;

import java.util.List;

import org.example.gymmanagementhibernate.model.Client;
import org.example.gymmanagementhibernate.model.Membership;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello Hibernate!
 *
 */
public class App {
	
	static SessionFactory sessionFactory;
	
	public static void main(String[] args) {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		
		List<Client> clients = getAllClients();
		
		for(Client client : clients) {
			System.out.println(client);
		}
		
		Client client = new Client("Stefan", "Cel Mare", "345676543", "stefan@yahoo.com");
		//addClient(client);
		Client client1 = getClientById(22);
		System.out.println(client1);
		
		client1.setLastName("Cel Mircea");
		updateClient(client1);
		
		System.out.println(getClientById(22));
		
		List<Client> clientsByLastName = getClientsByLastName("Cel Mircea");
		for(Client client2 : clientsByLastName) {
			System.out.println(client2);
		}
		
		List<Membership> memberships = getMemberships();
		
		for(Membership m : memberships) {
			System.out.println("membership id: " + m.getId() + " start date: " + 
					m.getStartDate() + " first name " + m.getClient().getFirstName() 
					+ " last name " + m.getClient().getLastName() + 
					" membership type " + m.getMembershipType().getName());
		}
		
		sessionFactory.close();
	}
	
	private static List<Client> getAllClients() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Client> clients = session.createQuery("from Client").list();
		
		tx.commit();
		session.close();
		
		return clients;
	}
	
	public static void addClient(Client client) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(client);
		
		tx.commit();
		session.close();
	}
	
	private static Client getClientById(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Client client = session.find(Client.class, id);
		
		tx.commit();
		session.close();
		
		return client;
	}
	
	public static void updateClient(Client client) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.update(client);
		
		tx.commit();
		session.close();
	}
	
	public static List<Client> getClientsByLastName(String lastName) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query query = session.createQuery("from Client where lastName= :name");
		query.setParameter("name", lastName);
	
		
		List<Client> clients = query.list();
		
		tx.commit();
		session.close();
		
		return clients;
	}
	
	public static List<Membership> getMemberships() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Membership> memberships = session.createQuery("from Membership").list();
		
		tx.commit();
		session.close();
		
		return memberships;
	}
}
