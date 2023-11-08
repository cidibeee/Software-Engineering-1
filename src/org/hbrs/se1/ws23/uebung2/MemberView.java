package org.hbrs.se1.ws23.uebung2;

import java.util.List;

public class MemberView
{
    public static void dump(List<Member> liste)
    {
        for (Member m: liste)
        {
            System.out.println(m);
        }
    }
}
