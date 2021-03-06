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

package com.pvphall.vine.api;

import com.pvphall.vine.api.files.IFileProcessor;

import java.io.File;

public interface IPreProcessor {

    /**
     * Get the source file to copy and preprocess
     *
     * @return The source file to preprocess
     */
    File getSource();

    /**
     * Get the destination file which will be preprocessed.
     *
     * @return The preprocessed destination file
     */
    File getDestination();

    /**
     * Start the preprocessing with a custom {@link IFileProcessor}
     *
     * @param processor The processor to use
     * @return If the preprocessor has succeeded
     */
    boolean preprocess(IFileProcessor processor);
}
