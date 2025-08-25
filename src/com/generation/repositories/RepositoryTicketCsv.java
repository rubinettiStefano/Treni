package com.generation.repositories;

import com.generation.entities.Ticket;
import com.generation.library.FileReader;

import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class RepositoryTicketCsv
{
	//LISTA DI Ticket
	private ArrayList<Ticket> content = new ArrayList<>();
	private String fileName;

	public RepositoryTicketCsv(String fileName)
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
	}



	public void create(Ticket r)
	{
		content.add(r);
		sync();
	}

	public void delete(Ticket r)
	{
		content.remove(r);
		sync();
	}

	public void update(Ticket newVersion,Ticket oldVersion)
	{
		//versione modificata e originale
		content.remove(oldVersion);
		content.add(newVersion);
		sync();
	}

	public ArrayList<Ticket> read()
	{
		return content;
	}

	/**
	 * Questo metodo si occupa di convertire una SINGOLA RIGA del csv in un oggetto Ticket e lo aggiunge alla lista
	 */
	private void convertAndAddRow(String row)
	{
		//1 - Creo nuovo oggetto Ticket vuoto
		Ticket r = new Ticket();
		//2 - splitto la riga del csv
		String[] spl = row.split(",");
		//3 - utilizzo i valori della riga per riempire le proprietà dell'oggetto
		//id,customerFullName,seat,obliterated,discountPercentage,day
		r.setId(Long.parseLong(spl[0])); // nuovo campo id
		r.setCustomerFullName(spl[1]);
		r.setSeat(spl[2]);
		r.setObliterated(Boolean.parseBoolean(spl[3]));
		r.setDiscountPercentage(Integer.parseInt(spl[4]));
		r.setDay(LocalDate.parse(spl[5]));
		r.setRouteId(Long.parseLong(spl[6]));
		//4 - aggiungo oggetto alla lista
		content.add(r);
	}

	/**
	 * Questo metodo ricrea il file csv convertendo ogni oggetto Ticket della lista in un riga
	 */
	private void sync()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("id,customerFullName,seat,obliterated,discountPercentage,day,routeId");

		//scorriamo tutte le Ticket nella lista e le aggiungiamo come righe csv al builder
		for(Ticket r:content)
			builder.append("\n").append(convertTicketToRow(r));

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
	 * Questo metodo riceve un oggetto Ticket e dovrà produrre la sua versione in riga csv
	 id,customerFullName,seat,obliterated,discountPercentage,day,routeId
	 * Milano Centrale,Roma Termini,08:00,12:30,570,50,FrecciaRossa,Economy
	 */
	private String convertTicketToRow(Ticket r)
	{
		StringBuilder builder = new StringBuilder();
		builder.append(r.getId()).append(",");
		builder.append(r.getCustomerFullName()).append(",");
		builder.append(r.getSeat()).append(",");
		builder.append(r.isObliterated()).append(",");
		builder.append(r.getDiscountPercentage()).append(",");
		builder.append(r.getDay()).append(",");
		builder.append(r.getRouteId()).append(",");
		return builder.toString();
	}
}
