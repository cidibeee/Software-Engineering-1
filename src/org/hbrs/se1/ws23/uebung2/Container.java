package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategy;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private ArrayList<Member> members;
    private static Container instance;
    private PersistenceStrategy saveStrategy;

    private Container()
    {
        members = new ArrayList<>();
    }

    public static Container getInstance()
    {
        if (instance == null)
        {
            instance = new Container();
        }
        return instance;
    }

    public void addMember(Member member) throws ContainerException {
        if(members.contains(member))
        {
            throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }
        members.add(member);
    }

    public String deleteMember(int id) // Nachteil einer solchen Fehlerbehandlung ist, dass man immer einen Wert returnen muss,
    {                                  //und das der Fehler unter Umständen gar nicht vom Benutzer gesehen wird weil er den Rückgabewert der Funktion nicht beachtet.
        int indexOfID = -1;
        for(int i = 0; i < members.size(); i++)
        {
            if(id == members.get(i).getID())
            {
                indexOfID = i;
            }
        }

        if(indexOfID == -1)
        {
            return "Die eingegebene ID ist NICHT in vorhanden!";
        }

        members.remove(indexOfID);

        return "Erfolgreich entfernt!";
    }

    public int size()
    {
        return members.size();
    }


    public void setPersistance(PersistenceStrategy<Member> persistance)
    {
        saveStrategy = persistance;
    }

    //@SuppressWarnings("unchecked")
    public void store() throws PersistenceException
    {
        if (saveStrategy == null)
        {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Es wurde keine Strategie gesetzt");
        }
        saveStrategy.openConnection();
        saveStrategy.save(members);
        saveStrategy.closeConnection();
    }

    public void load() throws PersistenceException
    {
        if(saveStrategy == null)
        {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Es wurde keine Strategie gesetzt");
        }

        saveStrategy.openConnection();
        //noinspection unchecked
        members = (ArrayList<Member>) saveStrategy.load();
        saveStrategy.closeConnection();
    }

    public List<Member> getCurrentList()
    {
        return members;
    }


}