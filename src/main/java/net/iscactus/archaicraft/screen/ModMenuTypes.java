package net.iscactus.archaicraft.screen;

import net.iscactus.archaicraft.Archaicraft;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Archaicraft.MODID);

    public static final RegistryObject<MenuType<MortarMenu>> MORTAR_MENU =
            registerMenuType("mortar_menu", MortarMenu::new);
    public static final RegistryObject<MenuType<AlchemyTableMenu>> ALCHEMY_TABLE_MENU =
            registerMenuType("alchemy_table_menu", AlchemyTableMenu::new);

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
