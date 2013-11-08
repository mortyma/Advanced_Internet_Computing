package main;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.Options;
import weka.classifiers.bayes.NaiveBayes;
import classifier.ClassifierBuilder;
import classifier.WekaClassifier;

public class Test {

	public static void main(String[] args) {
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
			System.out.println("output is:"+ wc.classify("obama"));
			
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

}
