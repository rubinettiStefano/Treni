package com.generation.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringJoiner;

public class Ticket
{
	//primary key - id
	private Long id;

	private String customerFullName;
	private String seat;
	private boolean obliterated;
	private int discountPercentage;
	private LocalDate day;
	//proprietà relazionale
	//Molteplicità della relazione, Route 1 - N Ticket
	private Route route;
	//FOREIGN KEY - CHIAVE ESTERNA
	private Long routeId;


	public double finalPrice()
	{
		return ((100-discountPercentage)* route.price())/100;
	}

	public String getCustomerFullName()
	{
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName)
	{
		this.customerFullName = customerFullName;
	}

	public String getSeat()
	{
		return seat;
	}

	public void setSeat(String seat)
	{
		this.seat = seat;
	}

	public boolean isObliterated()
	{
		return obliterated;
	}

	public void setObliterated(boolean obliterated)
	{
		this.obliterated = obliterated;
	}

	public int getDiscountPercentage()
	{
		return discountPercentage;
	}

	public void setDiscountPercentage(int discountPercentage)
	{
		this.discountPercentage = discountPercentage;
	}

	public LocalDate getDay()
	{
		return day;
	}

	public void setDay(LocalDate day)
	{
		this.day = day;
	}

	public Route getRoute()
	{
		return route;
	}

	public void setRoute(Route route)
	{
		if(route==this.route)
			return;
		this.route = route;
		this.routeId = route.getId();
		route.addTicket(this);
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getRouteId()
	{
		return routeId;
	}

	public void setRouteId(Long routeId)
	{
		this.routeId = routeId;
	}

	public String toString()
	{
		return new StringJoiner(", ", Ticket.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("customerFullName='" + customerFullName + "'")
				.add("seat='" + seat + "'")
				.add("obliterated=" + obliterated)
				.add("discountPercentage=" + discountPercentage)
				.add("day=" + day)
				.add("routeId=" + routeId)
				.toString();
	}
}
