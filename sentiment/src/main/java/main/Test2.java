package main;
import java.util.LinkedList;
import java.util.List;

import classifier.*;

public class Test2 {

	public static void main(String[] args) {
		List<IClassifier> classifiers = new LinkedList<IClassifier>();
		ClassifierBuilder cb = new ClassifierBuilder();
		//prende tre classificatori gi�� costruiti
		try{
		WekaClassifier wc1 = cb.retrieveClassifier("weka.classifiers.bayes.NaiveBayes");
		WekaClassifier wc2 = cb.retrieveClassifier("weka.classifiers.trees.J48");
		WekaClassifier wc3 = cb.retrieveClassifier("weka.classifiers.functions.VotedPerceptron");
		classifiers.add(wc1);
		classifiers.add(wc2);
		classifiers.add(wc3);
		WeightedMajority wm  = new WeightedMajority(classifiers);
		//costruisce e classifica un tweet
		Item item = wm.weightedClassify("Nike");
		//stampa la classificazione data al tweet dal classificatore
		System.out.println(item.getPolarity());
		//imposta la classificazione reale del tweet
		item.setTarget("4");
		//comunica al classificatore weighted majority la polarit�� esatta del tweet
		wm.setTarget(item);
		System.out.println(wm.get_cl2weight().get(1) + " " + wm.get_cl2weight().get(2) + " " + wm.get_cl2weight().get(3));
		
		}catch (Exception e){
			
		}
	}

}
