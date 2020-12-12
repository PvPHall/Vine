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

package com.pvphall.vine;

import com.pvphall.vine.api.IPreProcessor;
import com.pvphall.vine.preprocessor.files.FileProcessor;
import com.pvphall.vine.preprocessor.PreProcessor;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.*;

public class Main {

    public static void main(String[] args) {

        try {

            start(args);

        } catch (FileNotFoundException e) {

            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void start(String[] args) throws FileNotFoundException {

        OptionParser optionParser = new OptionParser();

        OptionSpec<File> inputParam = optionParser.accepts("input").withRequiredArg().ofType(File.class);
        OptionSpec<File> outputParam = optionParser.accepts("output").withRequiredArg().ofType(File.class);
        OptionSpec<String> mcVersionParam = optionParser.accepts("mcVersion").withRequiredArg().ofType(String.class);

        OptionSet optionSet = optionParser.parse(args);

        File inputFile = optionSet.has(inputParam) ? optionSet.valueOf(inputParam) : null;
        File outputFile = optionSet.has(outputParam) ? optionSet.valueOf(outputParam) : null;
        String mcVersion = optionSet.has(mcVersionParam) ? optionSet.valueOf(mcVersionParam) : null;

        if(inputFile == null || !inputFile.exists())
            throw new FileNotFoundException("Input file does not exist.");

        if(outputFile == null)
            throw new FileNotFoundException("Cloud not determine output file.");

        if(mcVersion == null)
            throw new FileNotFoundException("Could not determine MC Version.");

        IPreProcessor preProcessor = PreProcessor.makePreProcessor(inputFile, outputFile);
        preProcessor.preprocess(new FileProcessor(mcVersion));
    }
}
