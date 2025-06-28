package drai.dev.gravelsextendedbattles.fabric;

import com.cobblemon.mod.common.*;
import drai.dev.gravelsextendedbattles.*;
import drai.dev.gravelsextendedbattles.loot.*;
import drai.dev.gravelsextendedbattles.mixin.loot.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.loot.v3.*;
import net.fabricmc.fabric.api.object.builder.v1.trade.*;
import net.fabricmc.fabric.api.registry.*;
import net.fabricmc.fabric.mixin.content.registry.*;
import net.fabricmc.loader.api.*;
import net.minecraft.world.entity.npc.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.*;
import net.minecraft.world.item.trading.*;
import net.minecraft.world.level.storage.loot.*;

import java.util.*;

import static drai.dev.gravelsextendedbattles.registries.GravelsExtendedBattlesItems.FROST_HEAL;

public class GravelsExtendedBattlesFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        GravelsExtendedBattles.init();
        LootTableEvents.MODIFY.register((key, tableBuilder, source, lookupProvider)-> {
            var pools = ((LootTableAccessor)tableBuilder.build()).getPools();
            GravelmonFossilManager.addLootPools(pools.toArray(new LootPool[0]));
            GravelmonFossilManager.processFossilAdditions(key.location(), tableBuilder);
        });
        CreativeTabsInit.initCreativeTabs();
    }

    public static void registerVillagerTrades(){
        TradeOfferHelper.registerVillagerOffers(CobblemonVillagerProfessions.NURSE, 5, factories -> {
            factories.add((entity, random) ->
                    new MerchantOffer(new ItemCost(Items.EMERALD, 10),
                            Optional.of(new ItemCost(Items.GLASS_BOTTLE)),
                            new ItemStack(CobblemonItems.ANTIDOTE, 1),
                            12, 30,.75f)
            );
        });
    }

    public static void registerBrewingRecipes(){
//        BrewingRecipeRegistryBuilderMixin.BUILD.registerItemRecipe(Potions.AWKWARD, Items.FEATHER, FROST_HEAL);
    }
}
