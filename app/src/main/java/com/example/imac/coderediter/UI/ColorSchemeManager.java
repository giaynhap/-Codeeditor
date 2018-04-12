package com.example.imac.coderediter.UI;

import android.graphics.Color;
import android.text.Editable;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by imac on 4/11/18.
 */

public class ColorSchemeManager {
    ColorScheme keywords = new ColorScheme(
            Pattern.compile(
                    "\\b(break|case|catch|continue|debugger|default|delete|do|else|finally|for|function|if|in|instanceof|new|return|switch|this|throw|try|typeof|var|void|while|with|constructor|this)\\b"),
            Color.parseColor("#80d7ee")
    );

   ColorScheme keywords2 = new ColorScheme(
            Pattern.compile(
                    "\\b(class|const|enum|export|extends|import|super|implements|interface|let|package|private|protected|public|static|yield|console)\\b"),
            Color.parseColor("#E03B6B")
    );


    ColorScheme textString = new ColorScheme(
            Pattern.compile(
                    "\"(.*?)\"|'(.*?)'"),
            Color.parseColor("#eae179")
    );


    ColorScheme functionName = new ColorScheme(
            Pattern.compile(
                    "(\\w*[0-9]*\\s*)\\((.*?)\\)"),
            Color.parseColor("#eae179")
    );

    ColorScheme className = new ColorScheme(
            Pattern.compile(
                    "(class|new|extends) (\\w*[0-9]*\\s*)"),
            Color.parseColor("#26c17e")
    );


    ColorScheme functionPare = new ColorScheme(
            Pattern.compile(
                    "\\((.*?)\\)"),
            Color.parseColor("#ffffff")
    );




    ColorScheme note = new ColorScheme(
            Pattern.compile(
                    "/\\*([^*]|[\\r\\n]|(\\*+([^*/]|[\\r\\n])))*\\*/"),
            Color.parseColor("#46586f")
    );


    ColorScheme numbers = new ColorScheme(
            Pattern.compile("(\\b(\\d*[.]?\\d+)\\b)"),
            Color.parseColor("#c1ed5c")
    );


    final ColorScheme[] schemes = { functionName,functionPare,className,keywords,keywords2, numbers ,note,textString};

    void removeSpans(Editable e, Class<? extends CharacterStyle> type) {
        CharacterStyle[] spans = e.getSpans(0, e.length(), type);
        for (CharacterStyle span : spans) {
            e.removeSpan(span);
        }
    }

    public void textChanged(Editable s) {
        removeSpans(s, ForegroundColorSpan.class);

        for (ColorScheme scheme : this.schemes) {
            for(Matcher m = scheme.pattern.matcher(s); m.find();) {
                s.setSpan(new ForegroundColorSpan(scheme.color),
                        m.start(),
                        m.end(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }
}
