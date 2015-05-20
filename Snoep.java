package com.company;

public class Snoep {

    // Declareer alle values die het snoep zal hebben
    public String Naam;
    public int Prijs;
    public String Code;
    public int Voorraad;

    // Een snoepprijs variable die je overal kan gebruiken
    public static int snoepPrijs;


    // Nieuwe snack constructor
    public Snoep(String naam, int prijs, String code, int voorraad ) {
        Naam = naam;
        Prijs = prijs;
        Code = code;
        Voorraad = voorraad;
    }

    // Alle soorten snoep die te koop zijn
    public static Snoep mars = new Snoep("Mars", 120, "A1", 5);
    public static Snoep snickers = new Snoep("Snickers", 80, "A2", 5);
    public static Snoep twix = new Snoep("Twix", 100, "A3", 5);
    public static Snoep bounty = new Snoep("Bounty", 110, "B1", 5);
    public static Snoep bros = new Snoep("Bros", 70, "B2", 5);
    public static Snoep milkyWay = new Snoep("Milky Way", 60, "B3", 5);
    public static Snoep mandM = new Snoep("M&M's", 110, "C1", 5);
    public static Snoep lion = new Snoep("Lion", 90, "C2", 5);
    public static Snoep kitKat = new Snoep("KitKat", 100, "C3", 5);

    // Een list met daarin alle soorten snoep voor gebruik met loops
    public static Snoep[] SnoepLijst = { mars, snickers, twix, bounty, bros, milkyWay, mandM, lion, kitKat };

    // Functie om de voorraadLabel te verversen
    public static void showVoorraad(){
        for (Snoep snoep : SnoepLijst){
            SnoepUI.voorraadLabel.setText(SnoepUI.voorraadLabel.getText().concat(" " + snoep.Naam + ": " + snoep.Voorraad));
        }
    }

    // Functie die wordt opgeroepen op het moment dat de gebruiker een snack uitkiest
    public static int koopSnoep(String naam) {
        // iterate over alle soorten snoep om het gekozen snoep te vinden in de snoepLijst
        for(Snoep snoep : SnoepLijst){
            if (naam.equals(snoep.Naam) && snoep.Voorraad != 0){
                snoepPrijs = snoep.Prijs;
                SnoepUI.automaatScherm.setText("Te betalen: €" + (float) snoepPrijs / 100);
                snoep.Voorraad--;
                return 1;
            }

            else if (snoep.Voorraad == 0){
                // Laat zien dat de gevraagde soort snoep op is en ververs voorraadLabel
                SnoepUI.automaatScherm.setText("Snoep op");
                SnoepUI.voorraadLabel.setText(" Voorraad:");
                showVoorraad();
            }
        }

        return 0;
    }

    // Functie die wordt opgeroepen om het moment dat de gebruiker op de betaalknop drukt
    public static int betaal(String invoer) {

        // Reken de ingevoerde string om naar een float (* 100 tegen floating point problemen)
        float betaaldFloat = Float.parseFloat(invoer) * 100;

        // zet de float om naar een int en rond af zodat we geen rare getallen krijgen
        int betaald = Math.round(betaaldFloat);
        // zodat de gebruiker geen negatieve getallen kan invoeren wat voor problemen zorgt
        if(betaald < 0){ SnoepUI.automaatScherm.setText("Geen negatief bedrag!"); return 0;}

        // De prijs van de snoep is gelijk aan de global variable uit de koopSnoep functie
        int prijs = snoepPrijs;

        // genoeg betaald
        if (betaald == prijs){
            SnoepUI.automaatScherm.setText("U heeft betaald");
            SnoepUI.voorraadLabel.setText(" Voorraad:");
            showVoorraad();
        }

        // te weinig betaald, vraag om meer geld
        else if (betaald < prijs) {
            snoepPrijs = prijs - betaald;
            SnoepUI.automaatScherm.setText("Te betalen: €" + (float) snoepPrijs / 100);
        }

        // te veel betaald, call de geldTerug functie om het terug te geven geld te berekenen
        else if (betaald > prijs) {
            Geld.geldTerug(betaald - prijs);
        }

        return 1;
    }
}