package com.helena128.metamodeling.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.helena128.metamodeling.parser.antlr4.StateMachineLexer;
import com.helena128.metamodeling.parser.antlr4.StateMachineParser;
import com.helena128.metamodeling.parser.TreePrinterListener;
import com.helena128.metamodeling.parser.TreeUtils;

public class App {

    public static void main(final String... args) {
        try {
            //Reading the DSL script
            InputStream is =
                    ClassLoader.getSystemResourceAsStream("statemachine1.example");

            //Loading the DSL script into the ANTLR stream.
            CharStream cs = new ANTLRInputStream(is);
            StateMachineLexer stateMachineLexer = new StateMachineLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(stateMachineLexer);
            StateMachineParser parser = new StateMachineParser(tokens);

            List<String> ruleNames = Arrays.asList(parser.getRuleNames());
            ParseTree tree = parser.fsm();

            TreePrinterListener listener = new TreePrinterListener(ruleNames);
            ParseTreeWalker.DEFAULT.walk(listener, tree);
            System.out.println(TreeUtils.toPrettyTree(tree, ruleNames));

        } catch (IOException ex) {
            System.err.println("IOException happened: " + ex.getMessage());
        }
    }

}
