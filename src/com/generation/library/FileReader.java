package com.generation.library;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader 
{

	private Scanner scanner;
	
	/**
	 * questo metodo è il costruttore per creare un oggetto FileReader <br/>
	 * che in seguito viene usato per invocare i metodi di lettura del testo o di numeri
	 * dal file che viene passato come parametro a questo metodo.
	 */
	
	public FileReader(String filename) throws RuntimeException
	{
		File file = new File(filename);
		
		try 
		{
			scanner = new Scanner (file);
		} 
		catch (Exception e)
		{
			throw new RuntimeException("Il file che stai cercando di leggere non è stato trovato");
		}
	}
	/**
	 * questo metodo viene usato per leggere una riga di testo dal file <br/>
	 * il file che viene letto è lo stesso che abbiamo passato come poarametro al costruttore del fileReader
	 * @return
	 * @throws RuntimeException
	 */
	public String readString() throws RuntimeException 
	{
		try
		{
			return scanner.nextLine();
		}
		catch (Exception e)
		{
			throw new RuntimeException("FileReader è stato chiuso oppure la riga che volevi leggere non esiste (il file è finito");
		}
	}
	
	/**
	 * questo metodo viene usato per leggere una riga con un valore numerico dal file <br/>
	 * il file che viene letto è lo stesso che abbiamo passato come poarametro al costruttore del fileReader
	 * @return
	 * @throws RuntimeException
	 */
	public int readInt() throws RuntimeException
	{
		try
		{
			return Integer.parseInt(scanner.nextLine());
		}
		catch (Exception e)
		{
			throw new RuntimeException("FileReader chiuso, riga non presente o valore non numerico");
		}
	}
	/**
	 * questo metodo viene usato per leggere una riga con un valore decimale dal file <br/>
	 * il file che viene letto è lo stesso che abbiamo passato come poarametro al costruttore del fileReader
	 * @return
	 * @throws RuntimeException
	 */
	public double readDouble() throws RuntimeException
	{
		try
		{
			return Double.parseDouble(scanner.nextLine());
		}
		catch (Exception e)
		{
			throw new RuntimeException("FileReader chiuso, riga non presente o valore non in formato di un numero con la virgola");
		}
	}
	
	/**
	 * Restituisce true se abbiamo ancora righe da leggere.
	 * @return
	 */
	public boolean hasRow()
	{
		return scanner.hasNextLine();
	}
	
	/**
	 * questo metodo conclude il lavoro con l'oggetto FileReader
	 * 
	 */
	public void close ()
	{
		scanner.close();
	}


	public ArrayList<String> readAllRows()
	{
		try
		{
			ArrayList<String> res = new ArrayList<>();
			while(scanner.hasNextLine())
				res.add(readString());
			return res;
		}
		catch (Exception e)
		{
			throw new RuntimeException("FileReader è stato chiuso oppure la riga che volevi leggere non esiste (il file è finito");
		}
	}
}
