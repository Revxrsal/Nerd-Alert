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

package net.reflxction.nerd_alert.listeners;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.nerd_alert.utils.ChatColor;
import net.reflxction.nerd_alert.utils.NerdUtils;
import net.reflxction.nerd_alert.utils.ThreadTask;

import java.util.Timer;

/**
 * Listener class which listeners when a nerd joins
 */
public class ChatListener {

    private static boolean start = false;

    // Listener for the chat
    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent event) {
        // Message
        final String message = event.message.getFormattedText();
        // The timer task that should be executed when a nerd joins
        final ThreadTask task = new ThreadTask();
        // Loop through the nerds
        for (String nerd : NerdUtils.getNerds()) {
            // Check if the one who joined is a nerd
            if (message.contains(ChatColor.YELLOW + nerd + " joined")) {
                // Start the task
                new Timer().schedule(task, 1000);
            }
        }
    }

    /**
     * @return Boolean if the renderer should start
     */
    static boolean shouldStart() {
        return start;
    }

    /**
     * @param shouldStart Set if it's time to start
     */
    public static void setShouldStart(boolean shouldStart) {
        ChatListener.start = shouldStart;
    }

}
