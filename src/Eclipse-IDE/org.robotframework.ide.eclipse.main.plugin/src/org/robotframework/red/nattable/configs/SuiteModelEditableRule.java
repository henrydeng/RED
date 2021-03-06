/*
 * Copyright 2016 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see license.txt file for details.
 */
package org.robotframework.red.nattable.configs;

import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.robotframework.ide.eclipse.main.plugin.model.RobotSuiteFile;

public class SuiteModelEditableRule implements IEditableRule {

    private final boolean isEditable;

    static IEditableRule createEditableRule(final RobotSuiteFile fileModel) {
        return new SuiteModelEditableRule(fileModel.isEditable());
    }

    protected SuiteModelEditableRule(final boolean isEditable) {
        this.isEditable = isEditable;
    }

    @Override
    public boolean isEditable(final ILayerCell cell, final IConfigRegistry configRegistry) {
        return isEditable;
    }

    @Override
    public boolean isEditable(final int columnIndex, final int rowIndex) {
        throw new IllegalStateException("Shouldn't be called");
    }
}