package sentiment.commands;
import classifier.ClassifierBuilder;

/**
 * class representing command for calculating weighted majority classifier's precision 
 */
public class CalculateWmPrecisionCommand implements Command {

	private ClassifierBuilder clb;
	
	/**
	 * @param clb representing the receiver object
	 */
	public CalculateWmPrecisionCommand(ClassifierBuilder clb) {
		this.clb = clb;
	}
	
	/**
	 * calls the receiver to execute the command
	 */
	public void execute() {
		try {
			this.clb.calculateWmPrecision();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
