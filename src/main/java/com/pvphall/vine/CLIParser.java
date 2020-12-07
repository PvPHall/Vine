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

import org.apache.commons.cli.*;

import java.io.*;
import java.util.*;

/**
 * Will be heavily modified.
 *
 * @deprecated
 */
public class CLIParser {

    private String sourceDir;
    private String destDir;
    private Properties properties;

    public void parseArguments(String [] args) throws Exception {

        boolean hasHelp = checkForHelp(args);

        Options options = new Options();
        Option help= Option.builder("h")
                .longOpt("help")
                .desc("print this message")
                .build();

        Option version = Option.builder("v")
                .longOpt("version")
                .desc("print the version information and exit")
                .build();

        Option srcDir = Option.builder("s")
                .argName("path")
                .hasArg()
                .required(true)
                .longOpt("sourcepath")
                .desc("directory where source file is located")
                .build();

        Option outDir = Option.builder("d")
                .argName("path")
                .hasArg()
                .required(true)
                .longOpt("destination")
                .desc("directory where parsed file should be stored")
                .build();

        Option property = Option.builder("D")
                .hasArgs()
                .numberOfArgs(2)
                .argName("property=value")
                .valueSeparator()
                .desc("use value for given property")
                .build();

        options.addOption(help);
        options.addOption(version);
        options.addOption(property);
        options.addOption(srcDir);
        options.addOption(outDir);

        if(hasHelp) {

            HelpFormatter formatter = new HelpFormatter();
            formatter.setWidth(120);
            formatter.setDescPadding(5);
            formatter.setLeftPadding(3);
            formatter.setOptionComparator(null);
            String header =  "\n"
                    + "Preprocess Java source code for debug/release compilation\n\n";
            String footer =  "\n"
                    + "sample: \n"
                    + "  jpp.sh -Ddebug=true -s sample/src -d sample/preprocessed -f Test.java";

            StringWriter out = new StringWriter();
            PrintWriter pw = new PrintWriter(out);

            formatter.printHelp(pw, 80, "jpp.sh", header, options, 2, 2, footer, true);

            pw.flush();

            System.out.println(out.toString());

            System.exit(1);
        }

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdline = parser.parse(options, args);

        properties = cmdline.getOptionProperties("D");

        if(cmdline.hasOption("s"))
            sourceDir = cmdline.getOptionValue("s");

        if(cmdline.hasOption("d"))
            destDir = cmdline.getOptionValue("d");

        if(sourceDir == null
                || sourceDir.length() == 0
                || destDir == null
                || destDir.length() == 0) {
            System.out.println("Make sure to pass all required arguments. Try to use -h.");
            System.exit(1);
        }
    }

    private boolean checkForHelp(String[] args) throws Exception {
        boolean hasHelp = false;
        Options options = new Options();

        Option help    = Option.builder("h")
                .longOpt("help")
                .build();

        Option version = Option.builder("v")
                .longOpt("version")
                .build();

        options.addOption(help);
        options.addOption(version);

        // we need that to overcome issue related to unknown args
        List<String> reduced = new ArrayList<String>();
        for (String arg : args) {
            if (options.hasOption(arg)) {
                reduced.add(arg);
            }
        }

        CommandLineParser parser = new DefaultParser();
        CommandLine cmdline = parser.parse(options, reduced.toArray(new String[reduced.size()]));

        if (cmdline.hasOption("h")) {
            hasHelp = true;
        }

        // we can show version together with other stuff
        if (cmdline.hasOption("v")) {
            System.out.println("java preprocessor version " + Main.version);
            System.exit(1);
        }

        return hasHelp;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public String getDestDir() {
        return destDir;
    }

    public Properties getProperties() {
        return properties;
    }

}