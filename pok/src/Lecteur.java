import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lecteur
{
    private List<rawMonster> listRawMonster;

// ====================================================
    public List<rawMonster> getListRawMonster(String _filePath)
    {
        this.listRawMonster = readMonstersFromFile(_filePath);
        return this.listRawMonster;
    }

    private static List<rawMonster> readMonstersFromFile(String _filePath)
    {
        List<rawMonster> listMonsters = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(_filePath)))
        {
            String line;
            boolean isReadingMonster = false;
            rawMonster currentMonster = null;

            while ((line = br.readLine()) != null)
            {
                if (line.startsWith("Monster"))
                {
                    isReadingMonster = true;
                    currentMonster = new rawMonster();
                } else if (line.startsWith("EndMonster"))
                {
                    isReadingMonster = false;
                    if (currentMonster != null)
                    {
                        listMonsters.add(currentMonster);
                    }
                } else if (isReadingMonster)
                {
                    String[] parts = line.split(" ");
                    if (parts.length >= 2)
                    {
                        String attribute = parts[0];
                        String value = parts[1];

                        switch (attribute)
                        {
                            case "Name":
                                currentMonster.setName(value);
                                break;
                            case "Type":
                                currentMonster.setType(value);
                                break;
                            case "HP":
                                String[] hpValues = value.split(" ");
                                if (hpValues.length == 2) {
                                    currentMonster.setMinHP(Integer.parseInt(hpValues[0]));
                                    currentMonster.setMaxHP(Integer.parseInt(hpValues[1]));
                                }
                                break;
                            // Handle other attributes similarly
                            default:
                                // Handle unrecognized attributes
                                break;
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