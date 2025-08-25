package com.generation.entities;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

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
	private LocalTime departureTime,arrivingTime;


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
		return 0;
		//TODO
		//STUB DEL METODO, versione ""Funzionante"", che non rompe il programma
		//da completare
	}

	/**
	 * Metodo che calcola il tempo di percorrenza
	 * @return il tempo di percorrenza in minuti
	 */
	public int travelTime()
	{
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
}
