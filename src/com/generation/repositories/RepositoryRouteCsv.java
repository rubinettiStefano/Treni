package com.generation.repositories;

import com.generation.entities.Route;
import com.generation.library.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * OFFRE LE FUNZIONALITÀ DI C.R.U.D. per l'entità Route
 */
public class RepositoryRouteCsv
{
	//LISTA DI ROUTE
	private ArrayList<Route> content = new ArrayList<>();
	private String fileName;

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
		r.setDepartureStation(spl[0]);
		r.setArrivalStation(spl[1]);
		r.setDepartureTime(LocalTime.parse(spl[2]));
		r.setArrivingTime(LocalTime.parse(spl[3]));
		r.setDistance(Integer.parseInt(spl[4]));
		r.setBasePrice(Double.parseDouble(spl[5]));
		r.setTrainType(spl[6]);
		r.setTier(spl[7]);
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
