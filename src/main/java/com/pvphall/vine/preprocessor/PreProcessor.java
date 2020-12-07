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

package com.pvphall.vine.preprocessor;

import com.pvphall.vine.api.IPreProcessor;
import com.pvphall.vine.api.AbstractPreProcessor;
import com.pvphall.vine.api.files.IFileProcessor;
import com.pvphall.vine.utils.FileUtils;

import java.io.File;

public class PreProcessor extends AbstractPreProcessor {

    private PreProcessor(File source, File destination) {

        super(source, destination);
    }

    public static IPreProcessor makePreProcessor(File source, File destination) {

        return new PreProcessor(source, destination);
    }

    @Override
    public boolean preprocess(IFileProcessor fileProcessor) {

        FileUtils.copyDirectory(this.getSource(), this.getDestination(), fileProcessor);

        return false;
    }
}
