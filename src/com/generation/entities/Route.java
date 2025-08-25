package com.generation.entities;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;

/**
 * La route rappresenta un biglietto generico (non legato a persona o posto)
 */
public class Route
{
	//Proprietà
	//fate anche getter e setters
	//dentro i setters controllate che:
	//distance > 0
	//basePrice> 0
	//trainType e tier uno di quelli specificati nel metodo del prezzo (se vi ricordate come sarebbe carino mettere i valori validi come proprietà static)
	//TODO
	private static String[] validTrainTypes =	 {"Regionale", "Intercity", "FrecciaRossa"};
	private static String[] validTiers		 = {"Economy", "Buisiness", "PrimaClasse"};

	private LocalTime departureTime,arrivingTime;
	private int distance;
	private String departureStation, arrivalStation;
	private double basePrice;
	private String trainType,tier;

	//proprietà relazionale
	//Molteplicità della relazione, Route 1 - N Ticket
	private ArrayList<Ticket> tickets = new ArrayList<>();

	//Metodi

	/**
	 *    Deve calcolare il prezzo finale del biglietto
	 *    esso è dato da:
	 *    prezzo base
	 *    moltiplicato per:
	 *   	 1 		se trainType = Regionale
	 *   	 1.2	se trainType = Intercity
	 *   	 1.5	se trainType = FrecciaRossa
	 *    moltiplicato per:
	 * 	   	 1 		se tier = Economy
	 * 	     1.6	se tier = Business
	 * 	     2	    se tier = PrimaClasse
	 */
	public double price()
	{
		return basePrice;
		//TODO
		//STUB DEL METODO, versione ""Funzionante"", che non rompe il programma
		//da completare
	}

	/**
	 * Metodo che calcola il tempo di percorrenza
	 * Non prevedo ne accetto viaggi di durata superiore alle 24 ore
	 * Quindi posso dire con certezza che se il tempo di arrivo
	 * è minore di quello di partenza, in realtà si riferisce al giorno dopo
	 * @return il tempo di percorrenza in minuti
	 */
	public int travelTime()
	{
		//se il tempo di arrivo è minore di quello di partenza
		if(arrivingTime.isBefore(departureTime))
//			return (int)departureTime.until(LocalTime.of(23,59,59), ChronoUnit.MINUTES) +(int)LocalTime.MIDNIGHT.until(arrivingTime, ChronoUnit.MINUTES) ;
			return (24*60)-(int)arrivingTime.until(departureTime, ChronoUnit.MINUTES);
		//lavorare con il complementare
		//21      10
		//10 to 21 -> 11
		//24 - 11  -> 13

		return (int)departureTime.until(arrivingTime, ChronoUnit.MINUTES);
	}

	/**
	 * Calcola la velocità media del treno
	 * @return velocità media in km/h
	 */
	public double avgSpeed()
	{
		return 0;//TODO
	}

	/**
	 * Deve contenere le informazioni importanti sul biglietto, decidete voi quali
	 */
	public String toString()
	{
		return "";
		//TODO
	}

	public double totalRevenue()
	{
		double res = 0;
		for(Ticket t : tickets)
			res+=t.finalPrice();
		return res;
	}

	public void addTicket(Ticket t)
	{
		if(tickets.contains(t))
			return;
		//aggiungi il ticket alla lista della route
		tickets.add(t);
		//imposta questa route come route del ticket
		t.setRoute(this);
	}


	public LocalTime getDepartureTime()
	{
		return departureTime;
	}

	public void setDepartureTime(LocalTime departureTime)
	{
		this.departureTime = departureTime;
	}

	public LocalTime getArrivingTime()
	{
		return arrivingTime;
	}

	public void setArrivingTime(LocalTime arrivingTime)
	{
		this.arrivingTime = arrivingTime;
	}

	public int getDistance()
	{
		return distance;
	}

	public void setDistance(int distance)
	{
		this.distance = distance;
	}

	public String getDepartureStation()
	{
		return departureStation;
	}

	public void setDepartureStation(String departureStation)
	{
		this.departureStation = departureStation;
	}

	public String getArrivalStation()
	{
		return arrivalStation;
	}

	public void setArrivalStation(String arrivalStation)
	{
		this.arrivalStation = arrivalStation;
	}

	public double getBasePrice()
	{
		return basePrice;
	}

	public void setBasePrice(double basePrice)
	{
		this.basePrice = basePrice;
	}

	public String getTrainType()
	{
		return trainType;
	}

	public void setTrainType(String trainType)
	{
		this.trainType = trainType;
	}

	public String getTier()
	{
		return tier;
	}

	public void setTier(String tier)
	{
		this.tier = tier;
	}


}
