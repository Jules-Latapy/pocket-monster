import java.util.*;

public class Choix {

    private final String output;
    private final Map<String, String> choice;

    public Choix(String question, String[] possibilities, boolean nullable) {
        this(question, List.of(possibilities), nullable);
    }

    public Choix(String question, Collection<String> possibilities, boolean nullable) {

        //on doit maintenir l'ordre d'insertion + ne pas avoir de doublon
        choice = new LinkedHashMap<>();

        ArrayList<Character> choiceletters = new ArrayList<>();
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
            System.out.println(output);
            rep = scanner.next().trim();
        }
        while (!choice.containsKey(rep));

        return choice.get(rep);
    }

    static class Index {
        int index;
        Index() {index = 1;}
        void increment(){index++;}

        /**
         * @return
         */
        String value() {

            StringBuilder sb = new StringBuilder();

            int reminder = index;
            int indexNbr = 0;
            while (reminder>=0) {
                int letter = (int) (reminder%Math.pow(26,indexNbr));
                sb.append((char)('a'+letter));
                indexNbr++;
                reminder-=letter;
            }

            return sb.toString();
        }

        /*
                    char charActu = 'a';
            for (int i = index; i >= 0; i--) {

                if (charActu=='z') {
                    for (int i1 = 0; i1 < sb.length(); i1++) {
                        if (sb.charAt(i1)!='z') {
                            sb.setCharAt();
                        }
                    }
                    charActu = 'a';
                }
                charActu++;
            }

            sb.append(charActu);
        */
    }
}
