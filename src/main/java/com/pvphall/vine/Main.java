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

import java.io.*;

/**
 * Will be heavily modified.
 *
 * @deprecated
 */
public class Main {

    public static String version = "2.7";

    public static void main(String[] args) {

        if(args == null || args.length == 0) {

            System.out.println("You have to specify arguments. Try to use -h.");
            System.exit(1);
        }

        CLIParser cli = new CLIParser();

        try {

            cli.parseArguments(args);

        } catch (Exception ex) {

            System.out.println(ex.getMessage());
            System.exit(1);
        }

        try {

            IPreProcessor preProcessor = PreProcessor.makePreProcessor(new File(cli.getSourceDir()), new File(cli.getDestDir()), cli.getProperties());

            preProcessor.preprocess(new FileProcessor(cli.getProperties()));

        } catch(Exception ex) {

            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
}
