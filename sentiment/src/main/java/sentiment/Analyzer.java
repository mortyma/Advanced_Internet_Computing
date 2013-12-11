package sentiment;


import at.ac.tuwien.infosys.cloudscale.annotations.ByValueParameter;
import sentiment.classifier.ClassifierBuilder;
import sentiment.classifier.IClassifier;
import sentiment.classifier.Item;
import sentiment.classifier.WeightedMajority;
import sentiment.classifier.WekaClassifier;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author martin
 */
//@CloudObject
public class Analyzer implements IAnalyzer {

    WeightedMajority wm;

    public Analyzer() {
        List<IClassifier> classifiers = new LinkedList<IClassifier>();
        ClassifierBuilder cb = new ClassifierBuilder();
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

    public int analyze(@ByValueParameter String text) {
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
