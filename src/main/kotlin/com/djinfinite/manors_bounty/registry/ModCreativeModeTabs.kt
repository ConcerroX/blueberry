package com.djinfinite.manors_bounty.registry

import com.djinfinite.manors_bounty.ManorsBounty
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTabs
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier

object ModCreativeModeTabs {

    val CREATIVE_MODE_TABS: DeferredRegister<CreativeModeTab> = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ManorsBounty.ID)

    // A lazy list that will contain all registered items for the creative tab.
    // This ensures that all items are initialized before being added to the tab.
    private val TAB_ITEMS by lazy {
        listOf<ItemLike>(
            ModItems.PEARL_ROCK.get(),
            ModItems.SNOW_DYE.get(),
            ModItems.CREATIVITY_MECHANISM.get(),

            ModItems.OLIVE_OIL_BUCKET.get(),
            ModItems.HOT_SPRING_BUCKET.get(),
            ModItems.CAKE_LIQUID_BUCKET.get(),

            ModItems.VANILLA_CAKE_SLICE.get(),
            ModItems.CARAMEL_CHOCOLATE_CAKE.get(),
            ModItems.CARAMEL_CHOCOLATE_CAKE_SLICE.get(),
            ModItems.SWEET_BERRY_CAKE.get(),
            ModItems.SWEEP_BERRY_CAKE_SLICE.get(),
            ModItems.CHORUS_FLOWER_JELLY_CAKE.get(),
            ModItems.CHORUS_FLOWER_JELLY_CAKE_SLICE.get(),
            ModItems.NETHER_WART_SOUL_CAKE.get(),
            ModItems.NETHER_WART_SOUL_CAKE_SLICE.get(),

            ModItems.MARBLE_BOWL.get(),
            ModItems.BORSCHT.get(),
            ModItems.CREAM_WITH_MUSHROOM_SOUP.get(),
            ModItems.MISO_SOUP.get(),
            ModItems.PRAWN_AND_CORN_SOUP.get(),
            ModItems.COSMIC_DRAGON_SALAD.get(),
            ModItems.ROSA_SALAD.get(),
            ModItems.SEA_PEARL_SALAD.get(),
            ModItems.MELON_SALAD.get(),

            ModItems.COFFEE.get(),
            ModItems.DEFORMED_GLASS_BOTTLE.get(),
            ModItems.MELON_SODA.get(),
            ModItems.BUBBLE_TEA.get(),
            ModItems.GRIMACE_SHAKE.get(),
            ModItems.ORANGE_JUICE.get(),
            ModItems.LEMONADE.get(),
            ModItems.APPLE_JUICE.get(),
            ModItems.PEAR_WITH_ROCK_SUGAR.get(),
            ModItems.BELLINI_BASE.get(),
            ModItems.STRAWBERRY_SHAKE.get(),
            ModItems.MANGO_JUICE.get(),
            ModItems.PINEAPPLE_JUICE.get(),
            ModItems.WATERMELON_JUICE.get(),
            ModItems.MARTINI.get(),
            ModItems.CHORUS_FRUIT_JUICE.get(),
            ModItems.FRUIT_PUNCH.get(),
            ModItems.BLUEBERRY_YOGURT.get(),
            ModItems.MANGO_PUDDING.get(),
            ModItems.RISALAMANDE.get(),
            ModItems.ETON_MESS.get(),
            ModItems.MARMALADE.get(),
            ModItems.BOXED_CHOCOLATE_MILK.get(),
            ModItems.BOXED_LEMON_TEA.get(),
            ModItems.BOTTLED_OLIVE_OIL.get(),
            ModItems.LAVENDER_ESSENTIAL_OIL.get(),

            ModItems.CHORUS_CHROME_CUBE.get(),

            ModItems.ICE_CREAM_CONE.get(),
            ModItems.VANILLA_ICE_CREAM.get(),
            ModItems.CHOCOLATE_ICE_CREAM.get(),
            ModItems.BLUEBERRY_ICE_CREAM.get(),
            ModItems.CHERRIES_ICE_CREAM.get(),
            ModItems.STARFRUIT_ICE_CREAM.get(),
            ModItems.JALAPENO_ICE_CREAM.get(),
            ModItems.VANILLA_CHOCOLATE_ICE_CREAM.get(),
            ModItems.BLUEBERRY_CHERRIES_ICE_CREAM.get(),
            ModItems.STARFRUIT_JALAPENO_ICE_CREAM.get(),

            ModItems.VANILLA_ICE_CREAM_FLUID_BUCKET.get(),
            ModItems.CHOCOLATE_ICE_CREAM_FLUID_BUCKET.get(),
            ModItems.BLUEBERRY_ICE_CREAM_FLUID_BUCKET.get(),
            ModItems.CHERRIES_ICE_CREAM_FLUID_BUCKET.get(),
            ModItems.STARFRUIT_ICE_CREAM_FLUID_BUCKET.get(),
            ModItems.JALAPENO_ICE_CREAM_FLUID_BUCKET.get(),

            ModItems.POWSICLE_PALETERIA.get(),
            ModItems.LUAU_PALETERIA.get(),
            ModItems.WATERMELON_PALETERIA.get(),
            ModItems.PINK_SNOWMAN_PALETERIA.get(),
            ModItems.HEART_CRUSH_PALETERIA.get(),
            ModItems.PINEAPPLE_PALETERIA.get(),
            ModItems.HAMIMELON_PALETERIA.get(),

            ModItems.CHERRIES_PIE.get(),
            ModItems.CHERRIES_PIE_SLICE.get(),
            ModItems.TRUFFLE_PIE.get(),
            ModItems.TRUFFLE_PIE_SLICE.get(),
            ModItems.PANCAKE.get(),
            ModItems.DURIAN_MILE_CREPE_CAKE.get(),
            ModItems.DURIAN_MILE_CREPE_CAKE_SLICE.get(),

            ModItems.MUFFIN.get(),
            ModItems.BLUEBERRY_MUFFIN.get(),
            ModItems.CRANBERRY_MUFFIN.get(),
            ModItems.CHOCOLATE_MUFFIN.get(),
            ModItems.ORANGE_MUFFIN.get(),

            ModItems.CUPCAKE_QAQ.get(),
            ModItems.TACO.get(),
            ModItems.FRIED_CHICKEN.get(),
            ModItems.FRIED_DRUMSTICK.get(),
            ModItems.FRIED_PORKCHOP.get(),
            ModItems.FRIED_STEAK.get(),
            ModItems.FRIED_FISH.get(),
            ModItems.FRIED_TENPURA.get(),

            ModItems.DONUT.get(),
            ModItems.CHOCOLATE_DONUT.get(),
            ModItems.STRAWBERRY_DONUT.get(),
            ModItems.WHITE_CHOCOLATE_DONUT.get(),
            ModItems.LEMON_DONUT.get(),
            ModItems.SNOW_DONUT.get(),

            ModItems.CRANBERRY_COOKIE.get(),
            ModItems.BLUEBERRY_COOKIE.get(),
            ModItems.LEMON_COOKIE.get(),

            ModItems.HANAMI_DANGO.get(),
            ModItems.SAKURA_MOCHI.get(),
            ModItems.CHOCOLATE.get(),

            ModItems.JALAPENO.get(),
            ModItems.CORN.get(),
            ModItems.CORN_SEED.get(),
            ModItems.APPLE_SLICE.get(),
            ModItems.PINEAPPLE.get(),
            ModItems.PINEAPPLE_CROP.get(),
            ModItems.PINEAPPLE_SLICE.get(),
            ModItems.PINEAPPLE_PASTRIES.get(),
            ModItems.PINEAPPLE_PIES.get(),
            ModItems.MUSANG_KING_DURIAN.get(),
            ModItems.HALF_OF_MUSANG_KING_DURIAN.get(),
            ModItems.MUSANG_KING_DURIAN_FLESH.get(),
            ModItems.COOKED_DURIAN_FLESH.get(),
            ModItems.DURIAN_CRISP.get(),
            ModItems.HAMIMELON.get(),
            ModItems.HAMIMELON_SLICE.get(),
            ModItems.HAMIMELON_SEED.get(),
            ModItems.STARFRUIT.get(),
            ModItems.STARFRUIT_SLICE.get(),
            ModItems.MANGO.get(),
            ModItems.MANGO_SLICE.get(),
            ModItems.KIWIFRUIT.get(),
            ModItems.HALF_OF_KIWIFRUIT.get(),
            ModItems.KIWIFRUIT_SLICE.get(),
            ModItems.AVOCADO.get(),
            ModItems.HALF_OF_AVOCADO.get(),
            ModItems.AVOCADO_SLICE.get(),
            ModItems.ORANGE.get(),
            ModItems.ORANGE_SEGMENT.get(),
            ModItems.LEMON.get(),
            ModItems.LEMON_SLICE.get(),
            ModItems.PEACH.get(),
            ModItems.PEACH_SLICE.get(),
            ModItems.PEAR.get(),
            ModItems.PEAR_SLICE.get(),
            ModItems.DRAGON_FRUIT.get(),
            ModItems.DRAGON_FRUIT_SLICE.get(),
            ModItems.DRAGON_FRUIT_CACTUS.get(),
            ModItems.DRAGON_FRUIT_CACTUS_FLOWER.get(),
            ModItems.STRAWBERRY.get(),
            ModItems.PINEBERRY.get(),
            ModItems.BLUEBERRY.get(),
            ModItems.CRANBERRY.get(),
            ModItems.CHERRIES.get(),
            ModItems.OLIVE_FRUIT.get(),

            ModItems.ALFALFA.get(),
            ModItems.ALFALFA_SEED.get(),
            ModItems.ACORN.get(),
            ModItems.FILBERT.get(),
            ModItems.TRUFFLE.get(),
            ModItems.GARLIC.get(),
            ModItems.BOTTON_MUSHROOM.get(),
            ModItems.COOKED_BOTTON_MUSHROOM.get(),
            ModItems.WOOD_MUSHROOM.get(),
            ModItems.RAW_PRAWN.get(),
            ModItems.COOKED_PRAWN.get(),

            ModItems.FLOWER_RING_HELMET.get(),
            ModItems.WITCH_HAT_HELMET.get(),
            ModItems.CHEF_HAT_HELMET.get(),
            ModItems.CHRISTMAS_HAT_HELMET.get(),
            ModItems.MINER_HAT_HELMET.get(),

            ModItems.CHRISTMAS_MUFFLER_CHESTPLATE.get(),
            ModItems.CHRISTMAS_WREATHS.get(),
            ModItems.CHRISTMAS_SOCK.get(),

            ModItems.GIFT_SHORT_RED.get(),
            ModItems.GIFT_TALL_RED.get(),
            ModItems.GIFT_SHORT_GREEN.get(),
            ModItems.GIFT_TALL_GREEN.get(),
            ModItems.GIFT_SHORT_BLUE.get(),
            ModItems.GIFT_TALL_BLUE.get(),
            ModItems.GIFT_SHORT_PINK.get(),
            ModItems.GIFT_TALL_PINK.get(),
            ModItems.GIFT_SHORT_LUCKY.get(),
            ModItems.GIFT_TALL_LUCKY.get()
        )
    }

    init {
        CREATIVE_MODE_TABS.register("tab", Supplier {
            CreativeModeTab.builder().title(Component.translatable("itemGroup.manors_bounty"))
                .withTabsBefore(CreativeModeTabs.COMBAT) // Places this tab before the Combat tab
                // .icon { ModItems.PEARL_ROCK.get().defaultInstance } // Uncomment and choose an icon if desired
                .displayItems { _, output ->
                    // Adds all items from the TAB_ITEMS list to the creative tab
                    TAB_ITEMS.forEach {
                        output.accept(it)
                    }
                }.build()
        })
    }
}