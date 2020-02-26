package no.hvl.dat110.broker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	// data structure for managing subscriptions
	// maps from user to set of topics subscribed to by user
	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	
	// data structure for managing currently connected clients
	// maps from user to corresponding client session object
	
	protected ConcurrentHashMap<String, ClientSession> clients;
	
	//data structure for managing messages for disconnected subscribers
	//maps from user to a set of messages that was sent when user was disconnected
	
	protected ConcurrentHashMap<String, Set<Message>> buffer;
	

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
		buffer = new ConcurrentHashMap<String, Set<Message>>();
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	// get the session object for a given user
	// session object can be used to send a message to the user
	
	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {

		// TODO: add corresponding client session to the storage
		ClientSession clientSession = new ClientSession(user, connection);
		clients.put(user, clientSession);
		
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void removeClientSession(String user) {

		// TODO: remove client session for user from the storage
		clients.remove(user);
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void createTopic(String topic) {

		// TODO: create topic in the storage
		subscriptions.computeIfAbsent(topic, v -> new HashSet<String>());
		//throw new UnsupportedOperationException(TODO.method());
	
	}

	public void deleteTopic(String topic) {

		// TODO: delete topic from the storage
		subscriptions.remove(topic);
		//throw new UnsupportedOperationException(TODO.method());
		
	}

	public void addSubscriber(String user, String topic) {

		// TODO: add the user as subscriber to the topic
		Set<String> users = getSubscribers(topic);
		users.add(user);
		subscriptions.put(topic, users);
		//throw new UnsupportedOperationException(TODO.method());
	}

	public void removeSubscriber(String user, String topic) {

		// TODO: remove the user as subscriber to the topic
		Set<String> users = getSubscribers(topic);
		users.remove(user);
		subscriptions.put(topic, users);
		//throw new UnsupportedOperationException(TODO.method());
	}
	
	public void addDisconnectedUser(String user) {
		buffer.computeIfAbsent(user, v -> new HashSet<Message>());
	}
	
	public void addMessageToBuffer(String user,Message message) {
		Set<Message> messages = getMessages(user);
		messages.add(message);
		buffer.put(user, messages);
	}
	public Set<Message> getMessages(String user){
		return buffer.get(user);
	}
	
	public void deleteMessages(String user) {
		buffer.get(user).clear();
	}
}
