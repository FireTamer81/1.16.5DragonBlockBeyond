package io.github.firetamer.dbb.util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.github.firetamer.dbb.DragonBlockBeyond;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.text.TranslationTextComponent;

public class DBBResourceLocation extends ResourceLocation {

    private static final SimpleCommandExceptionType ERROR_INVALID = new SimpleCommandExceptionType(
            new TranslationTextComponent("argument.id.invalid"));

    public DBBResourceLocation(int id) {
        super(DragonBlockBeyond.MOD_ID, String.valueOf(id));
    }

    public DBBResourceLocation(String name) {
        super(checkModId(name));
    }

    public DBBResourceLocation(String modId, String path) {
        super(modId, path);
    }

    public static String checkModId(String input) {
        return input.contains(":") ? input : DragonBlockBeyond.MOD_ID + ":" + input;
    }

    public static DBBResourceLocation read(StringReader pReader) throws CommandSyntaxException {
        int i = pReader.getCursor();

        while (pReader.canRead() && isAllowedInResourceLocation(pReader.peek())) {
            pReader.skip();
        }

        String s = pReader.getString().substring(i, pReader.getCursor());

        try {
            return new DBBResourceLocation(s);
        } catch (ResourceLocationException resourcelocationexception) {
            pReader.setCursor(i);
            throw ERROR_INVALID.createWithContext(pReader);
        }
    }

}
