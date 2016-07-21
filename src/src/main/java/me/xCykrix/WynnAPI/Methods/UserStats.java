package me.xCykrix.WynnAPI.Methods;

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
public class UserStats {
    private static JSONParser parser = new JSONParser();

    public static void userLookup(String userName){
        URL wynnAPI=null; // URL for USER LOOKUP (Defined Below)
        String JReader; // JSONReader - Requests return json format
        String className;

        try {
            wynnAPI = new URL("https://api.wynncraft.com/public_api.php?action=playerStats&command=" + userName);
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
            JSONObject global = (JSONObject) reader.get("global");
            JSONObject wizard_fortress = (JSONObject) reader.get("wizard_fortress");
            JSONObject rankings = (JSONObject) reader.get("rankings");
            JSONObject guild = (JSONObject) reader.get("guild");

            JSONObject classInfo = (JSONObject) reader.get("classes");
            JSONObject classChild;


            String tag = (String) reader.get("tag");
            if(tag.equals("")){
                tag="None";
            }

            String current_server = (String) reader.get("current_server");
            if(current_server == null){
                current_server = "Offline";
            } else {
                current_server = "Online: " + current_server;
            }

            String guild_info = guild.get("rank") + " of " + guild.get("name");
            if(guild.get("rank").equals("") || guild.get("name").equals("None")){
                guild_info = "No Guild Found";
            }

            long playtime = Long.valueOf(reader.get("playtime").toString());
            long playtimeHours = playtime / 60;

            println(" ");
            println("[UserStats] Request Complete! Printing Found Information.");
            println("+--------------------------------+");
            println(">>> Basic Information <<<");
            println("Username -> " + reader.get("username"));
            println("User Tag -> " + tag.toUpperCase());
            println("Show Tag -> " + reader.get("displayTag"));
            println("Playtime -> About " + playtimeHours + " Hours");
            println("Join Date -> " + reader.get("first_join_friendly"));
            println("Last Seen -> " + reader.get("last_join_friendly"));
            println("+--------------------------------+");
            println(">>> Global Stats <<<");
            println("Total Levels -> " + global.get("total_level"));
            println("Items Identified -> " + global.get("items_identified"));
            println("Chests Found -> " + global.get("chests_found"));
            println("Blocks Walked -> " + global.get("blocks_walked"));
            println("Logins -> " + global.get("logins"));
            println("Deaths -> " + global.get("deaths"));
            println("+--------------------------------+");
            println(">>> Ranking Stats <<<");
            println("Guild Rank -> " + rankings.get("guild"));
            println("Player Rank -> " + rankings.get("player"));
            println("Versus Rank -> " + rankings.get("pvp"));
            println("+--------------------------------+");
            println(">>> Wizard Fortress Stats <<<");
            println("Kills : Deaths -> " + wizard_fortress.get("kills") + " : " + wizard_fortress.get("deaths"));
            println("Wins : Losses -> " + wizard_fortress.get("wins") + " : " + wizard_fortress.get("losses"));
            println("+--------------------------------+");
            println(">>> Guild Information <<<");
            println("Guild -> " + guild_info);
            println("+--------------------------------+");
            println(">>> Classes List <<<");
            Iterator<?> classIterator = classInfo.values().iterator();
            Iterator<?> nameIterator = classInfo.keySet().iterator();
            while (classIterator.hasNext() && nameIterator.hasNext()) {
                className = (String) nameIterator.next();
                classChild = (JSONObject) classIterator.next();

                long classPlaytime = Long.valueOf(classChild.get("playtime").toString())/60;

                println("+---------------------------------+");
                println("[" + className.toUpperCase() + "] Level : XP -> " + classChild.get("level") + " : " + classChild.get("xp") + "%");
                println("Mob Kills : Items Identified -> " + classChild.get("mob_killed") + " : " + classChild.get("items_identified"));
                println("Quests : Dungeons -> " + classChild.get("questsAmount") + " : " + classChild.get("dungeonsAmount"));
                println("Logins : Deaths -> " + classChild.get("logins") + " : " + classChild.get("deaths"));
                println("Chests : Walked -> " + classChild.get("chests_found") + " : " + classChild.get("blocks_walked"));
                println("Playtime : Events -> About " + classPlaytime + " Hours : " + classChild.get("events_won"));
            }
            println("+--------------------------------+");
            println(" ");

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


