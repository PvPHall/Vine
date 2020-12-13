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

package com.pvphall.vine.utils;

import com.pvphall.vine.api.files.IFileProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void copyDirectory(File source, File destination, IFileProcessor fileProcessor) throws IOException {

        if(source.isDirectory()) {

            if(!destination.exists())
                destination.mkdir();

            for(String file : source.list()) {

                File currentSource = new File(source, file);
                File currentDestination = new File(destination, file);
                copyDirectory(currentSource, currentDestination, fileProcessor);
            }

        } else
            fileProcessor.processFile(source, destination);
    }

    public static void copyFiles(File source, File destination, IFileProcessor fileProcessor) throws IOException {

        List<String> lines = new ArrayList<String>();
        String line = null;

        FileReader fileReader = new FileReader(source);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {

            line = fileProcessor.processLine(line);

            if(line != null)
                lines.add(line);
        }

        fileReader.close();
        bufferedReader.close();

        if(destination.getParentFile() != null)
            destination.getParentFile().mkdirs();

        FileWriter fileWriter = new FileWriter(destination);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for(String currentLine : lines)
            bufferedWriter.write(currentLine + System.lineSeparator());

        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
