package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// TODO - start

		// create a client object and use it to
		
		Client client = new Client("tempDevice", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();
		for(int i=0; i<COUNT; i++) {
			String temp = Integer.toString(sn.read());
			client.publish(Common.TEMPTOPIC, temp);
		}
		client.disconnect();
		// - connect to the broker
		// - publish the temperature(s)
		// - disconnect from the broker

		// TODO - end

		System.out.println("Temperature device stopping ... ");

		//throw new UnsupportedOperationException(TODO.method());

	}
}
