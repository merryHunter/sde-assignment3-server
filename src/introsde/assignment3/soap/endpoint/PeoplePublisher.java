package introsde.assignment3.soap.endpoint;

import introsde.assignment3.soap.ws.PeopleImpl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.ws.Endpoint;

public class PeoplePublisher {
    public static void main(String[] args) throws UnknownHostException {
        Endpoint.publish("https://assignment3-chernukha.herokuapp.com/person", new PeopleImpl());
    }
}