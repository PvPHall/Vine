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

package com.pvphall.vine.preprocessor.files;

import com.pvphall.vine.api.expressions.EndExpression;
import com.pvphall.vine.api.files.IFileProcessor;
import com.pvphall.vine.api.expressions.AbstractExpression;
import com.pvphall.vine.preprocessor.expressions.Expressions;
import com.pvphall.vine.preprocessor.expressions.EndIfExpression;
import com.pvphall.vine.preprocessor.expressions.IfExpression;
import com.pvphall.vine.api.matchers.AbstractMatcher;
import com.pvphall.vine.preprocessor.matchers.ExpressionsMatcher;
import com.pvphall.vine.preprocessor.matchers.LinesMatcher;
import com.pvphall.vine.utils.FileUtils;

import java.io.File;
import java.util.Properties;

public class FileProcessor implements IFileProcessor {

    private static final AbstractMatcher EXPRESSIONS_MATCHER = new ExpressionsMatcher();
    private static final AbstractMatcher LINES_MATCHER = new LinesMatcher();

    private String mcVersion;
    private AbstractExpression previousExpression;

    private boolean removeNextLine;

    public FileProcessor(String mcVersion) {

        this.mcVersion = mcVersion;
        this.removeNextLine = false;
    }

    @Override
    public void processFile(File source, File destination) {

        FileUtils.copyFiles(source, destination, this);
    }

    @Override
    public String processLine(String line) {

        line = EXPRESSIONS_MATCHER.executeMatcher(line, (currentLine, baseMatch, match) -> {

            String[] expressions = match.split(" ");

            AbstractExpression expression = Expressions.resolve(expressions[0]);
            String body = expressions.length == 2 ? expressions[1] : null;

            expression.setPassed(expression.validate(body, this.previousExpression, this.mcVersion));

            if(!expression.hasPassed() && expression instanceof IfExpression)
                this.removeNextLine = true;

            if(expression instanceof EndExpression)
                this.removeNextLine = false;

            this.previousExpression = expression;

            return null;
        });

        if(!EXPRESSIONS_MATCHER.isFound()) {

            line = LINES_MATCHER.executeMatcher(line, (currentLine, baseMatch, match) -> {

                if(this.previousExpression != null && this.previousExpression.hasPassed())
                    return currentLine.replace(baseMatch, match);

                return null;
            });
        }

        if(this.removeNextLine)
            return null;

        return line;
    }
}
