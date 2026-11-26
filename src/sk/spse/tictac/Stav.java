package sk.spse.tictac;

public class Stav {

    private char[][] doska;          // 3x3 doska
    private static final char PRAZDNA = ' ';

    public Stav() {
        doska = new char[3][3];
        inicializujDosku();
    }

    public char[][] getDoska() {
        return doska;
    }

    // Naplní celú dosku prázdnymi políčkami.
    public void inicializujDosku() {
        for (int riadok = 0; riadok < 3; riadok++) {
            for (int stlpec = 0; stlpec < 3; stlpec++) {
                doska[riadok][stlpec] = PRAZDNA;
            }
        }
    }

    // Pokúsi sa umiestniť symbol na zadanú pozíciu.
    // Vráti true, ak sa ťah podaril, inak false.
    public boolean vykonajTah(int riadok, int stlpec, char symbol) {
        if (riadok < 0 || riadok > 2 || stlpec < 0 || stlpec > 2) {
            return false; // mimo dosky
        }
        if (doska[riadok][stlpec] != PRAZDNA) {
            return false; // políčko už obsadené
        }

        doska[riadok][stlpec] = symbol;
        return true;
    }

    // Skontroluje, či daný symbol vyhral (riadky, stĺpce, diagonály).
    public boolean skontrolujVyhru(char symbol) {
        // Riadky
        for (int i = 0; i < 3; i++) {
            if (doska[i][0] == symbol && doska[i][1] == symbol && doska[i][2] == symbol) {
                return true;
            }
        }

        // Stĺpce
        for (int j = 0; j < 3; j++) {
            if (doska[0][j] == symbol && doska[1][j] == symbol && doska[2][j] == symbol) {
                return true;
            }
        }

        // Diagonály
        if (doska[0][0] == symbol && doska[1][1] == symbol && doska[2][2] == symbol) {
            return true;
        }
        if (doska[0][2] == symbol && doska[1][1] == symbol && doska[2][0] == symbol) {
            return true;
        }

        return false;
    }

    // Vráti true, ak je doska úplne plná (remíza).
    public boolean jeDoskaPlna() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (doska[i][j] == PRAZDNA) {
                    return false;
                }
            }
        }
        return true;
    }

    // Pomocná metóda pre AI – nájde výherný ťah pre daný symbol.
    // Vráti súradnice [riadok, stlpec] alebo null, ak žiadny nie je.
    public int[] najdiVyhernyTah(char symbol) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (doska[i][j] == PRAZDNA) {
                    doska[i][j] = symbol;               // dočasne položíme symbol
                    boolean vyhra = skontrolujVyhru(symbol);
                    doska[i][j] = PRAZDNA;              // vrátime späť

                    if (vyhra) {
                        return new int[]{i, j};
                    }
                }
            }
        }
        return null;
    }
}