package me.xCykrix.WynnAPI.Methods;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import static me.xCykrix.WynnAPI.APIs.PrintAPI.print;
import static me.xCykrix.WynnAPI.APIs.PrintAPI.println;

/**
 * File Created by samuel at 7/20/16.
 * This File is not to be modified or redistributed without explicit permission from myself.
 */
public class ItemLookup {
    private static JSONParser parser = new JSONParser();

    public static void itemLookup(String itemName){
        URL wynnAPI=null; // URL for USER LOOKUP (Defined Below)
        String JReader; // JSONReader - Requests return json format
        String className;

        try {
            itemName = itemName.replaceAll("'", "%27");
            itemName = itemName.replaceAll(" ", "%20");
            wynnAPI = new URL("https://api.wynncraft.com/public_api.php?action=itemDB&search=" + itemName);
        }
        catch(MalformedURLException e) {
            e.printStackTrace();
            println(" ");
            println("[Error] MalformedURLException | Please contact the developer with stacktrace above.");
        }

        try {
            assert wynnAPI != null;
            Scanner s = new Scanner(wynnAPI.openStream(), "UTF-8");
            JReader = s.useDelimiter("\\A").next();
            s.close();

            JSONObject reader = (JSONObject) parser.parse(JReader);
            JSONArray items = (JSONArray) reader.get("items");

            JSONObject item_1=null;
            JSONObject item_2=null;
            JSONObject item_3=null;

            try {
                item_1 = (JSONObject) items.get(0);
            } catch (Exception e){
                item_1 = null;
            }

            if(item_1 != null) {
                String questRequired = "None";
                if(item_1.get("quest") != null){
                    questRequired = item_1.get("quest").toString();
                }

                String addedLore = "None";
                if(item_1.get("addedLore") != null){
                    addedLore = item_1.get("addedLore").toString();
                }

                println("+--------------------------------+");
                println(">>> Item Found -> " + item_1.get("name") + " <<<");
                println("Lore -> " + addedLore);
                println("Tier : Level -> " + item_1.get("tier") + " : " + item_1.get("level"));
                println("Required Quest -> " + questRequired);
                println("Type -> " + item_1.get("type"));
                println("Required Stats -> " +
                        "Str:" + item_1.get("strength") +
                        ", Dext:" + item_1.get("strength") +
                        ", Intel:" + item_1.get("intelligence") +
                        ", Agil:" + item_1.get("agility") +
                        ", Def:" + item_1.get("defense")
                );
                println("+--------------------------------+");

            }

        } catch (ParseException e) {
            e.printStackTrace();
            println(" ");
            println("[Error] ParseException | Please contact the developer with stacktrace above.");
        } catch (IOException e) {
            e.printStackTrace();
            println(" ");
            println("[Error] IOException | Please try again later then contact the developer with stacktrace above.");
        }

    }
}


