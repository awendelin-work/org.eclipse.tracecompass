/*******************************************************************************
 * Copyright (c) 2014, 2016 École Polytechnique de Montréal and others.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Geneviève Bastien - Initial API and implementation
 *******************************************************************************/

package org.eclipse.tracecompass.internal.tmf.analysis.xml.ui.views.xychart;

import java.util.Set;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.tracecompass.internal.tmf.analysis.xml.ui.views.XmlViewInfo;
import org.eclipse.tracecompass.tmf.analysis.xml.core.module.TmfXmlStrings;
import org.eclipse.tracecompass.tmf.analysis.xml.core.module.TmfXmlUtils;
import org.eclipse.tracecompass.tmf.analysis.xml.core.module.XmlXYDataProvider;
import org.eclipse.tracecompass.tmf.core.trace.ITmfTrace;
import org.eclipse.tracecompass.tmf.ui.viewers.xycharts.linecharts.TmfCommonXAxisChartViewer;
import org.eclipse.tracecompass.tmf.ui.viewers.xycharts.linecharts.TmfXYChartSettings;
import org.w3c.dom.Element;

/**
 * Main viewer to display XML-defined xy charts. It uses an XML
 * {@link TmfXmlStrings#XY_VIEW} element from an XML file. This element defines
 * which entries from the state system will be shown and also gives additional
 * information on the presentation of the view.
 *
 * @author Geneviève Bastien
 */
public class XmlXYViewer extends TmfCommonXAxisChartViewer {

    private final XmlViewInfo fViewInfo;

    /**
     * Constructor
     *
     * @param parent
     *            parent view
     * @param settings
     *            See {@link TmfXYChartSettings} to know what it contains
     * @param viewInfo
     *            The view info object
     */
    public XmlXYViewer(@Nullable Composite parent, TmfXYChartSettings settings, XmlViewInfo viewInfo) {
        super(parent, settings);
        fViewInfo = viewInfo;
    }

    @Override
    protected void initializeDataProvider() {
        ITmfTrace trace = getTrace();
        Element viewElement = fViewInfo.getViewElement(TmfXmlStrings.XY_VIEW);
        if (viewElement == null) {
            return;
        }

        Set<String> analysisIds = fViewInfo.getViewAnalysisIds(viewElement);
        Element entry = TmfXmlUtils.getChildElements(viewElement, TmfXmlStrings.ENTRY_ELEMENT).get(0);
        setDataProvider(XmlXYDataProvider.create(trace, analysisIds, entry));
    }

    /**
     * Tells the viewer that the view info has been updated and the viewer needs to
     * be reinitialized
     */
    public void viewInfoUpdated() {
        initializeDataProvider();
    }
}
