package io.github.firetamer.dbb.datagen.client.lang;

import com.matyrobbrt.lib.registry.MatyLibRegistries;
import io.github.firetamer.dbb.DragonBlockBeyond;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraftforge.common.data.LanguageProvider;

public class EnUs extends LanguageProvider {

    public EnUs(DataGenerator gen) {
        super(gen, DragonBlockBeyond.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        MatyLibRegistries.ITEMS.get(DragonBlockBeyond.MOD_ID).forEach(item -> {
            if (!(item instanceof BlockItem)) {
                String name = item.getRegistryName().getPath().replace("_", " ");
                add(item, capitalizeWord(name));
            }
        });
    }

    public static String capitalizeWord(String str) {
        String words[] = str.split("\\s");
        String capitalizeWord = "";
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord += first.toUpperCase() + afterfirst + " ";
        }
        return capitalizeWord.trim();
    }
}
