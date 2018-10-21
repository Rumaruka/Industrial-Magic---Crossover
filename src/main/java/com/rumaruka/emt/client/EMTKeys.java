package com.rumaruka.emt.client;

import com.rumaruka.emt.util.EMTTextHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class EMTKeys {
    public static KeyBinding keyUnequip;

    public static void registerKeys() {
        keyUnequip = new KeyBinding(EMTTextHelper.localize("gui.EMT.key.unequip"), Keyboard.KEY_Z, "EMT");
        ClientRegistry.registerKeyBinding(keyUnequip);
    }
}
