package com.generation.repositories;

import com.generation.entities.Route;
import com.generation.entities.Ticket;
import com.generation.library.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * OFFRE LE FUNZIONALITÀ DI C.R.U.D. per l'entità Route
 */
public class RepositoryRouteCsv
{
	//LISTA DI ROUTE
	private ArrayList<Route> content = new ArrayList<>();
	private String fileName;
	private RepositoryTicketCsv ticketRepo = new RepositoryTicketCsv("tickets.csv");

	public RepositoryRouteCsv(String fileName)
	{
		//lo salvo come proprietà perchè mi servirà per la riscrittura
		this.fileName = fileName;

		//1 - apriamo il file
		FileReader reader = new FileReader(fileName);
		//2 - saltiamo la riga di intestazione
		reader.readString();
		//3 - leggiamo riga per riga
		//esempio
		//departureStation,arrivalStation,departureTime,arrivingTime,distance,basePrice,trainType,tier
		//Milano Centrale,Roma Termini,08:00,12:30,570,50,FrecciaRossa,Economy
		while (reader.hasRow())
			convertAndAddRow(reader.readString());

//		//5 *100 -> 500 -> N^2 -> con n 1000 1000000
//		for(Route r : content)
//			for(Ticket t: ticketRepo.read())
//				if(t.getRouteId()==r.getId())//chiave esterna ticket UGUALE chiave primaria Route
//					r.addTicket(t);
//
//		//2N   2000


		HashMap<Long, ArrayList<Ticket>> fkToTicket = new HashMap<>();

		for (Ticket t : ticketRepo.read())//scorro tutti i ticket
			if (!fkToTicket.containsKey(t.getRouteId()))//se è il primo con quella chiave esterna
			{
				//creo una lista che contiene solo lui
				ArrayList<Ticket> temp = new ArrayList<>();
				temp.add(t);
				//la metto nella mappa chiave esterna
				fkToTicket.put(t.getRouteId(),temp);
			}
			else //altrimenti lo aggiungo alla lista che ha la sua stessa chiave esterna
				fkToTicket.get(t.getRouteId()).add(t);

		for(Route r : content)//scorro tutte le route
		{
			//prendo dalla mappa la lista di ticket la cui chiave esterna
			//è uguale alla chiave primaria della route
			ArrayList<Ticket> ticketDaCollegare = fkToTicket.get(r.getId());
			for(Ticket t:ticketDaCollegare )//li scorro e li collego 1 a 1
				r.addTicket(t);
		}
	}



	public void create(Route r)
	{
		content.add(r);
		sync();
	}

	public void delete(Route r)
	{
		content.remove(r);
		sync();
	}

	public void update(Route newVersion,Route oldVersion)
	{
		//versione modificata e originale
		content.remove(oldVersion);
		content.add(newVersion);
		sync();
	}

	public ArrayList<Route> read()
	{
		return content;
	}

	/**
	 * Questo metodo si occupa di convertire una SINGOLA RIGA del csv in un oggetto Route e lo aggiunge alla lista
	 */
	private void convertAndAddRow(String row)
	{
		//1 - Creo nuovo oggetto route vuoto
		Route r = new Route();
		//2 - splitto la riga del csv
		String[] spl = row.split(",");
		//3 - utilizzo i valori della riga per riempire le proprietà dell'oggetto
		r.setId(Long.parseLong(spl[0])); // nuovo campo id
		r.setDepartureStation(spl[1]);
		r.setArrivalStation(spl[2]);
		r.setDepartureTime(LocalTime.parse(spl[3]));
		r.setArrivingTime(LocalTime.parse(spl[4]));
		r.setDistance(Integer.parseInt(spl[5]));
		r.setBasePrice(Double.parseDouble(spl[6]));
		r.setTrainType(spl[7]);
		r.setTier(spl[8]);
		//4 - aggiungo oggetto alla lista
		content.add(r);
	}

	/**
	 * Questo metodo ricrea il file csv convertendo ogni oggetto Route della lista in un riga
	 */
	private void sync()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("departureStation,arrivalStation,departureTime,arrivingTime,distance,basePrice,trainType,tier");

		//scorriamo tutte le route nella lista e le aggiungiamo come righe csv al builder
		for(Route r:content)
			builder.append("\n").append(convertRouteToRow(r));

		//sovrascriviamo il file
		try
		{
			FileWriter writer = new FileWriter(fileName);
			writer.write(builder.toString());
			writer.close();
		}
		catch (Exception e)
		{
			System.out.println("SOVRASCRITTURA FALLITA");
		}
	}

	/**
	 * Questo metodo riceve un oggetto Route e dovrà produrre la sua versione in riga csv
	 * departureStation,arrivalStation,departureTime,arrivingTime,distance,basePrice,trainType,tier
	 * Milano Centrale,Roma Termini,08:00,12:30,570,50,FrecciaRossa,Economy
	 */
	private String convertRouteToRow(Route r)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(r.getDepartureStation()).append(",");
		builder.append(r.getArrivalStation()).append(",");
		builder.append(r.getDepartureTime()).append(",");
		builder.append(r.getArrivingTime()).append(",");
		builder.append(r.getDistance()).append(",");
		builder.append(r.getBasePrice()).append(",");
		builder.append(r.getTrainType()).append(",");
		builder.append(r.getTier());
		return builder.toString();
	}
}
