// Copyright (c) 2014 The Chromium Embedded Framework Authors. All rights
// reserved. Use of this source code is governed by a BSD-style license that
// can be found in the LICENSE file.

package org.cef.handler;

import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.misc.BoolRef;
import org.cef.network.CefRequest;
import org.cef.network.CefWebPluginInfo;

/**
 * Implement this interface to provide handler implementations.
 */
public interface CefRequestContextHandler {
    /**
     * Called on multiple browser process threads before a plugin instance is loaded.
     * @param mime_type is the mime type of the plugin that will be loaded.
     * @param plugin_url is the content URL that the plugin will load and may be empty.
     * @param is_main_frame will be true if the plugin is being loaded in the main (top-level)
     *         frame.
     * @param top_origin_url is the URL for the top-level frame that contains the plugin when
     *         loading a specific plugin instance or empty when building the initial list of enabled
     *         plugins for 'navigator.plugins' JavaScript state.
     * @param plugin_info includes additional information about the plugin that will be loaded.
     *         |plugin_policy| is the recommended policy.
     * @return true to block loading of the plugin.
     */
    boolean onBeforePluginLoad(String mime_type, String plugin_url, boolean is_main_frame,
            String top_origin_url, CefWebPluginInfo plugin_info);

    /**
     * Called on the IO thread before a resource request is initiated. The |browser| and |frame|
     * values represent the source of the request. This method will not be called if the client
     * associated with |browser| returns a non-null value from
     * CefRequestHandler.getResourceRequestHandler for the same request (identified by
     * CefRequest.getIdentifier).
     *
     * @param browser The corresponding browser.
     * @param frame The frame generating the event. Instance only valid within the scope of this
     *         method.
     * @param request The request itself. Cannot be modified in this callback. Instance only valid
     *         within the scope of this method.
     * @param isNavigation True if the resource request is a navigation.
     * @param isDownload True if the resource request is a download.
     * @param requestInitiator The origin (scheme + domain) of the page that initiated the request.
     * @param disableDefaultHandling Set to true to disable default handling of the request, in
     *         which case it will need to be handled via
     *         CefResourceRequestHandler.getResourceHandler or it will be canceled.
     * @return A CefResourceRequestHandler instance or null.
     */
    CefResourceRequestHandler getResourceRequestHandler(CefBrowser browser, CefFrame frame,
                                                        CefRequest request, boolean isNavigation, boolean isDownload, String requestInitiator,
                                                        BoolRef disableDefaultHandling);
}
