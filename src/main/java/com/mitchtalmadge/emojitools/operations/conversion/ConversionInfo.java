/*
 * Copyright (C) 2015 - 2016 Mitch Talmadge (https://mitchtalmadge.com/)
 * Emoji Tools helps users and developers of Android, iOS, and OS X extract, modify, and repackage Emoji fonts.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mitchtalmadge.emojitools.operations.conversion;

public class ConversionInfo {

    public static final int DIRECTION_CGBI_RGBA = 0;
    public static final int DIRECTION_RGBA_CGBI = 1;

    private int conversionDirection;

    public ConversionInfo(int conversionDirection) {
        if (conversionDirection < 0 || conversionDirection > 1) {
            conversionDirection = 0;
        }
        this.conversionDirection = conversionDirection;
    }

    public int getConversionDirection() {
        return conversionDirection;
    }
}
