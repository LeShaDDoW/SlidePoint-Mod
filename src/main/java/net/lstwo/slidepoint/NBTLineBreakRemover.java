package net.lstwo.slidepoint;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;

public class NBTLineBreakRemover {
    public static void removeLineBreaks(NBTTagCompound nbt, String key) {
        List<String> lines = new ArrayList<>();
        String text = nbt.getString(key);
        for (String line : text.split("\n")) {
            lines.add(line);
        }
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            newText.append(lines.get(i));
            if (i % 2 == 1 && i != lines.size() - 1) {
                newText.append("\n");
            }
        }
        nbt.setString(key, newText.toString());
    }

    public static String getStringFromNBT(NBTTagCompound compound, String key) {
        StringBuilder builder = new StringBuilder();
        String[] lines = compound.getString(key).split("\n");
        for (int i = 0; i < lines.length; i++) {
            builder.append(lines[i]);
            if (i % 2 == 1 && i != lines.length - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
