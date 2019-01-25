// Modifications copyright (C) 2017, Baidu.com, Inc.
// Copyright 2017 The Apache Software Foundation

// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.baidu.palo.rewrite;

import com.baidu.palo.analysis.Analyzer;
import com.baidu.palo.analysis.Expr;
import com.baidu.palo.common.AnalysisException;
import com.baidu.palo.common.util.Util;
import com.baidu.palo.qe.StmtExecutor;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Helper class that drives the transformation of Exprs according to a given list of
 * ExprRewriteRules. The rules are applied as follows:
 * - a single rule is applied repeatedly to the Expr and all its children in a bottom-up
 *   fashion until there are no more changes
 * - the rule list is applied repeatedly until no rule has made any changes
 * - the rules are applied in the order they appear in the rule list
 * Keeps track of how many transformations were applied.
 */
public class ExprRewriter {

    private static final Logger LOG = LogManager.getLogger(StmtExecutor.class);
    private int numChanges_ = 0;
    private final List<ExprRewriteRule> rules_;

    public ExprRewriter(List<ExprRewriteRule> rules) {
        rules_ = rules;
    }

    public ExprRewriter(ExprRewriteRule rule) {
        rules_ = Lists.newArrayList(rule);
    }

    public Expr rewrite(Expr expr, Analyzer analyzer) throws AnalysisException {
        // Keep applying the rule list until no rule has made any changes.
        int oldNumChanges;
        Expr rewrittenExpr = expr;
        do {
            oldNumChanges = numChanges_;
            for (ExprRewriteRule rule: rules_) {
                rewrittenExpr = applyRuleRepeatedly(rewrittenExpr, rule, analyzer);
            }
        } while (oldNumChanges != numChanges_);
        return rewrittenExpr;
    }

    /**
     * Applies 'rule' on the Expr tree rooted at 'expr' until there are no more changes.
     * Returns the transformed Expr or 'expr' if there were no changes.
     */
    private Expr applyRuleRepeatedly(Expr expr, ExprRewriteRule rule, Analyzer analyzer)
            throws AnalysisException {
        int oldNumChanges;
        Expr rewrittenExpr = expr;
        do {
            oldNumChanges = numChanges_;
            rewrittenExpr = applyRuleBottomUp(rewrittenExpr, rule, analyzer);
        } while (oldNumChanges != numChanges_);
        return rewrittenExpr;
    }

    /**
     * Applies 'rule' on 'expr' and all its children in a bottom-up fashion.
     * Returns the transformed Expr or 'expr' if there were no changes.
     */
    private Expr applyRuleBottomUp(Expr expr, ExprRewriteRule rule, Analyzer analyzer)
            throws AnalysisException {
        for (int i = 0; i < expr.getChildren().size(); ++i) {
            expr.setChild(i, applyRuleBottomUp(expr.getChild(i), rule, analyzer));
        }
        Expr rewrittenExpr = rule.apply(expr, analyzer);
        if (rewrittenExpr != expr) {

            LOG.debug("before rewrite , expr: " + expr.toSql()  + " after rewrite :" + rewrittenExpr.toSql() );
            Util.printStack();
            ++numChanges_;
        }
        return rewrittenExpr;
    }

    public void rewriteList(List<Expr> exprs, Analyzer analyzer) throws AnalysisException {
        for (int i = 0; i < exprs.size(); ++i) exprs.set(i, rewrite(exprs.get(i), analyzer));
    }

    public void reset() { numChanges_ = 0; }
    public boolean changed() { return numChanges_ > 0; }
    public int getNumChanges() { return numChanges_; }
}
