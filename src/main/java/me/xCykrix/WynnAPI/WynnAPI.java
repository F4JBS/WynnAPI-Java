package me.xCykrix.WynnAPI;

import me.xCykrix.WynnAPI.Methods.UserStats;

import java.util.Scanner;

import static me.xCykrix.WynnAPI.APIs.PrintAPI.print;
import static me.xCykrix.WynnAPI.APIs.PrintAPI.println;

/**
 * File Created by samuel at 7/20/16.
 * This File is not to be modified or redistributed without explicit permission from myself.
 */
public class WynnAPI {
    private static boolean isRunning = true;
    private static Scanner commandInput = new Scanner(System.in);
    private static String commandLineInput = null;
    private static String[] commandList = null;

    public static void main(String[] args){
        println(" +---- Wynncraft CommandLine API -----+");
        println(" | > RateLimit: 250 Req. / 10 Mins    |");
        println(" | > Compiler: Java8u91               |");
        println(" | > Created by: xCykrix              |");
        println(" | > Open Source: <url_here>          |");
        println(" +------------------------------------+");

        while(isRunning) {
            /**
             * Input Query : Sets and Defines Variables
             */
            print("WynnAPI >> ");
            commandLineInput = commandInput.nextLine();
            commandList = commandLineInput.split(" ");

            runRequester(commandLineInput, commandList);

        }
    }

    public static void runRequester(String commandLineInput, String[] commandList){
        if(commandList[0].equalsIgnoreCase("help")) try {
            println("+----------- Command List ---------------------+");
            println("| help -> Displays this dialog.                |");
            println("| user <target> -> Queries a player for stats. |");
            println("+----------------------------------------------+");
        } catch(Exception e) {
            e.printStackTrace();
            println(" ");
            println("[HelpHandler] Exception Fired! Please send the text above to sammyvoeller@gmail.com");
        }

        if(commandList[0].equalsIgnoreCase("exit")) try {
            isRunning = false;
        } catch(Exception e) {
            e.printStackTrace();
            println(" ");
            println("[ExitHandler] Exception Fired! Please send the text above to sammyvoeller@gmail.com");
        }

        /**
         * @params player : Defined Player
         * @command user : Queries player to API
         * @usage Queries Player against WynncraftAPI
         */
        else if(commandList[0].equalsIgnoreCase("user")) try {
            String player = "";

            // Defines playername
            try { player = commandList[1]; } catch(Exception noReturn){ player = ""; }

            if(player.length() < 3 || player.length() == 0) {
                println("[UserStatsHandler] Exception Fired! Please specify a player name greater than 2 characters.");
            }
            else {
                UserStats.userLookup(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
            println(" ");
            println("[UserStatsHandler] Exception Fired! Please send the text above to sammyvoeller@gmail.com");
            println("[Possible Cause: Invalid Username (Non-Existant User)");
        }

        /**
         * @params guild : Defined Guild
         * @command guild : Queries guild to API
         * @usage Queries Guild against WynncraftAPI
         */
        else if(commandList[0].equalsIgnoreCase("guild")) try {
            int loopCount = 0;
            String guild = "";

            // Builds guildname
            for (String queryPass : commandList) {
                if (loopCount > 0) {
                    guild = guild + " " + queryPass;
                }
                loopCount++;
            }

            if(guild.replace(" ", "").length() < 4 || guild.replace(" ", "").length() == 0) {
                println("[GuildStatsHandler] Exception Fired! Please specify a guild name greater than 3 characters.");
            }
            else {
                guild = guild.replaceFirst(" ", "");
                //TODO: RequestCall - GUILD
            }

        } catch (Exception e) {
            e.printStackTrace();
            println(" ");
            println("[GuildStatsHandler] Exception Fired! Please send the text above to sammyvoeller@gmail.com");
        }

        /**
         * @params guildKeyword : Defined Guild Keyword
         * @command user : Queries guildlist to API
         * @usage Queries guildKeyword against WynncraftAPI
         */
        else if(commandList[0].equalsIgnoreCase("guildlookup")) try {
            String guildKeyword = "";

            // Defines guildkeyword
            try { guildKeyword = commandList[1]; } catch(Exception noReturn){ guildKeyword = ""; }

            if(guildKeyword.length() < 4 || guildKeyword.length() == 0) {
                println("[GuildLookupHandler] Exception Fired! Please specify a guild keyword name greater than 3 characters.");
            }
            else {
                //TODO: RequestCall - GuildLookup
            }

        } catch (Exception e) {
            e.printStackTrace();
            println(" ");
            println("[GuildLookupHandler] Exception Fired! Please send the text above to sammyvoeller@gmail.com");
        }

        /**
         * @params itemName : Defined Item
         * @command item : Queries itemName to API
         * @usage Queries itemName against WynncraftAPI
         */
        else if(commandList[0].equalsIgnoreCase("item")) try {
            int loopCount = 0;
            String itemName = "";

            // Builds guildname
            for (String queryPass : commandList) {
                if (loopCount > 0) {
                    itemName = itemName + " " + queryPass;
                }
                loopCount++;
            }

            if(itemName.replace(" ", "").length() < 4 || itemName.replace(" ", "").length() == 0) {
                println("[ItemLookupHandler] Exception Fired! Please specify a item name greater than 3 characters.");
            }
            else {
                itemName = itemName.replaceFirst(" ", "");
                //TODO: RequestCall - ITEM
            }

        } catch (Exception e) {
            e.printStackTrace();
            println(" ");
            println("[ItemLookupHandler] Exception Fired! Please send the text above to sammyvoeller@gmail.com");
        }

        /**
         * @command status : Queries guildlist to API
         * @usage Queries status against WynncraftAPI
         */
        else if(commandList[0].equalsIgnoreCase("status")) try {
            //TODO: RequestCall - STATUS
        } catch (Exception e) {
            e.printStackTrace();
            println(" ");
            println("[StatusHandler] Exception Fired! Please send the text above to sammyvoeller@gmail.com");
        }
    }

}
