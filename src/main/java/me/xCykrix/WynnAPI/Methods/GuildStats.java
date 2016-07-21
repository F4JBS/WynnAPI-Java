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
public class GuildStats {
    private static JSONParser parser = new JSONParser();

    public static void guildLookup(String guildName){
        URL wynnAPI=null; // URL for USER LOOKUP (Defined Below)
        String JReader; // JSONReader - Requests return json format
        String className;

        try {
            guildName = guildName.replaceAll(" ", "%20");
            wynnAPI = new URL("https://api.wynncraft.com/public_api.php?action=guildStats&command=" + guildName);
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
            JSONArray members = (JSONArray) reader.get("members");

            println(" ");
            println("[GuildStats] Request Complete! Printing Found Information.");
            println("+--------------------------------+");
            println(">>> Basic Information <<<");
            println("Name : Prefix -> " + reader.get("name") + " : " + reader.get("prefix"));
            println("Level : XP -> " + reader.get("level") + " : " + reader.get("xp") + "%");
            println("Created -> " + reader.get("createdFriendly"));
            println("Territories -> " + reader.get("territories"));
            println("+--------------------------------+");
            println(">>> Member List <<<");
            println(reader.toString());
            for (int i = 0, size = members.size(); i < size; i++)
            {
                JSONObject objectInArray = (JSONObject) members.get(i);
                println("[" + objectInArray.get("rank") + "][Joined: " + objectInArray.get("joinedFriendly") + "] " + objectInArray.get("name") + ", Contributed XP: " + objectInArray.get("contributed"));
            }
            println("+--------------------------------+");


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


