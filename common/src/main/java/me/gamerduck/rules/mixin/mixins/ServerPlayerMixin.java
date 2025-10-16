package me.gamerduck.rules.mixin.mixins;

import com.mojang.authlib.GameProfile;
import me.gamerduck.rules.common.GameRule;
import me.gamerduck.rules.mixin.MixinsVariable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.component.ResolvableProfile;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.ThreadLocalRandom;

import static me.gamerduck.rules.mixin.MixinsVariable.gameRules;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {

    public ServerPlayerMixin(Level p_250508_, GameProfile p_252153_) {
        super(p_250508_, p_252153_);
    }

    @Inject(method = "die", at = @At(value = "RETURN"))
    private void inject(DamageSource p_9035_, CallbackInfo ci) {
        if (getType().equals(EntityType.PLAYER) && gameRules.gameRuleValueBool(level(), GameRule.PLAYERS_HEAD_DROP)) {
            Double chance = gameRules.gameRuleValueDouble(level(), GameRule.PLAYERS_HEAD_DROP_CHANCE);
            if (chance < 0 || chance >= ThreadLocalRandom.current().nextDouble(0, 100)) {
                ItemStack head = new ItemStack(Items.PLAYER_HEAD);
                head.set(DataComponents.PROFILE, ResolvableProfile.createResolved(getGameProfile()));
                EntityType.ITEM.spawn((ServerLevel) level(), (ent) -> {
                    ent.setItem(head);
                }, BlockPos.containing(position()), EntitySpawnReason.EVENT, false, false);

            }
        }
    }
}
