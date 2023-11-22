package org.hbrs.se1.ws23.uebung4.prototype;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * Klasse zum Management sowie zur Eingabe unnd Ausgabe von User-Stories.
 * Die Anwendung wird über dies Klasse auch gestartet (main-Methode hier vorhanden)
 *
 * erstellt von Julius P., H-BRS 2023, Version 1.0
 *
 * Strategie für die Wiederverwendung (Reuse):
 * - Anlegen der Klasse UserStory
 * - Anpassen des Generic in der List-Klasse (ALT: Member, NEU: UserStory)
 * - Anpassen der Methodennamen
 *
 * ToDo: Was ist ihre Strategie zur Wiederverwendung? (F1)
 *
 * Alternative 1:
 * Klasse UserStory implementiert Interface Member (UserStory implements Member)
 * Vorteil: Wiederverwendung von Member, ID verwenden; Strenge Implementierung gegen Interface
 * Nachteil: Viele Casts notwendig, um auf die anderen Methoden zuzugreifen
 *
 * Alternative 2:
 * Container mit Generic entwickeln (z.B. Container<E>))
 *
 * Entwurfsentscheidung: Die wichtigsten Zuständigkeiten (responsibilities) sind in einer Klasse, d.h. Container,
 * diese liegt in einem Package.
 * ToDo: Wie bewerten Sie diese Entscheidung? (F2, F6)
 * 
 */

public class 	Container {
	 
	// Interne ArrayList zur Abspeicherung der Objekte vom Type UserStory
	private List<UserStory> liste = null;
	
	// Statische Klassen-Variable, um die Referenz
	// auf das einzige Container-Objekt abzuspeichern
	// Diese Variante sei thread-safe, so hat Hr. P. es gehört... stimmt das?
	// Todo: Bewertung Thread-Safeness (F1)
	// Nachteil: ggf. geringer Speicherbedarf, da Singleton zu Programmstart schon erzeugt wird
	// Todo: Bewertung Speicherbedarf (F1)
	private static Container instance;
	
	// URL der Datei, in der die Objekte gespeichert werden 
	final static String LOCATION = "allStories.ser";

	/**
	 * Liefert ein Singleton zurück.
	 * @return
	 */
	public static Container getInstance() {
		if(instance == null)
		{
			instance = new Container();
		}
		return instance;
	}
	
	/**
	 * Vorschriftsmäßiges Ueberschreiben des Konstruktors (private) gemaess Singleton-Pattern (oder?)
	 * Nun auf private gesetzt! Vorher ohne Access Qualifier (--> dann package-private)
	 */
	private Container(){
		liste = new ArrayList<UserStory>();
	}
	
	/**
	 * Start-Methoden zum Starten des Programms 
	 * (hier koennen ggf. weitere Initialisierungsarbeiten gemacht werden spaeter)
	 */
	public static void main (String[] args) throws Exception {
		// ToDo: Bewertung Exception-Handling (F3, F7)
		Container con = Container.getInstance();
		con.startEingabe(); 
	}
	
	/*
	 * Diese Methode realisiert eine Eingabe ueber einen Scanner
	 * Alle Exceptions werden an den aufrufenden Context (hier: main) weitergegeben (throws)
	 * Das entlastet den Entwickler zur Entwicklungszeit und den Endanwender zur Laufzeit
	 */
	public void startEingabe() throws ContainerException, Exception {
		String strInput = null;
		
		// Initialisierung des Eingabe-View
		// ToDo: Funktionsweise des Scanners erklären (F3)
		Scanner scanner = new Scanner( System.in );

		System.out.println("UserStory-Tool V1.1 © Cid Bocklemünd");

		while ( true ) {
			// Ausgabe eines Texts zur Begruessung

			System.out.print( "> "  );

			strInput = scanner.nextLine();
		
			// Extrahiert ein Array aus der Eingabe
			String[] strings = strInput.split(" ");

			// 	Falls 'help' eingegeben wurde, werden alle Befehle ausgedruckt
			if ( strings[0].equals("help") ) {
				System.out.println("befehl [Parameter]\n" +
						"Folgende Befehle stehen zur Verfuegung:\n" +
						"help\n" +
						"enter[id title akzeptanz mehrwert strafe aufwand risk project]\n" +
						"store\n" +
						"load\n" +
						"dump\n" +
						"search[Project]\n" +
						"exit");
			}

			if(strings[0].equals(("enter")))
			{
				if(strings.length < 9)
				{
					System.out.println("Nicht genuegend Parameter vorhanden");
					continue;
				}
				int[] intsinUserstory = {1, 4, 5, 6, 7};

				for(int i:intsinUserstory)
				{
					if(Integer.parseInt(strings[i]) < 0)
					{
						System.out.println("Parameter duerfen nicht negativ sein!");
						return;
					}

				}

				UserStory uss = new UserStory(Integer.parseInt(strings[1]), strings[2], strings[3],
						Integer.parseInt(strings[4]), Integer.parseInt(strings[5]), Integer.parseInt(strings[6]), Integer.parseInt(strings[7]),
						strings[8]);
				addUserStory(uss);
			}
			if(strings[0].equals(("store")))
			{
				store();
			}

			if(strings[0].equals(("load")))
			{
				load();
			}

			if(strings[0].equals(("dump")))
			{
				startAusgabe();
			}

			if(strings[0].equals(("search")))
			{
				if(strings.length < 2)
				{
					System.out.println("Der eingabe fehlt ein Parameter!");
					continue;
				}
				search(strings[1]);
			}
			// Auswahl der bisher implementierten Befehle:
			if ( strings[0].equals("exit") ) {
				break;
			}


		} // Ende der Schleife
		System.out.println("Programm wurde beendet");
	}

	private void search(String projectsearch)
	{
		liste.stream()
				.filter(element -> element.getProject().equals(projectsearch))
				.forEach(System.out::println);
	}

	/**
	 * Diese Methode realisiert die Ausgabe.
	 */
	public void startAusgabe() {

		// Hier möchte Herr P. die Liste mit einem eigenen Sortieralgorithmus sortieren und dann
		// ausgeben. Allerdings weiss der Student hier nicht weiter

		// [Sortierung ausgelassen]
		// Todo: Implementierung Sortierung (F4)
		liste.sort(new UserStoryComparator());
		// Klassische Ausgabe ueber eine For-Each-Schleife
		liste.stream()
				.filter(element -> element.getId() > 0)
				.filter(element -> element.getId() < 100)
				.forEach(System.out::println);

		// [Variante mit forEach-Methode / Streams (--> Kapitel 9, Lösung Übung Nr. 2)?
		//  Gerne auch mit Beachtung der neuen US1
		// (Filterung Projekt = "ein Wert (z.B. Coll@HBRS)" und Risiko >=5
		// Todo: Implementierung Filterung mit Lambda (F5)

	}

	/*
	 * Methode zum Speichern der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte gespeichert.
	 * 
	 */
	private void store() throws ContainerException {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( Container.LOCATION );
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject( this.liste );
			System.out.println( this.size() + " UserStory wurden erfolgreich gespeichert!");
		}
		catch (IOException e) {
			e.printStackTrace();
		  //  Delegation in den aufrufendem Context
		  // (Anwendung Pattern "Chain Of Responsibility)
		  throw new ContainerException("Fehler beim Abspeichern");
		}
	}

	/*
	 * Methode zum Laden der Liste. Es wird die komplette Liste
	 * inklusive ihrer gespeicherten UserStory-Objekte geladen.
	 * 
	 */
	public void load() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
		  fis = new FileInputStream( Container.LOCATION );
		  ois = new ObjectInputStream(fis);
		  
		  // Auslesen der Liste
		  Object obj = ois.readObject();
		  if (obj instanceof List<?>) {
			  this.liste = (List) obj;
		  }
		  System.out.println("Es wurden " + this.size() + " UserStory erfolgreich reingeladen!");
		}
		catch (IOException e) {
			System.out.println("LOG (für Admin): Datei konnte nicht gefunden werden!");
		}
		catch (ClassNotFoundException e) {
			System.out.println("LOG (für Admin): Liste konnte nicht extrahiert werden (ClassNotFound)!");
		}
		finally {
		  if (ois != null) try { ois.close(); } catch (IOException e) {}
		  if (fis != null) try { fis.close(); } catch (IOException e) {}
		}
	}

	/**
	 * Methode zum Hinzufügen eines Mitarbeiters unter Wahrung der Schlüsseleigenschaft
	 * @param userStory
	 * @throws ContainerException
	 */
	public void addUserStory ( UserStory userStory ) throws ContainerException {
		if ( contains(userStory) == true ) {
			ContainerException ex = new ContainerException("ID bereits vorhanden!");
			throw ex;
		}
		liste.add(userStory);
	}

	/**
	 * Prüft, ob eine UserStory bereits vorhanden ist
	 * @param userStory
	 * @return
	 */
	private boolean contains( UserStory userStory) {
		int ID = userStory.getId();
		for ( UserStory userStory1 : liste) {
			if ( userStory1.getId() == ID ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ermittlung der Anzahl von internen UserStory
	 * -Objekten
	 * @return
	 */
	public int size() {
		return liste.size();
	}

	/**
	 * Methode zur Rückgabe der aktuellen Liste mit Stories
	 * Findet aktuell keine Anwendung bei Hr. P.
	 * @return
	 */
	public List<UserStory> getCurrentList() {
		return this.liste;
	}

	/**
	 * Liefert eine bestimmte UserStory zurück
	 * @param id
	 * @return
	 */
	private UserStory getUserStory(int id) {
		for ( UserStory userStory : liste) {
			if (id == userStory.getId() ){
				return userStory;
			}
		}
		return null;
	}
}
