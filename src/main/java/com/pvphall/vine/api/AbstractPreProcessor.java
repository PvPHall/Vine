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

import java.io.File;
import java.util.Properties;

public abstract class AbstractPreProcessor implements IPreProcessor {

    /**
     * The source file to copy and preprocess.
     */
    private File source;

    /**
     * The destination file to copy to.
     */
    private File destination;

    /**
     * The properties for this preprocessor.
     */
    private Properties properties;

    public AbstractPreProcessor(File source, File destination) {

        this(source, destination, new Properties());
    }

    public AbstractPreProcessor(File source, File destination, Properties properties) {

        this.source = source;
        this.destination = destination;
        this.properties = properties;
    }

    @Override
    public File getSource() {

        return this.source;
    }

    @Override
    public File getDestination() {

        return this.destination;
    }

    @Override
    public Properties getProperties() {

        return this.properties;
    }
}