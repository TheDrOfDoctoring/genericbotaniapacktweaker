package com.doctor.genericbotaniapacktweaker.block.entity;

import com.doctor.genericbotaniapacktweaker.init.BlockEntityRegistry;
import com.doctor.genericbotaniapacktweaker.init.BlockRegistry;
import com.hollingsworth.arsnouveau.common.block.tile.ArcaneCoreTile;
import com.hollingsworth.arsnouveau.common.block.tile.ModdedTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.ars_nouveau.geckolib3.core.IAnimatable;
import software.bernie.ars_nouveau.geckolib3.core.PlayState;
import software.bernie.ars_nouveau.geckolib3.core.builder.AnimationBuilder;
import software.bernie.ars_nouveau.geckolib3.core.controller.AnimationController;
import software.bernie.ars_nouveau.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.ars_nouveau.geckolib3.core.manager.AnimationData;
import software.bernie.ars_nouveau.geckolib3.core.manager.AnimationFactory;
import software.bernie.ars_nouveau.geckolib3.util.GeckoLibUtil;

//to clarify this isn't a more performant version of something, it just makes the apparatus recipe go faster
public class FastArcaneCoreBlockEntity extends ModdedTile implements IAnimatable {
    AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public FastArcaneCoreBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.FAST_CORE.get(), pos, state);
    }


    public PlayState spin(AnimationEvent<?> e) {
        e.getController().setAnimation((new AnimationBuilder()).addAnimation("gem_spin"));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 1.0F, this::spin));
    }

    public AnimationFactory getFactory() {
        return this.factory;
    }
}
