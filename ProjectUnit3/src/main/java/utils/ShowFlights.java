package utils;

import java.util.List;

import models.Flight;

public class ShowFlights {
	public static void showFlights(List<Flight> flights) {
		for(Flight f : flights) {
			System.out.println(f.toString());
			System.out.println("----------------------");
		}
	}
	public static void showOntimeFlights(List<Flight> flights) {
		for(Flight f : flights) {
			if(f.getStatus() == "Ontime") {
				System.out.println(f.toString());  
				System.out.println("----------------------");
			}
		}
	}
	public static void showDelayedFlights(List<Flight> flights) {
		for(Flight f : flights) {
			if(f.getStatus() == "Delayed") {
				System.out.println(f.toString());  
				System.out.println("----------------------");
			}
		}
	}
	public static void showCancelledFlights(List<Flight> flights) {
		for(Flight f : flights) {
			if(f.getStatus() == "Cancelled") {
				System.out.println(f.toString());  
				System.out.println("----------------------");
			}
		}
	}
}
