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

package net.reflxction.nerd_alert.core;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.reflxction.nerd_alert.commands.MainCommand;
import net.reflxction.nerd_alert.listeners.ChatListener;
import net.reflxction.nerd_alert.listeners.RenderListener;
import net.reflxction.nerd_alert.utils.Reference;

import java.io.File;

/**
 * ReflxctionDev presents: The most useless mod founded on earth, and let me honest I have no idea why do I spend my time doing such things
 * <p>
 * Sponsored by: The Impurity Hypixel Guild
 */
@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_VERSIONS)
public class NerdAlert {

    // Config to save data
    private static Configuration config;

    // boolean value if the mod is enabled
    public static boolean isEnabled;

    // X for the image to render
    private static int X = 0;

    // Y for the image to render
    private static int Y = 0;


    /**
     * Initialize variables here
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(new File("config/nerd-alert.cfg"));
        config.load();
        isEnabled = getConfig().get("Enabled", "Enabled", true).getBoolean();
        X = getConfig().get("X", "X", 0).getInt();
        Y = getConfig().get("Y", "Y", 0).getInt();
        config.save();
    }

    /**
     * Register events and client commands here
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ChatListener());
        MinecraftForge.EVENT_BUS.register(new RenderListener());
        ClientCommandHandler.instance.registerCommand(new MainCommand());

    }

    /**
     * Register server commands here
     */
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new MainCommand());
    }

    /**
     * Config of the mod
     *
     * @return Config of the mod
     */
    public static Configuration getConfig() {
        return config;
    }

    /**
     * If the mod is enabled
     *
     * @return boolean if the mod is enabled
     */
    public static boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Set cached X
     * @param x X to set
     */
    public static void setX(int x) {
        NerdAlert.X = x;
    }

    /**
     * Set cached Y
     * @param y Y to set
     */
    public static void setY(int y) {
        NerdAlert.Y = y;
    }

    /**
     * Get the X
     */
    public static int getX() {
        return X;
    }

    /**
     * Get the Y
     */
    public static int getY() {
        return Y;
    }

}
