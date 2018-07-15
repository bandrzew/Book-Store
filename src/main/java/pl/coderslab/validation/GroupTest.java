package pl.coderslab.validation;

import javax.validation.constraints.Min;
import javax.validation.groups.Default;

public class GroupTest {
	@Min(42)
	private int testDefaultGroup;
	@Min(value = 42, groups = { OurGroup.class })
	private int testOurGroup;
	@Min(value = 42, groups = { OurGroup.class, Default.class })
	private int testBothGroups;

	public int getTestDefaultGroup() {
		return testDefaultGroup;
	}

	public void setTestDefaultGroup(int testDefaultGroup) {
		this.testDefaultGroup = testDefaultGroup;
	}

	public int getTestOurGroup() {
		return testOurGroup;
	}

	public void setTestOurGroup(int testOurGroup) {
		this.testOurGroup = testOurGroup;
	}

	public int getTestBothGroups() {
		return testBothGroups;
	}

	public void setTestBothGroups(int testBothGroups) {
		this.testBothGroups = testBothGroups;
	}

	
}
