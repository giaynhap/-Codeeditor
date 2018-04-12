package com.example.imac.coderediter.UI;

import java.util.regex.Pattern;

/**
 * Created by imac on 4/11/18.
 */

class ColorScheme {
    final Pattern pattern;
    final int color;

    ColorScheme(Pattern pattern, int color) {
        this.pattern = pattern;
        this.color = color;
    }
}