package com.generation.businesslogic;

import com.generation.entities.Route;
import com.generation.library.Console;
import com.generation.repositories.RepositoryRouteCsv;

public class RoutesBusinessLogic
{
	private RepositoryRouteCsv repo;

	public RoutesBusinessLogic()
	{
		repo = new RepositoryRouteCsv("routes.csv");
	}

	public void printAll()
	{
		System.out.println("Ecco le tue routes");
		for(Route r : repo.read())
			System.out.println(r);
	}

	public void createNewRoutes()
	{
		Route r = new Route();
		System.out.println("Inserisci stazione di partenza");
		r.setDepartureStation(Console.readString());
		//TODO di tutti gli altri campi

		repo.create(r);
	}

	/**
	 * Chiedere a utente una Stazione di partenza
	 * Stampare tutte le route che hanno quella come stazione di partenza
	 * vi metto sotto il confronto carino con contains
	 * al posto di equals
	 * route.getDepartureStation().toLowerCase().contains(input.toLowerCase());
	 */
	public void printFrom()
	{
	}
}
