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

public class App {

    public static void main(final String... args) {
        try {
            //Reading the DSL script
            InputStream is =
                    ClassLoader.getSystemResourceAsStream("statemachine1.example");

            //Loading the DSL script into the ANTLR stream.
            CharStream cs = new ANTLRInputStream(is);
            com.helena128.metamodeling.parser.StateMachineLexer stateMachineLexer = new com.helena128.metamodeling.parser.StateMachineLexer(cs);
            CommonTokenStream tokens = new CommonTokenStream(stateMachineLexer);
            com.helena128.metamodeling.parser.StateMachineParser parser = new com.helena128.metamodeling.parser.StateMachineParser(tokens);

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
