package OverrideStudio.STRlantian.Listeners;

import OverrideStudio.STRlantian.PlayerCharacters.Enums.Languages;
import OverrideStudio.STRlantian.PlayerCharacters.InitialiseCharacters;
import OverrideStudio.STRlantian.PlayerCharacters.Localisation;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;

import java.util.List;

import static OverrideStudio.STRlantian.PlayerCharacters.InitialiseCharacters.*;

public final class InventoryListeners implements Listener
{
    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onCloseInventory(InventoryCloseEvent e)
    {
        Player pl = (Player) e.getPlayer();
        InventoryView inv = e.getView();
        Languages lang = Localisation.getLanguage(pl);
        String title = inv.getTitle();
        switch(title)
        {
            case ASKTITLECN, ASKTITLEEN ->
            {
                pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                switch(lang)
                {
                    case CN->
                    {
                        pl.sendMessage(ChatColor.RED + "你取消了测试");
                        break;
                    }

                    case EN->
                    {
                        pl.sendMessage(ChatColor.RED + "You cancelled the test");
                        break;
                    }
                }
                break;
            }

            case TESTINGCN, TESTINGEN ->
            {
                pl.playSound(pl, Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                switch(lang)
                {
                    case CN->
                    {
                        pl.sendMessage(ChatColor.RED + "你取消了测试,结果将不被保存");
                        break;
                    }
                    case EN->
                    {
                        pl.sendMessage(ChatColor.RED + "You cancelled the test so your characters won't be saved");
                        break;
                    }
                }
                break;
            }
        }
    }

    @SuppressWarnings("Deprecation")
    @EventHandler
    public void onClickInventory(InventoryClickEvent e)
    {
        Player pl = (Player) e.getWhoClicked();
        InventoryView inv = e.getView();
        String title = inv.getTitle();
        String name = pl.getName();

        if(title.equalsIgnoreCase(name + "的性格页面")
        || title.equalsIgnoreCase(name + "'s Character Page"))
        {
            e.setCancelled(true);
        }

        switch(title)
        {
            case INITITLEMAINCN, INITITLEMAINEN ->
            {
                List<Integer> tempList = getRandomConstList(pl);
                e.setCancelled(true);
                int slot = e.getSlot();
                switch(slot)
                {
                    case 2->
                    {
                        randCharacters(pl, tempList);
                        break;
                    }
                    case 4->
                    {
                        testCharactersPre(pl);
                        break;
                    }
                    case 6->
                    {
                        chooseCharacters(pl);
                        break;
                    }
                }
                break;
            }

            case ASKTITLECN, ASKTITLEEN ->
            {
                List<Integer> tempList = getRandomConstList(pl);
                e.setCancelled(true);
                int slot = e.getSlot();

                switch (slot)
                {
                    case 2->
                    {
                        InitialiseCharacters.testCharacters(pl, tempList);
                        break;
                    }

                    case 6->
                    {
                        pl.closeInventory();
                        break;
                    }
                }
                break;
            }
        }
    }
}
