import java.util.*;

public class Choix {

    private final String output;
    private final Map<String, String> choice;

    public Choix(String question, String[] possibilities, boolean nullable) {
        this(question, List.of(possibilities), nullable);
    }

    public Choix(String question, Iterable<String> possibilities, boolean nullable) {

        //on doit maintenir l'ordre d'insertion s'il y en a un + ne pas avoir de doublon
        choice = new LinkedHashMap<>();

        Index i = new Index();

        if (nullable)
            choice.put("*", null);

        for (String possibility : possibilities) {
            String key = i.value();
            choice.put(key, possibility);
            i.increment();
        }

        StringBuilder stringBuilder = new StringBuilder(question).append("\n");

        //pour eviter de recree la chaine a chaque fois on utilise un StringBuilder
        for (Map.Entry<String, String> entry : choice.entrySet()) {
            stringBuilder.append("\t").append(entry.getKey()).append(") ").append(entry.getValue()).append("\n");
        }

        output = stringBuilder.toString();
    }
    public String getInput() {

        Scanner scanner = new Scanner(System.in);

        String rep ;

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(output);
            rep = scanner.next().trim();
        }
        while (!choice.containsKey(rep));

        return choice.get(rep);
    }

    public static class Index {
        int index;
        public Index() {index = 0;}

        public Index(int beginAt) { index = beginAt; }
        void increment(){index++;}

        /**
         * @return
         */
        public String value() {

            int cpt = index;
            StringBuilder result = new StringBuilder();

            // Convertir le nombre en index
            while (cpt >= 0) {
                int remainder = cpt % 26;
                result.insert(0, (char) ('a' + remainder));
                cpt = (cpt / 26) - 1; // Soustraire 1 car 'a' correspond à zéro
            }

            return result.toString();
        }
    }
}
