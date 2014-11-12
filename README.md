This is a collection of algorithm  which I implemented for training purposes.

Running from the command-line (using Gradle):
----------------------------------------

    gradle -Dtest.single=<test-name> cleanTest test

For example:

    gradle -Dtest.single=Knapsack cleanTest test # Note that test class name is missing "Test" suffix

The task `cleanTest` ensures that [test output is discarded](http://gradle.1045684.n5.nabble.com/how-does-gradle-decide-when-to-run-tests-tp3314172p3315330.html) and test is run every time
More options about [running tests with Gradle](http://mrhaki.blogspot.com/2013/05/gradle-goodness-running-single-test.html)

Running from the IDE (IntelliJ IDEA)
--------------------------------

Right click on the test name in the `Project` pane and select `Run <test-name>`
