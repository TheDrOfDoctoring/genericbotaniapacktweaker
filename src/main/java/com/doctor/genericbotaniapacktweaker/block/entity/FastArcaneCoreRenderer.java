package com.doctor.genericbotaniapacktweaker.block.entity;


import com.doctor.genericbotaniapacktweaker.GenericBotaniaPackTweaker;
import com.doctor.genericbotaniapacktweaker.client.GenericModel;
import com.hollingsworth.arsnouveau.client.renderer.item.GenericItemBlockRenderer;
import com.hollingsworth.arsnouveau.client.renderer.tile.ArsGeoBlockRenderer;
import com.hollingsworth.arsnouveau.common.items.AnimBlockItem;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import software.bernie.ars_nouveau.geckolib3.model.AnimatedGeoModel;


public class FastArcaneCoreRenderer extends ArsGeoBlockRenderer<FastArcaneCoreBlockEntity> {
    public static AnimatedGeoModel model = new GenericModel("fast_arcane_core", "arcane_core");

    public FastArcaneCoreRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn) {
        this(rendererDispatcherIn, model);
    }

    public FastArcaneCoreRenderer(BlockEntityRendererProvider.Context rendererDispatcherIn, AnimatedGeoModel<FastArcaneCoreBlockEntity> modelProvider) {
            super(rendererDispatcherIn, modelProvider);
            }

    public static GenericItemBlockRenderer getISTER() {
            return new GenericItemBlockRenderer(model);
    }
}
