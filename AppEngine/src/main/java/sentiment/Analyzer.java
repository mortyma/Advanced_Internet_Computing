package sentiment;


import at.ac.tuwien.IResourceLocator;
import classifier.ClassifierBuilder;
import classifier.IClassifier;
import classifier.Item;
import classifier.WeightedMajority;

import java.util.LinkedList;
import java.util.List;
import classifier.WekaClassifier;


public class Analyzer implements IAnalyzer {

    WeightedMajority wm;

    public Analyzer(IResourceLocator locator) {
        List<IClassifier> classifiers = new LinkedList<IClassifier>();
        ClassifierBuilder cb = new ClassifierBuilder(locator);
        try {
            //use pre-trained classifiers
            WekaClassifier wc1 = cb.retrieveClassifier("weka.classifiers.bayes.NaiveBayes");
            WekaClassifier wc2 = cb.retrieveClassifier("weka.classifiers.trees.J48");
            WekaClassifier wc3 = cb.retrieveClassifier("weka.classifiers.functions.VotedPerceptron");
            classifiers.add(wc1);
            classifiers.add(wc2);
            classifiers.add(wc3);
            wm = new WeightedMajority(classifiers);

         } catch (Exception e) {
            System.err.println("Error initializing classifiers: " + e);
            System.exit(-1);
        }
    }

    public int analyze(String text) {
        Item item = null;
        try {
            item = wm.weightedClassify(text);
        } catch (Exception e) {
            System.err.println("Error on classifying: " + e);
            System.exit(-1);
        }

        if (item.getPolarity().equals("0")) {
            return 0;
        } else if (item.getPolarity().equals("4")) {
            return 1;
        }
        //classifier returned some unexpected value
        throw new IllegalStateException("Unexpected value returned by classifier: " + item.getPolarity());
    }

}
