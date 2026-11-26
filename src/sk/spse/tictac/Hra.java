package sk.spse.tictac;

import java.util.Random;

public class Hra {

    private Stav stav;
    private TextoveUI ui;
    private Random random;

    private static final char HRAC = 'X';
    private static final char POCITAC = 'O';

    public Hra() {
        stav = new Stav();
        ui = new TextoveUI();
        random = new Random();
    }

    public void spusti() {
        ui.vypisSpravu("=== PIŠKVORKY proti počítaču ===\nTy si X, počítač je O.");

        boolean hraBezi = true;
        boolean tahHraca = true;   // hráč začína

        while (hraBezi) {
            ui.vypisDosku(stav.getDoska());

            if (tahHraca) {
                // Ťah hráča
                boolean platnyTah = false;
                while (!platnyTah) {
                    int[] tah = ui.ziskajTahOdHraca();
                    platnyTah = stav.vykonajTah(tah[0], tah[1], HRAC);

                    if (!platnyTah) {
                        ui.vypisSpravu("Toto políčko je už obsadené alebo neplatné. Skús znova.", true);
                    }
                }

                // Kontrola po ťahu hráča
                if (stav.skontrolujVyhru(HRAC)) {
                    ui.vypisDosku(stav.getDoska());
                    ui.vypisSpravu("Vyhral si! Gratulujem!");
                    hraBezi = false;
                } else if (stav.jeDoskaPlna()) {
                    ui.vypisDosku(stav.getDoska());
                    ui.vypisSpravu("Remíza!");
                    hraBezi = false;
                } else {
                    tahHraca = false;   // teraz ide počítač
                }

            } else {
                // Ťah počítača
                vykonajTahPocitaca();

                if (stav.skontrolujVyhru(POCITAC)) {
                    ui.vypisDosku(stav.getDoska());
                    ui.vypisSpravu("Počítač vyhral!");
                    hraBezi = false;
                } else if (stav.jeDoskaPlna()) {
                    ui.vypisDosku(stav.getDoska());
                    ui.vypisSpravu("Remíza!");
                    hraBezi = false;
                } else {
                    tahHraca = true;    // späť na hráča
                }
            }
        }
    }

    /**
     * Logika AI počítača:
     * 1. Ak môže vyhrať – vyhrá
     * 2. Ak hráč môže vyhrať – zablokuje ho
     * 3. Inak náhodný ťah
     */
    private void vykonajTahPocitaca() {
        // 1. Skús vyhrať
        int[] tah = stav.najdiVyhernyTah(POCITAC);
        if (tah != null) {
            stav.vykonajTah(tah[0], tah[1], POCITAC);
            return;
        }

        // 2. Zablokuj hráča
        tah = stav.najdiVyhernyTah(HRAC);
        if (tah != null) {
            stav.vykonajTah(tah[0], tah[1], POCITAC);
            return;
        }

        // 3. Náhodný voľný ťah
        int riadok, stlpec;
        do {
            riadok = random.nextInt(3);
            stlpec = random.nextInt(3);
        } while (!stav.vykonajTah(riadok, stlpec, POCITAC));
    }

    public static void main(String[] args) {
        Hra hra = new Hra();
        hra.spusti();
    }
}