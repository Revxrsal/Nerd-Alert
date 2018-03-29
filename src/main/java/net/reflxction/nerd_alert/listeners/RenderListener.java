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

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.reflxction.nerd_alert.core.NerdAlert;
import net.reflxction.nerd_alert.utils.Reference;

/**
 * Listener which renders the alert
 */
public class RenderListener {

    // The resource location for the alert.png
    private ResourceLocation alert = new ResourceLocation(Reference.MOD_ID, "textures/alert.png");

    // Listener to render the alert
    @SubscribeEvent
    public void onGameRenderOverlayPost(RenderGameOverlayEvent.Post event) {
        // Check if it's enabled
        if (NerdAlert.isEnabled()) {
            // Check if it's time to render the alert
            if (ChatListener.shouldStart()) {
                // Render the alert
                render();
            }
        }
    }

    /**
     * Method which renders the alert after managing all the things
     */
    private void render() {
        // Enable Alpha, Matrix and Blend from LWJGL so we can use colors
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        // Append the texture
        Minecraft.getMinecraft().getTextureManager().bindTexture(alert);
        // Render the texture
        Minecraft.getMinecraft().ingameGUI.drawTexturedModalRect(NerdAlert.getX(), NerdAlert.getY(), 0, 0, 600, 300);
        // Disable Alpha, Matrix and Blend
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }
}
