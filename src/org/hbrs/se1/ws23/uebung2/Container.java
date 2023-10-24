package org.hbrs.se1.ws23.uebung2;

import java.util.ArrayList;

public class Container {
    private ArrayList<Member> members;

    public Container()
    {
        members = new ArrayList<>();
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

    public void dump()
    {
        for (Member m: members)
        {
            System.out.println(m);
        }
    }

    public int size()
    {
        return members.size();
    }

}

