package trivia;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void caracterizationTest() {
        // runs 10.000 "random" games to see the output of old and new code mathces
        for (int seed = 1; seed < 10_000; seed++) {
            testSeed(seed, false);
        }
    }

    private void testSeed(int seed, boolean printExpected) {
        String expectedOutput = extractOutput(new Random(seed), new Game());
        if (printExpected) {
            System.out.println(expectedOutput);
        }
        String actualOutput = extractOutput(new Random(seed), GameInitializer.initBetterGame());
        assertEquals("Change detected for seed " + seed + ". To breakpoint through it, run this seed alone using the (ignored) test below",
                expectedOutput, actualOutput
        );
    }

    @Test
    @Ignore("enable back and set a particular seed to see the output")
    public void oneSeed() {
        testSeed(1, true);
    }

    private String extractOutput(Random rand, IGame aGame) {
        PrintStream old = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (PrintStream inmemory = new PrintStream(baos)) {
            // WARNING: System.out.println() doesn't work in this try {} as the sysout is captured and recorded in memory.
            System.setOut(inmemory);

            aGame.addPlayer("Chet");
            aGame.addPlayer("Pat");
            aGame.addPlayer("Sue");

            boolean notAWinner = false;
            do {
                aGame.takeTurn(rand.nextInt(5) + 1);

                if (rand.nextInt(9) == 7) {
                    notAWinner = aGame.handleWrongAnswer();
                } else {
                    notAWinner = aGame.handleCorrectAnswer();
                }

            } while (notAWinner);
        } finally {
            System.setOut(old);
        }

        return new String(baos.toByteArray());
    }
}
