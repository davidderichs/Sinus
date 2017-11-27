package UE03A01ThreadingTuermeVonHanoi;

public class TuermeVonHanoi {
    static void ziehe_scheibe(int nummer, String von, String nach) {
        System.out.println("Scheibe " + nummer + " wird von " + von +
                " nach " + nach + " verschoben ");
    }
    static void hanoi(int N, String platz1, String hilfsplatz, String platz2)
    {
        if (N == 1) {
            ziehe_scheibe(N, platz1, platz2);
        } else {
            hanoi(N-1, platz1, platz2, hilfsplatz);
            ziehe_scheibe(N, platz1, platz2);
            hanoi(N-1, hilfsplatz, platz1, platz2);
        }
    }
}


