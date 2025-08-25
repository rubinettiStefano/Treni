package com.generation;

import com.generation.businesslogic.RoutesBusinessLogic;
import com.generation.library.Console;

public class Executable
{
	public static void main(String[] args)
	{
		RoutesBusinessLogic rbl = new RoutesBusinessLogic();

		String cmd = "";

		do
		{
			System.out.println("Inserisci comando");
			cmd = Console.readString().toLowerCase();

			switch (cmd)
			{
				case "print"            -> rbl.printAll();
				case "new"            	-> rbl.createNewRoutes();
				case "print-from"        -> rbl.printFrom();
				case "help"            	-> help();
				case "quit"            	-> System.out.println("BYE BYE");
				default                	-> System.out.println("Comando non valido");
			}


		}while (!cmd.equals("quit"));
	}

	private static void help()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Comandi disponibili\n");
		builder.append("print - stampa tutte le routes");
		builder.append("print-from - stampa tutte le routes che partono da una stazione");
		builder.append("new - crea nuova route");
		builder.append("quit - esci");

		System.out.println(builder.toString());
	}
}
