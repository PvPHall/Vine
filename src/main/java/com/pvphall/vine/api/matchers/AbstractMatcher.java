/*
 * MIT License
 *
 * Copyright (c) 2020 PvPHall
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.pvphall.vine.api.matchers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractMatcher {

    /**
     * The matcher to use for this matcher.
     */
    private Pattern matcher;

    /**
     * The identifier of this matcher.
     */
    private String identifier;

    /**
     * If the matcher found matches or not.
     */
    private boolean found;

    public AbstractMatcher() {

        this.matcher = this.getMatcher();
        this.identifier = this.getIdentifier();
        this.found = false;
    }

    /**
     * Execute this matcher with the {@link AbstractMatcher#matcher} and a {@link IMatcherExecutor}
     * to run when matches are found.
     *
     * @param line The line to check for matches
     * @param matcherExecutor The matcher executor to run
     * @return The line modified by the matcher executor
     */
    public String executeMatcher(String line, IMatcherExecutor matcherExecutor) {

        this.found = false;

        Matcher matcher = this.matcher.matcher(line);

        while(matcher.find()) {

            this.found = true;

            String baseMatch = matcher.group();
            String match = baseMatch.replace(this.identifier, "");

            line = matcherExecutor.processMatcher(line, baseMatch, match);
        }

        return line;
    }

    /**
     * Return if this matcher found one or more occurrences in the line.
     *
     * @return True if this matcher found occurrences
     */
    public boolean isFound() {

        return this.found;
    }

    /**
     * Provide the {@link Pattern} instance for this matcher.
     *
     * @return The Pattern instance
     */
    protected abstract Pattern getMatcher();

    /**
     * Provide the unique identifier for this matcher.
     *
     * @return This matcher's identifier
     */
    protected abstract String getIdentifier();
}
