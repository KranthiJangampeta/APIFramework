package stepDefinitions;




import io.cucumber.java.After;
import io.cucumber.java.Before;
import resources.Utils;

public class Hooks extends Utils {

	@Before
	public void BeforeScenario() {
		//Do Nothing
	}

	@After
	public void AfterScenario() {
		// clears the parameters hashmap and keep it ready empty for next scenario
		params.clear();

	}
}
