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

package com.pvphall.vine.api.expressions;

import com.pvphall.vine.api.IPreProcessor;

public abstract class AbstractExpression {

    /**
     * If this expression has passed or not.
     */
    private boolean passed;

    public AbstractExpression() {

        this.passed = false;
    }

    /**
     * @return If this expression has passed or not
     */
    public boolean hasPassed() {

        return this.passed;
    }

    /**
     * Set if this expression has passed or not after validating it.
     *
     * @param passed If this expression passed
     */
    public void setPassed(boolean passed) {

        this.passed = passed;
    }

    /**
     * Get the string keyword which identify this expression.
     *
     * @return The keyword representing this expression
     */
    public abstract String getKeyword();

    /**
     * Validate this expression using the {@code body} of this expression, the previous
     * {@code AbstractExpression} and the {@code Properties} of the {@link IPreProcessor}.
     * The {@code body} can be {@code null}
     *
     * @param body The body of this expression
     * @param previousExpression The previous parsed expression
     * @param mcVersion The current properties
     * @return If this expression is validated or not
     */
    public abstract boolean validate(String body, AbstractExpression previousExpression, String mcVersion);
}
