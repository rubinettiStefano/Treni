package pkfk;

import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
		ArrayList<Team> teams = new ArrayList<>();

		Team rossi = new Team();
		rossi.pk = 'R';
		rossi.nome = "Rossi";
		Team blu = new Team();
		blu.pk = 'B';
		blu.nome = "Blu";
		Team verdi = new Team();
		verdi.pk = 'V';
		verdi.nome = "Verdi";

		teams.add(rossi);
		teams.add(blu);
		teams.add(verdi);


		ArrayList<Player> players = new ArrayList<>();

		Player p1 = new Player();
		p1.nome = "Pippo";
		p1.pk = 1;
		p1.fk_team_collegato = 'R';

		Player p2 = new Player();
		p2.nome = "Pluto";
		p2.pk = 2;
		p2.fk_team_collegato = 'B';

		Player p3 = new Player();
		p3.nome = "Paperino";
		p3.pk = 3;
		p3.fk_team_collegato = 'B';
		Player p4 = new Player();
		p4.nome = "Qui";
		p4.pk = 4;
		p4.fk_team_collegato = 'V';
		Player p5 = new Player();
		p5.nome = "Quo";
		p5.pk = 5;
		p5.fk_team_collegato = 'V';
		Player p6 = new Player();
		p6.nome = "Qua";
		p6.pk = 6;
		p6.fk_team_collegato = 'V';

		players.add(p1);
		players.add(p2);
		players.add(p3);
		players.add(p4);
		players.add(p5);
		players.add(p6);


		for(Team t:teams)
			for(Player p : players)
				if(t.pk==p.fk_team_collegato)
				{
					t.players.add(p);
					p.team = t;
				}

//		System.out.println(p5.nome + " gioca nel team "+p5.team.nome);
//
//		System.out.println("I giocatori dei Blu sono");
//		for(Player p :blu.players)
//			System.out.println(p.nome);

//		for(Player p : players)
//			if(verdi.pk==p.fk_team_collegato)
//				System.out.println(p.nome +" gioca nei verdi");


//
//		for(Team t : teams)
//			if(t.pk==p2.fk_team_collegato)
//				System.out.println("Pluto gioca nel team "+t.nome);

		System.out.println("Compagni di squadra di Qui");
		for(Player p : p4.team.players)
			if(p!=p4)
				System.out.println(p.nome);

	}
}
