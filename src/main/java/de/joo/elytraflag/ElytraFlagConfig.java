package de.joo.elytraflag;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;

import java.nio.file.Path;

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
public class ElytraFlagConfig extends Configuration {
    protected ElytraFlagConfig(Path configPath) {
        super(configPath);
    }

    @Comment("Nachricht beim Betreten einer Region, wo Eltra Fliegen verboten ist.")
    private String enterFly = "§7Das Fliegen mit Elytren ist hier deaktiviert.";
    @Comment("Nachricht beim Versuch zu fliegen.")
    private String tryFly = "§7Das Fliegen mit Elytren ist hier deaktiviert.";

    public String getEnterFly() {
        return enterFly;
    }

    public String getTryFly() {
        return tryFly;
    }
}
