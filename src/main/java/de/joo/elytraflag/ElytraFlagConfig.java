package de.joo.elytraflag;

import de.exlll.configlib.annotation.Comment;
import de.exlll.configlib.configs.yaml.YamlConfiguration;

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

public class ElytraFlagConfig extends YamlConfiguration {
    protected ElytraFlagConfig(Path configPath) {
        super(configPath);
    }

    @Comment("Message entering with elytra without permission.")
    private String enterFly = "§7You can't use elytras here.";
    @Comment("Message toggling gliding state.")
    private String tryFly = "§7You can't use elytras here.";

    public String getEnterFly() {
        return enterFly;
    }

    public String getTryFly() {
        return tryFly;
    }
}
