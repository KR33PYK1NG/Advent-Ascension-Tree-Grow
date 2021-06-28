package rmc.mixins.advent_ascension_tree_grow.inject;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.SaplingBlock;
import net.minecraft.block.trees.Tree;
import net.tslat.aoa3.block.functional.sapling.DarkGrowingSapling;
import net.tslat.aoa3.common.registration.worldgen.AoAFeatures.Features;
import net.tslat.aoa3.worldgen.feature.features.trees.AoATree;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
@Mixin(value = SaplingBlock.class)
public abstract class SaplingBlockMixin {

    private static int rmc$counter;

    @Shadow @Final @Mutable
    private Tree treeGrower;

    @Inject(method = "Lnet/minecraft/block/SaplingBlock;<init>(Lnet/minecraft/block/trees/Tree;Lnet/minecraft/block/AbstractBlock$Properties;)V",
            at = @At(value = "TAIL"))
    private void assignTreeGrower(CallbackInfo mixin) {
        if (this.treeGrower == null
         && (Object) this instanceof DarkGrowingSapling) {
            switch (rmc$counter++) {
                case 0: this.treeGrower = new AoATree(Features.HAUNTED_TREE); break;
                case 1: this.treeGrower = new AoATree(Features.LUNICIA_TREE); break;
            }
        }
    }

}