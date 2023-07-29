package org.thyone.teamme.model;

public class SubCommandSyntax {
    public int index;
    public String name;
    public boolean isRequied;

    public SubCommandSyntax(int syntaxIndex, String syntaxName, boolean isSyntaxRequied) {
        index = syntaxIndex;
        name = syntaxName;
        isRequied = isSyntaxRequied;
    }
}
