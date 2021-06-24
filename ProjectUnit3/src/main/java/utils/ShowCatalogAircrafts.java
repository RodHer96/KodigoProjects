package utils;

import java.util.List;

import models.Aircraft;

public class ShowCatalogAircrafts {
	public static void showCatalog(List<Aircraft> aircrafts) {
		for(Aircraft air : aircrafts) {
			System.out.println(air.toString());
			System.out.println("----------------------");
		}
	}
}
