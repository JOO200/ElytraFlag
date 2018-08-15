package de.joo.elytraflag;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;

import java.util.List;

/**
 * Created by JOO200 on 30.10.2017.
 * *
 * *
 * This file is part of ElytraFlag.
 * Copyright (C) 2017
 * *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
public class ElytraFlagListener implements Listener {

    private ElytraFlagPlugin plugin;

    public ElytraFlagListener(ElytraFlagPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onElytraFly(EntityToggleGlideEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player)event.getEntity();
        if(player.hasPermission("elytraflag.bypass")) return;

        RegionQuery query = WorldGuard.getInstance().getPlatform().getRegionContainer().createQuery();
        LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player);
        if(query.testState(localPlayer.getLocation(), localPlayer, ElytraFlag.ELYTRA_FLAG)) return;
        for(ProtectedRegion region : query.getApplicableRegions(localPlayer.getLocation())) {
            if(region.isMember(localPlayer) || region.isOwner(localPlayer)) return;
        }
        if(!player.isGliding()) {
            event.setCancelled(true);
            player.sendMessage(ElytraFlagPlugin.config.getTryFly());
        }
    }
}
