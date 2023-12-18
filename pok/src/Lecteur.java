import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Lecteur
{
    private List<RawMonster> listRawMonster;

    public List<Monster> getListMonster(String filePath)
    {
        return readMonstersFromFile(filePath).stream().map(RawMonster::toMonster).collect(Collectors.toList());
    }

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
                }

                if (line.startsWith("EndMonster"))
                {
                    isReadingMonster = false;
                    if (currentMonster != null)
                    {
                        listMonsters.add(currentMonster);
                    }
                }

                if (!isReadingMonster) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts.length >= 2)
                {
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
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return listMonsters;
    }
}