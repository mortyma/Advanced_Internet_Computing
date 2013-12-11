/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import classifier.ClassifierBuilder;
import classifier.IClassifier;
import classifier.Item;
import classifier.WeightedMajority;
import classifier.WekaClassifier;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import junit.framework.TestCase;
import util.Options;
import weka.classifiers.bayes.NaiveBayes;

/**
 *
 * @author Martin
 */
public class ClassifierTest extends TestCase {

    public ClassifierTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of main method, of class Test.
     */
    public void testClassifierCreation() {
        ClassifierBuilder clb = new ClassifierBuilder();
        Options opt = new Options();
        clb.setOpt(opt);

        opt.setSelectedFeaturesByFrequency(true);

        opt.setNumFeatures(150);

        opt.setRemoveEmoticons(true);

        try {
            clb.prepareTrain();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            clb.prepareTest();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        NaiveBayes nb = new NaiveBayes();

        WekaClassifier wc = null;
        try {
            wc = clb.constructClassifier(nb);

        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            System.out.println("output is:" + wc.classify("obama"));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void TestClassification() {
        List<IClassifier> classifiers = new LinkedList<IClassifier>();
        ClassifierBuilder cb = new ClassifierBuilder();
        //prende tre classificatori gi�� costruiti
        try {
            WekaClassifier wc1 = cb.retrieveClassifier("weka.classifiers.bayes.NaiveBayes");
            WekaClassifier wc2 = cb.retrieveClassifier("weka.classifiers.trees.J48");
            WekaClassifier wc3 = cb.retrieveClassifier("weka.classifiers.functions.VotedPerceptron");
            classifiers.add(wc1);
            classifiers.add(wc2);
            classifiers.add(wc3);
            WeightedMajority wm = new WeightedMajority(classifiers);
            //costruisce e classifica un tweet
            Item item = wm.weightedClassify("Nike");
            //stampa la classificazione data al tweet dal classificatore
            System.out.println(item.getPolarity());
            //imposta la classificazione reale del tweet
            item.setTarget("4");
            //comunica al classificatore weighted majority la polarit�� esatta del tweet
            wm.setTarget(item);
            System.out.println(wm.get_cl2weight().get(1) + " " + wm.get_cl2weight().get(2) + " " + wm.get_cl2weight().get(3));

        } catch (Exception e) {
        }
    }
}
