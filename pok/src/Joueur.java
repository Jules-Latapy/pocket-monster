import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Joueur {

    public static final String POKEDEX_PATH = "./pok/rsrc/pokemon-data.txt";
    public static final String ATTACKDEX_PATH = "./pok/rsrc/attack-data.txt";

    public static final List<Monster> POKEDEX = Lecteur.getListMonster(POKEDEX_PATH);
    public static final List<Attaque> ATTACKDEX = Lecteur.getListAttack(ATTACKDEX_PATH);

    public static final Map<String, Monster> POKEMON_CHOICES = initPokemonChoices();
    public static final Map<String, Attaque> ATTACK_CHOICES = initAttaqueChoices();

    //=enum donc pas besoin de stocker l'objet associé grâce à la méthode valueOf()
    public static final List<String> OBJET_CHOICES =
            Arrays.stream(Objet.values())
            .map(Objet::toSentenceLike)
            .collect(Collectors.toList());

    //list → parce que je veux ajouter et retirer des elements facilement
    private final List<Objet> objets = new ArrayList<>(5);

    private final List<Monster> monstres = new ArrayList<>(3);

    private Monster monstrePrincipal;

    private final Terrain terrainPartage ;



    //From command line
    public Joueur(Terrain terrain) {

        terrainPartage = terrain;

        initObjet();

        /*----------------------------*/

        initPokemon();

        monstrePrincipal = monstres.get(0);
    }

    private void initPokemon() {
        Choix whichMonster = new Choix("Quel pokémon voulez vous ?", POKEMON_CHOICES.keySet(), true);

        for (int counter = 0; counter <3; counter++) {

            String chosenMonsterName = whichMonster.getInput();

            //on oblige à avoir au moins 1 pokémon
            if (counter == 2 && monstres.isEmpty()) {
                whichMonster = new Choix("Quel pokémon voulez vous ?", POKEMON_CHOICES.keySet(), false);
                chosenMonsterName = whichMonster.getInput();
            }

            if (chosenMonsterName!=null) {
                Monster chosenMonster = POKEMON_CHOICES.get(chosenMonsterName);

                Map<String, Attaque> onlyPokemonType = filterOnlyType(chosenMonster.getType(), ATTACK_CHOICES);

                Choix whichAttack = new Choix("Quel attaque souhaitez-vous pour '"+chosenMonster.getName()+"' ?", onlyPokemonType.keySet(), true);
                List<Attaque> attacks = new ArrayList<>(5);

                for (int i = 0; i < 4; i++) {

                    String chosenAttackName = whichAttack.getInput();

                    if (chosenAttackName!=null) {
                        attacks.add(onlyPokemonType.get(chosenAttackName));
                        onlyPokemonType.remove(chosenAttackName);
                    }
                }

                attacks.add(Attaque.MAIN_NUE);

                chosenMonster.setAttaques(attacks.toArray(new Attaque[0]));
                monstres.add(chosenMonster);
            }
        }
    }

    private void initObjet() {
        Choix whichObjet = new Choix("Quel objet voulez vous ?", OBJET_CHOICES, true);

        for (int i = 0; i <5; i++) {
            String chosenObjetName = whichObjet.getInput();

            if (chosenObjetName!=null) {
                objets.add(Objet.fromSentenceLike(chosenObjetName));
            }
        }
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

        //opération impossible
        if (monstres.size()==1) {
            return;
        }

        Map<String, Monster> withoutPrincipal = monstres.stream().collect(Collectors.toMap(Monster::getName, Function.identity()));
        withoutPrincipal.remove(this.monstrePrincipal.getName());

        if (withoutPrincipal.size()==1) {
            monstrePrincipal= withoutPrincipal.values().stream().findAny().orElseThrow();
            System.out.println("Monstre changé !");
            return;
        }

        Choix whichMonster = new Choix("Pour quel pokémon changez ?", withoutPrincipal.keySet(), false);

        String input = whichMonster.getInput();

        monstrePrincipal = withoutPrincipal.get(input);
    }

    public void utiliserObjet() {

        Choix whichObjet = new Choix("Quel objet utiliser ?", this.objets.stream()
                .map(Objet::toSentenceLike)
                .collect(Collectors.toList()), true);

        String input = whichObjet.getInput();

        if (input != null) {
           Objet objet = Objet.fromSentenceLike(input);
           objet.consume(monstrePrincipal, terrainPartage);
           this.objets.remove(objet);
        }
    }

    public Attaque choisirAttaque() {

        Map<String, Attaque> collect = Arrays.stream(this.monstrePrincipal.getAttaques())
                .filter(a -> a.getUsage() > 0)
                .collect(
                        Collectors.toMap(Attaque::getNom, Function.identity()));

        Choix whichAttack = new Choix("Quel attaque utiliser pour le pokemon '"+monstrePrincipal.getName()+"'?",
                collect.keySet(),
                false);

        String input = whichAttack.getInput();

        return collect.get(input);
    }

    public List<Monster> getMonstres() {
        return monstres;
    }

    public List<Objet> getObjets() {
        return objets;
    }

    public Monster getMonstrePrincipal() {
        return monstrePrincipal;
    }

    public boolean asLost() {
        return monstres.isEmpty();
    }

    public Terrain getTerrain() {
        return terrainPartage;
    }
}
