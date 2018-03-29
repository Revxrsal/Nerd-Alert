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

package net.reflxction.nerd_alert.utils;

import net.reflxction.nerd_alert.core.NerdAlert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NerdUtils {

    /**
     * Method to set if the mod is either enabled or disabled
     *
     * @param flag Should be enabled or disabled
     */
    public static void setEnabled(boolean flag) {
        NerdAlert.isEnabled = flag;
        NerdAlert.getConfig().get("Enabled", "Enabled", true).set(flag);
        NerdAlert.getConfig().save();
    }

    /**
     * @return List of the nerds
     */
    public static List<String> getNerds() {

        String nerdsString = NerdAlert.getConfig().get("Nerds", "Nerds", "").getString().trim();
        String[] array = nerdsString.split(" ");
        List<String> nerds = new ArrayList<String>();
        Collections.addAll(nerds, array);
        DebugUtils.debug(nerds + " <- nerds array");
        return nerds;
    }

    /**
     * Adds a nerd to the config and to the nerds cache
     *
     * @param nerd The nerd to add
     */
    public static void addNerd(String nerd) {
        StringBuilder sb = new StringBuilder();
        for (String s : getNerds()) {
            sb.append(" ").append(s);
            NerdAlert.getConfig().get("Nerds", "Nerds", "").set(sb.toString() + " " + nerd);
        }
        getNerds().add(nerd);
        DebugUtils.debug(getNerds() + " <- new nerds array");
        NerdAlert.getConfig().save();
    }

    /**
     * List of all the nerds as a string, e.g "Technoblade Sk1er"
     *
     * @return Nerds string
     */
    private static String getNerdsString() {
        StringBuilder sb = new StringBuilder();
        for (String s : getNerds()) {
            sb.append(" ").append(s);
        }
        return sb.toString().trim();
    }

    /**
     * Removes a nerd from the config and from the nerds cache
     * @param nerd The nerd to remove
     */
    public static void removeNerd(String nerd) {
        if (!isNerd(nerd)) return;
        getNerds().remove(nerd);
        NerdAlert.getConfig().get("Nerds", "Nerds", "").set(getNerdsString().replace(" " + nerd, ""));
        NerdAlert.getConfig().save();
    }

    /**
     * Check if a player is a nerd
     * @param nerd Nerd to check on
     * @return If the string is a nerd
     */
    private static boolean isNerd(String nerd) {
        return getNerds().contains(nerd);
    }

    /**
     * Set the X to render the image in
     * @param x X to set
     */
    public static void setX(int x) {
        NerdAlert.setX(x);
        NerdAlert.getConfig().get("X", "X", 0).set(x);
        NerdAlert.getConfig().save();
    }

    /**
     * Set the Y to render the image in
     * @param y Y to set
     */
    public static void setY(int y) {
        NerdAlert.setY(y);
        NerdAlert.getConfig().get("Y", "Y", 0).set(y);
        NerdAlert.getConfig().save();
    }

}
