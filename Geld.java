package com.company;

public class Geld {
    // declareer de geld values
    public int Waarde;
    public int Voorraad;


    // nieuwe geldeenheid constructor
    public Geld(int waarde, int voorraad) {
        Voorraad = voorraad;
        Waarde = waarde;
    }

    // maak de verschillende soorten geld
    // ik heb gekozen voor centen omdat dat veiliger rekenen is
    public static Geld tweeEuro = new Geld(200, 10);
    public static Geld eenEuro = new Geld(100, 10);
    public static Geld vijftigCent = new Geld(50, 10);
    public static Geld twintigCent = new Geld(20, 10);
    public static Geld tienCent = new Geld(10, 10);
    public static Geld vijfCent = new Geld(5, 10);

    // weer een list met daarin alle geld instances.
    public static Geld[] geldLijst = { tweeEuro, eenEuro, vijftigCent, twintigCent, tienCent, vijfCent };

    // De functie die het geld terug berekend en in het automaatScherm laat zien
    static int geldTerug(int wisselGeld) {

        // ik maak een string met daarin al het wisselgeld om makkelijk op het automaatScherm te laten zien
        String geldText = "€";

        // loop door alle eenheden om te kijken wel wisselgeld nodig is.
        for(int i = 0; i < geldLijst.length; i++){
            // als het wisselgeld is betaald: laat de geldText zien en ververs de voorraad
            if (wisselGeld == 0){
                Snoep.snoepPrijs = 0;
                Snoep.betaal("0");
                SnoepUI.automaatScherm.setText(geldText + "euro terug");
                SnoepUI.voorraadLabel.setText(" Voorraad:");
                Snoep.showVoorraad();
                return 0;
            }

            // als het wisselgeld nog niet betaald is: voeg het nieuwe wisselgeld toe aan geldText en update het te betalen bedrag
            else if (geldLijst[i].Waarde <= wisselGeld && geldLijst[i].Voorraad != 0) {
                // ik gebruik hier een ternary condition om te bepalen of er een extra 0 achter de prijs moet (overal behalve 5 cent)
                geldText = geldText.concat(Float.toString((float) geldLijst[i].Waarde / 100) + (geldLijst[i].Waarde == 5 ? ", \n" : "0, \n"));

                wisselGeld -= geldLijst[i].Waarde;
                geldLijst[i].Voorraad--;
                i = 0;
            }

            // als het wisselgeld niet uit te betalen is: laat gebruiker weten dat het geld niet terug te krijgen is. je kan wel gepast en te weinig blijven betalen
            else if (geldLijst[i].Voorraad == 0) {
                SnoepUI.automaatScherm.setText("Te weinig wisselgeld");
            }
        }

        return wisselGeld;
    }
}
