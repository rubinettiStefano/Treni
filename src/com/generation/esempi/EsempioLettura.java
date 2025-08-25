package com.generation.esempi;

import com.generation.entities.Route;
import com.generation.entities.Ticket;
import com.generation.repositories.RepositoryRouteCsv;
import com.generation.repositories.RepositoryTicketCsv;

import java.util.ArrayList;
import java.util.HashMap;

public class EsempioLettura
{

	public static void main(String[] args)
	{
		RepositoryRouteCsv routeRepo = new RepositoryRouteCsv("routes.csv");
		RepositoryTicketCsv ticketRepo = new RepositoryTicketCsv("tickets.csv");

//		for(Route r : routeRepo.read())
//			for(Ticket t: ticketRepo.read())
//				if(t.getRouteId()==r.getId())//chiave esterna ticket UGUALE chiave primaria Route
//					r.addTicket(t);

		//voglio una mappa con chiave ID della Route
		//e valore lista di Ticket con quell'id come chiave esterna
		HashMap<Long, ArrayList<Ticket>> fkToTicket = new HashMap<>();

		for (Ticket t : ticketRepo.read())
			if (!fkToTicket.containsKey(t.getRouteId()))
			{
				ArrayList<Ticket> temp = new ArrayList<>();
				temp.add(t);
				fkToTicket.put(t.getRouteId(),temp);
			}
			else
				fkToTicket.get(t.getRouteId()).add(t);

		for(Route r : routeRepo.read())
			for(Ticket t: fkToTicket.get(r.getId()))
				r.addTicket(t);

		for(Route r : routeRepo.read())
			System.out.println(r);

		for(Ticket t: ticketRepo.read())
			System.out.println(t);
	}
}
