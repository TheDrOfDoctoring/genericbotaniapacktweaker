package com.doctor.genericbotaniapacktweaker.init;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.glyphs.EffectPebbleSpawn;
import com.hollingsworth.arsnouveau.api.ArsNouveauAPI;
import com.hollingsworth.arsnouveau.api.spell.AbstractSpellPart;

import java.util.ArrayList;
import java.util.List;

public class GlyphRegistry {
    //effectively a copy of TooManyGlyph's ArsNouveauRegistry class

    public static List<AbstractSpellPart> registeredSpells = new ArrayList<>();
    public static void registerGlyphs() {
        register(EffectPebbleSpawn.INSTANCE);
    }
    public static void register(AbstractSpellPart spellPart) {
        if (!GenericBotaniaPackTweaker.MODID.equals(spellPart.getRegistryName().getNamespace())) {
            GenericBotaniaPackTweaker.LOGGER.error("Unintended unsafe glyph namespace '{}' found in glyph '{}'.",
                    spellPart.getRegistryName().getNamespace(),
                    spellPart.getRegistryName().getPath());
            throw new AssertionError();
        }
        if (!"glyph_".equals(spellPart.getRegistryName().getPath().substring(0, 6))) {
            GenericBotaniaPackTweaker.LOGGER.error("Unintended unsafe glyph name '{}' does not begin with 'glyph_'.",
                    spellPart.getRegistryName().getPath());
            throw new AssertionError();
        }
        ArsNouveauAPI.getInstance().registerSpell(spellPart);
        registeredSpells.add(spellPart);
    }
}
