package com.doctor.genericbotaniapacktweaker.client;

import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.hollingsworth.arsnouveau.ArsNouveau;
import net.minecraft.resources.ResourceLocation;
import software.bernie.ars_nouveau.geckolib3.core.IAnimatable;
import software.bernie.ars_nouveau.geckolib3.model.AnimatedGeoModel;


public class GenericModel<T extends IAnimatable> extends AnimatedGeoModel<T> {
    public String path;

    public ResourceLocation modelLocation;
    public ResourceLocation textLoc;
    public ResourceLocation animationLoc;
    public String textPathRoot = "block";
    public String name;

    public GenericModel(String name, String theftName) {
        this.modelLocation = new ResourceLocation(GenericBotaniaPackTweaker.MODID, "geo/" + name + ".geo.json");
        this.textLoc = new ResourceLocation(GenericBotaniaPackTweaker.MODID, "textures/" + textPathRoot + "/" + name + ".png");
        //im lazy lol!
        this.animationLoc = new ResourceLocation(ArsNouveau.MODID, "animations/" + theftName + "_animations.json");
        this.name = name;
    }

    public GenericModel(String name, String badName, String textPath) {
        this(name, badName);
        this.textPathRoot = textPath;
        this.textLoc = new ResourceLocation(GenericBotaniaPackTweaker.MODID, "textures/" + textPathRoot + "/" + name + ".png");
    }

    public GenericModel withEmptyAnim(){
        this.animationLoc = new ResourceLocation(GenericBotaniaPackTweaker.MODID, "animations/empty.json");
        return this;
    }

    @Override
    public ResourceLocation getModelResource(T iAnimatable) {
        return modelLocation;
    }

    @Override
    public ResourceLocation getTextureResource(T iAnimatable) {
        return textLoc;
    }

    @Override
    public ResourceLocation getAnimationResource(T iAnimatable) {
        return animationLoc;
    }
}
