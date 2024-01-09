package org.hbrs.se1.ws23.uebung8;

import javax.management.Query;

public interface Hotelsuche {
    Vector sucheHotel(Suchauftrag s);
    Vector bewerteHotel(String hotelname);
    Result newexecuteQuery(Query d);
}

//Anlegen der Klassen damit die Begriffe Vorhanden sind ohne Fehler
class Vector{}
class Suchauftrag{}
class Result{}