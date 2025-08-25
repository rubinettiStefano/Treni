package com.generation.esempi;

import com.generation.entities.Route;
import com.generation.entities.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;

public class EsempioRelazione
{

	public static void main(String[] args)
	{
		// === CREAZIONE DI 2 TRATTE ===
		Route r1 = new Route();
		r1.setDepartureStation("Milano Centrale");
		r1.setArrivalStation("Roma Termini");
		r1.setDepartureTime(LocalTime.of(9, 0));
		r1.setArrivingTime(LocalTime.of(12, 0));
		r1.setDistance(600);
		r1.setBasePrice(50);
		r1.setTrainType("FrecciaRossa");
		r1.setTier("Economy");

//		Route r2 = new Route();
//		r2.setDepartureStation("Torino Porta Nuova");
//		r2.setArrivalStation("Genova Brignole");
//		r2.setDepartureTime(LocalTime.of(14, 30));
//		r2.setArrivingTime(LocalTime.of(16, 0));
//		r2.setDistance(170);
//		r2.setBasePrice(20);
//		r2.setTrainType("Regionale");
//		r2.setTier("Business");

		// === CREAZIONE DI 6 TICKET (non collegati alle tratte) ===
		Ticket t1 = new Ticket();
		t1.setCustomerFullName("Mario Rossi");
		t1.setSeat("12A");
		t1.setObliterated(false);
		t1.setDiscountPercentage(0);
		t1.setDay(LocalDate.of(2025, 9, 1));

		Ticket t2 = new Ticket();
		t2.setCustomerFullName("Luca Bianchi");
		t2.setSeat("7B");
		t2.setObliterated(true);
		t2.setDiscountPercentage(10);
		t2.setDay(LocalDate.of(2025, 9, 2));

//		Ticket t3 = new Ticket();
//		t3.setCustomerFullName("Anna Verdi");
//		t3.setSeat("3C");
//		t3.setObliterated(false);
//		t3.setDiscountPercentage(20);
//		t3.setDay(LocalDate.of(2025, 9, 3));
//
//		Ticket t4 = new Ticket();
//		t4.setCustomerFullName("Giovanni Neri");
//		t4.setSeat("15D");
//		t4.setObliterated(false);
//		t4.setDiscountPercentage(0);
//		t4.setDay(LocalDate.of(2025, 9, 4));
//
//		Ticket t5 = new Ticket();
//		t5.setCustomerFullName("Elisa Gallo");
//		t5.setSeat("8F");
//		t5.setObliterated(true);
//		t5.setDiscountPercentage(50);
//		t5.setDay(LocalDate.of(2025, 9, 5));
//
//		Ticket t6 = new Ticket();
//		t6.setCustomerFullName("Marco Fontana");
//		t6.setSeat("21E");
//		t6.setObliterated(false);
//		t6.setDiscountPercentage(30);
//		t6.setDay(LocalDate.of(2025, 9, 6));

		//collego prima route con primo ticket
		r1.addTicket(t1);
		//	//collego prima route con secondo ticket
		t2.setRoute(r1);

		System.out.println(t1.finalPrice());
		System.out.println(t2.finalPrice());

		System.out.println(r1.totalRevenue());
	}
}
