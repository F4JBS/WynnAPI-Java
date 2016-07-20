package me.xCykrix.WynnAPI.Methods;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;

import static me.xCykrix.WynnAPI.APIs.PrintAPI.println;

/**
 * File Created by samuel at 7/20/16.
 * This File is not to be modified or redistributed without explicit permission from myself.
 */
public class UserStats {
    private static JSONParser parser = new JSONParser();

//    public static void query_userStats(String userName){
//        URL wynnapi_stats=null; // URL for GUILD LOOKUP (Defined Below)
//        String JReader; // JSONReader - Requests return json format
//        String className;
//
//        try {
//            wynnapi_stats = new URL("http://api.wynncraft.com/public_api.php?action=playerStats&command=" + userName);
//            println(wynnapi_stats.toString());
//        } catch(MalformedURLException except) {
//            except.printStackTrace();
//            println("[UserStatsHandler] Exception Fired! Please send the text above to sammyvoeller@gmail.com");
//        }
//
//        try {
//            Scanner scan = new Scanner(wynnapi_stats.openStream(), "UTF-8");
//            JReader = scan.useDelimiter("\\A").next();
//            scan.close();
//
//            JSONObject reader = (JSONObject) parser.parse(JReader);
//            JSONObject globalStats = (JSONObject) reader.get("global");
//            JSONObject wizardStats = (JSONObject) reader.get("wizard_fortress");
//            JSONObject rankingStats = (JSONObject) reader.get("rankings");
//            JSONObject guildInfo = (JSONObject) reader.get("guild");
//
//            JSONObject classInfo = (JSONObject) reader.get("classes");
//            JSONObject classChild;
//
//            String tag = (String) reader.get("tag");
//            if(tag.equals("")){
//                tag="None";
//            }
//
//            long play = (long) Long.valueOf(reader.get("playtime").toString());
//            play = play/60;
//
//            println("[UserLookup] Request Complete.");
//            println(">>> Information <<<");
//            println("- Name: " + reader.get("username"));
//            println("- Rank: " + tag);
//            println("- Veteran: " + reader.get("veteran"));
//            println("- Playtime: " + play + " hours");
//            println("- First Join | Last Join: " + reader.get("first_join_friendly") + " | " + reader.get("last_join_friendly"));
//            println("- Current Server: " + reader.get("current_server"));
//            println(">>> Player Classes <<<");
//            Iterator<?> iterator = classInfo.values().iterator();
//            Iterator<?> nameIterator = classInfo.keySet().iterator();
//            while (iterator.hasNext() && nameIterator.hasNext()) {
//                className = (String) nameIterator.next();
//                classChild = (JSONObject) iterator.next();
//                println("-------------------------");
//                println(">> " + className);
//                println("- Level: " + classChild.get("level"));
//                println("- XP Percent: " + classChild.get("xp") + "%");
//                println("- Quests Amount: " + classChild.get("questsAmount"));
//                println("- Dungeons Amount: " + classChild.get("dungeonsAmount"));
//                println("- Items Identified: " + classChild.get("items_identified"));
//                println("- Mobs Killed: " + classChild.get("mobs_killed"));
//                println("-------------------------");
//
//            }
//            println(">>> Global Rankings Stats <<<");
//            println("> Guild Ranking: " + rankingStats.get("guild"));
//            println("> Player Ranking: " + rankingStats.get("player"));
//            println("> PVP Ranking: " + rankingStats.get("pvp"));
//            println(">>> Global Stats <<<");
//            println("> Logins: " + globalStats.get("logins"));
//            println("> Items Identified: " + globalStats.get("items_identified"));
//            println("> Chests Found: " + globalStats.get("chests_found"));
//            println("> Mobs Killed: " + globalStats.get("mobs_killed"));
//            println("> Deaths: " + globalStats.get("deaths"));
//            println("> PVP Kills: " + globalStats.get("pvp_kills"));
//            println("> PVP Deaths: " + globalStats.get("pvp_deaths"));
//            println("> Blocks Walked: " + globalStats.get("blocks_walked"));
//            println("> Total Levels: " + globalStats.get("total_level"));
//            println(">>> Wizard Fortress Stats <<<");
//            println("> Wins: " + wizardStats.get("wins"));
//            println("> Losses: " + wizardStats.get("losses"));
//            println("> Kills: " + wizardStats.get("kills"));
//            println("> Deaths: " + wizardStats.get("deaths"));
//            println(">>> Guild Stats <<<");
//            println("> Name: " + guildInfo.get("name"));
//            println("> Rank: " + guildInfo.get("rank"));
//            println(">>> Information <<<");
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//            println(" ");
//            println("[Error] ParseException | Please contact the developer with stacktrace above.");
//        } catch (IOException e) {
//            e.printStackTrace();
//            println(" ");
//            println("[Error] IOException | Please try again later then contact the developer with stacktrace above.");
//        }
//    }

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
            JSONObject globalStats = (JSONObject) reader.get("global");
            JSONObject wizardStats = (JSONObject) reader.get("wizard_fortress");
            JSONObject rankingStats = (JSONObject) reader.get("rankings");
            JSONObject guildInfo = (JSONObject) reader.get("guild");

            JSONObject classInfo = (JSONObject) reader.get("classes");
            JSONObject classChild;

            String tag = (String) reader.get("tag");
            if(tag.equals("")){
                tag="None";
            }

            println("[UserLookup] Request Complete.");
            println(">>> Information <<<");
            println("- Player: " + reader.get("username"));
            println("- Rank: " + tag);
            println("- Veteran: " + reader.get("veteran"));
            println("- Playtime: " + reader.get("playtime"));
            println("- First Join | Last Join: " + reader.get("first_join_friendly") + " | " + reader.get("last_join_friendly"));
            println("- Current Server: " + reader.get("current_server"));
            println(">>> ---------- Classes ---------- <<<");
            Iterator<?> iterator = classInfo.values().iterator();
            Iterator<?> nameIterator = classInfo.keySet().iterator();
            while (iterator.hasNext() && nameIterator.hasNext()) {
                className = (String) nameIterator.next();
                classChild = (JSONObject) iterator.next();
                println("-------------------------");
                println(">> " + className.toUpperCase());
                println("- Level: " + classChild.get("level"));
                println("- XP Percent: " + classChild.get("xp") + "%");
                println("- Quests Complete: " + classChild.get("questsAmount"));
                println("- Dungeons Complete: " + classChild.get("dungeonsAmount"));
                println("- Items Identified: " + classChild.get("items_identified"));
                println("- Mobs Killed: " + classChild.get("mobs_killed"));
                println("-------------------------");
            }
            println(">>> ----------------------------- <<<");
            println("+ Global Stats +");
            println("- Logins: " + globalStats.get("logins"));
            println("- Items Identified: " + globalStats.get("items_identified"));
            println("- Chests Found: " + globalStats.get("chests_found"));
            println("- Mobs Killed: " + globalStats.get("mobs_killed"));
            println("- Deaths: " + globalStats.get("deaths"));
            println("- PVP Kills: " + globalStats.get("pvp_kills"));
            println("- PVP Deaths: " + globalStats.get("pvp_deaths"));
            println("- Blocks Walked: " + globalStats.get("blocks_walked"));
            println("- Total Levels: " + globalStats.get("total_level"));
            println(">>> ----------------------------- <<<");
            println("+ Global Rankings Stats +");
            println("- Guild Ranking: " + rankingStats.get("guild"));
            println("- Player Ranking: " + rankingStats.get("player"));
            println("- PVP Ranking: " + rankingStats.get("pvp"));
            println(">>> ----------------------------- <<<");
            println("+ Wizard Fortress Stats +");
            println("- Wins: " + wizardStats.get("wins"));
            println("- Losses: " + wizardStats.get("losses"));
            println("- Kills: " + wizardStats.get("kills"));
            println("- Deaths: " + wizardStats.get("deaths"));
            println(">>> ----------------------------- <<<");
            println("+ Guild Stats +");
            println("- Name: " + guildInfo.get("name"));
            println("- Rank: " + guildInfo.get("rank"));
            println(">>> ----------------------------- <<<");
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
