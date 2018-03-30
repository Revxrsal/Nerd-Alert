/*
 * * Copyright 2018 github.com/ReflxctionDev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.reflxction.nerd_alert.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.reflxction.nerd_alert.core.NerdAlert;
import net.reflxction.nerd_alert.utils.NerdUtils;
import net.reflxction.nerd_alert.utils.Player;
import net.reflxction.nerd_alert.utils.Reference;
import net.reflxction.nerd_alert.utils.ThreadTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the mod command class "/nerds"
 */
public class MainCommand implements ICommand {

    private Player p = new Player();

    private final ThreadTask task = new ThreadTask();

    @Override
    public String getCommandName() {
        return "nerds";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/nerds <add | remove | list | toggle> <name>";
    }

    @Override
    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<String>();
        aliases.add("nerd");
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        switch (args.length) {
            case 0:
                p.sendMessage(Reference.PREFIX + "&cIncorrect command usage. Try " + getCommandUsage(sender));
                break;
            case 1:
                if (args[0].equalsIgnoreCase("toggle")) {
                    NerdUtils.setEnabled(!NerdAlert.isEnabled());
                    p.sendMessage(Reference.PREFIX + (NerdAlert.isEnabled() ? "&aNerd Alert has been enabled" : "&cNerd Alert has been disabled"));
                }
                if (args[0].equalsIgnoreCase("add")) {
                    p.sendMessage(Reference.PREFIX + "&cIncorrect usage. Try /nerds add <name>");
                }
                if (args[0].equalsIgnoreCase("remove")) {
                    p.sendMessage(Reference.PREFIX + "&cIncorrect usage. Try /nerds remove <name>");
                }
                if (args[0].equalsIgnoreCase("list")) {
                    p.sendMessage(Reference.PREFIX + "&bCurrent nerds:");
                    if (NerdUtils.getNerds().size() != 0) {
                        for (String nerd : NerdUtils.getNerds()) {
                            p.sendMessage("&7- &a" + nerd);
                        }
                    } else {
                        p.sendMessage("&cNone! Nerdify some with &e/nerds add <name>");
                    }
                }
                if(args[0].equalsIgnoreCase("test")) {
                    task.run();
                }
                break;
            case 2:
                if (args[0].equalsIgnoreCase("add")) {
                    NerdUtils.addNerd(args[1]);
                    p.sendMessage(Reference.PREFIX + "&b" + args[1] + " &ahas been nerdified!");
                }
                if (args[0].equalsIgnoreCase("remove")) {
                    NerdUtils.removeNerd(args[1]);
                    p.sendMessage(Reference.PREFIX + "&b" + args[1] + " &cis no longer a nerd");
                }
                if (args[0].equalsIgnoreCase("x")) {
                    int x;
                    try {
                        x = Integer.parseInt(args[1]);
                        NerdUtils.setX(x);
                        p.sendMessage(Reference.PREFIX + "&aX has been set to " + args[1]);
                    } catch (NumberFormatException ex) {
                        p.sendMessage(Reference.PREFIX + "&cExpected a number, but found &e" + args[1]);
                    }
                }

                if(args[0].equalsIgnoreCase("y")) {
                    int y;
                    try {
                        y = Integer.parseInt(args[1]);
                        NerdUtils.setY(y);
                        p.sendMessage(Reference.PREFIX + "&aY has been set to " + args[1]);
                    } catch (NumberFormatException ex) {
                        p.sendMessage(Reference.PREFIX + "&cExpected a number, but found " + args[1]);
                    }
                }
                break;
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        List<String> autocomplete = new ArrayList<String>();
        autocomplete.add("nerds");
        return autocomplete;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
