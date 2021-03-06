package ch.cyberduck.ui.cocoa.quicklook;

/*
 * Copyright (c) 2002-2009 David Kocher. All rights reserved.
 *
 * http://cyberduck.ch/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Bug fixes, suggestions and comments should be sent to:
 * dkocher@cyberduck.ch
 */

import ch.cyberduck.binding.application.NSPanel;
import ch.cyberduck.binding.application.NSScreen;
import ch.cyberduck.binding.foundation.NSDictionary;

import org.rococoa.ID;
import org.rococoa.ObjCClass;

/**
 * The QLPreviewPanel class implements the Preview Panel - a user interface object that displays the preview of a list of items.<br>
 * Every application only has on instance of QLPreviewPanel. You can not subclass QLPreviewPanel.<br>
 * *      QLPreviewPanel follows the responder chain and adapts to the first responder willing to control it. It uses a data source to<br>
 * gets its content or can use regular bindings. QLPreviewPanel also can have a delegate.<br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses
 * a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a>, <a href="http://rococoa.dev.java.net/">Rococoa</a>,
 * or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public abstract class QLPreviewPanel extends NSPanel {
    private static final _Class CLASS = org.rococoa.Rococoa.createClass("QLPreviewPanel", _Class.class);

    public interface _Class extends ObjCClass {
        /**
         * @abstract Returns the QLPreviewPanel instance, creating it if it doesn\u00d5t exist yet.<br>
         * Original signature : <code>+(QLPreviewPanel*)sharedPreviewPanel</code><br>
         * <i>native declaration : line 39</i>
         */
        QLPreviewPanel sharedPreviewPanel();

        /**
         * @abstract Returns YES if the shared Preview Panel has been created, NO if it hasn\u00d5t..<br>
         * Original signature : <code>+(BOOL)sharedPreviewPanelExists</code><br>
         * <i>native declaration : line 44</i>
         */
        boolean sharedPreviewPanelExists();
    }

    /**
     * @abstract Returns the QLPreviewPanel instance, creating it if it doesn\u00d5t exist yet.<br>
     * Original signature : <code>+(QLPreviewPanel*)sharedPreviewPanel</code><br>
     * <i>native declaration : line 39</i>
     */
    public static QLPreviewPanel sharedPreviewPanel() {
        return CLASS.sharedPreviewPanel();
    }

    /**
     * @abstract Returns YES if the shared Preview Panel has been created, NO if it hasn\u00d5t..<br>
     * Original signature : <code>+(BOOL)sharedPreviewPanelExists</code><br>
     * <i>native declaration : line 44</i>
     */
    public static boolean sharedPreviewPanelExists() {
        return CLASS.sharedPreviewPanelExists();
    }

    public abstract void setDataSource(org.rococoa.ID dataSource);

    public abstract org.rococoa.ID dataSource();

    /**
     * @abstract Asks the Preview Panel to update its current controller.<br>
     * @discussion The Preview Panel automatically updates its controller (by searching the responder chain) whenever the main or key window changes.
     * Invoke updateController if the responder chain changes without explicit notice.<br>
     * Original signature : <code>-(void)updateController</code><br>
     * <i>native declaration : line 54</i>
     */
    public abstract void updateController();

    /**
     * You should never change the preview panel???s state (its delegate, datasource, and so on) if you are not controlling it.
     *
     * @return The current first responder accepting to control the preview panel.
     */
    public abstract ID currentController();

    /**
     * @abstract Asks the Preview Panel to reload its data from its data source.<br>
     * @discussion This method does not refresh the visible item if it has not changed.<br>
     * Original signature : <code>-(void)reloadData</code><br>
     * <i>native declaration : line 69</i>
     */
    public abstract void reloadData();

    /**
     * @abstract Asks the Preview Panel to recompute the preview of the currently previewed item.<br>
     * Original signature : <code>-(void)refreshCurrentPreviewItem</code><br>
     * <i>native declaration : line 74</i>
     */
    public abstract void refreshCurrentPreviewItem();

    /**
     * @abstract Enters full screen mode.<br>
     * @discussion If panel is not on-screen, the panel will go directly to full screen mode.<br>
     * Original signature : <code>-(BOOL)enterFullScreenMode:(NSScreen*) withOptions:(NSDictionary*)</code><br>
     * <i>native declaration : line 85</i>
     */
    public abstract boolean enterFullScreenMode_withOptions(NSScreen screen, NSDictionary options);

    /**
     * @abstract Exits full screen mode.<br>
     * Original signature : <code>-(void)exitFullScreenModeWithOptions:(NSDictionary*)</code><br>
     * <i>native declaration : line 90</i>
     */
    public abstract void exitFullScreenModeWithOptions(NSDictionary options);
}
