package org.hbrs.se1.ws23.uebung9;

//@SuppressWarnings("ALL")
public class TestClient {
    public static void main(String[] args) {
        Document doc0 = new ComplexDocument(0);
        Document doc2 = new TextDocument("Die Klausur im Fach SE findet bald statt!", TextDocument.Encoding.UTF8);
        doc2.setID(1);
        Document doc3 = new ComplexDocument(2);
        Document doc4 = new GraficDocument("Localhost:8080");
        doc4.setID(3);
        Document doc5 = new TextDocument("Software Engineering I ist eine Vorlesung in den Studiengaengen BSI und BCS", TextDocument.Encoding.UTF32);
        doc5.setID(4);
        ((ComplexDocument) doc0).addDocument(doc2);
        ((ComplexDocument) doc0).addDocument(doc3);
        ((ComplexDocument) doc3).addDocument(doc4);
        ((ComplexDocument) doc3).addDocument(doc5);
        System.out.println(doc0.size());
    }
}
