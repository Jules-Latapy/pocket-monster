import java.util.*;
import java.util.stream.Collectors;

public class Joueur {

    public static final String POKEDEX_PATH = "./pok/rsrc/pokemon-data.txt";
    public static final String ATTACKDEX_PATH = "./pok/rsrc/attack-data.txt";

    public static final List<Monster> POKEDEX = Lecteur.getListMonster(POKEDEX_PATH);
    public static final List<Attaque> ATTACKDEX = Lecteur.getListAttack(ATTACKDEX_PATH);

    public static final Map<String, Monster> POKEMON_CHOICES = initPokemonChoices();
    public static final Map<String, Attaque> ATTACK_CHOICES = initAttaqueChoices();
    public static final List<String> OBJET_CHOICES = Arrays.stream(Objet.values())
            .map(Objet::toString)
            .map(String::toLowerCase)
            .collect(Collectors.toList());

    Objet[] objets;
    Monster[] monstres;
    Monster monstrePrincipal;

    //From command line
    public Joueur() {

        List<Objet> objetsList = new ArrayList<>(5);

        Choix whichObjet = new Choix("Quel objet voulez vous ?", OBJET_CHOICES, true);

        for (int __=0; __<5; __++) {
            String chosenObjetName = whichObjet.getInput();

            if (chosenObjetName!=null) {
                objetsList.add(Objet.valueOf(chosenObjetName.toUpperCase()));
            }
        }

        //une estrange facon de convertir mais bon...
        objets = objetsList.toArray(new Objet[0]);

        /*----------------------------*/
        List<Monster> monsterList = new ArrayList<>(3);

        Choix whichMonster = new Choix("Quel pokémon voulez vous ?", POKEMON_CHOICES.keySet(), true);

        for (int nbr=0; nbr<3; nbr++) {
            String chosenMonsterName = whichMonster.getInput();

            //on oblige à avoir au moins 1 pokémon
            if (nbr==2 && monsterList.isEmpty()) {
                whichMonster = new Choix("Quel pokémon voulez vous ?", POKEMON_CHOICES.keySet(), false);
                chosenMonsterName = whichMonster.getInput();
            }

            if (chosenMonsterName!=null) {
                Monster chosenMonster = POKEMON_CHOICES.get(chosenMonsterName);

                Map<String, Attaque> onlyPokemonType = filterOnlyType(chosenMonster.getType(), ATTACK_CHOICES);

                Choix whichAttack = new Choix("Quel attaque souhaitez-vous pour '"+chosenMonster.getName()+"' ?", onlyPokemonType.keySet(), true);
                List<Attaque> attacks = new ArrayList<>(5);

                for (int __ = 0; __ < 4; __++) {
                    String chosenAttackName = whichAttack.getInput();

                    if (chosenAttackName!=null) {
                        attacks.add(onlyPokemonType.get(chosenMonsterName));
                    }
                }

                attacks.add(Attaque.MAIN_NUE);

                chosenMonster.setAttaques(attacks.toArray(new Attaque[0]));
                monsterList.add(chosenMonster);
            }
        }

        monstres = monsterList.toArray(new Monster[0]);

        monstrePrincipal = monstres[0];
    }

    private static Map<String, Monster> initPokemonChoices() {
        //on doit maintenir l'ordre
        Map<String, Monster> result = new LinkedHashMap<>();

        for (Monster monster : POKEDEX) {
            result.put(monster.getName(), monster);
        }

        return result;
    }

    private static Map<String, Attaque> initAttaqueChoices() {
        //on doit maintenir l'ordre
        Map<String, Attaque> result = new LinkedHashMap<>();

        for (Attaque attaque : ATTACKDEX) {
            result.put(attaque.getNom(), attaque);
        }

        return result;
    }

    private static Map<String, Attaque> filterOnlyType(Type type, Map<String, Attaque> from) {
        Map<String, Attaque> result = new LinkedHashMap<>();
        for (Map.Entry<String, Attaque> kv : from.entrySet()) {
            if (kv.getValue().getType()==type || kv.getValue().getType()==Type.NORMAL)
                result.put(kv.getKey(), kv.getValue());
        }

        return result;
    }

    public void changerMonstre() {

    }

    public void utiliserObjet() {

    }

    public Attaque choisirAttaque() {

        return null;
    }
}
