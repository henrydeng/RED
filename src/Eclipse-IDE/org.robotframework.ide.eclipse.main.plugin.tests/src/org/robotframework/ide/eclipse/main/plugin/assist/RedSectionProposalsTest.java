/*
 * Copyright 2016 Nokia Solutions and Networks
 * Licensed under the Apache License, Version 2.0,
 * see license.txt file for details.
 */
package org.robotframework.ide.eclipse.main.plugin.assist;

import static com.google.common.collect.Lists.transform;
import static org.assertj.core.api.Assertions.assertThat;
import static org.robotframework.ide.eclipse.main.plugin.assist.Commons.prefixesMatcher;
import static org.robotframework.ide.eclipse.main.plugin.assist.Commons.reverseComparator;

import java.util.List;

import org.junit.Test;

public class RedSectionProposalsTest {

    @Test
    public void allProposalsAreProvided_whenInputIsEmpty() {
        final List<? extends AssistProposal> proposals = new RedSectionProposals().getSectionsProposals("");

        assertThat(transform(proposals, AssistProposal::getLabel)).containsExactly("*** Keywords ***",
                "*** Settings ***", "*** Test Cases ***", "*** Variables ***");
    }

    @Test
    public void noProposalsAreProvided_whenNothingMatchesToGivenInput() {
        final List<? extends AssistProposal> proposals = new RedSectionProposals().getSectionsProposals("*Section");
        assertThat(proposals).isEmpty();
    }

    @Test
    public void proposalsAreProvidedInOrderInducedByGivenComparator_whenCustomComparatorIsProvided() {
        final List<? extends AssistProposal> proposals = new RedSectionProposals().getSectionsProposals("*",
                reverseComparator(AssistProposals.sortedByLabels()));

        assertThat(transform(proposals, AssistProposal::getLabel)).containsExactly("*** Variables ***",
                "*** Test Cases ***", "*** Settings ***", "*** Keywords ***");
    }

    @Test
    public void onlyProposalsContainingInputAreProvided_whenDefaultMatcherIsUsed() {
        final List<? extends AssistProposal> proposals = new RedSectionProposals().getSectionsProposals("es");

        assertThat(transform(proposals, AssistProposal::getLabel)).containsExactly("*** Test Cases ***",
                "*** Variables ***");
    }

    @Test
    public void onlyProposalsMatchingGivenMatcherAreProvided_whenMatcherIsGiven() {
        final List<? extends AssistProposal> proposals = new RedSectionProposals(prefixesMatcher())
                .getSectionsProposals("*** K");

        assertThat(transform(proposals, AssistProposal::getLabel)).containsExactly("*** Keywords ***");
    }
}
