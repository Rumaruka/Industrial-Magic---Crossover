package com.rumaruka.emt.command;

import com.rumaruka.emt.util.EMTEssentiasOutputs;
import com.rumaruka.emt.util.EMTTextHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import thaumcraft.api.aspects.Aspect;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;

public class CommandOutput extends CommandBase {

    public String text;





    @Override
    public String getName() {
        return "emt_outputs";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/emt_outputs";
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return null;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        text = EMTTextHelper.localize("gui.EMT.book.aspect.ouput.essentia.eu");
        Collection<Aspect> aspectCollection = Aspect.aspects.values();
        for(Aspect aspect:aspectCollection){
            String text2 = addString(text,(aspect.getTag()+":"+String.valueOf(EMTEssentiasOutputs.outputs.get(aspect.getTag()))+"\n"));
            text=text2;
        }
        addOutputsBook(EMTTextHelper.localize("gui.EMT.book.aspect.output.eu.title"),text,sender,args);

    }

    @Override
    public int compareTo(ICommand p_compareTo_1_) {
        return this.compareTo(p_compareTo_1_);
    }

    public String addString(String t_1,String t_2){
        String result = t_1+t_2;
        return result;
    }
    public void addOutputsBook(String title, String text, ICommandSender command, String[] astring) {
        ItemStack book = new ItemStack(Items.WRITTEN_BOOK);
        book.setTagInfo("author", new NBTTagString("Tombenpotter"));
        book.setTagInfo("title", new NBTTagString(title));
        NBTTagCompound nbttagcompound = book.getTagCompound();
        NBTTagList bookPages = new NBTTagList();

        bookPages.appendTag(new NBTTagString(text.substring(0, 237)));
        bookPages.appendTag(new NBTTagString(text.substring(237, 476)));
        bookPages.appendTag(new NBTTagString(text.substring(476, 709)));
        bookPages.appendTag(new NBTTagString(text.substring(709)));

        nbttagcompound.setTag("pages", bookPages);

        System.out.println(text.length());

        if (!command.getEntityWorld().getPlayerEntityByName(command.getName()).inventory.addItemStackToInventory(book))
            command.getEntityWorld().getPlayerEntityByName(command.getName()).entityDropItem(book, 0);
    }
}
