import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Lecteur
{
    public static List<Monster> getListMonster(String filePath)
    {
        return readMonstersFromFile(filePath).stream().map(RawMonster::toMonster).collect(Collectors.toList());
    }

    //on pourrait ajouter un booleen sur chaque attribut,
    //pour verifier leur présence ou les doublons
    private static List<RawMonster> readMonstersFromFile(String filePath)
    {
        List<RawMonster> listMonsters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            boolean isReadingMonster = false;
            RawMonster currentMonster = null;

            while ((line = br.readLine()) != null)
            {
                if (line.startsWith("Monster"))
                {
                    isReadingMonster = true;
                    currentMonster = new RawMonster();
                    continue;
                }

                if (line.startsWith("EndMonster"))
                {
                    isReadingMonster = false;
                    if (currentMonster != null)
                    {
                        listMonsters.add(currentMonster);
                    }
                    continue;
                }

                if (!isReadingMonster) {
                    continue;
                }

                String[] parts = line.trim().split("\\s+");

                String attribute = parts[0];
                String value = parts[1];

                switch (attribute)
                {
                    case "Name" -> {
                        currentMonster.setName(value);
                    }
                    case "Type" -> {
                        currentMonster.setType(Type.valueOf(value.toUpperCase()));
                    }
                    case "HP" -> {
                        String max = parts[2];
                        currentMonster.setMinHP(Integer.parseInt(value));
                        currentMonster.setMaxHP(Integer.parseInt(max));
                    }
                    case "Attack" -> {
                        String max = parts[2];
                        currentMonster.setMinAttack(Integer.parseInt(value));
                        currentMonster.setMaxAttack(Integer.parseInt(max));
                    }
                    case "Speed" -> {
                        String max = parts[2];
                        currentMonster.setMinSpeed(Integer.parseInt(value));
                        currentMonster.setMaxSpeed(Integer.parseInt(max));
                    }
                    case "Defense" -> {
                        String max = parts[2];
                        currentMonster.setMinDefense(Integer.parseInt(value));
                        currentMonster.setMaxDefense(Integer.parseInt(max));
                    }
                    default -> {
                        currentMonster.getSpecialAttribut().put(attribute, Double.parseDouble(value));
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listMonsters;
    }

    public static List<Attaque> getListAttack(String filePath) {
        List<Attaque> listMonsters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            boolean isReadingAttaque = false;

            //attribut
            String name = "";
            Type type = null;
            double power = -1;
            int nbUse = -1;
            double fail = -1;

            while ((line = br.readLine()) != null)
            {
                if (line.startsWith("Attack"))
                {
                    isReadingAttaque = true;
                    continue;
                }

                if (line.startsWith("EndAttack"))
                {
                    if (power == -1 ||
                        nbUse == -1 ||
                        fail  == -1 ||
                        type  == null ||
                        name.isEmpty())
                        throw new IllegalArgumentException("un des attribut est manquant dans le fichier :"+filePath);

                    isReadingAttaque = false;

                    listMonsters.add(new Attaque(
                            name,
                            type,
                            power,
                            nbUse,
                            fail
                    ));

                    continue;
                }

                if (!isReadingAttaque) {
                    continue;
                }

                String[] parts = line.trim().split("\\s+");

                String attribute = parts[0];
                String value = parts[1];

                switch (attribute)
                {
                    case "Name" -> {
                        name=value;
                    }
                    case "Type" -> {
                        type = Type.valueOf(value.toUpperCase());
                    }
                    case "Power" -> {
                        power = Double.parseDouble( value );
                    }
                    case "NbUse" -> {
                        nbUse = Integer.parseInt(value);
                    }
                    case "Fail" -> {
                        fail = Double.parseDouble(value);
                    }
                    default -> {
                        throw new IllegalArgumentException("mauvais attribut dans le fichier :"+filePath);
                    }
                }
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return listMonsters;
    }
}