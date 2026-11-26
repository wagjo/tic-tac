package sk.spse.tictac;

import java.util.Scanner;

public class TextoveUI {

    private Scanner scanner = new Scanner(System.in);;

    // Vypíše pekne formátovanú dosku s vonkajším rámikom.
    public void vypisDosku(char[][] doska) {
        System.out.println();
        System.out.println("    1   2   3");
        System.out.println("  ┌───┬───┬───┐");

        for (int riadok = 0; riadok < 3; riadok++) {
            System.out.print((riadok + 1) + " │");
            for (int stlpec = 0; stlpec < 3; stlpec++) {
                System.out.print(" " + doska[riadok][stlpec] + " ");
                if (stlpec < 2) {
                    System.out.print("│");
                }
            }
            System.out.println("│");

            if (riadok < 2) {
                System.out.println("  ├───┼───┼───┤");
            }
        }
        System.out.println("  └───┴───┴───┘");
        System.out.println();
    }

    // Opýta sa hráča na ťah a vráti súradnice [riadok, stlpec] (0-2).
    // Kontroluje, či je vstup číslo v rozsahu.
    public int[] ziskajTahOdHraca() {
        System.out.print("Tvoj ťah - Zadaj riadok (1-3): ");
        int riadok = scanner.nextInt() - 1;

        System.out.print("Zadaj stĺpec (1-3): ");
        int stlpec = scanner.nextInt() - 1;

        return new int[]{riadok, stlpec};
    }

    // Vypíše ľubovoľnú správu (víťazstvo, remíza, atď.).
    public void vypisSpravu(String sprava, boolean chyba) {
        if (chyba)
            System.out.println("!!!!!!!!!!!!!!!!!!!");
        else
            System.out.println("-------------------");
        System.out.println(sprava);
        if (chyba)
            System.out.println("!!!!!!!!!!!!!!!!!!!");
        else
            System.out.println("-------------------");
    }

    public void vypisSpravu(String sprava) {
        vypisSpravu(sprava, false);
    }
}