/*
 * The MIT License
 *
 * Copyright 2015 CloudBees, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.cloudbees.literate.jenkins;

import hudson.model.DescriptorVisibilityFilter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import jenkins.branch.BranchProperty;
import jenkins.branch.BranchPropertyDescriptor;
import jenkins.branch.BuildRetentionBranchProperty;
import jenkins.branch.RateLimitBranchProperty;
import jenkins.branch.UntrustedBranchProperty;
import org.cloudbees.literate.jenkins.promotions.PromotionBranchProperty;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.jvnet.hudson.test.JenkinsRule;

public class ParametersDefinitionBranchPropertyTest {

    @Rule
    public JenkinsRule r = new JenkinsRule();

    @Test
    public void propertyVisible() throws Exception {
        LiterateMultibranchProject p = r.jenkins.createProject(LiterateMultibranchProject.class, "p");
        Set<Class<? extends BranchProperty>> clazzes = new HashSet<Class<? extends BranchProperty>>();
        for (BranchPropertyDescriptor d : DescriptorVisibilityFilter.apply(p, BranchPropertyDescriptor.all())) {
            clazzes.add(d.clazz);
        }
        @SuppressWarnings("unchecked")
        Set<Class<? extends BranchProperty>> expected = new HashSet<Class<? extends BranchProperty>>(Arrays.asList(
                ParametersDefinitionBranchProperty.class,
                RateLimitBranchProperty.class,
                BuildRetentionBranchProperty.class,
                UntrustedBranchProperty.class,
                PromotionBranchProperty.class));
        assertEquals(expected, clazzes);
    }

}
